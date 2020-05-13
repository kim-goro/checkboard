CREATE TABLE t_checkboardgoal(
		i_checkboardgoal INT UNSIGNED AUTO_INCREMENT NOT NULL,
		i_checkboard INT UNSIGNED NOT NULL, -- 해당되는 체크보드
		goalName VARCHAR(50) NOT NULL,
		r_dt DATETIME DEFAULT NOW() NOT NULL, -- 추가된 날짜
		PRIMARY KEY(i_checkboardgoal),
		FOREIGN KEY(i_checkboard) REFERENCES t_checkboard(i_checkboard)
)
