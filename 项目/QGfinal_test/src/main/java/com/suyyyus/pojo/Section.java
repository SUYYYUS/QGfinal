package com.suyyyus.pojo;

/**
 * 章节表
 */
public class Section {
    private int id;
    private String sectionname;
    private String content;
    private int course_id; //课程ID
    private String create_time;

    public Section(int id, String sectionname, String content, int course_id, String create_time) {
        this.id = id;
        this.sectionname = sectionname;
        this.content = content;
        this.course_id = course_id;
        this.create_time = create_time;
    }

    public Section(String sectionname, String content, int course_id ) {
        this.sectionname = sectionname;
        this.content = content;
        this.course_id = course_id;
    }

    public Section() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getSectionname() {
        return sectionname;
    }

    public void setSectionname(String sectionname) {
        this.sectionname = sectionname;
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", sectionname='" + sectionname + '\'' +
                ", content='" + content + '\'' +
                ", course_id=" + course_id +
                ", create_time='" + create_time + '\'' +
                '}';
    }
}
