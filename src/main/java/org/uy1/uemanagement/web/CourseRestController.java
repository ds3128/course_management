package org.uy1.uemanagement.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.uy1.uemanagement.entities.Course;
import org.uy1.uemanagement.entities.Supports;
import org.uy1.uemanagement.services.CourseService;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CourseRestController {
    private CourseService courseService;

    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/course/newCourse")
    @ResponseStatus(HttpStatus.CREATED)
    public Course createCourse(@RequestBody Course course){
        return courseService.createCourse(course);
    }

    @PutMapping("/course/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Course updateCourse(@RequestBody Course course, @PathVariable("id") Long courseId){
        course.setId(courseId);
        return courseService.updateCourse(course);
    }

    @DeleteMapping("/course/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteCourse(@PathVariable("id") Long courseId){
        courseService.removeCourse(courseId);
    }

    @GetMapping("/course")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Course> getAllCourse(){
        return courseService.getAllCourse();
    }

    @GetMapping("/course/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Course> getCourseByTitle(@RequestParam("courseTitle") String courseTitle){
        return courseService.findCourseByTitle(courseTitle);
    }

    @PostMapping("/support/newSupport")
    @ResponseStatus(HttpStatus.OK)
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
}
