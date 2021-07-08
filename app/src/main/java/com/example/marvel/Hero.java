package com.example.marvel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hero{

    @SerializedName("results")
    public List<Result> results ;

    public List<Result> getResults() {
        return results;
    }

    class Result{
        @SerializedName("poster_path")
        public String poster;

        @SerializedName("title")
        public String title ;

        @SerializedName("overview")
        public String overView ;
    }
}
