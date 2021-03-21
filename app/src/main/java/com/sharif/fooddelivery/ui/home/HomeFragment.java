package com.sharif.fooddelivery.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sharif.fooddelivery.R;

import com.sharif.fooddelivery.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {



    RecyclerView recyclerView;
    HomeRecylerAdapter adapter;
    List<Restaurant> myRestaurant = new ArrayList<>();
    private static final String TAG = "HomeFragment";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=root.findViewById(R.id.homeRecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new HomeRecylerAdapter(myRestaurant);
        recyclerView.setAdapter(adapter);
        GetDataFromFirestore();

        return root;


    }
    private void GetDataFromFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Restaurant")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot documentSnapshot:task.getResult()){
                        Restaurant restaurant = documentSnapshot.toObject(Restaurant.class);
                        myRestaurant.add(restaurant);
                    }
                    adapter.notifyDataSetChanged();
                }

            }
        });


    }
}