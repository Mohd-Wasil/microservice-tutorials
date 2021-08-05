package io.javabrains.ratingsdataservice.resource;

import io.javabrains.ratingsdataservice.model.Rating;
import io.javabrains.ratingsdataservice.model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingDataResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return  new Rating(movieId, 4);
    }

    @RequestMapping("users/{userId}")
    public UserRating getRatings(@PathVariable("userId") String userId) {
    //not recommended to returna list just have a wrapper object and then put a list
       List<Rating> ratings =   Arrays.asList(
                new Rating("100", 5),
                new Rating("200", 3),
               new Rating("300", 4),
               new Rating("400", 3)
        );
       return new UserRating(ratings);
    }
}
