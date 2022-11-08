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
@Table (name = "customer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @NotBlank
    @Length (min = 2, max = 50)
    @Column (name = "first_name")
    private String firstName;

    @NotBlank
    @Length (min = 2, max = 50)
    @Column (name = "last_name")
    private String lastName;

     @NotNull
     @ManyToOne
    @JoinColumn (name = "country_code", referencedColumnName = "country_code")
     private CountryEntity country;


}
