package com.example.marvel;

import java.io.Serializable;
import java.util.List;

public class Parent implements Serializable {
    private List<Hero.Result> results ;
    private String title ;

    public Parent(List<Hero.Result> results, String title) {
        this.results = results;
        this.title = title;
    }

    public List<Hero.Result> getResults() {
        return results;
    }

    public String getTitle() {
        return title;
    }

}
