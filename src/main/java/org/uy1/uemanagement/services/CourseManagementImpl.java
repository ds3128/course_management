package org.uy1.uemanagement.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uy1.uemanagement.entities.Auteur;
import org.uy1.uemanagement.entities.Course;
import org.uy1.uemanagement.entities.Supports;
import org.uy1.uemanagement.execptions.DuplicateCourseException;
import org.uy1.uemanagement.execptions.DuplicateSupportsException;
import org.uy1.uemanagement.repositories.AuteurRepository;
import org.uy1.uemanagement.repositories.CourseRepository;
import org.uy1.uemanagement.repositories.SupportRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class CourseManagementImpl implements CourseManagement {

    private CourseRepository courseRepository;
    private SupportRepository supportRepository;
    private AuteurRepository auteurRepository;

    public CourseManagementImpl(CourseRepository courseRepository, SupportRepository supportRepository, AuteurRepository auteurRepository) {
        this.courseRepository = courseRepository;
        this.supportRepository = supportRepository;
        this.auteurRepository = auteurRepository;
    }

    @Override
    public Course createCourse(Course course) throws DuplicateCourseException {
        log.info("Creating course");
        if (courseRepository.existsByTitle(course.getTitle())) {
            throw new DuplicateCourseException("This course already exist");
        }
        Course savedCourse = courseRepository.save(course);
        return savedCourse;
    }


    @Override
    public Course updateCourse(Course course) throws DuplicateCourseException {
        log.info("Updating course");
        if (!courseRepository.existsById(course.getId())){
            throw new DuplicateCourseException("Course id not exist");
        }
        Course save = courseRepository.save(course);
        return save;
    }

    @Override
    public void removeCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }


    @Override
    public List<Course> findCourseByTitle(String name) {
        return courseRepository.findByTitle(name);
    }


    @Override
    public Supports createSupports(Supports supports) throws DuplicateSupportsException {
        if (supportRepository.existsByTitle(supports.getTitle())) {
            throw new DuplicateSupportsException("This support already exist");
        }

        Supports saveSupports = Supports.builder()
                .title(supports.getTitle())
                .typeSupport(supports.getTypeSupport())
                .auteur(supports.getAuteur())
                .build();

        switch (supports.getTypeSupport()) {
            case DOCUMENTS:
                saveSupports.setDocumentDirectory(supports.getDocumentDirectory());
                break;
            case VIDEO:
                saveSupports.setVideoDirectory(supports.getVideoDirectory());
                break;
            case LINKS:
                saveSupports.setLinkDirectory(supports.getLinkDirectory());
                break;
            default:
                throw new IllegalArgumentException("Type of support is invalid");
        }
        return supportRepository.save(saveSupports);
    }


    @Override
    public Supports updateSupport(Supports supports) throws DuplicateSupportsException {
        Optional<Supports> existingSupportOptional = supportRepository.findById(supports.getId());
        if (existingSupportOptional.isEmpty()) {
            throw new DuplicateSupportsException("Support id those not exist");
        }

        Supports existingSupport = existingSupportOptional.get();

        existingSupport.setTitle(supports.getTitle());
        existingSupport.setTypeSupport(supports.getTypeSupport());
        existingSupport.setAuteur(supports.getAuteur());

        switch (supports.getTypeSupport()) {
            case DOCUMENTS:
                existingSupport.setDocumentDirectory(supports.getDocumentDirectory());
                existingSupport.setVideoDirectory(null);
                existingSupport.setLinkDirectory(null);
                break;
            case VIDEO:
                existingSupport.setDocumentDirectory(null);
                existingSupport.setVideoDirectory(supports.getVideoDirectory());
                existingSupport.setLinkDirectory(null);
                break;
            case LINKS:
                existingSupport.setDocumentDirectory(null);
                existingSupport.setVideoDirectory(null);
                existingSupport.setLinkDirectory(supports.getLinkDirectory());
                break;
            default:
                throw new IllegalArgumentException("Invalid type of support");
        }

        return supportRepository.save(existingSupport);
    }




    @Override
    public void removeSupport(Long id) {
        supportRepository.deleteById(id);
    }

    @Override
    public List<Supports> getAllSupports() {
        return supportRepository.findAll();
    }

    @Override
    public Auteur createAuteur(Auteur auteur) {
        return null;
    }

    @Override
    public Auteur updateAuteur(Auteur auteur) {
        return null;
    }

    @Override
    public void removeAuteur(Long id) {

    }

    @Override
    public List<Auteur> getAllAuteur() {
        return null;
    }

    @Override
    public List<Auteur> findAuteurByName(String name) {
        return null;
    }
}
