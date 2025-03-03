package com.lerdev.SpringSecurityEducation.controller;

import com.lerdev.SpringSecurityEducation.model.Course;
import com.lerdev.SpringSecurityEducation.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;


    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT') or hasRole('ROLE_PROFESSOR')")

    public List<Course> findAll(){
        return courseService.findAll();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN'), hasRole('ROLE_PROFESSOR'), hasRole('ROLE_STUDENT')")
    public Course findById(Long id){
        return courseService.findById(id).get();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(Long id){
        courseService.deleteById(id);
    }


    @PutMapping("/{id}/{dni}")
    @PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_PROFESSOR')")

    public Course update(@PathVariable Long id, @RequestBody Course course,@PathVariable Integer dni){
        if(!isCourseOwner(id, dni)){
            throw new RuntimeException("You are not the owner of this course");
        }

        return courseService.update(id, course);
    }

    private Boolean isCourseOwner(Long courseId, Integer dni) {
        Course course = courseService.findById(courseId).orElse(null);
        if (course == null) {
            System.out.println("Course with ID " + courseId + " not found.");
            return false;
        }
        Integer professorDni = course.getProfessor().getDni();
        System.out.println("Course Professor DNI: " + professorDni);
        System.out.println("Provided DNI: " + dni);
        if (professorDni == null || !professorDni.equals(dni)) {
            System.out.println("DNI mismatch.");
            return false;
        }
        System.out.println("DNI match.");
        return true;
    }


    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Course createCourse(@RequestBody Course course){
        return courseService.save(course);
    }









}
