package com.githubapi.appstreet.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionInflater;

import android.os.Bundle;

import com.githubapi.appstreet.R;
import com.githubapi.appstreet.base.BaseActivity;
import com.githubapi.appstreet.databinding.ActivityMainBinding;
import com.githubapi.appstreet.models.Repo;
import com.githubapi.appstreet.ui.trendings.RepoViewHolder;
import com.githubapi.appstreet.ui.trendings.listeners.RepoActivityListener;
import com.githubapi.appstreet.ui.trendings.view.fragments.ReposDetailFragment;
import com.githubapi.appstreet.ui.trendings.view.fragments.ReposFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity implements HasSupportFragmentInjector, RepoActivityListener {

    ActivityMainBinding activityMainBinding;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(activityMainBinding.toolbar);
        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(activityMainBinding.container.getId(), new ReposFragment(this)).commit();

    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().popBackStack();
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void onRepoActivity(Repo repo, RepoViewHolder viewHolder) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ReposDetailFragment.KEY_ARGUMENT, repo);

        getSupportFragmentManager().beginTransaction()
                .add(activityMainBinding.container.getId(), ReposDetailFragment.newInstance(bundle)).addToBackStack(null).addSharedElement(viewHolder.rowRepoBinding.sharedImageRepoOwner, ViewCompat.getTransitionName(viewHolder.rowRepoBinding.sharedImageRepoOwner)).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();

    }
}
