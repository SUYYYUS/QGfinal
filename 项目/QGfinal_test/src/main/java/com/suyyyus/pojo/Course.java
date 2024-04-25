package com.suyyyus.pojo;

/**
 * 课程类
 */
public class Course implements Comparable<Course>{
   private int id;
   private String coursename;
   private String subject; //学科分类
   private String description; //描述
   private int teacher_id; //教师ID
   private int section_number; //章节数
   private int limitnumber;//限制人数
   private int registernumber;//报名人数
   private String create_time; //开课时间
   private String end_time; //结课时间


    public Course() {
    }

    public Course(int id, String coursename, String subject, String description,
                  int teacher_id, int section_number, int limitnumber,
                  int registernumber, String create_time, String end_time) {
        this.id = id;
        this.coursename = coursename;
        this.subject = subject;
        this.description = description;
        this.teacher_id = teacher_id;
        this.section_number = section_number;
        this.limitnumber = limitnumber;
        this.registernumber = registernumber;
        this.create_time = create_time;
        this.end_time = end_time;
    }

    public Course(String coursename, String subject, String description, int teacher_id, int limitnumber) {
        this.coursename = coursename;
        this.subject = subject;
        this.description = description;
        this.teacher_id = teacher_id;
        this.limitnumber = limitnumber;
    }

    public int getSection_number() {
        return section_number;
    }

    public void setSection_number(int section_number) {
        this.section_number = section_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getLimitnumber() {
        return limitnumber;
    }

    public void setLimitnumber(int limitnumber) {
        this.limitnumber = limitnumber;
    }

    public int getRegisternumber() {
        return registernumber;
    }

    public void setRegisternumber(int registernumber) {
        this.registernumber = registernumber;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", coursename='" + coursename + '\'' +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", teacher_id=" + teacher_id +
                ", limitnumber=" + limitnumber +
                ", registernumber=" + registernumber +
                ", create_time='" + create_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", section_number=" + section_number +
                '}';
    }

    @Override
    public int compareTo(Course othercourse) {
        // 按照报名升序排序
        return othercourse.getRegisternumber() - this.getRegisternumber();
    }
}
