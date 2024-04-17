package com.suyyyus.pojo;

/**
 * 学生答题情况存放处
 */
public class Answer {
   private int id;
   private int student_id;
   private int question_id;
   private int course_id;
   private String answer; //学生答案
   private int score; //该题得分
   private String answer_time; //答题时间

    public Answer() {
    }

    public Answer(int student_id, int question_id, int course_id, String answer, int score, String answer_time) {
        this.student_id = student_id;
        this.question_id = question_id;
        this.course_id = course_id;
        this.answer = answer;
        this.score = score;
        this.answer_time = answer_time;
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

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAnswer_time() {
        return answer_time;
    }

    public void setAnswer_time(String answer_time) {
        this.answer_time = answer_time;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", student_id=" + student_id +
                ", question_id=" + question_id +
                ", course_id=" + course_id +
                ", answer='" + answer + '\'' +
                ", score=" + score +
                ", answer_time='" + answer_time + '\'' +
                '}';
    }
}
