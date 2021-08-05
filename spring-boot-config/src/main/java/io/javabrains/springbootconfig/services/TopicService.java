package io.javabrains.springbootconfig.services;

import io.javabrains.springbootconfig.aspects.TrackExecutionTime;
import io.javabrains.springbootconfig.model.Topic;
import io.javabrains.springbootconfig.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

   /* private List<Topic> topics = new ArrayList<>(Arrays.asList(new Topic("java", "Core Java", " this is java course"),
            new Topic("javascript", "Type Script", " this is ts course"),
            new Topic("kafka", "Kafka Publisher", " this is kafka Pub course"),
            new Topic("spring", "Spring framework", " this is Spring core course")));*/


    @TrackExecutionTime
    public List<Topic> getAllTopics() {
        //long startTime = System.currentTimeMillis();
        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll() //returns iterable
                .forEach(topic-> topics.add(topic));
//                .forEach(topics :: add); //method reference
        //long endTime = System.currentTimeMillis();
        //System.out.println("Time took for method execution");
        //System.out.println(  endTime-startTime );
        return topics;
    }


    public Topic getTopic(String id) {

        /* topics.stream().
                filter(topic -> topic.getId().equals(id))
                .findFirst().get();*/
        return topicRepository.findById(id).get();
    }

    @TrackExecutionTime
    public Topic addTopic(Topic topic) {
        List<Topic> topics = new ArrayList<>();
        for(int i=0;i<1000; i++){
            Topic topic1 = new Topic();
            topic1.setDescription("custom");
            topic1.setId("id_"+i);
            topic1.setName(topic.getName() + Math.random());
            topicRepository.save(topic1);
            topics.add(topic);
        }
       //


        return topic;
    }

    public Topic updateTopic(Topic topic, String id) {
       topicRepository.save(topic);
        return topic;
    }

    public void deleteTopic(String id) {
     //   topics.removeIf(topic -> topic.getId().equals(id));
        topicRepository.deleteById(id);

    }
}
