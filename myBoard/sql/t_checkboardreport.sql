CREATE TABLE t_checkboardreport(
		i_checkboardreport INT UNSIGNED AUTO_INCREMENT NOT NULL,
		i_checkboard INT UNSIGNED NOT NULL, -- �ش�Ǵ� üũ����
		i_user INT UNSIGNED NOT NULL, -- ������
		i_checkboardgoal INT NOT NULL, -- ��ǥ
		goalState ENUM('Y','N') INDEX DEFAULT NULL, -- Y���� �Ϸ� N��
		r_dt DATETIME DEFAULT NOW() NOT NULL, -- �߰��� ��¥
		PRIMARY KEY(i_checkboardreport)
		FOREIGN KEY(i_checkboard) REFERENCES t_checkboard(i_checkboard)
		FOREIGN KEY(i_user) REFERENCES t_user(i_user)
		FOREIGN KEY(i_checkboardgoal) REFERENCES t_checkboardgoal(i_checkboardgoal)
)
