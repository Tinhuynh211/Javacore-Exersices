package model;

public class Student {
    private String studentName;
    private String studentId;
    private double studentScore;

    public Student(String studentName, String studentId, double studentScore) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.studentScore = studentScore;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(double studentScore) {
        this.studentScore = studentScore;
    }

    @Override
    public String toString() {
        return studentName + "," + studentId + "," + studentScore;
    }

    public static Student fromString(String str) {
        String[] parts = str.split(" , ");
        return new Student(parts[0], parts[1], Double.parseDouble(parts[2]));
    }
}
