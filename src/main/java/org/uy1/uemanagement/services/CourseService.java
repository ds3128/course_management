package org.uy1.uemanagement.services;

import org.uy1.uemanagement.entities.Auteur;
import org.uy1.uemanagement.entities.Course;
import org.uy1.uemanagement.entities.Supports;
import org.uy1.uemanagement.execptions.DuplicateAuteurException;
import org.uy1.uemanagement.execptions.DuplicateCourseException;
import org.uy1.uemanagement.execptions.DuplicatePhoneNumberException;
import org.uy1.uemanagement.execptions.DuplicateSupportsException;

import java.util.List;
import java.util.Optional;

public interface CourseService {

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
    List<Supports> getSupportByTitle(String name);

    // Interface for authors methods

    Auteur createAuteur(Auteur auteur) throws DuplicateAuteurException, DuplicatePhoneNumberException;
    Auteur updateAuteur(Auteur auteur);
    void removeAuteur(Long id);
    Optional<Auteur> searchAuteurById(Long id);
    List<Auteur> getAllAuteur();
    List<Auteur> searchAuteur(String keyword);

    // Nombre de fichier charge, Taille des fichiers et Nombre de champs

    int getSupportNumber();
//    int getUploadedFileNumber();
//    Double getSizeDocuments();

}
