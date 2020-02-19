package com.githubapi.appstreet.ui.trendings.listeners;

import android.widget.ImageView;

import com.githubapi.appstreet.models.Repo;

public interface RepoSelectedListener {

    void onRepoSelected(Repo repo, ImageView imageView);
}
