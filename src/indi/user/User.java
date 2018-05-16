package indi.user;

import javax.swing.JOptionPane;

import indi.LogOnFrm;

public class User {
	private int userid;
	private int power;
	private String password;
	
	public User() {
		
	}
	
	public User(int userid , String password,int power) {
		this.userid = userid;
		this.power = power;
		this.password = password;
	}
	
	public int getUserId() {
		return userid;
	}
	
	public void setUserId(int userid) {
		this.userid = userid;
	}
	
	public int getPower() {
		return power;
	}
	
	public void setPower(int power) {
		this.power = power;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password; 
	}
	
	public boolean quit() {
		int i = 0;
		Object[] options = { "是", "否" }; 
		i = JOptionPane.showOptionDialog(null, "是否要退出管理系统?", "Warning", 
		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
		null, options, options[0]); 
		if (i == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean logout() {
		int i = 0;
		Object[] options = { "是", "否" }; 
		i = JOptionPane.showOptionDialog(null, "是否要注销当前帐号?", "Warning", 
		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
		null, options, options[0]); 
		if (i == 0) {
			LogOnFrm logOnFrm =  new LogOnFrm();
			logOnFrm.setVisible(true);
			return true;
		}else {
			return false;
		}
	}
}
