package org.uy1.uemanagement.services;

import org.uy1.uemanagement.entities.Auteur;
import org.uy1.uemanagement.entities.Course;
import org.uy1.uemanagement.entities.Supports;
import org.uy1.uemanagement.execptions.DuplicateCourseException;
import org.uy1.uemanagement.execptions.DuplicateSupportsException;

import java.util.List;

public interface CourseManagement {

   //Interface for course methods

   Course createCourse(Course course) throws DuplicateCourseException;
   Course updateCourse(Course course) throws DuplicateCourseException;
   void removeCourse(Long id);
   List<Course> getAllCourse();
   List<Course> findCourseByTitle(String name);

   // Interface for supports methods

    Supports createSupports(Supports supports) throws DuplicateSupportsException;
    Supports updateSupport(Supports supports) throws DuplicateSupportsException;
    void removeSupport(Long id);
    List<Supports> getAllSupports();
    //List<Supports> findSupportsByName(String name);

    // Interface for authors methods

    Auteur createAuteur(Auteur auteur);
    Auteur updateAuteur(Auteur auteur);
    void removeAuteur(Long id);
    List<Auteur> getAllAuteur();
    List<Auteur> findAuteurByName(String name);

}
