package com.githubapi.appstreet.ui.trendings.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.githubapi.appstreet.R;

public class ReposDetailFragment extends Fragment {
    public static final String KEY_ARGUMENT = "Arugment_data";

    public static ReposDetailFragment newInstance(Bundle repoBundle) {
        ReposDetailFragment fragment = new ReposDetailFragment();
        fragment.setArguments(repoBundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_detail, container, false);
    }
}
