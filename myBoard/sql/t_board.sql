CREATE TABLE t_board(
		i_board INT UNSIGNED AUTO_INCREMENT  NOT NULL,
		i_user INT UNSIGNED  NOT NULL,
		board_title VARCHAR(100) NOT NULL,
		board_content VARCHAR(300) NOT NULL,
		r_dt DATETIME DEFAULT NOW() NOT NULL, -- 작성날짜
		board_readCount INT UNSIGNED DEFAULT '0' NOT NULL,
		board_ref INT UNSIGNED NOT NULL, -- 글 그룹 번호 지정
		board_restep INT UNSIGNED NOT NULL, -- 부모글 -> 자식글(답글) 순
		board_relevel INT UNSIGNED NOT NULL, -- 답글 중에서도 순서를 나눔
		PRIMARY KEY(i_board),
		FOREIGN KEY(i_user) REFERENCES t_user(i_user)
)engine=InnoDB default character set = utf8;
