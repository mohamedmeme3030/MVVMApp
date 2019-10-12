package com.example.mvvmapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mvvmapp.Model.NameModel;
import com.example.mvvmapp.ViewModel.NameViewModel;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements DataLoadListener {

    private NameViewModel nameViewModel;
    private AdpterItem adpterItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        RecyclerView recyclerView;


        recyclerView = findViewById(R.id.recycler_item);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        nameViewModel = ViewModelProviders.of(this).get(NameViewModel.class);
        nameViewModel.init(this);
        adpterItem = new AdpterItem(nameViewModel.getNames().getValue());
        recyclerView.setAdapter(adpterItem);

    }

    @Override
    public void onNameLoads() {
        nameViewModel.getNames().observe(this, new Observer<ArrayList<NameModel>>() {
            @Override
            public void onChanged(ArrayList<NameModel> nameModels) {
                adpterItem.notifyDataSetChanged();
            }
        });
    }
}
