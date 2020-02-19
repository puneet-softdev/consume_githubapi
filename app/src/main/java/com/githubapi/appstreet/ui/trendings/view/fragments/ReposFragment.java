package com.githubapi.appstreet.ui.trendings.view.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.githubapi.appstreet.BaseApplication;
import com.githubapi.appstreet.R;
import com.githubapi.appstreet.data.ApiResponse;
import com.githubapi.appstreet.databinding.FragmentReposBinding;
import com.githubapi.appstreet.models.Repo;
import com.githubapi.appstreet.ui.trendings.RepoViewHolder;
import com.githubapi.appstreet.ui.trendings.adapter.RepoAdapter;
import com.githubapi.appstreet.ui.trendings.listeners.RepoActivityListener;
import com.githubapi.appstreet.ui.trendings.listeners.RepoSelectedListener;
import com.githubapi.appstreet.ui.trendings.view.BaseFragment;
import com.githubapi.appstreet.ui.trendings.viewmodel.ReposViewModel;
import com.githubapi.appstreet.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import dagger.android.AndroidInjection;

public class ReposFragment extends BaseFragment implements RepoSelectedListener {

    ReposViewModel reposViewModel;
    ProgressDialog progressDialog;
    List<Repo> reposList;

    FragmentReposBinding fragmentReposBinding;
    RepoAdapter repoAdapter;

    RepoActivityListener repoActivityListener;

    public ReposFragment(RepoActivityListener repoActivityListener){
        this.repoActivityListener = repoActivityListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        reposViewModel = new ViewModelProvider(this).get(ReposViewModel.class);
        reposViewModel.loginResponse().observe(getViewLifecycleOwner(), this::consumeResponse);
        fragmentReposBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_repos, container, false);
        View view = fragmentReposBinding.getRoot();

        repoAdapter = new RepoAdapter(reposList, this);
        fragmentReposBinding.recyclerRepos.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL));
        fragmentReposBinding.recyclerRepos.setAdapter(repoAdapter);
        fragmentReposBinding.recyclerRepos.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressDialog = AppUtil.getProgressDialog(getContext(), "Please wait...");

        reposViewModel.hitTrendingRepoApi(fragmentReposBinding.selectorLang.getSelectedItem().toString(), fragmentReposBinding.selectorSince.getSelectedItem().toString(), BaseApplication.getInstance().gitWebService);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidInjection.inject(getActivity());
        super.onAttach(context);
    }

    private void consumeResponse(ApiResponse apiResponse) {
        switch (apiResponse.status) {

            case LOADING:
                progressDialog.show();
                break;

            case SUCCESS:
                progressDialog.dismiss();
                ((RepoAdapter)fragmentReposBinding.recyclerRepos.getAdapter()).notifyData((ArrayList<Repo>) apiResponse.data);
                break;

            case ERROR:
                progressDialog.dismiss();
                Toast.makeText(getActivity(),"something went wrong", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }

    @Override
    public void onRepoSelected(Repo repo, ImageView imageView) {
        repoActivityListener.onRepoActivity(repo, imageView);
    }

}
