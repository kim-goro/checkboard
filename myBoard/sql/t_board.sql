CREATE TABLE t_board(
		i_board INT UNSIGNED AUTO_INCREMENT,
		board_writer INT UNSIGNED,
		board_title VARCHAR2(100),
		board_content VARCHAR(300),
		r_dt DATETIME DEFAULT NOW() NOT NULL, -- �ۼ���¥
		board_readCount INT UNSIGNED DEFAULT '0',
		board_ref INT UNSIGNED NOT NULL, -- �� �׷� ��ȣ ����
		board_restep INT UNSIGNED NOT NULL, -- �θ�� -> �ڽı�(���) ��
		board_relevel INT UNSIGNED NOT NULL, -- ��� �߿����� ������ ����
		PRIMARY KEY(i_board)
		FOREIGN KEY(writer) REFERENCES t_user(i_user)
)
