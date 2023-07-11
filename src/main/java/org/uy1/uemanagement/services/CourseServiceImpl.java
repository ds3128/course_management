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
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final SupportRepository supportRepository;
    private final AuteurRepository auteurRepository;

    public CourseServiceImpl(CourseRepository courseRepository, SupportRepository supportRepository, AuteurRepository auteurRepository) {
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
                .nbCredit(course.getNbCredit())
                .build();
        return courseRepository.save(course);
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
        course.setNbCredit(course.getNbCredit());
        return courseRepository.save(course);
    }

    @Override
    public List<Course> saveListCourse(List<Course> courses) {
        log.info("Saving list course");

        return courses.stream().map(course -> {
            if (!courseRepository.existsById(course.getId())) {
                throw new DuplicateCourseException("Course id not exist");
            }
            return Course.builder()
                    .title(course.getTitle())
                    .description(course.getDescription())
                    .times(course.getTimes())
                    .nbCredit(course.getNbCredit())
                    .build();
        }).map(courseRepository::save).collect(Collectors.toList());
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
        Auteur auteur = new Auteur();
        if (supportRepository.existsByTitle(supports.getTitle())) {
            throw new DuplicateSupportsException("This support already exists");
        }

        Supports saveSupports = Supports.builder()
                .title(supports.getTitle())
                .typeSupport(supports.getTypeSupport())
                .auteur(supports.getAuteur())
                .build();

        switch (supports.getTypeSupport()) {
            case DOCUMENTS:
                saveSupports.setDocumentContent(supports.getDocumentContent());
                saveSupports.setLinkDirectory(null);
                saveSupports.setVideoContent(null);
                break;
            case VIDEO:
                saveSupports.setDocumentContent(null);
                saveSupports.setLinkDirectory(null);
                saveSupports.setVideoContent(supports.getVideoContent());
                break;
            case LINKS:
                saveSupports.setVideoContent(null);
                saveSupports.setDocumentContent(null);
                saveSupports.setLinkDirectory(supports.getLinkDirectory());
                break;
            default:
                throw new IllegalArgumentException("Type of support is invalid");
        }
        return supportRepository.save(saveSupports);
    }



    @Override
    public Supports updateSupport(Supports supports) throws DuplicateSupportsException {
        Supports supports1 = supportRepository.findById(supports.getId()).orElseThrow(() -> new DuplicateSupportsException("This support id those not exist"));

        supports1.setTitle(supports.getTitle());
        supports1.setTypeSupport(supports.getTypeSupport());
        supports1.setAuteur(supports.getAuteur());

        switch (supports.getTypeSupport()) {
            case DOCUMENTS:
                supports1.setDocumentContent(supports.getDocumentContent());
                supports1.setVideoContent(null);
                supports1.setLinkDirectory(null);
                break;
            case VIDEO:
                supports1.setDocumentContent(null);
                supports1.setVideoContent(supports.getVideoContent());
                supports1.setLinkDirectory(null);
                break;
            case LINKS:
                supports1.setDocumentContent(null);
                supports1.setVideoContent(null);
                supports1.setLinkDirectory(supports.getLinkDirectory());
                break;
            default:
                throw new IllegalArgumentException("Invalid type of support");
        }

        return supportRepository.save(supports1);
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
    public List<Supports> getSupportByTitle(String name) {
        return supportRepository.findByTitleContainingIgnoreCase(name);
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
        log.info("Updating auteur");
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
    public Optional<Auteur> searchAuteurById(Long id) {
        log.info("finding auteur with id : " +id);
        Optional<Auteur> byId = auteurRepository.findById(id);
        return byId;
    }

    @Override
    public List<Auteur> getAllAuteur() {
        List<Auteur> all = auteurRepository.findAll();
        return all;
    }

    @Override
    public List<Auteur> searchAuteur(String keyword) {
        List<Auteur> byNameContainingIgnoreCase = auteurRepository.searchAuteur(keyword).stream().collect(Collectors.toList());
        return byNameContainingIgnoreCase;
    }

    @Override
    public int getSupportNumber() {
        return (int) supportRepository.count();
    }

//    @Override
//    public int getUploadedFileNumber() {
//        return supportRepository.countUploadedFile();
//    }
//
//    @Override
//    public Double getSizeDocuments() {
//        return supportRepository.getTotalSize();
//    }

}
