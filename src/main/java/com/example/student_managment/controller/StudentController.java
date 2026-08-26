package com.example.student_managment.controller;

import com.example.student_managment.domain.Student;
import com.example.student_managment.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // კურსზე სტუდენტის მიბმა (Enroll)
    @PostMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<Student> enrollInCourse(
            @PathVariable long studentId,
            @PathVariable long courseId) {
        Student updatedStudent = studentService.enrollInCourse(studentId, courseId);
        return ResponseEntity.ok(updatedStudent);
    }
}
