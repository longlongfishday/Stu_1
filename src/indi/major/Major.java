package indi.major;

public class Major {
	private int studentid;
	private int courseid;
	private int teacherid;
	private int totalscore;
	private int midscore;
	private int expermentscore;
	private int ordinaryscore;
	private int lastscore;
	
	public int getMidscore() {
		return midscore;
	}

	public void setMidscore(int midscore) {
		this.midscore = midscore;
	}

	public int getExpermentscore() {
		return expermentscore;
	}

	public void setExpermentscore(int expermentscore) {
		this.expermentscore = expermentscore;
	}

	public int getOrdinaryscore() {
		return ordinaryscore;
	}

	public void setOrdinaryscore(int ordinaryscore) {
		this.ordinaryscore = ordinaryscore;
	}

	public int getLastscore() {
		return lastscore;
	}

	public void setLastscore(int lastscore) {
		this.lastscore = lastscore;
	}

	public void setTotalScore(int totalscore) {
		this.totalscore = totalscore;
	}
	
	public int getTotalScore() {
		return totalscore;
	}
	
	public void setTeacherId(int teacherid) {
		this.teacherid = teacherid;
	}
	
	public int getTeacherId() {
		return teacherid;
	}
	
	public int getStudentId() {
		return studentid;
	}
	
	public void setStudentId(int studentid) {
		this.studentid = studentid;
	}
	
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	
	public int getCourseid() {
		return courseid;
	}
	
}
