package uk.co.jamesmcguigan.algorithms.highestaveragescore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HighestAverageScore {
    public Student calculate(String[][] scores) {
        Collection<Student> students = buildStudentCollection(scores);
        Student highestAverage = null;
        Double highestAverageScore = 0d;
        for(Student student:students) {
            Double averageScore = student.calculateAverageScore();
            if ((highestAverage == null) || (averageScore > highestAverageScore)) {
                highestAverage = student;
                highestAverageScore = averageScore;
            }
        }
        return highestAverage;
    }

    private Collection<Student> buildStudentCollection(String[][] scores){
        Map<String, Student> studentScores = new HashMap<>();
        for (String[] strings : scores) {
            String studentName = strings[0];
            int score = Integer.parseInt(strings[1]);
            if (!studentScores.containsKey(studentName)) {
                Student student = new Student(studentName);
                student.addScores(score);
                studentScores.put(studentName, student);
            } else {
                Student student = studentScores.get(studentName);
                student.addScores(score);
            }
        }
        return studentScores.values();
    }
}

