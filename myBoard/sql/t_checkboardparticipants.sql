CREATE TABLE t_checkboardparticipants(
		i_checkboardparticipants INT UNSIGNED AUTO_INCREMENT,
		i_checkboard INT UNSIGNED NOT NULL, -- �ش�Ǵ� üũ����
		i_user INT UNSIGNED NOT NULL, -- ������
		r_dt DATETIME DEFAULT NOW() NOT NULL, -- ������¥
		PRIMARY KEY(i_checkboardparticipants)
		FOREIGN KEY(i_checkboard) REFERENCES t_checkboard(i_checkboard),
		FOREIGN KEY(i_user) REFERENCES t_user(i_user)
)
