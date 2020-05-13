CREATE TABLE t_checkboardreport(
		i_checkboardreport INT UNSIGNED AUTO_INCREMENT NOT NULL,
		i_checkboard INT UNSIGNED NOT NULL, -- 해당되는 체크보드
		i_user INT UNSIGNED NOT NULL, -- 보고자
		i_checkboardgoal INT NOT NULL, -- 목표
		goalState ENUM('Y','N') INDEX DEFAULT NULL, -- Y오늘 완료 N끝
		r_dt DATETIME DEFAULT NOW() NOT NULL, -- 추가된 날짜
		PRIMARY KEY(i_checkboardreport)
		FOREIGN KEY(i_checkboard) REFERENCES t_checkboard(i_checkboard)
		FOREIGN KEY(i_user) REFERENCES t_user(i_user)
		FOREIGN KEY(i_checkboardgoal) REFERENCES t_checkboardgoal(i_checkboardgoal)
)
