package com.example.student_managment.service;

import com.example.student_managment.domain.Course;
import com.example.student_managment.domain.Student;
import com.example.student_managment.repository.BookRepository;
import com.example.student_managment.repository.CourseRepository;
import com.example.student_managment.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;
    private final CourseRepository courseRepository;

    public List<Student>getAllStudent(){
        return studentRepository.findAll();
    }

    public Student getStudentById(long studentId){
        return studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
    }

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }

    public void deleteStudent(long studentId){
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public Student enrollInCourse(long studentId, long courseId){
        Student student = getStudentById(studentId);
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        student.addCourse(course);

        return studentRepository.save(student);
    }
}
