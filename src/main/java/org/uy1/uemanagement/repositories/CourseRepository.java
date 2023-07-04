package org.uy1.uemanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uy1.uemanagement.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByTitle(String title);

    boolean existsByTitle();
}
