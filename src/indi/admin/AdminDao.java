package indi.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import indi.user.User;

public class AdminDao {
	public Admin showAdminData(Connection connection , User user)throws Exception {
		Admin resultadmin = new Admin();
		String sql = "select * from admin where adminid = '"+ user.getUserId()+"'";
		PreparedStatement preparedStatement  = connection.prepareStatement(sql);
		ResultSet rSet = preparedStatement.executeQuery(sql);
		if (rSet.next()) {
			resultadmin.setId(rSet.getString("adminid")); 
			resultadmin.setName(rSet.getString("name"));
		}
		return resultadmin;
	}
}
