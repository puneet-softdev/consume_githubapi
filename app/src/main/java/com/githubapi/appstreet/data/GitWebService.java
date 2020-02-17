package com.githubapi.appstreet.data;

import com.githubapi.appstreet.models.Repo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitWebService {

    @GET("developers")
    Single<List<Repo>> loadTrendingRepos(@Query("language") String language, @Query("since") String since);
}
