package io.javabrains.springbootconfig.resources;

import io.javabrains.springbootconfig.model.Topic;
import io.javabrains.springbootconfig.services.TopicService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class TopicController {

    @Autowired
    private TopicService topicService;

    @RequestMapping("/topics")
    @ApiOperation(value = "find topics all",
    notes = "uses a GET operation , need oauth token",
    response = Topic.class)
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

    @RequestMapping("/topics/{id}")
    public Topic getTopic(@PathVariable("id") String id) {
        return topicService.getTopic(id);
    }

    @RequestMapping(value = "/topics", method = RequestMethod.POST)
    public Topic addTopic(@RequestBody Topic topic){
        return topicService.addTopic(topic);
    }

    @RequestMapping(value = "/topics/{id}", method = RequestMethod.PUT)
    public Topic updateTopic(@RequestBody Topic topic, @PathVariable String id){
        return topicService.updateTopic(topic, id);
    }

    @RequestMapping(value = "/topics/{id}", method = RequestMethod.DELETE)
    public void deleteTopic(@PathVariable String id){
         topicService.deleteTopic(id);
    }
}
