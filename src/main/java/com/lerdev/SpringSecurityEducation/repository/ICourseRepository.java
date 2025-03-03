package com.lerdev.SpringSecurityEducation.repository;


import com.lerdev.SpringSecurityEducation.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Long> {
}
