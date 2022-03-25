package com.example.lol_a_z_backend.security.model;

import com.example.lol_a_z_backend.model.Champion;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Builder @Entity @Getter @Setter public class Summoner {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private long id;
    private String username;
    private String password;

    @JsonManagedReference @OneToMany(mappedBy = "summoner", cascade = CascadeType.ALL) private List<Champion> champions;

}



