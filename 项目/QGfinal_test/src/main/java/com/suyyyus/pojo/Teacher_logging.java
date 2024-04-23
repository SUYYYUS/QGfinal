package com.suyyyus.pojo;

public class Teacher_logging {
    private int id;
    private int teacher_id;
    private String logging;

    public Teacher_logging() {
    }

    public Teacher_logging(int id, int teacher_id, String logging) {
        this.id = id;
        this.teacher_id = teacher_id;
        this.logging = logging;
    }

    public Teacher_logging(int teacher_id, String logging) {
        this.teacher_id = teacher_id;
        this.logging = logging;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getLogging() {
        return logging;
    }

    public void setLogging(String logging) {
        this.logging = logging;
    }

    @Override
    public String toString() {
        return "Teacher_logging{" +
                "id=" + id +
                ", teacher_id=" + teacher_id +
                ", logging='" + logging + '\'' +
                '}';
    }
}
