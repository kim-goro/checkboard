CREATE TABLE t_checkboardgoal(
		i_checkboardgoal INT UNSIGNED AUTO_INCREMENT NOT NULL,
		i_checkboard INT UNSIGNED NOT NULL, -- �ش�Ǵ� üũ����
		goalName VARCHAR(50) NOT NULL,
		r_dt DATETIME DEFAULT NOW() NOT NULL, -- �߰��� ��¥
		PRIMARY KEY(i_checkboardgoal),
		FOREIGN KEY(i_checkboard) REFERENCES t_checkboard(i_checkboard)
)
