package io.javabrains.moviecatalogservice.resource;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.javabrains.moviecatalogservice.model.CatalogItem;
import io.javabrains.moviecatalogservice.model.Movie;
import io.javabrains.moviecatalogservice.model.Rating;
import io.javabrains.moviecatalogservice.model.UserRating;
import io.javabrains.moviecatalogservice.services.MovieInfo;
import io.javabrains.moviecatalogservice.services.UserRatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

  /*  @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        //not good everytime a new request a new  resttemplate is created
        RestTemplate restTemplate = new RestTemplate();
        //get all rated movie id for a given user Id
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 5),
                new Rating("5678", 2)
        );

        // for each movie Id , call movie Info service and get details
        return ratings.stream().map(rating ->
        {
            //not good hardcoded service url
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(), "Sci Fci", rating.getRating());
        })
                .collect(Collectors.toList());
        // put them all together
       *//* return Collections.singletonList(
                new CatalogItem("Transformers", "scifi movie", 4)
        );*//*
    }*/

    @Autowired
    //to be deprecated //restTemplate is threadSafe ,
    // extracted to moviInfoservice and userrating server
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder; //new ways of invking rest api CALL

    @Autowired //to check explicit discovery instances host port and do sonething
    private DiscoveryClient discoveryClient;

    @Autowired
    private MovieInfo movieInfo;

    @Autowired
    private UserRatingInfo userRatingInfo;


    //using webclient builder

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        //get list of all rated movie id for a given user Id
        final List<Rating> ratings = userRatingInfo.getUserRating(userId);

        // for each rating's movie Id , call movie Info service and get details
        return ratings.stream()
                .map(rating -> movieInfo.getCatalogItem(rating))
                .collect(Collectors.toList());

    }

}


