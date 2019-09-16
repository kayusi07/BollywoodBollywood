package com.kayushi07.bollywood.Activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.kayushi07.bollywood.Adapter.MultiViewTypeAdapter;
import com.kayushi07.bollywood.Model.*;
import com.kayushi07.bollywood.R;

import java.util.ArrayList;

public class LevelActivity extends AppCompatActivity {


    ArrayList<Model> levelListFinal;
    DatabaseHandler mDB;
    RecyclerView mRecyclerView;
    MultiViewTypeAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);

        mDB = new DatabaseHandler(getApplicationContext());
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);


        levelListFinal = mDB.getAllLevels();
        adapter = new MultiViewTypeAdapter(levelListFinal, this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        levelListFinal = mDB.getAllLevels();
        adapter = new MultiViewTypeAdapter(levelListFinal, this);
        mRecyclerView.setAdapter(adapter);
    }
}