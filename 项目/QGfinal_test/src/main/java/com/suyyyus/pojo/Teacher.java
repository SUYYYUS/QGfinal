package com.suyyyus.pojo;

/**
 * 教师类
 */
public class Teacher {
    private int id;
    private String teachername;
    private String teacherid;
    private String password;
    private String college;
    private String qq;
    private String email;
    private String description;
    private String create_time;
    private String update_time;

    public Teacher() {
    }

    /**
     * 用于修改教师信息
     * @param teachername
     * @param teacherid
     * @param password
     * @param college
     * @param qq
     * @param email
     * @param description
     * @param update_time
     */
    public Teacher(String teachername, String teacherid, String password,
                   String college, String qq, String email, String description,
                   String update_time) {
        this.teachername = teachername;
        this.teacherid = teacherid;
        this.password = password;
        this.college = college;
        this.qq = qq;
        this.email = email;
        this.description = description;
        this.update_time = update_time;
    }

    /**
     * 用于添加教师
     * @param teachername
     * @param teacherid
     * @param password
     * @param college
     * @param qq
     * @param email
     * @param description
     */
    public Teacher(String teachername, String teacherid, String password,
                   String college, String qq, String email, String description) {
        this.teachername = teachername;
        this.teacherid = teacherid;
        this.password = password;
        this.college = college;
        this.qq = qq;
        this.email = email;
        this.description = description;
    }

    public Teacher(int id, String teachername, String teacherid,
                   String password, String college, String qq, String email,
                   String description, String create_time, String update_time) {
        this.id = id;
        this.teachername = teachername;
        this.teacherid = teacherid;
        this.password = password;
        this.college = college;
        this.qq = qq;
        this.email = email;
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

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", teachername='" + teachername + '\'' +
                ", teacherid='" + teacherid + '\'' +
                ", password='" + password + '\'' +
                ", college='" + college + '\'' +
                ", qq='" + qq + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                '}';
    }
}
