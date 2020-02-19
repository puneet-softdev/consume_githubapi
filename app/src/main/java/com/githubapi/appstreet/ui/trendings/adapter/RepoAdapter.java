package com.githubapi.appstreet.ui.trendings.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.githubapi.appstreet.BR;
import com.githubapi.appstreet.R;
import com.githubapi.appstreet.databinding.RowRepoBinding;
import com.githubapi.appstreet.models.Repo;
import com.githubapi.appstreet.ui.BitmapBind;
import com.githubapi.appstreet.ui.trendings.RepoViewHolder;
import com.githubapi.appstreet.ui.trendings.listeners.RepoSelectedListener;
import com.githubapi.appstreet.ui.trendings.viewmodel.ReposViewModel;

import java.util.ArrayList;
import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoViewHolder> {

    private List<Repo> repos;
    private RepoSelectedListener repoSelectedListener;
    RowRepoBinding rowRepoBinding;

    public RepoAdapter(List<Repo> repos, RepoSelectedListener repoSelectedListener){
        this.repos = repos;
        this.repoSelectedListener = repoSelectedListener;
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        rowRepoBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_repo, parent, false);
        return new RepoViewHolder(rowRepoBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        Repo repo = repos.get(position);
        holder.bind(repo);
        ViewCompat.setTransitionName(holder.rowRepoBinding.sharedImageRepoOwner, repos.get(position).getUserName());
        holder.rowRepoBinding.sharedImageRepoOwner.setOnClickListener(view -> {
            repoSelectedListener.onRepoSelected(repo, holder.rowRepoBinding.sharedImageRepoOwner);
        });
    }

    @Override
    public int getItemCount() {
        return repos!=null?repos.size():0;
    }

    public void notifyData(ArrayList<Repo> list) {
        repos = new ArrayList<>();
        repos.clear();
        repos.addAll(list);
        notifyDataSetChanged();
    }
}
