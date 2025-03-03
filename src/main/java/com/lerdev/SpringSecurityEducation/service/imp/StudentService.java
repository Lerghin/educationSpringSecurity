package com.lerdev.SpringSecurityEducation.service.imp;

import com.lerdev.SpringSecurityEducation.model.Student;
import com.lerdev.SpringSecurityEducation.repository.IStudentRepository;
import com.lerdev.SpringSecurityEducation.service.IStudenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService  implements IStudenService {
    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Long id) {

        Optional<Student> student = studentRepository.findById(id);

        return  student;
    }


    @Override
    public Student save(Student student) {
        if (student.getDni() == null) {
            throw new IllegalArgumentException("DNI cannot be null");
        }
        return studentRepository.save(student);
    }

    @Override
    public void deleteById(Long id) {
      studentRepository.deleteById(id);
    }

    @Override
    public Student update(Long id, Student student) {
        Student  studentFound = this.findById(id).get();
        studentFound.setName(student.getName());
        studentFound.setDni(student.getDni());
        studentFound.setLastName(student.getLastName());
        studentFound.setCourses(student.getCourses());

        Student studentUpdate= studentRepository.save(studentFound);
        return studentUpdate;
    }
}

