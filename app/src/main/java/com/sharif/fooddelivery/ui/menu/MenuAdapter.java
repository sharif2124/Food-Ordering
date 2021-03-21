package com.sharif.fooddelivery.ui.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sharif.fooddelivery.R;
import com.sharif.fooddelivery.model.MenuItem;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.viewholder> {

    public MenuAdapter(List<MenuItem> allRestaurant) {
        this.allmenuItems = allRestaurant;
    }

    List<MenuItem>allmenuItems;
    @NonNull
    @Override
    public MenuAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item,parent,false);
        return new MenuAdapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.viewholder holder, int position) {
        MenuItem current = allmenuItems.get(position);

    }

    @Override
    public int getItemCount() {
        if(allmenuItems==null||allmenuItems.size()==0){
            return 0;
        }else {
            return allmenuItems.size();
        }
    }

    public class viewholder extends RecyclerView.ViewHolder{


        TextView menuItemName, menuDescription,priceTextview;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            menuItemName =itemView.findViewById(R.id.itemNameTextview);
            menuDescription =itemView.findViewById(R.id.itemDescriptionTextview);
            priceTextview=itemView.findViewById(R.id.itemPrice);


        }
    }
}
