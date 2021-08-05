package io.javabrains.movieinfoservice.resource;

import io.javabrains.movieinfoservice.model.Movie;
import io.javabrains.movieinfoservice.model.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.key}")
    private String apiKey;

    @RequestMapping("{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        //return new Movie(movieId ,"Transformers");
        MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" +
                movieId + "?api_key=" + apiKey, MovieSummary.class);
        return new Movie(movieSummary.getId(), movieSummary.getTitle(), movieSummary.getOverview());

    }

}
