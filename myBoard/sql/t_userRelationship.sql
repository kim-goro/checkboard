CREATE DATABASE board DEFAULT CHARACTER SET utf8;

CREATE USER 'jspexam'@'localhost' IDENTIFIED BY 'jsppw';
CREATE USER 'jspexam'@'%' IDENTIFIED BY 'jsppw';

GRANT ALL PRIVILEGES ON board.* TO 'jspexam'@'localhost';
GRANT ALL PRIVILEGES ON board.* TO 'jspexam'@'%';

CREATE TABLE t_userRelationship(
		i_userRelationship INT UNSIGNED AUTO_INCREMENT NOT NULL,
		i_user INT UNSIGNED NOT NULL, -- 본인
		i_userf INT UNSIGNED NOT NULL. -- 친구
		PRIMARY KEY(i_userRelationship),
		FOREIGN KEY(i_user) REFERENCES t_user(i_user),
		FOREIGN KEY(i_userf) REFERENCES t_user(i_userf)
)engine=InnoDB default character set = utf8;

