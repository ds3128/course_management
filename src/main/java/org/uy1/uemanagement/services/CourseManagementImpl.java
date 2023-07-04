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
import java.util.stream.Collectors;

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
        if (courseRepository.existsByTitle()){
            throw new DuplicateCourseException("This course already exist");
        }
        Course saveCourse = Course.builder()
                .title(course.getTitle())
                .description(course.getDescription())
                .times(course.getTimes())
                .supports(course.getSupports())
                .auteur(course.getAuteur())
                .build();
        Course save = courseRepository.save(saveCourse);
        return save;
    }

    @Override
    public Course updateCourse(Course course, Long id) throws DuplicateCourseException {
        log.info("Updating course");
        if (courseRepository.existsById(id)){
            throw new DuplicateCourseException("Course id already exist");
        }
        course.setTitle(course.getTitle());
        course.setDescription(course.getDescription());
        course.setTimes(course.getTimes());
        course.setSupports(course.getSupports());
        course.setAuteur(course.getAuteur());
        Course save = courseRepository.save(course);
        return save;
    }

    @Override
    public void removeCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getAllCourse() {
        List<Course> allCourse = courseRepository.findAll().stream().collect(Collectors.toList());
        return allCourse;
    }

    @Override
    public List<Course> findCourseByTitle(String name) {

        return null;
    }

    @Override
    public Supports createSupports(Supports supports) throws DuplicateSupportsException {
        if (supportRepository.existsByTitle()){
            throw new DuplicateSupportsException("This supports already exist");
        }
        Supports saveSupports = Supports.builder()
                .title(supports.getTitle())
                .typeSupport(supports.getTypeSupport())
                .auteur(supports.getAuteur())
                .build();
//        Supports saveSupports = new Supports();
//        saveSupports.getTitle();
//        saveSupports.getTypeSupport();
//        saveSupports.getAuteur();
        return supportRepository.save(saveSupports);
    }

    @Override
    public Supports updateSupport(Supports supports, Long id) throws DuplicateSupportsException {
        if (supportRepository.existsById(id)){
            throw new DuplicateSupportsException("This Support id already exist");
        }
        supports.setTitle(supports.getTitle());
        supports.setTypeSupport(supports.getTypeSupport());
        supports.setAuteur(supports.getAuteur());
        return supportRepository.save(supports);
    }

    @Override
    public void removeSupport(Long id) {

    }

    @Override
    public List<Supports> getAllSupports() {

        return null;
    }

    @Override
    public Auteur createAuteur(Auteur auteur) {
        return null;
    }

    @Override
    public Auteur updateCourse(Auteur auteur, Long id) {
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
