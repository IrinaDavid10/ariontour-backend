CREATE TABLE support_chat

(
    id   int     NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL,
    admin_id int,
    subject varchar(50) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (admin_id) REFERENCES user (id)
);