package indi.student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jws.soap.SOAPBinding.Use;
import javax.swing.JOptionPane;

import indi.LogOnFrm;
import indi.tools.Constants;
import indi.tools.JDBCTools;
import indi.tools.StringTools;
import indi.user.User;
import indi.user.UserDao;

import java.sql.PreparedStatement;

public class StudentDao {
	private User user = new User();
	private UserDao userDao = new UserDao();
	private JDBCTools jdbcTools = new JDBCTools();
	private StringTools stringTools = new StringTools();

	public ResultSet showStudentData(Connection connection , int studentid)throws Exception {
		String sql = "select * from student where studentid = '"+ studentid +"'";
		PreparedStatement preparedStatement  = connection.prepareStatement(sql);
		return preparedStatement.executeQuery(sql);
	}
		
	public boolean addStudent(Connection connection , User user,String name)throws Exception{
		if (userDao.isUserExited(connection, user)) {
			JOptionPane.showMessageDialog(null, "此帐号已注册,请选择其余未注册帐号!");
			return false;
		}
		userDao.addUser(connection, user);
		String sql = "insert into student(studentid,name)" +" values('"+user.getUserId()+"','"+name+"')";
		PreparedStatement prepared3Statement  = connection.prepareStatement(sql);
		prepared3Statement.executeUpdate();
		return true;
	}
	
	public void updateStudent( Student student) throws Exception {
		Connection connection = jdbcTools.getConnection();
		String sql = "update student set name = ? ,sex = ? ,  hometown = ? , tel = ? ,department = ? where studentid = ?";
		PreparedStatement ptmt = connection.prepareStatement(sql);
	    ptmt.setString(1, student.getName());
	    ptmt.setString(2, student.getSex());
	    ptmt.setString(3, student.getHomeTown());
	    ptmt.setString(4, student.getTel());
	    ptmt.setString(5, student.getDepartment());
	    ptmt.setInt(6, student.getStudentId());
	    ptmt.executeUpdate();
	}
	
	public ResultSet getAllDepart(Connection connection) throws Exception {
		String sql = "select distinct department from student where department is not null";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		return preparedStatement.executeQuery();
	}
	
	public ResultSet SelectAllStudentData(Connection connection, Student student) throws Exception {
		String sql = "select studentid,name,sex,hometown,tel,department from student where 1=1";
		if (student.getStudentId() != -1) {
			sql += " and studentid like '%"+student.getStudentId()+"%'";
		}
		if (stringTools.isNotEmpty(student.getName())) {
			sql += " and name like '%"+student.getName()+"%'";
		}
		if (stringTools.isNotEmpty(student.getSex())) {
			if (student.getSex() != "全") {
				sql += " and sex = '"+student.getSex()+"'";
			}
		}
		if (stringTools.isNotEmpty(student.getHomeTown())) {
			sql += " and hometown like '%"+student.getHomeTown()+"%'";
		}
		if (stringTools.isNotEmpty(student.getTel())) {
			sql += " and tel like '%"+student.getTel()+"%'";
		}
		if (stringTools.isNotEmpty(student.getDepartment())) {
			sql += " and department = '"+student.getDepartment()+"'";
		}
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		return preparedStatement.executeQuery();
	}
	
	public void deleteStduent(Connection connection , String stuid) throws Exception {
		userDao.deleteUser(connection, stuid);
		String sql = "delete from student where studentid = '"+stuid+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.executeUpdate();
	}

	public ResultSet getDepartment(Connection connection) throws Exception{
		String sql = "select departmentname  from department";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		return preparedStatement.executeQuery();
	}
}
