package com.githubapi.appstreet.ui.trendings.view.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.githubapi.appstreet.BaseApplication;
import com.githubapi.appstreet.R;
import com.githubapi.appstreet.data.ApiResponse;
import com.githubapi.appstreet.databinding.FragmentReposBinding;
import com.githubapi.appstreet.ui.trendings.view.BaseFragment;
import com.githubapi.appstreet.ui.trendings.viewmodel.ReposViewModel;
import com.githubapi.appstreet.utils.AppUtil;

import dagger.android.AndroidInjection;

public class ReposFragment extends BaseFragment {

    ReposViewModel reposViewModel;
    ProgressDialog progressDialog;

//    @Inject
//    ReposViewModelFactory reposViewModelFactory;


    FragmentReposBinding fragmentReposBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        reposViewModel = new ViewModelProvider(this).get(ReposViewModel.class);
        reposViewModel.loginResponse().observe(getViewLifecycleOwner(), this::consumeResponse);
        fragmentReposBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_repos, container, false);
        View view = fragmentReposBinding.getRoot();
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
                // notify adapter
                break;

            case ERROR:
                progressDialog.dismiss();
                Toast.makeText(getActivity(),"something went wrong", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }
}
