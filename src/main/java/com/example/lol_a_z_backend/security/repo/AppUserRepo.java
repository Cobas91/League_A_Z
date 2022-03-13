package com.example.lol_a_z_backend.security.repo;

import com.example.lol_a_z_backend.security.model.AppUserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUserDTO, String> {
    Optional<AppUserDTO> findByUsername(String username);
}
