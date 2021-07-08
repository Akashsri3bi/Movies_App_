package com.example.marvel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDetail extends AppCompatActivity {

    private TextView selected_title , selected_des ;
    private Button Watch ;
    private ImageView selected_image ;
    Parent parent ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        String Image_Base_Url = "http://image.tmdb.org/t/p/w185/" ;

        selected_title = findViewById(R.id.selected_movie_title) ;
        selected_des = findViewById(R.id.selected_movie_des) ;
        selected_image = findViewById(R.id.selected_movie_image );

        final Intent intent = getIntent() ;
        String title = intent.getStringExtra("Parent") ;
        String image = intent.getStringExtra("Image") ;
        String des = intent.getStringExtra("Des") ;

        selected_title.setText(title);
        selected_des.setText(des);

        Glide.with(this).load(Image_Base_Url+image).into(selected_image) ;

        Watch = findViewById(R.id.Watch) ;

        Watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Watch.setEnabled(true);
                Watch.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                Uri uri = Uri.parse("https://movies4us.me/movies/");
                Intent intent1 = new Intent(Intent.ACTION_VIEW , uri) ;
                startActivity(intent1);
            }
        });
    }
}