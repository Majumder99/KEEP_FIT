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

    /**
     * Initialising components: data list, data adapter, button, recycler view, text view, database
     */
    private List<MainClass> MainClassList;
    private MainAdapter MainAdapter;
    private FloatingActionButton floatingActionButton;
    private RecyclerView review_recycler;
    private TextView textView;


    private DBRepository repository;


    private List<MainClass> allCourses;

     public EspressoIdle mIdlingResource;


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);


        /**
         * Finding views by id
         */
        getIdlingResource();
        mIdlingResource.setIdleState(false);

        floatingActionButton = findViewById(R.id.floating_id);
        review_recycler = findViewById(R.id.user_data_recycler);
        textView = findViewById(R.id.textView);

        MainClassList = new ArrayList<>();

        /**
         * Database
         */
        repository = new DBRepository(getApplication());  // ------ DB ---------- //

        MainClassList = repository.getAllData();  // ------get Data DB ---------- //

        review_recycler.setLayoutManager(new LinearLayoutManager(this));

        MainAdapter = new MainAdapter(this, this);


        //---Espresso IDLE---//



        Collections.reverse(MainClassList);
        MainAdapter.setList(MainClassList);
        MainAdapter.notifyDataSetChanged();
        mIdlingResource.setIdleState(true);

        review_recycler.setAdapter(MainAdapter);
        MainAdapter.notifyDataSetChanged();

        /**
         * Floating action button action definition
         */
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InsertData.class);

                startActivity(intent);


            }
        });


    }

    /**
     * Clickable list in recycler view action definition
     * @param MainClass
     */
    @Override
    public void onListClick(MainClass MainClass) {
        Intent intent = new Intent(MainActivity.this, UserDetails.class);
        intent.putExtra("MainClass", MainClass);
        startActivity(intent);

    }


    /**
     * EspressoIdle for creating delay while running ui testing-- waiting for database to retrieve data
     * on the page
     * @return
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new EspressoIdle();
        }
        return mIdlingResource;
    }
}