package org.uy1.uemanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.uy1.uemanagement.entities.Supports;

import java.util.List;
@Repository
public interface SupportRepository extends JpaRepository<Supports, Long> {
    boolean existsByTitle(String title);

    List<Supports> findByTitleContainingIgnoreCase(String title);
}
