package com.example.marvel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HeroViewModel extends ViewModel {

    private MutableLiveData<List<Hero.Result>> results ;
    private MutableLiveData<List<Hero.Result>> rated ;
    private MutableLiveData<List<Hero.Result>> searches ;

    public LiveData<List<Hero.Result>> getResults(){
        if(results==null) {
            results = new MutableLiveData<>();
            loadResult() ;
        }

        return results ;
    }

    public LiveData<List<Hero.Result>> getRated(){
        if(rated==null) {
            rated = new MutableLiveData<>();
            loadRated() ;
        }

        return rated ;
    }

    public LiveData<List<Hero.Result>> getSearches(String query){
        if(searches==null){
            searches = new MutableLiveData<>() ;
            loadSearches(query) ;
        }

        return searches ;
    }

    private void loadResult(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl(HeroApi.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build() ;
        HeroApi heroApi = retrofit.create(HeroApi.class) ;
        Call<Hero> call = heroApi.getHeroes("bb9166dbfbad0dee49894404b3ab8220") ;
        call.enqueue(new Callback<Hero>() {
            @Override
            public void onResponse(Call<Hero> call, Response<Hero> response) {
                Hero hero = response.body() ;
                List<Hero.Result> heroes = hero.getResults() ;
                results.setValue(heroes);
            }

            @Override
            public void onFailure(Call<Hero> call, Throwable t) {
                results.setValue(null);
            }
        });
    }

    private void loadRated(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl(HeroApi.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build() ;
        HeroApi heroApi = retrofit.create(HeroApi.class) ;
        Call<Hero> call = heroApi.getTop("bb9166dbfbad0dee49894404b3ab8220") ;
        call.enqueue(new Callback<Hero>() {
            @Override
            public void onResponse(Call<Hero> call, Response<Hero> response) {
                Hero hero = response.body() ;
                List<Hero.Result> heroes = hero.getResults() ;
                rated.setValue(heroes);
            }

            @Override
            public void onFailure(Call<Hero> call, Throwable t) {
                rated.setValue(null);
            }
        });
    }

    private void loadSearches(String text){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(HeroApi.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        HeroApi heroApi = retrofit.create(HeroApi.class) ;
        Call<Hero> heroCall = heroApi.getquery("bb9166dbfbad0dee49894404b3ab8220" , text) ;
        heroCall.enqueue(new Callback<Hero>() {
            @Override
            public void onResponse(Call<Hero> call, Response<Hero> response) {
                Hero hero = response.body() ;
                List<Hero.Result> results = hero.getResults() ;
                searches.setValue(results);
            }

            @Override
            public void onFailure(Call<Hero> call, Throwable t) {

            }
        });
    }

}
