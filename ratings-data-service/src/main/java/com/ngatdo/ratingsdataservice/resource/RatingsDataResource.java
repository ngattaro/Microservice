package com.ngatdo.ratingsdataservice.resource;

import com.ngatdo.ratingsdataservice.model.Rating;
import com.ngatdo.ratingsdataservice.model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource
{
    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId)
    {
        return new Rating(movieId, 5);
    }
    @RequestMapping("users/{userId}")
    public UserRating getUsersRating(@PathVariable("userId") String userId)
    {
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 5)
        );
        UserRating userRating = new UserRating(ratings);
        return userRating;
    }
}
