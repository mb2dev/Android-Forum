package com.projet.esgi.android_forum.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projet.esgi.android_forum.Adapter.ItemClickListener;
import com.projet.esgi.android_forum.R;

import java.util.List;

/**
 * Created by Mickael on 02/07/2017.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<String> list;
    private ItemClickListener clickListener;


    public MyAdapter(List<String> list) {
        this.list = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String myObject = list.get(position);
        holder.bind(myObject);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView textViewView;


        //itemView est la vue correspondante à 1 cellule
        public MyViewHolder(View itemView) {
            super(itemView);
            textViewView = (TextView) itemView.findViewById(R.id.info_text);
            itemView.setOnClickListener(this);

        }

        //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
        public void bind(String myObject){
            textViewView.setText(myObject);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}


