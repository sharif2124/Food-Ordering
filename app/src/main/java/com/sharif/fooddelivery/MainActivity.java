package com.sharif.fooddelivery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sharif.fooddelivery.model.DataContolar;
import com.sharif.fooddelivery.model.MenuItem;
import com.sharif.fooddelivery.model.Restaurant;
import com.sharif.fooddelivery.model.RestaurantInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RestaurantInterface {
    private static final String TAG = "MainActivity";
    RestaurantInterface restaurantInterface;
    DataContolar contolar;
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        restaurantInterface=this;
       contolar = DataContolar.getInstance();
       contolar.setRestaurantInterface(restaurantInterface);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
       navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);



       // SendDataToFirestore();

        GetDataFromFirestore();




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
                     Log.e(TAG, "onComplete: "+restaurant.getRestaurantName() );
                 }
             }

         }
     });


    }

    private void SendDataToFirestore() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference reference = db.collection("Restaurant");
        Restaurant myRestaurant = new Restaurant();
        myRestaurant.setRestaurantName("Green Garden");
        myRestaurant.setRestaurantDescription("Best Restaurant in Ashulia");
        myRestaurant.setRestaurantLocation("Dhaka, Ashulia");
        myRestaurant.setRestaurantImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/RedDot_Burger.jpg/1200px-RedDot_Burger.jpg");
        List<MenuItem>myMenus=new ArrayList<>();
        for (int i=0;i<=10;i++){
           myMenus.add(new MenuItem("Matton Kacchi","Khub Test",450));
        }
        myRestaurant.setRestaurantMenuList(myMenus);
        reference.add(myRestaurant).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
           @Override
           public void onComplete(@NonNull Task<DocumentReference> task) {
               if(task.isSuccessful()){
                   Toast.makeText(MainActivity.this, "Restaurant Uploaded", Toast.LENGTH_SHORT).show();
               }else {
                   Toast.makeText(MainActivity.this, "Sorry not Success", Toast.LENGTH_SHORT).show();
               }

           }
       });




    }

    @Override
    public void onRestaurantClick(Restaurant restaurant) {
        contolar.setCurrentMenuItemList(restaurant.getRestaurantMenuList());
       navController.navigate(R.id.action_navigation_home_to_navigation_menu);
    }
}