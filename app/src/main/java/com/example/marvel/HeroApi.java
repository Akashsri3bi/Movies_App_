package com.example.marvel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HeroApi {

    public String BaseUrl = "https://api.themoviedb.org/3/";

    @GET("movie/popular")
    Call<Hero> getHeroes(@Query("api_key")String ApiKey) ;

    @GET("movie/top_rated")
    Call<Hero> getTop(@Query("api_key")String ApiKey);

    @GET("search/movie?")
    Call<Hero> getquery(@Query("api_key") String ApiKey , @Query("query") String search) ;
    
}
