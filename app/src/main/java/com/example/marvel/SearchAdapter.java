package com.example.marvel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.Viewholder> {

    private List<Hero.Result> results ;
    Context context ;
    private Listener listener ;

    interface Listener{
        void oneClick(int pos) ;
    }

    public void setListener(Listener listener){
        this.listener = listener ;
    }

    public SearchAdapter(List<Hero.Result> results, Context context) {
        this.results = results;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_recycler_item,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, final int position) {
        View view = holder.itemView ;
        Hero.Result result = results.get(position) ;
        holder.search_title.setText(result.title);
        String Image_Base_Url = "http://image.tmdb.org/t/p/w185/" ;
        Glide.with(context).load(Image_Base_Url+result.poster).into(holder.search_image) ;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.oneClick(position);
                }

            }
        });
    }

    public void clearAll(){
        results.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    class Viewholder extends RecyclerView.ViewHolder{
        TextView search_title ;
        ImageView search_image ;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            search_title = itemView.findViewById(R.id.search_title);
            search_image = itemView.findViewById(R.id.search_images) ;
        }
    }
}
