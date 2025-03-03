package com.lerdev.SpringSecurityEducation.controller;

import com.lerdev.SpringSecurityEducation.model.Professor;
import com.lerdev.SpringSecurityEducation.service.IProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/professor")
public class ProfessorController {

    @Autowired
    private IProfessorService professorService;

  @GetMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')  or hasRole('ROLE_PROFESSOR')")
    public List<Professor> findAll(){
        return professorService.findAll();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PROFESSOR')")
    public Professor findById(Long id){
        return professorService.findById(id).get();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(@PathVariable Long id){
        professorService.deleteById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Professor update(@PathVariable Long id, @RequestBody Professor professor){
        return professorService.update(id, professor);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Professor createProfessor(@RequestBody Professor professor){
        return professorService.save(professor);
    }

}
