CREATE TABLE t_user_img(
		i_user INT UNSIGNED,
		seq INT UNSIGNED,
		img VARCHAR(100) NOT NULL,
		r_dt DATETIME DEFAULT NOW(),
		PRIMARY KEY(i_user, seq),
		FOREIGN KEY(i_user) REFERENCES t_user(i_user)
)
