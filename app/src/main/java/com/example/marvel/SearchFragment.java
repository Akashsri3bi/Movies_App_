package com.example.marvel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment {
    SearchView searchView ;
    HeroViewModel heroViewModel ;
    RecyclerView recyclerView ;
    SearchAdapter searchAdapter ;
    TextView searchResults ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchView = view.findViewById(R.id.search) ;
        recyclerView = view.findViewById(R.id.search_recycler) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        searchResults = view.findViewById(R.id.search_result) ;

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                final List<Hero.Result> results = loadSearches(query) ;
                Log.i("String" , query) ;
                String text = "responses for " + query;
                searchResults.setText(text);
                searchAdapter = new SearchAdapter(results , getContext()) ;
                searchAdapter.setListener(new SearchAdapter.Listener() {
                    @Override
                    public void oneClick(int pos) {
                        Intent intent = new Intent(getContext() , MovieDetail.class) ;
                        intent.putExtra("Parent" , results.get(pos).title);
                        intent.putExtra("Image" , results.get(pos).poster);
                        intent.putExtra("Des" , results.get(pos).overView);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(searchAdapter);
                return true;
            }
            @Override
                public boolean onQueryTextChange(String newText) {
                return true;
            }
        }) ;

        return view ;
    }

    private List<Hero.Result> loadSearches(String text) {
        final List<Hero.Result> results = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(HeroApi.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        HeroApi heroApi = retrofit.create(HeroApi.class);
        Call<Hero> heroCall = heroApi.getquery("bb9166dbfbad0dee49894404b3ab8220", text);
        heroCall.enqueue(new Callback<Hero>() {
            @Override
            public void onResponse(Call<Hero> call, Response<Hero> response) {
                Hero hero = response.body();
                results.addAll(hero.getResults()) ;
            }

            @Override
            public void onFailure(Call<Hero> call, Throwable t) {

            }
        });
        return results ;
    }
}