package com.githubapi.appstreet.cache;

import android.graphics.Bitmap;

import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

public class BitmapCacheProvider implements CoreCache{
    private ConcurrentHashMap<String, SoftReference<Bitmap>> cache= new ConcurrentHashMap();

    @Override
    public void add(String key, Object value) {
        cache.put(key, new SoftReference(value));
    }

    @Override
    public  void remove(String key) {
        cache.remove(key);
    }

    @Override
    public Bitmap get(String key) {
        if (isDataAvailable(key)) {
            return cache.get(key).get();
        }
        return null;
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public boolean isDataAvailable(String key) {
        return (cache.get(key) != null);
    }
}
