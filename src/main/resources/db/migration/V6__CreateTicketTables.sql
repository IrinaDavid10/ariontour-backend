CREATE TABLE ticket_type

(
    id   int     NOT NULL AUTO_INCREMENT,
    type_name varchar(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE ticket

(
    id   int     NOT NULL AUTO_INCREMENT,
    event_id int     NOT NULL,
    ticket_type_id int     NOT NULL,
    price DOUBLE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (ticket_type_id) REFERENCES ticket_type (id),
    FOREIGN KEY (event_id) REFERENCES event (id)
);



