package com.example.lol_a_z_backend.security.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class AppUserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private String username;
    private String password;
}



