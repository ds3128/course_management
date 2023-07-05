package org.uy1.uemanagement.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uy1.uemanagement.entities.Auteur;
import org.uy1.uemanagement.entities.Course;
import org.uy1.uemanagement.entities.Supports;
import org.uy1.uemanagement.execptions.*;
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
        course = Course.builder()
                .title(course.getTitle())
                .description(course.getDescription())
                .times(course.getTimes())
                .supports(course.getSupports())
                .auteur(course.getAuteur())
                .build();
        Course savedCourse = courseRepository.save(course);
        return savedCourse;
    }


    @Override
    public Course updateCourse(Course course) throws DuplicateCourseException {
        log.info("Updating course");
        if (!courseRepository.existsById(course.getId())){
            throw new DuplicateCourseException("Course id not exist");
        }
        course.setTitle(course.getTitle());
        course.setDescription(course.getDescription());
        course.setTimes(course.getTimes());
        course.setSupports(course.getSupports());
        course.setAuteur(course.getAuteur());
        Course updatedCourse = courseRepository.save(course);
        return updatedCourse;
    }

    @Override
    public void removeCourse(Long id) {
        courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("This course id those not exist"));
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }


    @Override
    public List<Course> findCourseByTitle(String name) {
        return courseRepository.findByTitleContainingIgnoreCase(name);
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
        supportRepository.findById(supports.getId()).orElseThrow(() -> new DuplicateSupportsException("This support id those not exist"));

        supports.setTitle(supports.getTitle());
        supports.setTypeSupport(supports.getTypeSupport());
        supports.setAuteur(supports.getAuteur());

        switch (supports.getTypeSupport()) {
            case DOCUMENTS:
                supports.setDocumentDirectory(supports.getDocumentDirectory());
                supports.setVideoDirectory(null);
                supports.setLinkDirectory(null);
                break;
            case VIDEO:
                supports.setDocumentDirectory(null);
                supports.setVideoDirectory(supports.getVideoDirectory());
                supports.setLinkDirectory(null);
                break;
            case LINKS:
                supports.setDocumentDirectory(null);
                supports.setVideoDirectory(null);
                supports.setLinkDirectory(supports.getLinkDirectory());
                break;
            default:
                throw new IllegalArgumentException("Invalid type of support");
        }

        return supportRepository.save(supports);
    }




    @Override
    public void removeSupport(Long id) {
        log.info("deleting support");
        supportRepository.findById(id).orElseThrow(() -> new SupportNotFoundException("Support id not exist"));
        supportRepository.deleteById(id);
    }

    @Override
    public List<Supports> getAllSupports() {
        List<Supports> all = supportRepository.findAll();
        return all;
    }

    @Override
    public Auteur createAuteur(Auteur auteur) throws DuplicateAuteurException, DuplicatePhoneNumberException {
        log.info("Saving auteur");
        if (auteurRepository.existsByEmail(auteur.getEmail())){
            throw new DuplicateAuteurException("The auteur already exist");
        }
        if (auteurRepository.existsByTel(auteur.getTel())){
            throw new DuplicatePhoneNumberException("This phone number already exist");
        }
        auteur = Auteur.builder()
                .firstName(auteur.getFirstName())
                .lastName(auteur.getLastName())
                .email(auteur.getEmail())
                .tel(auteur.getTel())
                .grade(auteur.getGrade())
                .build();
        Auteur savedAuteur = auteurRepository.save(auteur);
        return savedAuteur;
    }

    @Override
    public Auteur updateAuteur(Auteur auteur) {
        auteurRepository.findById(auteur.getId()).orElseThrow(() -> new NotFoundAuteurException("This auteur those not exist"));
        auteur.setFirstName(auteur.getFirstName());
        auteur.setLastName(auteur.getLastName());
        auteur.setEmail(auteur.getEmail());
        auteur.setTel(auteur.getTel());
        auteur.setGrade(auteur.getGrade());
        Auteur updatedAuteur = auteurRepository.save(auteur);
        return updatedAuteur;
    }

    @Override
    public void removeAuteur(Long id) {
        log.info("Deleting auteur");
        auteurRepository.findById(id).orElseThrow(() -> new NotFoundAuteurException("Auteur id those not exist"));
        auteurRepository.deleteById(id);
    }

    @Override
    public List<Auteur> getAllAuteur() {
        List<Auteur> all = auteurRepository.findAll();
        return all;
    }

    @Override
    public List<Auteur> findAuteurByName(String name) {
        return auteurRepository.findByNameContainingIgnoreCase(name);
    }

}
