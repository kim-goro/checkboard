package checkboard.vo;

public class CheckBoardReportVO {
	private int i_checkboardreport;
	private int i_checkboard;
	private int i_user;
	private int i_checkboardgoal;
	private String goalState;
	private String r_dt;
	
	public int getI_checkboardreport() {
		return i_checkboardreport;
	}
	public void setI_checkboardreport(int i_checkboardreport) {
		this.i_checkboardreport = i_checkboardreport;
	}
	public int getI_checkboard() {
		return i_checkboard;
	}
	public void setI_checkboard(int i_checkboard) {
		this.i_checkboard = i_checkboard;
	}
	public int getI_user() {
		return i_user;
	}
	public void setI_user(int i_user) {
		this.i_user = i_user;
	}
	public int getI_checkboardgoal() {
		return i_checkboardgoal;
	}
	public void setI_checkboardgoal(int i_checkboardgoal) {
		this.i_checkboardgoal = i_checkboardgoal;
	}
	public String getGoalState() {
		return goalState;
	}
	public void setGoalState(String goalState) {
		this.goalState = goalState;
	}
	public String getR_dt() {
		return r_dt;
	}
	public void setR_dt(String r_dt) {
		this.r_dt = r_dt;
	} 
}
