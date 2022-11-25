package com.ariontour.ariontourwebsite.persistance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "event")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column (name = "title")
    private String title;

    @NotBlank
    @Length( max = 500)
    @Column (name = "description")
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private LocationEntity location;

    @NotNull
    @Column(name = "date_time")
    LocalDateTime dateTime;
}
