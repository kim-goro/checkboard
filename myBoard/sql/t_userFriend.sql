CREATE DATABASE board DEFAULT CHARACTER SET utf8;

CREATE USER 'jspexam'@'localhost' IDENTIFIED BY 'jsppw';
CREATE USER 'jspexam'@'%' IDENTIFIED BY 'jsppw';

GRANT ALL PRIVILEGES ON board.* TO 'jspexam'@'localhost';
GRANT ALL PRIVILEGES ON board.* TO 'jspexam'@'%';

CREATE TABLE t_user(
		i_user INT UNSIGNED AUTO_INCREMENT,
		user_id varchar(100) UNIQUE NOT NULL,
		user_email VARCHAR(100) UNIQUE NOT NULL,
		user_password char(32) NOT NULL, -- (MD5 ��ȣȭ�� 32�ڸ� ����ó��)
		user_gender ENUM('M','F','U') NOT NULL,
		user_hobby varchar(200), -- ���ɻ�{�౸;�豸;...}
		user_state ENUM('Y','N') DEFAULT 'Y' INDEX NOT NULL, -- Y����ȸ�� N����ȸ��
		r_dt DATETIME DEFAULT NOW() NOT NULL, -- ȸ������ �Ͻ�
		user_logtime DATETIME NOT NULL, -- ������ ���� ���
		user_checkboard VARCHAR(100) DEFAULT '', -- ����� üũ���� ��� ����
		user_friend VARCHAR(100) DEFAULT '', -- ����� ģ�� ��� ����
		is_super ENUM('Y','N') DEFAULT 'N' NOT NULL, -- Y������ N�Ϲ�����
		PRIMARY KEY(i_user)
)engine=InnoDB default character set = utf8;
