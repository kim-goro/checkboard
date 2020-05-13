CREATE DATABASE board DEFAULT CHARACTER SET utf8;

CREATE USER 'jspexam'@'localhost' IDENTIFIED BY 'jsppw';
CREATE USER 'jspexam'@'%' IDENTIFIED BY 'jsppw';

GRANT ALL PRIVILEGES ON board.* TO 'jspexam'@'localhost';
GRANT ALL PRIVILEGES ON board.* TO 'jspexam'@'%';

CREATE TABLE t_user(
		i_user INT UNSIGNED AUTO_INCREMENT,
		user_id varchar(100) UNIQUE NOT NULL,
		user_email VARCHAR(100) UNIQUE NOT NULL,
		user_password char(32) NOT NULL, -- (MD5 암호화로 32자리 고정처리)
		user_gender ENUM('M','F','U') NOT NULL,
		user_hobby varchar(200), -- 관심사{축구;배구;...}
		user_state ENUM('Y','N') DEFAULT 'Y' INDEX NOT NULL, -- Y정상회원 N유령회원
		r_dt DATETIME DEFAULT NOW() NOT NULL, -- 회원가입 일시
		user_logtime DATETIME NOT NULL, -- 마지막 접근 기록
		user_checkboard VARCHAR(100) DEFAULT '', -- 등록한 체크보드 목록 나열
		user_friend VARCHAR(100) DEFAULT '', -- 등록한 친구 목록 나열
		is_super ENUM('Y','N') DEFAULT 'N' NOT NULL, -- Y관리자 N일반유저
		PRIMARY KEY(i_user)
)engine=InnoDB default character set = utf8;
