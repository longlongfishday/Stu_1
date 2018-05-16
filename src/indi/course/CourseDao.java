package indi.course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.xml.ws.AsyncHandler;

import indi.major.Major;
import indi.major.MajorDao;
import indi.teacher.Teacher;
import indi.teacher.TeacherDao;
import indi.tools.StringTools;
import indi.user.UserDao;

public class CourseDao {
	//private UserDao userDao = new UserDao();
	private TeacherDao teacherDao = new TeacherDao();
	private StringTools stringTools = new StringTools();
	//private MajorDao majorDao = new MajorDao();
	public boolean addCourse(Connection connection, Course course)throws Exception {
		if (isCourseExisted(connection, course)) {
			JOptionPane.showMessageDialog(null,"课程已存在,请查证后再添加!");
			return false;
		}
		String sql = "insert into course(courseid,coursename,teacherid)" +" values('"+course.getCourseId()+"','"+course.getCouseName()+"','"+course.getTeacherId()+"')";
		PreparedStatement preparedStatement  = connection.prepareStatement(sql);
		preparedStatement.executeUpdate();
		return true;
	}
	
	public boolean isCourseExisted(Connection connection , Course course) throws Exception{
		String sql = "select * from course where courseid = '"+ course.getCourseId()+"'and teacherid = '"+course.getTeacherId()+"'" ;
		PreparedStatement preparedStatement  = connection.prepareStatement(sql);
		ResultSet rSet = preparedStatement.executeQuery(sql);
		if (rSet.next()) {
			return true;
		}else {
			return false;
		}
	}
	
	public ResultSet selectByOption(Connection connection,Course course , String teachername) throws Exception {
		String sql = "select course.courseid,coursename,teacher.teacherid,teachername,courseday,coursehour,classroom from course,teacher where course.teacherid = teacher.teacherid";
		if (stringTools.isNotEmpty(course.getClassRoom())) {
			sql  += " and classroom like '%"+course.getClassRoom()+"%' ";
		}
		if (stringTools.isNotEmpty(course.getCouseName())) {
			sql += " and coursename like '%"+course.getCouseName()+"%'";
		}
		if (stringTools.isNotEmpty(teachername)) {
			sql += " and teachername like '%"+teachername+"%'";
		}
		if (course.getCourseDay() != 0) {
			sql += String.format(" and courseday = '"+course.getCourseDay()+"'");
		}
		if (course.getCourseHour() != 0) {
			sql += String.format(" and coursehour = '"+course.getCourseHour()+"'");
		}
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		return preparedStatement.executeQuery();
	}
	
	public ResultSet getAllTeacherName(Connection connection)throws Exception {
		String sql = "select distinct teachername from teacher";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		return preparedStatement.executeQuery();
	}
	
	public void deleteCourse(Connection connection , String courseid) throws Exception{
		Major tmajor = new Major();
		tmajor.setCourseid(Integer.parseInt(courseid));
		MajorDao majorDao = new MajorDao();
		majorDao.deleteMajor(connection, tmajor);
		String sql = "delete from course where courseid = '"+courseid+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.executeUpdate();
	}
	
	public void updateCourseData(Connection connection ,Course course ,String teachername)throws Exception{
		if (stringTools.isNotEmpty(teachername)) {
			ResultSet rSet = teacherDao.getTeacheridByTeahcerName(connection, teachername);
			while(rSet.next()) {
				course.setTeacherId(rSet.getInt("teacherid"));
			}
		}
		String sql = "update course set coursename = ?,courseday = ? ,coursehour = ?,classroom = ? , teacherid = ? where courseid = ? ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, course.getCouseName());
		preparedStatement.setInt(2, course.getCourseDay());
		preparedStatement.setInt(3, course.getCourseHour());
		preparedStatement.setString(4, course.getClassRoom());
		preparedStatement.setInt(5, course.getTeacherId());
		preparedStatement.setInt(6, course.getCourseId());
		preparedStatement.executeUpdate();
	}
	
	public int getNextCourseId(Connection connection)throws Exception {
		String sql = "SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_SCHEMA='demo' AND TABLE_NAME= 'course'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rSet = preparedStatement.executeQuery();
		int nextstudentid = 0;
		while(rSet.next()) {
			nextstudentid = rSet.getInt("auto_increment");		
		}
		return nextstudentid;
	}
}
