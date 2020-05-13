CREATE TABLE t_checkboardparticipants(
		i_checkboardparticipants INT UNSIGNED AUTO_INCREMENT,
		i_checkboard INT UNSIGNED NOT NULL, -- 해당되는 체크보드
		i_user INT UNSIGNED NOT NULL, -- 참가자
		r_dt DATETIME DEFAULT NOW() NOT NULL, -- 참여날짜
		PRIMARY KEY(i_checkboardparticipants)
		FOREIGN KEY(i_checkboard) REFERENCES t_checkboard(i_checkboard),
		FOREIGN KEY(i_user) REFERENCES t_user(i_user)
)
