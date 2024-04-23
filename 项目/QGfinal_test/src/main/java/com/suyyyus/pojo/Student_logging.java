package com.suyyyus.pojo;

public class Student_logging {
   private int id;
   private int student_id;
   private String logging;

    public Student_logging() {
    }

    public Student_logging(int id, int student_id, String logging) {
        this.id = id;
        this.student_id = student_id;
        this.logging = logging;
    }

    public Student_logging(int student_id, String logging) {
        this.student_id = student_id;
        this.logging = logging;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getLogging() {
        return logging;
    }

    public void setLogging(String logging) {
        this.logging = logging;
    }

    @Override
    public String toString() {
        return "Student_logging{" +
                "id=" + id +
                ", student_id=" + student_id +
                ", logging='" + logging + '\'' +
                '}';
    }
}
