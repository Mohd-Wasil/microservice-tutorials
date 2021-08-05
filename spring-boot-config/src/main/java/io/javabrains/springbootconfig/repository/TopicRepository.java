package io.javabrains.springbootconfig.repository;

import io.javabrains.springbootconfig.model.Topic;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, String> {

    //getALLtopc
    //getTopicById
    //updateTopic
    //deleteTopic
}
