package com.ngatdo.movieinfoservice.resource;

import com.ngatdo.movieinfoservice.model.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResource
{
    @RequestMapping("/{movieId}")
    public Movie getMovieInfor(@PathVariable("movieId") String movieId)
    {
        return new Movie(movieId,"Test name");
    }
}
