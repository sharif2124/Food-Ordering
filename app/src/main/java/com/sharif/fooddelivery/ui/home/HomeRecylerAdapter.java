package com.sharif.fooddelivery.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sharif.fooddelivery.R;
import com.sharif.fooddelivery.model.DataContolar;
import com.sharif.fooddelivery.model.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeRecylerAdapter extends RecyclerView.Adapter<HomeRecylerAdapter.viewholder>  {

    public HomeRecylerAdapter(List<Restaurant> allRestaurant) {
        this.allRestaurant = allRestaurant;
    }

    List<Restaurant>allRestaurant;
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_item,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Restaurant current = allRestaurant.get(position);
        holder.restaurentName.setText(current.getRestaurantName());
        holder.restaurentDescription.setText(current.getRestaurantDescription());
        Picasso.get().load(current.getRestaurantImageUrl()).fit().into(holder.restaurentImage);
    }

    @Override
    public int getItemCount() {
        if(allRestaurant==null||allRestaurant.size()==0){
            return 0;
        }else {
            return allRestaurant.size();
        }
    }

    public class viewholder extends RecyclerView.ViewHolder{

        ImageView restaurentImage;
        TextView restaurentName,restaurentDescription;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            restaurentImage=itemView.findViewById(R.id.restaurantImageView);
            restaurentName=itemView.findViewById(R.id.restaurantTextView);
            restaurentDescription=itemView.findViewById(R.id.restaurantDescriptionView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Restaurant current = allRestaurant.get(getAdapterPosition());
                    DataContolar.instance.getRestaurantInterface().onRestaurantClick(current);
                }
            });

        }
    }
}
