package com.suyyyus.pojo;

public class Student {
   private int id;
   private String studentname;
   private String studentid; //学号
   private String password;
   private String grade;
   private String qq;
   private String description;
   private String create_time;
   private String update_time;
   private int remember; //勾选是否记住我

    public Student() {
    }

    /**
     * 用于修改信息
     * @param studentname
     * @param studentid
     * @param password
     * @param grade
     * @param qq
     * @param description
     * @param update_time
     */
    public Student(String studentname, String studentid, String password,
                   String grade, String qq, String description,
                   String update_time) {
        this.studentname = studentname;
        this.studentid = studentid;
        this.password = password;
        this.grade = grade;
        this.qq = qq;
        this.description = description;
        this.update_time = update_time;
    }

    /**
     * 用于创建学生
     * @param studentname
     * @param studentid
     * @param password
     * @param grade
     * @param qq
     * @param description
     */
    public Student(String studentname, String studentid, String password,
                   String grade, String qq, String description) {
        this.studentname = studentname;
        this.studentid = studentid;
        this.password = password;
        this.grade = grade;
        this.qq = qq;
        this.description = description;
    }

    public Student(int id, String studentname, String studentid, String password,
                   String grade, String qq, String description,
                   String create_time, String update_time) {
        this.id = id;
        this.studentname = studentname;
        this.studentid = studentid;
        this.password = password;
        this.grade = grade;
        this.qq = qq;
        this.description = description;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public int getRemember() {
        return remember;
    }

    public void setRemember(int remember) {
        this.remember = remember;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentname='" + studentname + '\'' +
                ", studentid='" + studentid + '\'' +
                ", password='" + password + '\'' +
                ", grade='" + grade + '\'' +
                ", qq='" + qq + '\'' +
                ", description='" + description + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                '}';
    }
}
