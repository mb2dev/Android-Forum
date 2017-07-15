package com.projet.esgi.android_forum.Adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.projet.esgi.android_forum.R;
import com.projet.esgi.android_forum.model.User;

import java.util.List;

/**
 * Created by Mickael on 08/07/2017.
 */

public class MyAdapterUser extends RecyclerView.Adapter<MyAdapterUser.MyViewHolder> {

    List<User> list;
    private ItemClickListener clickListener;


    public MyAdapterUser(List<User> list) {
        this.list = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User myObject = list.get(position);
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
        private Button btnUpdate;


        //itemView est la vue correspondante à 1 cellule
        public MyViewHolder(View itemView) {
            super(itemView);
            textViewView = (TextView) itemView.findViewById(R.id.info_text);
            textViewDescription = (TextView) itemView.findViewById(R.id.info_description);
            btnDelete = (Button) itemView.findViewById(R.id.btn_update);
            btnUpdate = (Button) itemView.findViewById(R.id.btn_delete);
            btnDelete.setVisibility(View.INVISIBLE);
            btnUpdate.setVisibility(View.INVISIBLE);
            itemView.setOnClickListener(this);

        }

        //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
        public void bind(User myObject){
            textViewView.setText(myObject.getFirstname());
            textViewDescription.setText(myObject.getLastname());
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}
