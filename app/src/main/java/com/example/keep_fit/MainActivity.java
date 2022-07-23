package com.example.keep_fit;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingResource;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainAdapter.ListClickListener{

    private List<MainClass> MainClassList;
    private MainAdapter MainAdapter;
    private FloatingActionButton floatingActionButton;
    private RecyclerView review_recycler;
    private TextView textView;


    private DBRepository repository;    // ------ DB ---------- //


    private List<MainClass> allCourses;

    //public ExpresssoIdlingResource mIdlingResource;


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);


        // ------------- FIND VIEW BY ID --------------- //
       //  getIdlingResource();
       // mIdlingResource.setIdleState(false);

        floatingActionButton = findViewById(R.id.floating_id);
        review_recycler = findViewById(R.id.user_data_recycler);
        textView = findViewById(R.id.textView);

        MainClassList = new ArrayList<>();

        // -- Database -- //
       // mIdlingResource.setIdleState(false);
        repository = new DBRepository(getApplication());  // ------ DB ---------- //

        MainClassList = repository.getAllData();  // ------get Data DB ---------- //

        review_recycler.setLayoutManager(new LinearLayoutManager(this));

        MainAdapter = new MainAdapter(this, this);


        //---Espresso IDLE---//



        Collections.reverse(MainClassList);
        MainAdapter.setList(MainClassList);
        MainAdapter.notifyDataSetChanged();
      //  mIdlingResource.setIdleState(true);

        review_recycler.setAdapter(MainAdapter);
        MainAdapter.notifyDataSetChanged();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InsertData.class);

                startActivity(intent);


            }
        });


    }


    @Override
    public void onListClick(MainClass MainClass) {
        Intent intent = new Intent(MainActivity.this, UserDetails.class);
        intent.putExtra("MainClass", MainClass);
        startActivity(intent);

    }


   /* @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new ExpresssoIdlingResource();
        }
        return mIdlingResource;
    }*/
}