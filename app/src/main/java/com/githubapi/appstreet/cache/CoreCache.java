package com.githubapi.appstreet.cache;

public interface CoreCache {
    void add(String key, Object value);
    void remove(String key);
    Object get(String key);
    void clear();
    boolean isDataAvailable(String key);
}
