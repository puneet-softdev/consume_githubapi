package com.githubapi.appstreet.ui.trendings;

import androidx.recyclerview.widget.RecyclerView;

import com.githubapi.appstreet.databinding.RowRepoBinding;
import com.githubapi.appstreet.models.Repo;
import com.githubapi.appstreet.ui.BitmapBind;

public class RepoViewHolder  extends RecyclerView.ViewHolder {

    public RowRepoBinding rowRepoBinding;

    public RepoViewHolder(RowRepoBinding rowRepoBinding) {
        super(rowRepoBinding.getRoot());
        this.rowRepoBinding = rowRepoBinding;
    }

    public void bind(Repo repo){
        rowRepoBinding.setVariable(com.githubapi.appstreet.BR.repo, repo);
        BitmapBind.bindBitmapToImage(rowRepoBinding.sharedImageRepoOwner, repo.getAvatar());
    }
}
