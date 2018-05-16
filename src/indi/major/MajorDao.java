package indi.major;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import indi.LogOnFrm;
import indi.course.CourseDao;
import indi.tools.Constants;
import indi.tools.JDBCTools;
import indi.tools.StringTools;
import indi.user.User;

public class MajorDao {
	
	private CourseDao courseDao = new CourseDao();
	private Constants constants = new Constants();
	private JDBCTools jdbcTools = new JDBCTools();
	private StringTools stringTools = new StringTools();
	public ResultSet getSchedule(Connection connection ,User user) throws Exception {
		String sql  = null;
		if (user.getPower() == constants.getIsStudent()) {
			sql = "select * from major , course where studentid = ? and course.courseid = major.courseid";
		}else if (user.getPower() == constants.getIsTeacher()) {
			sql = "select * from course where teacherid = ?";
		}
		PreparedStatement preparedStatement  = connection.prepareStatement(sql);
		preparedStatement.setInt(1, user.getUserId());
		return preparedStatement.executeQuery();
	}
	
	public ResultSet getCourseTime(Connection connection) throws SQLException {
		String sql = "SELECT * FROM course,teacher WHERE course.courseid NOT IN (SELECT major.courseid  FROM major,student WHERE major.studentid = student.studentid and student.studentid = 10001) and course.teacherid = teacher.teacherid and course.courseday != 0 and course.coursehour != 0" ;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		return preparedStatement.executeQuery();
	}
	
	public void selectCourse(Connection connection, Major major) throws Exception {
		String sql = "insert into major(studentid,courseid,teacherid)" + " value('"+major.getStudentId()+"','"+major.getCourseid()+"','"+major.getTeacherId()+"')";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.executeUpdate();
	}
	
	public void fillScheduleTable(JTable tmp) {
		DefaultTableModel defaultTableModel = (DefaultTableModel) tmp.getModel();
		Connection connection = null;
		try {
			connection = jdbcTools.getConnection();
			ResultSet rSet = getSchedule(connection, LogOnFrm.GLOBALUSER);
			while (rSet.next()) {
				String data = rSet.getString("coursename") +"  "+ rSet.getString("classroom");
				//StudentCurriculumScheduletable.setValueAt(rSet.getString("coursename"),rSet.getInt("coursehour")-1,rSet.getInt("courseday")-1);
				tmp.setValueAt(data,rSet.getInt("coursehour")-1,rSet.getInt("courseday")-1);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void storeScore(Connection connection , Major major , String coursename) throws Exception {
		major.setTeacherId(LogOnFrm.GLOBALUSER.getUserId());
		String sql = "select courseid from course where teacherid = '"+major.getTeacherId()+"' and coursename = '"+coursename+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rSet = preparedStatement.executeQuery();
		while (rSet.next()) {
			major.setCourseid(rSet.getInt("courseid"));
			sql = "update major set ordinaryscore = ? , midscore = ?,expermentscore = ? , lastscore = ? , totalscore = ? where studentid = ? and teacherid = ? and courseid = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, major.getOrdinaryscore());
			preparedStatement.setInt(2, major.getMidscore());
			preparedStatement.setInt(3, major.getExpermentscore());
			preparedStatement.setInt(4, major.getLastscore());
			preparedStatement.setInt(5, major.getTotalScore());
			preparedStatement.setInt(6, major.getStudentId());
			preparedStatement.setInt(7, major.getTeacherId());
			preparedStatement.setInt(8, major.getCourseid());
			preparedStatement.executeUpdate();
		}		
	}
	
	public void deleteMajor(Connection connection , Major major) throws Exception {
		String sql = "delete from major where 1=1";
		if (stringTools.isNotEmpty(String.valueOf(major.getCourseid()))) {
			sql += " and courseid = '"+major.getCourseid()+"'";
		}
		if (stringTools.isNotEmpty(String.valueOf(major.getCourseid()))) {
			sql += " and studentid = '"+major.getStudentId()+"'";
		}
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.executeUpdate();
	}
	
//	public void deleteMajorByStudentId(Connection connection , String studentid) throws Exception {
//		String sql = "delete form major where studentid = '"+studentid+"'";
//		PreparedStatement preparedStatement = connection.prepareStatement(sql);
//		preparedStatement.executeUpdate();
//	}
	
	public void updateMajorTeacher(Connection connection, String oldteacherid, String newteacherid)throws Exception {
		String sql = "update major set teacherid = '"+newteacherid+"' where teacherid = '"+oldteacherid+"'" ;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.executeUpdate();
	}
	
	public ResultSet getStudentAllExam(Connection connection) throws Exception {
		int stuid = LogOnFrm.GLOBALUSER.getUserId();
		String sql = "SELECT * from major,teacher,course WHERE teacher.teacherid = major.teacherid AND major.studentid = '"+stuid+"' and course.courseid = major.courseid";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		return preparedStatement.executeQuery();
	}
}
