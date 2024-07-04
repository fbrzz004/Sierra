package com.killa.sierravp.service;

import com.killa.sierravp.domain.*;
import com.killa.sierravp.repository.Universidad;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CRAService {
    private Universidad universidad;

    // Constructor que inicializa la clase CRAService con un objeto Universidad
    public CRAService(Universidad universidad) {
        this.universidad = universidad;
    }

    // Método para calcular el CRA de cada alumno en una clase específica
    public void calcularCRAporClase(int claseId) {
        // Obtener la clase por su ID usando el método auxiliar
        Clase clase = obtenerClasePorId(claseId);
        if (clase == null) {
            System.out.println("Clase no encontrada");
            return;
        }

        // Obtener las notas de los alumnos en la clase
        List<Nota> notas = clase.getNotas();
        if (notas.isEmpty()) {
            System.out.println("No hay notas para esta clase");
            return;
        }

        // Calcular la media y la desviación estándar de las notas
        DoubleSummaryStatistics stats = notas.stream()
                .mapToDouble(Nota::getValor)
                .summaryStatistics();
        double media = stats.getAverage();
        double sumaCuadrados = notas.stream()
                .mapToDouble(Nota::getValor)
                .map(nota -> Math.pow(nota - media, 2))
                .sum();
        double desviacionEstandar = Math.sqrt(sumaCuadrados / notas.size());

        // Calcular el CRA para cada alumno y actualizar su CRA ponderado actual
        for (Nota nota : notas) {
            Alumno alumno = nota.getAlumno();
            double craValue = calcularCRAPorAlumno(nota.getValor(), media, desviacionEstandar, clase.getCurso().getCreditos());
            CRA cra = new CRA(alumno, clase, craValue);
            // Añadir el CRA al histórico del alumno
            alumno.getCraHistorico().add(cra);

            // Actualizar el CRA ponderado actual del alumno
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

    // Método para calcular el CRA por ciclo
    public void calcularCRAPorCiclo(int cicloId) {
        // Obtener el ciclo por su ID usando el método auxiliar
        Ciclo ciclo = obtenerCicloPorId(cicloId);
        if (ciclo == null) {
            System.out.println("Ciclo no encontrado");
            return;
        }

        // Calcular el CRA para cada clase en el ciclo
        for (Clase clase : ciclo.getClases()) {
            calcularCRAporClase(clase.getId());
        }
    }

    // Método para calcular el CRA por Escuela Profesional
    public void calcularCRAPorEscuelaProfesional(int escuelaProfesionalId) {
        // Obtener la Escuela Profesional por su ID usando el método auxiliar
        EscuelaProfesional escuelaProfesional = obtenerEscuelaProfesionalPorId(escuelaProfesionalId);
        if (escuelaProfesional == null) {
            System.out.println("Escuela Profesional no encontrada");
            return;
        }

        // Calcular el CRA para cada ciclo en la Escuela Profesional
        for (Ciclo ciclo : escuelaProfesional.getCiclos()) {
            calcularCRAPorCiclo(ciclo.getId());
        }
    }

    // Método para calcular el CRA por facultad
    public void calcularCRAPorFacultad(int facultadId) {
        // Obtener la facultad por su ID usando el método auxiliar
        Facultad facultad = obtenerFacultadPorId(facultadId);
        if (facultad == null) {
            System.out.println("Facultad no encontrada");
            return;
        }

        // Calcular el CRA para cada Escuela Profesional en la facultad
        for (EscuelaProfesional escuelaProfesional : facultad.getEscuelasProfesionales()) {
            calcularCRAPorEscuelaProfesional(escuelaProfesional.getId());
        }
    }

    // Método auxiliar para obtener una clase por su ID
    private Clase obtenerClasePorId(int claseId) {
        for (Universidad.FacultadData facultadData : universidad.getFacultades().values()) {
            for (Universidad.EscuelaData escuelaData : facultadData.getEscuelas().values()) {
                for (Clase clase : escuelaData.getClases()) {
                    if (clase.getId() == claseId) {
                        return clase;
                    }
                }
            }
        }
        return null;
    }

    // Método auxiliar para obtener un ciclo por su ID
    private Ciclo obtenerCicloPorId(int cicloId) {
        for (Universidad.FacultadData facultadData : universidad.getFacultades().values()) {
            for (Universidad.EscuelaData escuelaData : facultadData.getEscuelas().values()) {
                for (EscuelaProfesional escuelaProfesional : escuelaData.getEscuelasProfesionales()) {
                    for (Ciclo ciclo : escuelaProfesional.getCiclos()) {
                        if (ciclo.getId() == cicloId) {
                            return ciclo;
                        }
                    }
                }
            }
        }
        return null;
    }

    // Método auxiliar para obtener una Escuela Profesional por su ID
    private EscuelaProfesional obtenerEscuelaProfesionalPorId(int escuelaProfesionalId) {
        for (Universidad.FacultadData facultadData : universidad.getFacultades().values()) {
            for (Universidad.EscuelaData escuelaData : facultadData.getEscuelas().values()) {
                for (EscuelaProfesional escuelaProfesional : escuelaData.getEscuelasProfesionales()) {
                    if (escuelaProfesional.getId() == escuelaProfesionalId) {
                        return escuelaProfesional;
                    }
                }
            }
        }
        return null;
    }

    // Método auxiliar para obtener una facultad por su ID
    private Facultad obtenerFacultadPorId(int facultadId) {
        for (Facultad facultad : universidad.getFacultades().values()) {
            if (facultad.getId() == facultadId) {
                return facultad;
            }
        }
        return null;
    }
}
