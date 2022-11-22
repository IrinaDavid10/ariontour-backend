
CREATE TABLE booking

(
    id   int     NOT NULL AUTO_INCREMENT,
    customer_id int NOT NULL,
    date_time TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);
