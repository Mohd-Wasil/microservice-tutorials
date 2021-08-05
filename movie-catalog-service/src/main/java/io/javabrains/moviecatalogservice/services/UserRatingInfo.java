package io.javabrains.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.javabrains.moviecatalogservice.model.Rating;
import io.javabrains.moviecatalogservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserRatingInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallBackUserRating",
            threadPoolKey = "movieInfoPool",
            threadPoolProperties = {
                @HystrixProperty(name = "coreSize", value = "20"), //max_concurrency parallel thread allowed waiting for response from API
                @HystrixProperty(name = "maxQueueSize", value = "10")//request waiting in queue before beng part of above thread pool
            }

    )
    public List<Rating> getUserRating(String userId) {

        final UserRating userRating = restTemplate.getForObject("http://rating-service/ratingsdata/users/" + userId, UserRating.class);
        return userRating.getUserRatings();
    }
    private List<Rating> getFallBackUserRating(String userId) {
        return  Arrays.asList(new Rating("100",3));
    }
}
