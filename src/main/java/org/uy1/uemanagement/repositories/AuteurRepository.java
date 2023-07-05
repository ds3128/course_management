package org.uy1.uemanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uy1.uemanagement.entities.Auteur;

import java.util.List;

public interface AuteurRepository extends JpaRepository<Auteur, Long> {
    Auteur findByFirstName(String firstName);

    boolean existsByEmail(String email);

    boolean existsByTel(String tel);

    List<Auteur> findByNameContainingIgnoreCase(String name);
}
