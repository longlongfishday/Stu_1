package indi.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


public class Admin {
	private String adminid;  
    private String name;  
    //private String password;  
  
    public Admin() {
		
	}
    
    public Admin(String adminid ) {
		this.adminid = adminid;
	//	this.password = password;
	}
    
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public String getAdminId() {  
        return adminid;  
    }  
  
    public void setId(String adminid) {  
        this.adminid = adminid;  
    }  
   
    /*public String getPassword() {  
        return password;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }  */
}
