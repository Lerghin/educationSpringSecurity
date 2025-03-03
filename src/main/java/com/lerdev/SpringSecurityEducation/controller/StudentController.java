package com.lerdev.SpringSecurityEducation.controller;

import com.lerdev.SpringSecurityEducation.model.Student;
import com.lerdev.SpringSecurityEducation.service.IStudenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {

    @Autowired
    private IStudenService studenService;


    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT') or hasRole('ROLE_PROFESSOR')")
    public List<Student> findAll(){
        return studenService.findAll();
    }
    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Student createStudent(@RequestBody Student student){
        return studenService.save(student);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT') or hasRole('ROLE_PROFESSOR')")
    public Student findById(Long id){
        return studenService.findById(id).get();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(Long id){
        studenService.deleteById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Student update(@PathVariable Long id, @RequestBody Student student){
        return studenService.update(id, student);
    }


}
