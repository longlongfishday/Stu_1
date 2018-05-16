package indi.course;

public class Course {
	private int courseid;
	private int teacherid;
	private String coursename;
	private int courseday;
	private int coursehour;
	private String classroom;
	
	public void setCourseDay(int coursetime) {
		this.courseday = coursetime;
	}
	
	public int getCourseDay() {
		return this.courseday;
	}
	
	public void setClassRoom(String classroom) {
		this.classroom = classroom;
	}
	
	public String getClassRoom() {
		return this.classroom;
	}
	
	public int getCourseId() {
		return courseid;
	}
	
	public void setCourseId(int courseid) {
		this.courseid = courseid;
	}
	
	public String getCouseName() {
		return coursename;
	}
	
	public void setCourseName(String coursename) {
		this.coursename = coursename;
	}
	
	public int getTeacherId() {
		return teacherid;
	}
	
	public void setTeacherId(int teacherid) {
		this.teacherid = teacherid;
	}
	
	public void setCourseHour(int coursehour) {
		this.coursehour = coursehour;
	}
	
	public int getCourseHour() {
		return coursehour;
	}
}
