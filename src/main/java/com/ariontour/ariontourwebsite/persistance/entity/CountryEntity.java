package com.ariontour.ariontourwebsite.persistance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "country")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryEntity implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @NotBlank
    @Length (min = 2, max = 50)
    @Column (name = "country_name")
    private String countryName;

    @NotBlank
    @Length (min = 2, max = 2)
    @Column (name = "country_code")
    private String countryCode;


}
