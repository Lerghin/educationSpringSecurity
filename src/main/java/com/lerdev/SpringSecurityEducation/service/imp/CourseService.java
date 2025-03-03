package com.lerdev.SpringSecurityEducation.service.imp;

import com.lerdev.SpringSecurityEducation.model.Course;
import com.lerdev.SpringSecurityEducation.model.Professor;
import com.lerdev.SpringSecurityEducation.repository.ICourseRepository;
import com.lerdev.SpringSecurityEducation.repository.IProfessorRepository;
import com.lerdev.SpringSecurityEducation.service.ICourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private IProfessorRepository professorRepository;
    @Override
    public List<Course> findAll() {


        return  courseRepository.findAll();
    }

    @Override
    public Optional<Course> findById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return course;
    }

    @Override
    public Course save(Course course) {
        // Ensure the professor is managed by the current session
        Professor professor = course.getProfessor();
        if (professor != null) {
            professor = professorRepository.findById(professor.getId())
                    .orElseThrow(() -> new RuntimeException("Professor not found"));
            course.setProfessor(professor);
        }
        return courseRepository.save(course);
    }

    @Override
    public void deleteById(Long id) {
   courseRepository.deleteById(id);
    }

    @Override
    public Course update(Long id, Course course) {
        Course courseFind= this.findById(id).get();
        courseFind.setName(course.getName());
        courseFind.setDescription(course.getDescription());
        courseFind.setProfessor(course.getProfessor());
        courseFind.setStudents(course.getStudents());
        Course courseUpdate= courseRepository.save(courseFind);
        return  courseUpdate;
    }
}
