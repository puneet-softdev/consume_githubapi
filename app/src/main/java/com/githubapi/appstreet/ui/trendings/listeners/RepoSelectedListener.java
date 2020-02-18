package com.githubapi.appstreet.ui.trendings.listeners;

import com.githubapi.appstreet.models.Repo;
import com.githubapi.appstreet.ui.trendings.RepoViewHolder;

public interface RepoSelectedListener {

    void onRepoSelected(Repo repo, RepoViewHolder viewHolder);
}
