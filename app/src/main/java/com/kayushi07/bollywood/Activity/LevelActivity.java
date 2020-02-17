package com.kayushi07.bollywood.Activity;

import android.content.Intent;
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
    int gametype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);

        Intent intent = getIntent();
        gametype = intent.getIntExtra("gametype",0);


        mDB = new DatabaseHandler(getApplicationContext());
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);


        levelListFinal = mDB.getAllLevels(gametype);
        adapter = new MultiViewTypeAdapter(levelListFinal, this, gametype);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        levelListFinal = mDB.getAllLevels(gametype);
        adapter = new MultiViewTypeAdapter(levelListFinal, this, gametype);
        mRecyclerView.setAdapter(adapter);
    }
}