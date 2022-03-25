package com.example.lol_a_z_backend.security.repo;

import com.example.lol_a_z_backend.security.model.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository public interface SummonerRepo extends JpaRepository<Summoner, String> {
    Optional<Summoner> findByUsername(String username);
}
