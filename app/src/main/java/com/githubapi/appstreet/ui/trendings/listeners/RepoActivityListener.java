package com.githubapi.appstreet.ui.trendings.listeners;

import android.widget.ImageView;

import com.githubapi.appstreet.models.Repo;

public interface RepoActivityListener {

    void onRepoActivity(Repo repo, ImageView imageView);
}
