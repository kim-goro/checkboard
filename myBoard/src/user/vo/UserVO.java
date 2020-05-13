package user.vo;

public class UserVO {
	private int i_user;
	private String user_id;
	private String user_password;
	private String user_email; 
	private String user_gender; // null
	private String user_hobby; // null
	private String user_birth; // null
	private String r_dt;
	private String user_state;
	private String is_super;
	
	public enum gender{
		M,
		F
	}
	public enum is_super{
		Y,
		N
	}
	
	public int getI_user() {
		return i_user;
	}
	public void setI_user(int i_user) {
		this.i_user = i_user;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}
	public String getUser_hobby() {
		return user_hobby;
	}
	public void setUser_hobby(String user_hobby) {
		this.user_hobby = user_hobby;
	}
	public String getUser_birth() {
		return user_birth;
	}
	public void setUser_birth(String user_birth) {
		this.user_birth = user_birth;
	}
	public String getUser_state() {
		return user_state;
	}
	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}
	public String getR_dt() {
		return r_dt;
	}
	public void setR_dt(String r_dt) {
		this.r_dt = r_dt;
	}
	public String getIs_super() {
		return is_super;
	}
	public void setIs_super(String is_super) {
		this.is_super = is_super;
	}
	

}
