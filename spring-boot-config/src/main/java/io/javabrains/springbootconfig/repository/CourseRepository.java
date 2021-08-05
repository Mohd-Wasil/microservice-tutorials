package io.javabrains.springbootconfig.repository;

import io.javabrains.springbootconfig.model.Course;
import io.javabrains.springbootconfig.model.Topic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, String> {
//not recommended .. shoul start with find...
    //public List<Course> getCoursesByTopic(String topicId);

    public List<Course> findByName(String name);

    public List<Course> findByTopicId(String topicId);
}

