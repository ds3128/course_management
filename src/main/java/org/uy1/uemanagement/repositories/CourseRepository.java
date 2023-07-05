package org.uy1.uemanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uy1.uemanagement.entities.Course;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByTitleContainingIgnoreCase(String courseTitle);

    boolean existsByTitle(String title);
}
