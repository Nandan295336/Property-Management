package com.mycompany.property_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AdressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String houseNo;
    private String street;
    private String landmark;
    private String lat;
    private String lng;
    private String city;
    private String postalCode;
    private String state;
    private String country;
}
