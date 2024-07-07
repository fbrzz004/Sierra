package com.killa.sierravp.service;

import com.killa.sierravp.domain.Ranking;
import com.killa.sierravp.domain.Alumno;
import com.killa.sierravp.repository.RankingRepository;
import com.killa.sierravp.repository.Universidad;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RankingService {

    private final RankingRepository rankingRepository;
    private final Universidad universidad;

    public RankingService(Universidad universidad) {
        this.universidad = universidad;
        this.rankingRepository = new RankingRepository();
    }

    public List<Ranking> obtenerRankings() {
        return rankingRepository.findAll();
    }

    public void guardarRanking(Ranking ranking) {
        rankingRepository.save(ranking);
    }

    public void generarRanking() {
        for (Universidad.FacultadData facultadData : universidad.getFacultades().values()) {
            for (Universidad.EscuelaData escuelaData : facultadData.getEscuelas().values()) {
                List<Alumno> alumnos = new ArrayList<>();
                escuelaData.getCiclos().forEach(ciclo -> alumnos.addAll(ciclo.getAlumnos()));

                alumnos.sort(Comparator.comparingDouble(Alumno::getCraPonderadoActual).reversed());

                int posicion = 1;
                for (Alumno alumno : alumnos) {
                    Ranking ranking = new Ranking(posicion, alumno, escuelaData.getEscuela());
                    guardarRanking(ranking);
                    posicion++;
                }
            }
        }
    }

    public void ordenarRankings(List<Ranking> rankings) {
        quicksort(rankings, 0, rankings.size() - 1);
    }

    private void quicksort(List<Ranking> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quicksort(list, low, pi - 1);
            quicksort(list, pi + 1, high);
        }
    }

    private int partition(List<Ranking> list, int low, int high) {
        Ranking pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).getPosicion() <= pivot.getPosicion()) {
                i++;
                Ranking temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        Ranking temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }
}
