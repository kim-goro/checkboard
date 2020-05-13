CREATE TABLE t_checkboard(
		i_checkboard INT UNSIGNED AUTO_INCREMENT,
		checkboard_writer INT UNSIGNED,
		checkboard_title VARCHAR(100),
		checkboard_decription VARCHAR(300),
		
		
		checkboard_participants JSON NOT NULL, -- 목표
		-- {"index:순서, "userName":골네임 } 
		checkboard_goal JSON NOT NULL, -- 목표
		-- {"index:순서, "goalName":골네임 , "goalState":N} 
		
		r_dt DATETIME DEFAULT NOW() NOT NULL, -- 작성날짜 
		due_dt DATETIME NOT NULL, -- 끝나는 날짜
		checkboard_state ENUM('Y','N') INDEX NOT NULL, -- Y진행중 N끝
		PRIMARY KEY(i_board)
		FOREIGN KEY(writer) REFERENCES t_user(i_user)
)
