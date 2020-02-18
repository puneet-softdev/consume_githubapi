package com.githubapi.appstreet;
import android.app.Activity;
import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.githubapi.appstreet.cache.BitmapCacheProvider;
import com.githubapi.appstreet.cache.CacheProvider;
import com.githubapi.appstreet.data.GitWebService;
import com.githubapi.appstreet.inject.DaggerApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class BaseApplication extends MultiDexApplication implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Inject
    public GitWebService gitWebService;

    @Inject
    public CacheProvider cacheProvider;
    @Inject
    public BitmapCacheProvider imageCacheProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = (BaseApplication)this;
        DaggerApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        cacheProvider.clear();
        imageCacheProvider.clear();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        cacheProvider.clear();
        imageCacheProvider.clear();
    }
}
