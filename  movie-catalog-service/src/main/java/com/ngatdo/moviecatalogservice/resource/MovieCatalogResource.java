package com.ngatdo.moviecatalogservice.resource;

import com.ngatdo.moviecatalogservice.model.CatalogItem;
import com.ngatdo.moviecatalogservice.model.Movie;

import com.ngatdo.moviecatalogservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource
{
    @Autowired
    RestTemplate restTemplate;
    @RequestMapping("/")
    public  String welcome()
    {
        return "WELCOME TO CATALOG SERVICE";
    }
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId)
    {

        //get all rated movieID

       UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);

        //for each moviedId, call movie-infor-service and get details(name)
        return userRating.getUserRating().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId() , Movie.class);
            return new CatalogItem(movie.getName(),"desc",rating.getRating());
        }).collect(Collectors.toList());

    }
}
