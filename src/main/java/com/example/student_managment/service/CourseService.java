package com.example.student_managment.service;

import com.example.student_managment.domain.Course;
import com.example.student_managment.domain.Student;
import com.example.student_managment.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public List<Course>getAllCourse(){
        return courseRepository.findAll();
    }

    public Course getCourseById(long courseId){
        return courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
    }

    public Course createCourse(Course course){
        return courseRepository.save(course);
    }

    @Transactional
    public void deleteCourse(long courseId){
        Course course = getCourseById(courseId);

        for (Student student : course.getStudents()){
            student.removeCourse(course);
        }

        course.getStudents().clear();

        courseRepository.delete(course);
    }

    public Set<Student> getStudentsInCourse(long courseId){
        return getCourseById(courseId).getStudents();
    }
}
