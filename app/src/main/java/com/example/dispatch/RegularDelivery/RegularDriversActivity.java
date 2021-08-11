package com.example.dispatch.RegularDelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dispatch.R;
import com.example.dispatch.utils.RecyclerAdapter;
import com.example.dispatch.utils.RegularDriversFeed;

import java.util.ArrayList;

public class RegularDriversActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<RegularDriversFeed> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_drivers);

        arrayList = new ArrayList<>();


        recyclerView = findViewById(R.id.recyclerView);

        //Do parse query with loop to add to array list
       // arrayList.add(new RegularDriversFeed())

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(arrayList);

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}