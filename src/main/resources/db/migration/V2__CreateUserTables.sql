
CREATE TABLE user

(
    id   int     NOT NULL AUTO_INCREMENT,
    username varchar(20) NOT NULL,
    password varchar(100) NOT NULL,
    customer_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE user_role

(
    id   int     NOT NULL AUTO_INCREMENT,
    role_name varchar(50) NOT NULL,
    user_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);
