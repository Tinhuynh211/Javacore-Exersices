package utils;

import model.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static void writeStudentsToFile(List<Student> students, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Student student : students) {
                writer.write(student.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Student> readStudentsFromFile(String fileName) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                students.add(Student.fromString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}
