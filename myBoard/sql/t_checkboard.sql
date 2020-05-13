CREATE TABLE t_checkboard(
		i_checkboard INT UNSIGNED AUTO_INCREMENT,
		checkboard_writer INT UNSIGNED,
		checkboard_title VARCHAR(100),
		checkboard_decription VARCHAR(300),
		
		
		checkboard_participants JSON NOT NULL, -- ��ǥ
		-- {"index:����, "userName":����� } 
		checkboard_goal JSON NOT NULL, -- ��ǥ
		-- {"index:����, "goalName":����� , "goalState":N} 
		
		r_dt DATETIME DEFAULT NOW() NOT NULL, -- �ۼ���¥ 
		due_dt DATETIME NOT NULL, -- ������ ��¥
		checkboard_state ENUM('Y','N') INDEX NOT NULL, -- Y������ N��
		PRIMARY KEY(i_board)
		FOREIGN KEY(writer) REFERENCES t_user(i_user)
)
