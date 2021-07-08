package com.example.marvel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.Viewholder> {

    List<Parent> parentList ;
    Context context ;
    List<Parent> parents = new ArrayList<>() ;

    public HeroAdapter(List<Parent> parentList , Context context){
        this.parentList = parentList;
        this.context = context;
        this.parents.addAll(parentList) ;
    }

    @NonNull
    @Override
    public HeroAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item ,parent , false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroAdapter.Viewholder holder, int position) {
        final Parent parent = parentList.get(position) ;
        holder.title.setText(parent.getTitle());
        holder.child_recycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
        ChildAdapter childAdapter = new ChildAdapter(parent.getResults() , context) ;
        holder.child_recycler.setAdapter(childAdapter);
        childAdapter.setListener(new ChildAdapter.Listener() {
            @Override
            public void oneClick(int pos) {
                Intent intent = new Intent(context , MovieDetail.class) ;
                intent.putExtra("Parent" , parent.getResults().get(pos).title) ;
                intent.putExtra("Image" , parent.getResults().get(pos).poster) ;
                intent.putExtra("Des" , parent.getResults().get(pos).overView) ;
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return parentList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{
        TextView title ;
        RecyclerView child_recycler ;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView) ;
            child_recycler = itemView.findViewById(R.id.Child) ;
        }
    }
}
