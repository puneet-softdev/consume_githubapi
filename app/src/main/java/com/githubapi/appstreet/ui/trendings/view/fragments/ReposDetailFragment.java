package com.githubapi.appstreet.ui.trendings.view.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.transition.TransitionInflater;

import com.githubapi.appstreet.R;
import com.githubapi.appstreet.databinding.FragDetailBinding;
import com.githubapi.appstreet.models.Repo;
import com.githubapi.appstreet.ui.BitmapBind;

public class ReposDetailFragment extends Fragment {
    public static final String KEY_ARGUMENT = "Arugment_data";
    private FragDetailBinding fragDetailBinding;

    public static ReposDetailFragment newInstance(Bundle repoBundle) {
        ReposDetailFragment fragment = new ReposDetailFragment();
        fragment.setArguments(repoBundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(
                    TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move)
            );
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragDetailBinding = DataBindingUtil.inflate(
                inflater, R.layout.frag_detail, container, false);
        View view = fragDetailBinding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments()!=null && getArguments().getParcelable(KEY_ARGUMENT)!=null){
            Repo repo = getArguments().getParcelable(KEY_ARGUMENT);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                fragDetailBinding.sharedImageRepoOwnerDetail.setTransitionName(repo.getUserName());
                fragDetailBinding.sharedTextNameDetail.setTransitionName(repo.getUserName()+"_name");
            }


            BitmapBind.bindBitmapToImage(fragDetailBinding.sharedImageRepoOwnerDetail, repo.getAvatar());
            fragDetailBinding.sharedTextNameDetail.setText(repo.getName());
            fragDetailBinding.sharedTextUsernameDetail.setText(repo.getUserName());
            fragDetailBinding.textAccountUrl.setText(repo.getRepoUrl());
            fragDetailBinding.textRepo.setText(repo.getRepo().getName());
            fragDetailBinding.textRepoDescription.setText(repo.getRepo().getDescription());
            fragDetailBinding.textRepoUrl.setText(repo.getRepo().getUrl());

            startPostponedEnterTransition();

            fragDetailBinding.textAccountUrl.setOnClickListener(v -> {
                openWebPage(fragDetailBinding.textRepoUrl.getText().toString());
            });
            fragDetailBinding.textAccountUrl.setOnClickListener (v -> {
                openWebPage(fragDetailBinding.textAccountUrl.getText().toString());
            });

        }
    }

    void openWebPage(String url) {
        if(url!=null) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }
}
