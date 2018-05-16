package indi.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jws.soap.SOAPBinding.Use;
import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import indi.LogOnFrm;
import indi.student.Student;
import indi.tools.Constants;

import java.sql.PreparedStatement;

public class UserDao {
	private Constants constants = new Constants();
	public User login(Connection connection , User user)throws Exception {
		User resultuser = null;
		String sql = "select * from user where userid = '"+ user.getUserId()+"' and password = '"+ user.getPassword() +"' and power = '"+ user.getPower() +"'";
		PreparedStatement preparedStatement  = connection.prepareStatement(sql);
		ResultSet rSet = preparedStatement.executeQuery(sql);
		if (rSet.next()) {
			 resultuser = new User(rSet.getInt("userid"),rSet.getString("password"),rSet.getInt("power"));
		}
		return resultuser;
	}
	
	public int addUser(Connection connection , User user)throws Exception {
		String sql = "insert into user(userid,password,power) value('"+user.getUserId()+"','"+user.getPassword()+"','"+user.getPower()+"')";
		PreparedStatement preparedStatement  = connection.prepareStatement(sql);
		return preparedStatement.executeUpdate();
	}
	
	public boolean isUserExited(Connection connection , User user) throws Exception{
		String sql = "select * from user where userid = '"+ user.getUserId()+"' and power = '"+user.getPower()+"'";
		PreparedStatement preparedStatement  = connection.prepareStatement(sql);
		ResultSet rSet = preparedStatement.executeQuery(sql);
		if (rSet.next()) {
			return true;
		}else {
			return false;
		}
	}
	
	public void UpdataPassword(Connection connection , String newpassword) throws Exception {
		String sql = "update user set password = ? where userid = ?";;
		PreparedStatement ptmt = connection.prepareStatement(sql);
		ptmt.setString(1,newpassword);
		ptmt.setInt(2,LogOnFrm.GLOBALUSER.getUserId());
		ptmt.executeUpdate();
	}
	
	public User getUser(Connection connection , String id)throws Exception {
		User resultuser = new User();
		String sql = "select * from user where userid = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, id);
		ResultSet rSet = preparedStatement.executeQuery();
		while(rSet.next()) {
			 resultuser = new User(rSet.getInt("userid"),rSet.getString("password"),rSet.getInt("power"));
		}
		return resultuser;
	}
	
	public void deleteUser(Connection connection,String id) throws Exception{
		String sql = "delete from user where userid = '"+id+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.executeUpdate();
	}
	
	public void updatePasswordByUser(Connection connection,User user) throws Exception {
		String sql = "update user set password = '"+user.getPassword()+"' where userid = '"+user.getUserId()+"' and power = '"+user.getPower()+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.executeUpdate();
	}
	
	public int getNextStudentId(Connection connection,String tablename) throws Exception {
		String sql = "SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_SCHEMA='demo' AND TABLE_NAME= '"+tablename+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet rSet = preparedStatement.executeQuery();
		int nextstudentid = 0;
		while(rSet.next()) {
			nextstudentid = rSet.getInt("auto_increment");		
		}
		return nextstudentid;
	}
}
