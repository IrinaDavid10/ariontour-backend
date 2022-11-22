package com.ariontour.ariontourwebsite.persistance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "location")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "country_code", referencedColumnName = "country_code")
    private CountryEntity country;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column (name = "city")
    private String city;


}
