package com.projet.esgi.android_forum.Adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.projet.esgi.android_forum.R;
import com.projet.esgi.android_forum.model.Post;
import com.projet.esgi.android_forum.model.Topic;

import java.util.List;

/**
 * Created by Mickael on 15/07/2017.
 */

public class MyAdapterPost extends RecyclerView.Adapter<MyAdapterPost.MyViewHolder> {

    List<Post> list;
    private ItemClickListener clickListener;


    public MyAdapterPost(List<Post> list) {
        this.list = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Post myObject = list.get(position);
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
            btnDelete = (Button) itemView.findViewById(R.id.btn_delete);
            btnUpdate =  (Button) itemView.findViewById(R.id.btn_update);
            btnDelete.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.colorTopic));
            btnUpdate.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.colorTopic));
            itemView.setOnClickListener(this);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());

                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null)  clickListener.onClick(btnDelete, getAdapterPosition());
                }
            });

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) clickListener.onClick(btnUpdate, getAdapterPosition());
                }
            });

        }


        public void bind(Post myObject){
            textViewView.setText(myObject.getTitle());
            textViewDescription.setText(myObject.getContent());
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

    public void removeAt(int position) {
        System.out.println("remove at " + position);
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(Post item) {
        list.add(item);
        notifyDataSetChanged();
    }
}

