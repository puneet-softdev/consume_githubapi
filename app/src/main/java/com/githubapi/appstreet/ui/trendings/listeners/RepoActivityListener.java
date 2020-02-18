package com.githubapi.appstreet.ui.trendings.listeners;

import androidx.recyclerview.widget.RecyclerView;

import com.githubapi.appstreet.models.Repo;
import com.githubapi.appstreet.ui.trendings.RepoViewHolder;

public interface RepoActivityListener {

    void onRepoActivity(Repo repo, RepoViewHolder viewHolder);
}
