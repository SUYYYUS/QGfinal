package com.suyyyus.pojo;

public class Student_study {
    int id;
    int student_id;
    int course_id;
    int answernumber; //答题次数
    double average_score; //总分
    double total_score; //总分
    double accuracy; //正确率
    int rightnumber; //答对次数

    public Student_study() {
    }

    public Student_study(int id, int student_id, int course_id, int answernumber, double average_score, double total_score, double accuracy, int rightnumber) {
        this.id = id;
        this.student_id = student_id;
        this.course_id = course_id;
        this.answernumber = answernumber;
        this.average_score = average_score;
        this.total_score = total_score;
        this.accuracy = accuracy;
        this.rightnumber = rightnumber;
    }

    public Student_study(int student_id, int course_id, int answernumber, double average_score, double total_score, double accuracy, int rightnumber) {
        this.student_id = student_id;
        this.course_id = course_id;
        this.answernumber = answernumber;
        this.average_score = average_score;
        this.total_score = total_score;
        this.accuracy = accuracy;
        this.rightnumber = rightnumber;
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

    public int getAnswernumber() {
        return answernumber;
    }

    public void setAnswernumber(int answernumber) {
        this.answernumber = answernumber;
    }

    public double getAverage_score() {
        return average_score;
    }

    public void setAverage_score(double average_score) {
        this.average_score = average_score;
    }

    public double getTotal_score() {
        return total_score;
    }

    public void setTotal_score(double total_score) {
        this.total_score = total_score;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public int getRightnumber() {
        return rightnumber;
    }

    public void setRightnumber(int rightnumber) {
        this.rightnumber = rightnumber;
    }

    @Override
    public String toString() {
        return "Student_study{" +
                "id=" + id +
                ", student_id=" + student_id +
                ", course_id=" + course_id +
                ", answernumber=" + answernumber +
                ", average_score=" + average_score +
                ", total_score=" + total_score +
                ", accuracy=" + accuracy +
                ", rightnumber=" + rightnumber +
                '}';
    }
}
