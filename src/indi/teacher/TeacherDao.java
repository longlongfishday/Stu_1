package indi.teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import indi.LogOnFrm;
import indi.student.Student;
import indi.user.User;
import indi.user.UserDao;

public class TeacherDao {
	private User user = new User();
	private UserDao userDao = new UserDao();
	public Teacher showTeacherData(Connection connection , User user)throws Exception {
		Teacher resultteacher = null;
		String sql = "select * from teacher where teacherid = '"+ user.getUserId()+"'";
		PreparedStatement preparedStatement  = connection.prepareStatement(sql);
		ResultSet rSet = preparedStatement.executeQuery(sql);
		if (rSet.next()) {
			resultteacher = new Teacher();
			resultteacher.setId(rSet.getInt("teacherid"));
			resultteacher.setName(rSet.getString("name"));
		}
		return resultteacher;
	}
	
	public boolean addTeacher(Connection connection , User user,String name)throws Exception{
		if (userDao.isUserExited(connection, user)) {
			return false; 
		}
		userDao.addUser(connection, user);
		String sql = "insert into teacher(teacherid,teachername)" +" values('"+user.getUserId()+"','"+name+"')";
		PreparedStatement prepared3Statement  = connection.prepareStatement(sql);
		prepared3Statement.executeUpdate();
		return true;
	}
	
	public ResultSet selectStudentByCourseName(Connection connection,String coursename) throws Exception {
		String sql = "SELECT * from major,course , student WHERE course.courseid = major.courseid and course.coursename = ? AND major.teacherid = ? and student.studentid = major.studentid";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, coursename);
		preparedStatement.setInt(2, LogOnFrm.GLOBALUSER.getUserId());
		return preparedStatement.executeQuery();
	}
	
	public ResultSet getTeachCourseName(Connection connection) throws Exception {
		String sql = "select coursename from course where teacherid = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, LogOnFrm.GLOBALUSER.getUserId());
		return preparedStatement.executeQuery();
	}
	
	public ResultSet getTeacheridByTeahcerName(Connection connection , String teachername) throws Exception {
		String sql = "select teacherid from teacher where teachername = '"+teachername+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		return preparedStatement.executeQuery();
	}
}
