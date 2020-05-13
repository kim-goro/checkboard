CREATE TABLE t_user_img(
		i_user INT UNSIGNED NOT NULL,
		img VARCHAR(100) NOT NULL,
		PRIMARY KEY(i_user),
		FOREIGN KEY(i_user) REFERENCES t_user(i_user)
)
