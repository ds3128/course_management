package org.uy1.uemanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.uy1.uemanagement.entities.Auteur;

import java.util.List;
@Repository
public interface AuteurRepository extends JpaRepository<Auteur, Long> {

    boolean existsByEmail(String email);

    boolean existsByTel(String tel);

    @Query("SELECT a FROM Auteur a WHERE a.firstName =: firstName")
    List<Auteur> findAuteursByName(String firstName);

//    List<Auteur> findByFirstNameContainingIgnoreCase(String firstName);
    @Query("select  a from Auteur a where a.firstName like :kw")
    List<Auteur> searchAuteur(@Param(("kw")) String keyword);
}
