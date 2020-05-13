package checkboard.vo;

public class CheckboardVO {
	private int i_checkboard;
	private int checkboard_i_user;
	private String checkboard_title;
	private String checkboard_decription; 
	private String r_dt; // null
	private String due_dt; // null
	private String checkboard_state; // null
	
	public enum checkboard_state{
		Y,
		N
	}

	public int getI_checkboard() {
		return i_checkboard;
	}

	public void setI_checkboard(int i_checkboard) {
		this.i_checkboard = i_checkboard;
	}

	public int getCheckboard_i_user() {
		return checkboard_i_user;
	}

	public void setCheckboard_i_user(int checkboard_i_user) {
		this.checkboard_i_user = checkboard_i_user;
	}

	public String getCheckboard_title() {
		return checkboard_title;
	}

	public void setCheckboard_title(String checkboard_title) {
		this.checkboard_title = checkboard_title;
	}

	public String getCheckboard_decription() {
		return checkboard_decription;
	}

	public void setCheckboard_decription(String checkboard_decription) {
		this.checkboard_decription = checkboard_decription;
	}

	public String getR_dt() {
		return r_dt;
	}

	public void setR_dt(String r_dt) {
		this.r_dt = r_dt;
	}

	public String getDue_dt() {
		return due_dt;
	}

	public void setDue_dt(String due_dt) {
		this.due_dt = due_dt;
	}

	public String getCheckboard_state() {
		return checkboard_state;
	}

	public void setCheckboard_state(String checkboard_state) {
		this.checkboard_state = checkboard_state;
	}
}
