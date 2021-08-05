package io.javabrains.springbootconfig.resources;

import io.javabrains.springbootconfig.model.Course;
import io.javabrains.springbootconfig.model.Topic;
import io.javabrains.springbootconfig.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/{topicId}/courses")
    public List<Course> getAllCourses(@PathVariable String topicId) {
        return courseService.getAllCourses(topicId);
    }

    @RequestMapping("/{topicId}/courses/{courseId}")
    public Course getCourse(@PathVariable("topicId") String topicId, @PathVariable String courseId) {
        return courseService.getCourse(courseId);
    }

    @RequestMapping(value = "/{topicId}/courses", method = RequestMethod.POST)
    public Course addCourse(@RequestBody Course course, @PathVariable String topicId){
        course.setTopic(new Topic(topicId, "", ""));
        return courseService.addCourse(course);
    }

    @RequestMapping(value = "/{topicId}/courses/{courseId}", method = RequestMethod.PUT)
    public Course updateCourse(@RequestBody Course course, @PathVariable String courseId, @PathVariable String topicId){
        course.setTopic(new Topic(topicId, "", ""));
        return courseService.updateCourse(course);
    }

    @RequestMapping(value = "/{topicId}/courses/{courseId}", method = RequestMethod.DELETE)
    public void deleteCourse(@PathVariable String id){
        courseService.deleteCourse(id);
    }
}
