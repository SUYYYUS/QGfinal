package com.suyyyus.pojo;

public class Discussion {
    int id;
    int student_id;
    int teacher_id;
    int course_id;
    String question; //提问内容
    String reply; //教师回复
    String question_time;
    String reply_time;

    public Discussion() {
    }

    public Discussion(int id, int student_id, int teacher_id, int course_id, String question, String reply, String question_time, String reply_time) {
        this.id = id;
        this.student_id = student_id;
        this.teacher_id = teacher_id;
        this.course_id = course_id;
        this.question = question;
        this.reply = reply;
        this.question_time = question_time;
        this.reply_time = reply_time;
    }

    public Discussion(int student_id, int teacher_id, int course_id, String question, String reply, String question_time, String reply_time) {
        this.student_id = student_id;
        this.teacher_id = teacher_id;
        this.course_id = course_id;
        this.question = question;
        this.reply = reply;
        this.question_time = question_time;
        this.reply_time = reply_time;
    }

    public Discussion(int student_id, int teacher_id, int course_id, String question, String question_time, String reply_time) {
        this.student_id = student_id;
        this.teacher_id = teacher_id;
        this.course_id = course_id;
        this.question = question;
        this.question_time = question_time;
        this.reply_time = reply_time;
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

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getQuestion_time() {
        return question_time;
    }

    public void setQuestion_time(String question_time) {
        this.question_time = question_time;
    }

    public String getReply_time() {
        return reply_time;
    }

    public void setReply_time(String reply_time) {
        this.reply_time = reply_time;
    }

    @Override
    public String toString() {
        return "Discussion{" +
                "id=" + id +
                ", student_id=" + student_id +
                ", teacher_id=" + teacher_id +
                ", course_id=" + course_id +
                ", question='" + question + '\'' +
                ", reply='" + reply + '\'' +
                ", question_time='" + question_time + '\'' +
                ", reply_time='" + reply_time + '\'' +
                '}';
    }
}
