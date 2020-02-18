package com.githubapi.appstreet.inject;

import com.githubapi.appstreet.cache.BitmapCacheProvider;
import com.githubapi.appstreet.cache.CacheProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CacheModule {
    @Provides
    @Singleton
    CacheProvider provideCacheInterface() {
        return new CacheProvider();
    }

    @Provides
    @Singleton
    BitmapCacheProvider provideImageCacheInterface() {
        return new BitmapCacheProvider();
    }
}
