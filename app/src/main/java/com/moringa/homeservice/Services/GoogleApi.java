package com.moringa.homeservice.Services;

import com.moringa.homeservice.models.GItems;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleApi {
    @GET("customsearch/v1")
    Call<GItems>getResults(
            @Query("q") String query
    );
}
