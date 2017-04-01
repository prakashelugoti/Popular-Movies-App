package foodzamo.user.com.retrofit.rest;

/**
 * Created by Satish on 3/11/2017.
 */


import foodzamo.user.com.retrofit.model.MovieResponseIndividual;
import foodzamo.user.com.retrofit.model.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static foodzamo.user.com.retrofit.R.id.info;


public interface ApiInterface {
    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieResponseIndividual> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
