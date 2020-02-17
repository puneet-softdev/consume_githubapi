package com.githubapi.appstreet.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Repo implements Parcelable {

    @Expose
    @SerializedName("username")
    String userName;

    @Expose
    @SerializedName("name")
    String name;

    @Expose
    @SerializedName("type")
    String type;

    @Expose
    @SerializedName("url")
    String repoUrl;

    @Expose
    @SerializedName("avatar")
    String avatar;

    @Expose
    @SerializedName("repo")
    RepoDescription repo;



    public Repo(Parcel in) {
        userName = in.readString();
        name = in.readString();
        type = in.readString();
        repoUrl = in.readString();
        avatar = in.readString();
        repo = in.readParcelable(RepoDescription.class.getClassLoader());
    }

    public static final Creator<Repo> CREATOR = new Creator<Repo>() {
        @Override
        public Repo createFromParcel(Parcel in) {
            return new Repo(in);
        }

        @Override
        public Repo[] newArray(int size) {
            return new Repo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userName);
        parcel.writeString(name);
        parcel.writeString(type);
        parcel.writeString(repoUrl);
        parcel.writeString(avatar);
        parcel.writeParcelable(repo, i);
    }
}
