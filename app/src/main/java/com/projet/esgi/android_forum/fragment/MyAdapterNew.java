package com.projet.esgi.android_forum.fragment;

/**
 * Created by Mickael on 08/07/2017.
 */

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.projet.esgi.android_forum.Adapter.ItemClickListener;
import com.projet.esgi.android_forum.R;
import com.projet.esgi.android_forum.listActivity;
import com.projet.esgi.android_forum.model.News;

import java.util.List;



public class MyAdapterNew extends RecyclerView.Adapter<MyAdapterNew.MyViewHolder> {

    List<News> list;
    private ItemClickListener clickListener;


    public MyAdapterNew(List<News> list) {
        this.list = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        News myObject = list.get(position);
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
        private TextView textViewDescription;
        private Button btnDelete;


        //itemView est la vue correspondante à 1 cellule
        public MyViewHolder(View itemView) {
            super(itemView);
            textViewView = (TextView) itemView.findViewById(R.id.info_text);
            textViewDescription = (TextView) itemView.findViewById(R.id.info_description);
            btnDelete = (Button) itemView.findViewById(R.id.btn_delete);
            btnDelete.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.colorNews));
            itemView.setOnClickListener(this);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());

                }
            });

        }

        //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
        public void bind(News myObject){
            textViewView.setText(myObject.getTitle());
            textViewDescription.setText(myObject.getContent());
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}



