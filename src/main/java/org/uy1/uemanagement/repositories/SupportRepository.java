package org.uy1.uemanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uy1.uemanagement.entities.Supports;

import java.util.List;

public interface SupportRepository extends JpaRepository<Supports, Long> {
    boolean existsByTitle(String title);

    List<Supports> findByTitleContainingIgnoreCase(String title);
}
