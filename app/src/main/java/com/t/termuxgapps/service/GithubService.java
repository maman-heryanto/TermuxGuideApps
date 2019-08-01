package com.t.termuxgapps.service;

import com.t.termuxgapps.model.GithubUser;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {

    //retrofit annontation
    @GET("/users")
    public Call<List<GithubUser>> getUsers(@Query("per_page") int perPage, @Query("page") int page);

    @GET("/users/{username}")
    Call<List<GithubUser>> getUser(@Path("username") String username);

    @GET("/users/{user}/repos")
    Call<List<GithubUser>> reposForuser(@Path("user") String user);

}
