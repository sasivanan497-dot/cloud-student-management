package com.sasi.student_management.controller;

import com.sasi.student_management.entity.Student;
import com.sasi.student_management.repository.StudentRepository;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // GET - All students
    @GetMapping
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    // POST - Add student
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // PUT - Update student
    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Long id,
            @RequestBody Student newStudent) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(newStudent.getName());
        student.setEmail(newStudent.getEmail());

        return studentRepository.save(student);
    }

    // DELETE - Delete student
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {

        studentRepository.deleteById(id);

        return "Student deleted successfully";
    }
}
