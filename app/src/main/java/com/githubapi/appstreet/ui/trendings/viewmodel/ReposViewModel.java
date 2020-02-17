package com.githubapi.appstreet.ui.trendings.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.githubapi.appstreet.data.ApiResponse;
import com.githubapi.appstreet.data.GitWebService;
import com.githubapi.appstreet.repository.RepoRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ReposViewModel extends ViewModel {

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();

    public MutableLiveData<ApiResponse> loginResponse() {
        return responseLiveData;
    }

    public void hitTrendingRepoApi(String language, String since, GitWebService gitWebService){
        RepoRepository repository = new RepoRepository(gitWebService);
        disposables.add(repository.apiCall(language, since)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> responseLiveData.setValue(ApiResponse.loading()))
                .subscribe(
                        result -> responseLiveData.setValue(ApiResponse.success(result)),
                        throwable -> responseLiveData.setValue(ApiResponse.error(throwable))
                ));
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
