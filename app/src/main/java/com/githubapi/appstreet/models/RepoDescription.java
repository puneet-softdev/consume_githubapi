package com.githubapi.appstreet.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepoDescription implements Parcelable {
    @Expose
    @SerializedName("name")
    String name;

    @Expose
    @SerializedName("description")
    String description;

    @Expose
    @SerializedName("url")
    String url;

    // Parcelling part
    public RepoDescription(Parcel in){
        this.name = in.readString();
        this.description = in.readString();
        this.url =  in.readString();
    }

    public RepoDescription(String name, String description, String url) {
        this.name = name;
        this.description = description;
        this.url = url;
    }

    public static final Creator<RepoDescription> CREATOR = new Creator<RepoDescription>() {
        @Override
        public RepoDescription createFromParcel(Parcel in) {
            return new RepoDescription(in);
        }

        @Override
        public RepoDescription[] newArray(int size) {
            return new RepoDescription[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(url);
    }
}
