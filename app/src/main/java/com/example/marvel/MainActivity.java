package com.example.marvel;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    HeroAdapter heroAdapter ;
    SearchView editSearch ;
    HeroViewModel heroViewModel ;
    FragmentManager fragmentManager ;
    FragmentTransaction fragmentTransaction ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager() ;
        fragmentTransaction = fragmentManager.beginTransaction() ;
        fragmentTransaction.replace(R.id.Container , new HomeFragment());
        fragmentTransaction.commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav) ;
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null ;
                String text = null ;
                switch (item.getItemId()){
                    case R.id.home:
                        fragment = new HomeFragment();
                        break ;
                    case R.id.search:
                        fragment = new SearchFragment();
                    case R.id.user:
                        break;
                    default:
                        fragment = new HomeFragment();
                }
                fragmentManager = getSupportFragmentManager() ;
                fragmentTransaction = fragmentManager.beginTransaction();
                if(fragment!=null) {
                    fragmentTransaction.replace(R.id.Container, fragment);
                    fragmentTransaction.commit();
                }
                return true ;
            }
        });

    }

}