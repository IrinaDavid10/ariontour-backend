
CREATE TABLE event

(
    id   int     NOT NULL AUTO_INCREMENT,
    title varchar(50) NOT NULL,
    location_id int NOT NULL,
    date_time TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (location_id) REFERENCES location (id)
);
/*


    @NotNull
    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private LocationEntity location;

    @NotNull
    @Column(name = "date")
    LocalDate date;

    @NotNull
    @Column(name = "time")
    LocalTime time;
*/