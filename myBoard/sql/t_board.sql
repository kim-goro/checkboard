CREATE TABLE t_board(
		i_board INT UNSIGNED AUTO_INCREMENT  NOT NULL,
		i_user INT UNSIGNED  NOT NULL,
		board_title VARCHAR(100) NOT NULL,
		board_content VARCHAR(300) NOT NULL,
		r_dt DATETIME DEFAULT NOW() NOT NULL, -- �ۼ���¥
		board_readCount INT UNSIGNED DEFAULT '0' NOT NULL,
		board_ref INT UNSIGNED NOT NULL, -- �� �׷� ��ȣ ����
		board_restep INT UNSIGNED NOT NULL, -- �θ�� -> �ڽı�(���) ��
		board_relevel INT UNSIGNED NOT NULL, -- ��� �߿����� ������ ����
		PRIMARY KEY(i_board),
		FOREIGN KEY(i_user) REFERENCES t_user(i_user)
)engine=InnoDB default character set = utf8;
