package com.example.marvel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView ;
    HeroViewModel heroViewModel ;
    HeroAdapter heroAdapter ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final List<Parent> parents = new ArrayList<>() ;

        heroViewModel = new ViewModelProvider(this , getDefaultViewModelProviderFactory()).get(HeroViewModel.class) ;
        heroViewModel.getResults().observe(this, new Observer<List<Hero.Result>>() {
            @Override
            public void onChanged(List<Hero.Result> results) {
                parents.add(new Parent(results , "Popular"));
            }
        });

        heroViewModel.getRated().observe(this, new Observer<List<Hero.Result>>() {
            @Override
            public void onChanged(List<Hero.Result> results) {
                parents.add(new Parent(results , "TopRated"));
                heroAdapter = new HeroAdapter(parents , getContext()) ;
                recyclerView.setAdapter(heroAdapter);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerView) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}