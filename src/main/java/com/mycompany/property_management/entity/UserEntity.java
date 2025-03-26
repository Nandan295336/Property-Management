package com.mycompany.property_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "USER_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id //to make this as primary key
    @GeneratedValue(strategy = GenerationType.AUTO)  //to generate primary key automatically
    private Long id;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @Column(name ="EMAIL", nullable = false)
    private String email;
    private String phone;
    @Column(nullable = false)
    private String password;
    private Date creationDate;

}
