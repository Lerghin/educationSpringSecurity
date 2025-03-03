package com.lerdev.SpringSecurityEducation.service;



import com.lerdev.SpringSecurityEducation.model.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
    List<Course> findAll();
    Optional<Course> findById(Long id);
    Course save(Course course);
    void deleteById(Long id);
    Course update(Long id, Course course);
}
