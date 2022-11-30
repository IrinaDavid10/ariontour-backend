package com.ariontour.ariontourwebsite.persistance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "city")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityEntity implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @NotBlank
    @Length (min = 2, max = 50)
    @Column (name = "city_name")
    private String cityName;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "country_code", referencedColumnName = "country_code")
    private CountryEntity country;


}
