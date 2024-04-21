
import com.suyyyus.dao.*;
import com.suyyyus.dao.impl.*;
import com.suyyyus.pojo.*;
import com.suyyyus.service.CourseService;
import com.suyyyus.service.QuestionService;
import com.suyyyus.service.StudentService;
import com.suyyyus.service.Student_studyService;
import com.suyyyus.service.impl.CourseServiceImpl;
import com.suyyyus.service.impl.QuestionServiceImpl;
import com.suyyyus.service.impl.StudentServiceImpl;
import com.suyyyus.service.impl.Student_studyServiceImpl;
import com.suyyyus.utils.DatabaseBackup.DatabaseBackup;
import com.suyyyus.utils.JWTUtil;
import com.suyyyus.utils.MD5Util;
import com.suyyyus.utils.MyConnectionPool;
import com.suyyyus.utils.TCP.Client;
import com.suyyyus.utils.TCP.Server;
import com.suyyyus.utils.TimeUtil;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test1 {

    private static final Logger logger =  LoggerFactory.getLogger(test1.class);

    @Test
    public void test_TimeUtil1(){
        LocalDateTime localDateTime = LocalDateTime.now();

        String s = TimeUtil.formatDateTime(localDateTime);

        System.out.println(s);
    }

    @Test
    public void test_TimeUtil2(){
        String time = "2024-04-13 15:47:25";

        LocalDateTime localDateTime = TimeUtil.parseDateTime(time);

        System.out.println(localDateTime);
    }


    //获取连接
    @Test
    public void test_connectionPool(){
        MyConnectionPool myConnectionPool = new MyConnectionPool();
        Connection connection = myConnectionPool.getConnection();
        System.out.println(connection);
    }

    //测试加密密码
    @Test
    public void test_MD5(){
        String password = "123456";
        String saltPassword = MD5Util.generateSaltPassword(password);
        System.out.println(saltPassword);
    }

    //生成JWT令牌
    @Test
    public void test_JWT(){
//生成令牌
                Map<String, Object> claims = new HashMap<>();
                claims.put("username","tom");
                claims.put("password","111");
                String jwt = JWTUtil.generateJwt(claims);
        System.out.println(jwt);
    }

    @Test
    public void test_login() throws SQLException {
        TeacherDao teacherDao = new TeacherDaoImpl();
        StudentDao studentDao = new StudentDaoImpl();
        CourseDao courseDao = new CourseDaoImpl();
        SectionDao sectionDao = new SectionDaoImpl();
//        boolean b = teacherDao.Login("1323004825", "123456");
//        if(b){
//            logger.info("登录成功");
//            System.out.println("11111111");
//        }else if(!b){
//            logger.info("登录失败");
//            System.out.println("22222222");
//        }

//        Student student = new Student("陈泽", "3123004824", "123456", "2024级","1234567890","活泼可爱");
//        studentDao.addStudent(student);

//
//        Teacher teacher = new Teacher("王炸", "1323004825", "123456", "计算机", "9987654321", "9987654321@qq.com", "凶猛");
//        teacherDao.addTeacher(teacher);
//        logger.info("添加成功");
//        Course course = new Course("高等数学", "数学", "简单上手", 1,50);
//        courseDao.addCourse(course);
//        teacherDao.addTeacher(teacher);



//        Section section = new Section("求导","好好学习",1);
//        sectionDao.addSection(section);
//        logger.info("添加成功");

        Course course = courseDao.queryByCourse_id(1);
        if(course != null){
            course.setSection_number(course.getSection_number() + 1);
        }
        System.out.println(course);
    }

    @Test
    public void test_querycoursebyteacher_id() throws Exception {
        CourseDao courseDao = new CourseDaoImpl();

        List<Course> courseList = courseDao.queryAllCourseByTeacher_id(9);

        System.out.println(courseList);

    }

    @Test
    public void test_page_select() throws Exception {
        CourseService courseService = new CourseServiceImpl();
        PageBean<Course> coursePageBean = courseService.selectByPage(1, 1);
        System.out.println(coursePageBean);
    }

    @Test
    public void test_querybysubject() throws Exception {
        CourseService courseService = new CourseServiceImpl();
        List<Course> courseList = courseService.queryBySubject("数学");
        System.out.println(courseList);
    }

    @Test
    public void test_queryAllSectionByCourse_id() throws Exception {
        SectionDao sectionDao = new SectionDaoImpl();
        List<Section> sections = sectionDao.queryAllSectionByCourse_id(1);
        System.out.println(sections);
    }

    @Test
    public void test_addQUestion() throws SQLException {
        QuestionDao questionDao = new QuestionDaoImpl();
        QuestionService questionService = new QuestionServiceImpl();
        Question question = new Question("1","1","1",10,1,1);

        questionDao.addQuestion(question);

        System.out.println(questionService.queryQuesionById(1));

    }

    @Test
    public void test_querystudentstudyrecord() throws SQLException {
        Student_studyService student_studyService = new Student_studyServiceImpl();

        Student_study student_study = student_studyService.queryStudentRecordByIds(3, 2);

        System.out.println(student_study);
    }

    @Test
    public void test_Server() throws IOException {
//        Server server = new Server();
        Server.startDiscussionArea();

        StudentService studentService = new StudentServiceImpl();

        studentService.sendMsg("123456");


    }

    @Test
    public void test_clientstudent() throws IOException {
        StudentService studentService = new StudentServiceImpl();

        studentService.sendMsg("123456");


    }


    @Test
    public void test_backup(){

//        DatabaseBackup.scheduleBackup("localhost","3306","root","csy090944CSY","db03","C:\\Users\\28937\\Desktop\\hhh.sql");

        DatabaseBackup.scheduleBackup();
        System.out.println("nbbbbbb");
    }

    @Test
    public void s1() throws IOException {
        String command = "cmd /c " + "mysqldump -uroot -pcsy090944CSY QGfinal_test>C:\\Users\\28937\\Desktop\\hhh.sql";

        Runtime.getRuntime().exec(command);

    }


}
