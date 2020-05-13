package board.vo;

public class BoardCommentVO {
	private int i_comment;
	private int i_board;
	private int seq;
	private String content;
	private int i_user;
	private String r_dt;
	
	private String user_img;
	private String user_nm;
	
	
	
	public int getI_comment() {
		return i_comment;
	}
	public void setI_comment(int i_comment) {
		this.i_comment = i_comment;
	}
	public int getI_board() {
		return i_board;
	}
	public void setI_board(int i_board) {
		this.i_board = i_board;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getI_user() {
		return i_user;
	}
	public void setI_user(int i_user) {
		this.i_user = i_user;
	}
	public String getR_dt() {
		return r_dt;
	}
	public void setR_dt(String r_dt) {
		this.r_dt = r_dt;
	}
	public String getUser_nm() {
		return user_nm;
	}
	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}
	public String getUser_img() {
		return user_img;
	}
	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}
}

//DROP TABLE t_board_comment;
//CREATE TABLE t_board_comment(
//		i_comment INT UNSIGNED AUTO_INCREMENT NOT NULL,
//		i_board INT UNSIGNED,
//		content VARCHAR(500) NOT NULL,
//		i_user INT UNSIGNED NOT NULL,
//		r_dt datetime default NOW(),
//		PRIMARY KEY(i_comment), 
//		FOREIGN KEY(i_board) references t_board(i_board),
//		FOREIGN KEY(i_user) references t_user(i_user)
//	);

