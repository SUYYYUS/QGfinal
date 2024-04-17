package com.suyyyus.pojo;

public class Question {
    private int id;
    private String type; //类型：选择，判断，填空
    private String content; //题目内容
    private String answer; //答案
    private int score; //分值
    private int course_section_id; //章节id
    private int course_id; //课程id
    private String create_time; //出题时间

    public Question(String type, String content, String answer,
                    int score, int course_section_id, int course_id,
                    String create_time) {
        this.type = type;
        this.content = content;
        this.answer = answer;
        this.score = score;
        this.course_section_id = course_section_id;
        this.course_id = course_id;
        this.create_time = create_time;
    }

    public Question(String type, String content, String answer,
                    int score, int course_section_id, int course_id
                    ) {
        this.type = type;
        this.content = content;
        this.answer = answer;
        this.score = score;
        this.course_section_id = course_section_id;
        this.course_id = course_id;
    }

    public Question() {
    }

    public Question(int id, String type, String content, String answer, int score, int course_section_id, int course_id, String create_time) {
        this.id = id;
        this.type = type;
        this.content = content;
        this.answer = answer;
        this.score = score;
        this.course_section_id = course_section_id;
        this.course_id = course_id;
        this.create_time = create_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getCourse_section_id() {
        return course_section_id;
    }

    public void setCourse_section_id(int course_section_id) {
        this.course_section_id = course_section_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", answer='" + answer + '\'' +
                ", score=" + score +
                ", course_section_id=" + course_section_id +
                ", course_id=" + course_id +
                ", create_time='" + create_time + '\'' +
                '}';
    }
}
