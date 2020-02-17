package com.githubapi.appstreet.ui.inject;

import com.githubapi.appstreet.repository.RepoRepository;
import com.githubapi.appstreet.ui.MainActivity;
import com.githubapi.appstreet.ui.trendings.view.fragments.ReposFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Module
public abstract class ContributorsModule {
    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract ReposFragment bindReposFragment();
}
