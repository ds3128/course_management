package org.uy1.uemanagement.web;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uy1.uemanagement.entities.Auteur;
import org.uy1.uemanagement.entities.Course;
import org.uy1.uemanagement.entities.Supports;
import org.uy1.uemanagement.services.CourseService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class CourseRestController {
    private CourseService courseService;

    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Controller for all Course
    @PostMapping("/course/newCourse")
    @ResponseStatus(HttpStatus.CREATED)
    public Course createCourse(@RequestBody Course course){
        return courseService.createCourse(course);
    }

    @PutMapping("/course/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Course updateCourse(@RequestBody Course course, @PathVariable("id") Long courseId){
        course.setId(courseId);
        return courseService.updateCourse(course);
    }

    @DeleteMapping("/course/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCourse(@PathVariable("id") Long courseId){
        courseService.removeCourse(courseId);
    }
    @GetMapping("/course/allCourse")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Course> getAllCourse(){
        return courseService.getAllCourse();
    }

    @GetMapping("/course/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Course> getCourseByTitle(@RequestParam(name = "keyword") String courseTitle){
        return courseService.findCourseByTitle(courseTitle);
    }

    // Controller for all Support
    @PostMapping("/support/newSupport")
    @ResponseStatus(HttpStatus.CREATED)
    public Supports createSupport(@RequestBody Supports supports){
        return courseService.createSupports(supports);
    }

    @PutMapping("/support/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Supports updateSupport(@PathVariable Long id, @RequestBody Supports supports){
        supports.setId(id);
        return courseService.updateSupport(supports);
    }

    @DeleteMapping("/support/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteSupport(@PathVariable("id") Long supportId){
        courseService.removeSupport(supportId);
    }

    @GetMapping("/support")
    @ResponseStatus(HttpStatus.OK)
    public List<Supports> getAllSupport(){
        return courseService.getAllSupports();
    }
    @GetMapping("/support/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Supports> getSupportByTitle(@RequestParam("keyword") String supportTitle){
        return courseService.getSupportByTitle(supportTitle);
    }

    // Controller for all Auteur
    @PostMapping("/auteur/newAuteur")
    @ResponseStatus(HttpStatus.CREATED)
    public Auteur addNewAuteur(@RequestBody Auteur auteur){
        return courseService.createAuteur(auteur);
    }

    @PutMapping("/auteur/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Auteur updateAuteur(@PathVariable Long id, Auteur auteur){
        auteur.setId(id);
        return courseService.updateAuteur(auteur);
    }

    @GetMapping("/auteur/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Auteur> searchAuteurById(@PathVariable Long id) {
        Optional<Auteur> auteurOptional = courseService.searchAuteurById(id);
        if (auteurOptional.isPresent()) {
            return ResponseEntity.ok(auteurOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/auteur/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAuteur(@PathVariable("id") Long auteurId){
        courseService.removeAuteur(auteurId);
    }

    @GetMapping("/auteur")
    @ResponseStatus(HttpStatus.OK)
    public List<Auteur> getAllAuteur(){
        return courseService.getAllAuteur();
    }

    @GetMapping("/auteur/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Auteur> searchAuteur(@RequestParam(name = "keyword", defaultValue = "") String keyword){
        List<Auteur> auteurs = courseService.searchAuteur("%"+keyword+"%");
        return auteurs;
    }

    //Statistics about course and supports
    @GetMapping("/supports_number")
    @ResponseStatus(HttpStatus.OK)
    public Integer getSupportsNumber() {
        int supportNumber = courseService.getSupportNumber();
        return supportNumber;
    }

//    @GetMapping("/uploaded_file_number")
//    @ResponseStatus(HttpStatus.OK)
//    public Integer getUploadedFileNumber() {
//        int uploadedFileNumber = courseService.getUploadedFileNumber();
//        return uploadedFileNumber;
//    }
//
//    @GetMapping("/documents_size")
//    @ResponseStatus(HttpStatus.OK)
//    public Double getSizeDocument() {
//        double documentsSize = courseService.getSizeDocuments();
//        return documentsSize;
//    }
}
