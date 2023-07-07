package org.uy1.uemanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.uy1.uemanagement.entities.Supports;

import java.util.List;
@Repository
public interface SupportRepository extends JpaRepository<Supports, Long> {
    boolean existsByTitle(String title);

    List<Supports> findByTitleContainingIgnoreCase(String title);
//    @Query("SELECT COUNT(c) FROM Supports c WHERE c.documentContent=?1")
//    int countUploadedFile();
//
//    int countByDocumentContent
//
//    @Query("SELECT COALESCE(SUM(s.documentContent), 0) FROM Supports s WHERE s.documentContent=?2")
//    Double getTotalSize();


}
