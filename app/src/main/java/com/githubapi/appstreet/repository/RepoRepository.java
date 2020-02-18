package com.githubapi.appstreet.repository;

import android.util.Log;

import com.githubapi.appstreet.BaseApplication;
import com.githubapi.appstreet.data.GitWebService;
import com.githubapi.appstreet.models.Repo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class RepoRepository {

    private final String taskKey = "trendingGit";
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
        if (BaseApplication.getInstance().cacheProvider.isDataAvailable(taskKey)){
            return cacheCall();
        } else {
            return networkCall(language, since);
        }
    }

    private Single<List<Repo>> networkCall(String language, String since){
        Single<List<Repo>> call = BaseApplication.getInstance().gitWebService.loadTrendingRepos(language, since)
                .subscribeOn(Schedulers.io());

        call.subscribe(repos -> {
            BaseApplication.getInstance().cacheProvider.add(taskKey, repos);
        }, error->{
            Log.e(taskKey, error.getMessage());
        });
        return call;
    }

    private Single<List<Repo>> cacheCall(){
        Object value = BaseApplication.getInstance().cacheProvider.get(taskKey);
        if(value instanceof List){
            return Single.just((List<Repo>)value);
        }
        return Single.error(new Throwable("No Data Found"));
    }
}
