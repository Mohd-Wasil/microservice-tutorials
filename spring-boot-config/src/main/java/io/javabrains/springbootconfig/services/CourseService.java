package io.javabrains.springbootconfig.services;

import io.javabrains.springbootconfig.model.Course;
import io.javabrains.springbootconfig.model.Topic;
import io.javabrains.springbootconfig.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;



    public List<Course> getAllCourses(String id) {
        List<Course> courses = new ArrayList<>();
       courseRepository.findByTopicId(id)
               .forEach(course -> courses.add(course));
        return courses;
    }

    public Course getCourse(String id) {
        return courseRepository.findById(id).get();
    }

    public Course addCourse(Course c) {
       courseRepository.save(c);
        return c;
    }

    public Course updateCourse(Course c) {
       courseRepository.save(c);
        return c;
    }

    public void deleteCourse(String id) {
        courseRepository.deleteById(id);

    }
}
