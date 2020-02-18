package com.githubapi.appstreet.cache;

import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

public class CacheProvider implements CoreCache {

    private ConcurrentHashMap<String, SoftReference<Object>> cache= new ConcurrentHashMap();

    @Override
    public void add(String key, Object value) {
        cache.put(key, new SoftReference(value));
    }

    @Override
    public  void remove(String key) {
        cache.remove(key);
    }

    @Override
    public Object get(String key) {
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
