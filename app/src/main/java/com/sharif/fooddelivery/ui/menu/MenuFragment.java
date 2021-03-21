package com.sharif.fooddelivery.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sharif.fooddelivery.R;
import com.sharif.fooddelivery.model.DataContolar;
import com.sharif.fooddelivery.model.MenuItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {
    View root;
    RecyclerView recyclerView;
    MenuAdapter adapter;
    List<MenuItem> myMenus =new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       root=inflater.inflate(R.layout.fragment_menu,container,false);
       recyclerView=root.findViewById(R.id.menuRecyler);
      myMenus= DataContolar.instance.getCurrentMenuItemList();
      recyclerView.setHasFixedSize(true);
      recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
      adapter=new MenuAdapter(myMenus);
      recyclerView.setAdapter(adapter);
       return root;
    }
}

