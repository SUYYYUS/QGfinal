package com.suyyyus.pojo;

public class Student_course {
    private int id;
    private int student_id; //学生id
    private int course_id; //课程id
    private String register_time; //报名时间
    private int status; //状态：1.在学习，2.已完成

    public Student_course() {
    }

    public Student_course(int student_id, int course_id, String register_time, int status) {
        this.student_id = student_id;
        this.course_id = course_id;
        this.register_time = register_time;
        this.status = status;
    }

    public Student_course(int id, int student_id, int course_id, String register_time, int status) {
        this.id = id;
        this.student_id = student_id;
        this.course_id = course_id;
        this.register_time = register_time;
        this.status = status;
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

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getRegister_time() {
        return register_time;
    }

    public void setRegister_time(String register_time) {
        this.register_time = register_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "student_course{" +
                "id=" + id +
                ", student_id=" + student_id +
                ", course_id=" + course_id +
                ", register_time='" + register_time + '\'' +
                ", status=" + status +
                '}';
    }
}
