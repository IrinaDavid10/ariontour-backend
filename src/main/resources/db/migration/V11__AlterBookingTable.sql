ALTER TABLE booking
    ADD COLUMN ticket_id INT NOT NULL,
ADD FOREIGN KEY (ticket_id) REFERENCES ticket (id);