package indi.tools;

import java.sql.*;
public class JDBCTools {
	
		private String user = "jiujiu";
		private String password = "jiujiu";
		private String url = "jdbc:mysql://mysql.rdsm39cew0303oe.rds.gz.baidubce.com:3306/jiujiu";
		private String jdbcDriver= "com.mysql.jdbc.Driver"; 
		   
		public Connection getConnection() throws Exception {  
		    	Class.forName(jdbcDriver);  
		    	// 3.获取数据库连接  
			Connection connection = DriverManager.getConnection(url,user,password);  
			return connection;  
		}  
		
		public void closeConnection(Connection con) throws Exception {  
			if (con != null) {
				con.close();
			}
		}
		    
		public static void main(String[] args) {
		    	JDBCTools jdbcTools = new JDBCTools();
				try {
					jdbcTools.getConnection();
					System.out.println("数据库连接成功");
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("数据库连接失败");
				}	
			}
}
