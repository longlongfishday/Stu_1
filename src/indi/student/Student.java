package indi.student;

import java.sql.Time;

public class Student {
	private int studentid;
	//private String password;
    private String name;  
    private String sex;  
    private String department;
    private String homeTown; 
    private String tel;  
    private Time birthday;
    
    public Student() {
		
	}
    public Student(int stuid) {
		this.studentid = stuid;
		//this.password = pwd;
	}
  
    public int getStudentId() {  
        return studentid;  
    }  
    public void setStudentId(int studentId) {  
        this.studentid = studentId;  
    }  
   /* public String getPassword() {  
        return password;  
    }   
    public void setPassword(String password) {  
        this.password = password;  
    }    */
    public String getName() {  
        return name;  
    }   
    public void setName(String name) {  
        this.name = name;  
    }  
    public String getSex() {  
        return sex;  
    }   
    public void setSex(String sex) {  
        this.sex = sex;  
    }   
    public String getDepartment() {  
        return department;  
    }  
    public void setDepartment(String department) {  
        this.department = department;  
    }   
    public String getHomeTown() {  
        return homeTown;  
    }    
    public void setHomeTown(String homeTown) {  
        this.homeTown = homeTown;  
    }  
    public String getTel() {  
        return tel;  
    }  
    public void setTel(String tel) {  
        this.tel = tel;  
    } 
    public void setBirthday(Time time) {
		this.birthday = time;
	}
    public Time getBirthday() {
		return birthday;
	}
}
