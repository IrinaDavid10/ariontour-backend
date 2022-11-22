
CREATE TABLE location

(
    id   int     NOT NULL AUTO_INCREMENT,
    city varchar(50) NOT NULL,
    country_code varchar(2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (country_code) REFERENCES country (country_code)
);
/*
CREATE TABLE user_role

(
    id   int     NOT NULL AUTO_INCREMENT,
    role_name varchar(50) NOT NULL,
    user_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);
*/