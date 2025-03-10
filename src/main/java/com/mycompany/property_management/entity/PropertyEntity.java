package com.mycompany.property_management.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PROPERTY_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class PropertyEntity {

    @Id //to make this primary key
    @GeneratedValue(strategy = GenerationType.AUTO)  //to generate primary key automatically
    private Long id;
    @Column(name = "PROPERTY_TITLE", nullable = false)
    private String title;
    private String description;
    private Double price;
    private String address;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }




}
