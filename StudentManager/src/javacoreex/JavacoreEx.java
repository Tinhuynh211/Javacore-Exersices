/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javacoreex;


import model.Student;
import utils.FileUtils;
import java.util.List;
import java.util.Scanner;

public class JavacoreEx {
    private static final String FILE_NAME = "C:\\Users\\ADMIN\\Desktop\\Javacore Exercises\\StudentManager\\src\\student.txt";
    private static List<Student> students;

    public static void main(String[] args) {
        students = FileUtils.readStudentsFromFile(FILE_NAME);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMenu();
            int choice = getValidChoice(scanner);
            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    updateStudent(scanner);
                    break;
                case 3:
                    deleteStudent(scanner);
                    break;
                case 4:
                    displayStudents();
                    break;
                case 5:
                    saveAndExit();
                    return;
                default:
                    // Thực tế không bao giờ vào đây vì đã kiểm tra trước khi vào switch
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Thêm sinh viên");
        System.out.println("2. Sửa thông tin sinh viên");
        System.out.println("3. Xóa sinh viên");
        System.out.println("4. Hiển thị danh sách sinh viên");
        System.out.println("5. Lưu và thoát");
        System.out.print("Chọn một tùy chọn: ");
    }
    
    private static int getValidChoice(Scanner scanner) {
        int choice = -1;
        boolean valid = false;
        while (!valid) {
            try {
                choice = Integer.parseInt(scanner.nextLine()); // Đọc lựa chọn người dùng
                if (choice >= 1 && choice <= 5) {
                    valid = true; // Lựa chọn hợp lệ
                } else {
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại (1-5).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi nhập! Vui lòng nhập một số nguyên từ 1 đến 5.");
            }
        }
        return choice;
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Nhập tên sinh viên: ");
        String name = scanner.nextLine();
        System.out.print("Nhập mã sinh viên: ");
        String id = scanner.nextLine();
        System.out.print("Nhập điểm sinh viên: ");
        double score = scanner.nextDouble();
        students.add(new Student(name, id, score));
    }

    private static void updateStudent(Scanner scanner) {
        System.out.print("Nhập mã sinh viên cần sửa: ");
        String id = scanner.nextLine();
        for (Student student : students) {
            if (student.getStudentId().equals(id)) {
                System.out.print("Nhập tên mới: ");
                String name = scanner.nextLine();
                System.out.print("Nhập điểm mới: ");
                double score = scanner.nextDouble();
                student.setStudentName(name);
                student.setStudentScore(score);
                return;
            }
        }
        System.out.println("Không tìm thấy sinh viên với mã " + id);
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.print("Nhập mã sinh viên cần xóa: ");
        String id = scanner.nextLine();
        students.removeIf(student -> student.getStudentId().equals(id));
    }

    private static void displayStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void saveAndExit() {
        FileUtils.writeStudentsToFile(students, FILE_NAME);
        System.out.println("Dữ liệu đã được lưu!");
    }
    
}
