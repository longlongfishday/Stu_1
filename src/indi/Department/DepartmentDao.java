package indi.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DepartmentDao {
	public ResultSet getAllDepartmentData(Connection connection)throws Exception {
		String sql = "select * from department";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		return preparedStatement.executeQuery();
	}
	
	public void addDepartment(Connection connection,String departmentname)throws Exception {
		String sql = "insert into department(departmentname) value('"+departmentname+"')";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.executeUpdate();
	}
}
