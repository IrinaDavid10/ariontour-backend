CREATE TABLE country
(
    id         int NOT NULL AUTO_INCREMENT,
    country_name        varchar(50) NOT NULL,
    country_code       varchar(2) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (country_code)
);

CREATE TABLE customer

(
    id   int     NOT NULL AUTO_INCREMENT,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    country_code varchar(2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (country_code) REFERENCES country (country_code)
);
