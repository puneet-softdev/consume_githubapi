package com.githubapi.appstreet.repository;

import com.githubapi.appstreet.data.GitWebService;
import com.githubapi.appstreet.models.Repo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class RepoRepository {

    private GitWebService gitWebService;

    public RepoRepository(GitWebService gitWebService){
        this.gitWebService = gitWebService;
    }

    /*
     * method to call github api
     * @param language
     * @param since
     * @return List<Repo>
     * */

    public Single<List<Repo>> apiCall(String language, String since) {
        return gitWebService.loadTrendingRepos(language, since);
    }

}
