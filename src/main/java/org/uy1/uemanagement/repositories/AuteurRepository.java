package org.uy1.uemanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uy1.uemanagement.entities.Auteur;

public interface AuteurRepository extends JpaRepository<Auteur, Long> {
    Auteur findByFirstName(String firstName);
}
