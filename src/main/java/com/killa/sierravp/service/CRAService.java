package com.killa.sierravp.service;

import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.domain.CRA;
import com.killa.sierravp.domain.Clase;
import com.killa.sierravp.domain.Nota;
import com.killa.sierravp.repository.Universidad;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class CRAService {
    private Universidad universidad;

    // Constructor que inicializa la clase CRAService con un objeto Universidad
    public CRAService(Universidad universidad) {
        this.universidad = universidad;
    }

    // Método para calcular y registrar el CRA de una clase
    
    public void calcularYRegistrarCRA(Clase clase) {
        List<Nota> notas = clase.getNotas();

        // Calcular la media de las notas
        
        double media = notas.stream()
                .mapToDouble(Nota::getValor)
                .average()
                .orElse(0);

        // Calcular la desviación estándar de las notas
        
        double sumaCuadrados = notas.stream()
                .mapToDouble(Nota::getValor)
                .map(nota -> Math.pow(nota - media, 2))
                .sum();
        double desviacionEstandar = Math.sqrt(sumaCuadrados / notas.size());

        Map<Alumno, Double> craMap = new HashMap<>();

        // Calcular el CRA para cada alumno y almacenar en el mapa
        
        for (Nota nota : notas) {
            Alumno alumno = nota.getAlumno();
            double craValue = calcularCRAPorAlumno(nota.getValor(), media, desviacionEstandar, clase.getCurso().getCreditos());
            craMap.put(alumno, craValue);
        }

        // Guardar los valores del CRA en la clase y actualizar el CRA ponderado del alumno
        
        for (Map.Entry<Alumno, Double> entry : craMap.entrySet()) {
            Alumno alumno = entry.getKey();
            double craValue = entry.getValue();

            CRA cra = new CRA(alumno, clase, craValue);
            clase.agregarCRA(cra);

            alumno.setCraPonderadoActual(craValue);
        }
    }

    // Método para calcular el CRA de un alumno usando la fórmula proporcionada
    
    private double calcularCRAPorAlumno(double nota, double media, double desviacionEstandar, int creditos) {
        if (desviacionEstandar == 0) {
            return 50; // Evita división por cero
        }
        return (((nota - media) / desviacionEstandar) * 10 * creditos) + 50;
    }

    // Método para obtener todos los CRAs
    
    public List<CRA> getAllCRAs() {
        return universidad.getFacultades().values().stream()
                .flatMap(facultad -> facultad.getEscuelas().values().stream())
                .flatMap(escuela -> escuela.getClases().stream())
                .flatMap(clase -> clase.getCRAs().stream())
                .collect(Collectors.toList());
    }

    // Método para obtener un CRA por su ID
    
    public CRA getCRAById(int id) {
        return getAllCRAs().stream()
                .filter(cra -> cra.getCraId()== id)
                .findFirst()
                .orElse(null);
    }
}
