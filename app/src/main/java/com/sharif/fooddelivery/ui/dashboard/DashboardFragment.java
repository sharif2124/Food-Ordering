package com.sharif.fooddelivery.ui.dashboard;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sharif.fooddelivery.R;
import com.squareup.picasso.Picasso;

public class DashboardFragment extends Fragment {

FirebaseUser user;
ImageView userprofile;
TextView emailtext,nametext;
    private Uri photoUrl;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        userprofile=root.findViewById(R.id.imageView);
        emailtext=root.findViewById(R.id.textView);
        nametext=root.findViewById(R.id.textView2);

        user= FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            emailtext.setText("Email:"+user.getEmail());
            nametext.setText("Name:"+user.getDisplayName());
           Picasso.get().load(user.getPhotoUrl()).fit().into(userprofile);
        }


        return root;
    }
}