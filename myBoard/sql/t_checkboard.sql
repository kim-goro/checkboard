CREATE TABLE t_checkboard(
		i_checkboard INT UNSIGNED AUTO_INCREMENT NOT NULL,
		checkboard_i_user INT UNSIGNED NOT NULL,
		checkboard_title VARCHAR(100) NOT NULL,
		checkboard_decription VARCHAR(300),
		r_dt DATETIME DEFAULT NOW() NOT NULL, -- 작성날짜 
		due_dt DATETIME NOT NULL, -- 끝나는 날짜
		checkboard_state ENUM('Y','N') DEFAULT 'Y' NOT NULL, -- Y진행중 N끝
		PRIMARY KEY(i_checkboard),
		FOREIGN KEY(checkboard_i_user) REFERENCES t_user(i_user)
)
