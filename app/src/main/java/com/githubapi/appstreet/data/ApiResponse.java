package com.githubapi.appstreet.data;

import com.githubapi.appstreet.models.Repo;
import com.google.gson.JsonElement;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

import static com.githubapi.appstreet.data.Status.LOADING;
import static com.githubapi.appstreet.data.Status.SUCCESS;
import static com.githubapi.appstreet.data.Status.ERROR;


/**
 * Created by ${Saquib} on 03-05-2018.
 */

public class ApiResponse {

    public final Status status;

    @Nullable
    public final List<Repo> data;

    @Nullable
    public final Throwable error;

    private ApiResponse(Status status, @Nullable List<Repo> data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static ApiResponse loading() {
        return new ApiResponse(LOADING, null, null);
    }

    public static ApiResponse success(@NonNull List<Repo> data) {
        return new ApiResponse(SUCCESS, data, null);
    }

    public static ApiResponse error(@NonNull Throwable error) {
        return new ApiResponse(ERROR, null, error);
    }

}
