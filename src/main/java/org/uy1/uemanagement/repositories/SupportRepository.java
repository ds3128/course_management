package org.uy1.uemanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uy1.uemanagement.entities.Supports;

public interface SupportRepository extends JpaRepository<Supports, Long> {
}
