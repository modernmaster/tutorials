package uk.co.jamesmcguigan.algorithms.highestaveragescore;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private List<Integer> scores;

    public Student(String name) {
        this.name = name;
        scores = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addScores(Integer score) {
        scores.add(score);
    }

    public Double calculateAverageScore() {
        return scores
                .stream()
                .mapToDouble(a -> a)
                .average().getAsDouble();
    }
}
