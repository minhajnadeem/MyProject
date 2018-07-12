package com.app.myproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.app.myproject.adapters.MyAdapter;
import com.app.myproject.interfces.ClickInterface;
import com.app.myproject.models.MyModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private ArrayList<MyModel> mArrayList;
    private RecyclerView mRecyclerView;
    private Button mBtnReset;
    private ClickInterface mClickInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setupListeners();
        setupAdapter();
    }

    private void init() {

        mArrayList = new ArrayList<>();

        mRecyclerView = findViewById(R.id.recyclerView);
        mBtnReset = findViewById(R.id.btnReset);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        MyAdapter adapter = new MyAdapter(this,mArrayList,mClickInterface);
        mRecyclerView.setAdapter(adapter);

        //populate list with data
        mArrayList.add(new MyModel("1",false));
        mArrayList.add(new MyModel("2",false));
        mArrayList.add(new MyModel("3",false));
        mArrayList.add(new MyModel("4",false));
        mArrayList.add(new MyModel("5",false));
        mArrayList.add(new MyModel("6",false));
        mArrayList.add(new MyModel("7",false));
        mArrayList.add(new MyModel("8",false));
        mArrayList.add(new MyModel("9",false));
    }

    private void setupListeners() {
        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupAdapter();
            }
        });

        mClickInterface = new ClickInterface() {
            @Override
            public void onClick(int position) {
                MyModel tileToBeSelect = mArrayList.get(position);
                if (!tileToBeSelect.isSelected()){
                    //MyModel previousSelectedTile = getSelectedTitle();
                    unSelectAllTiles();
                    //String tempNumber = tileToBeSelect.getNumber();
                    //tileToBeSelect.setNumber(previousSelectedTile.getNumber());
                    //previousSelectedTile.setNumber(tempNumber);
                    tileToBeSelect.setSelected(true);
                    setAdapter();
                }
            }
        };
    }

    private MyModel getSelectedTitle(){
        for (MyModel myModel : mArrayList){
            if (myModel.isSelected()){
                return myModel;
            }
        }
        return null;
    }

    private void unSelectAllTiles() {
        for (MyModel myModel : mArrayList){
            myModel.setSelected(false);
        }
    }

    private void setupAdapter() {
        //shuffle list
        unSelectAllTiles();
        Collections.shuffle(mArrayList);
        int randomPos = getRandomPos();
        Log.d(TAG, "setupAdapter: "+randomPos);

        MyModel myModel = mArrayList.get(randomPos);
        //if title is already selected then randomize again
        if (myModel.isSelected()){
            setupAdapter();
            return;
        }
        myModel.setSelected(true);

        setAdapter();
    }
    private void setAdapter(){
        MyAdapter adapter = new MyAdapter(this,mArrayList,mClickInterface);
        mRecyclerView.setAdapter(adapter);
    }

    /**
     *
     * generate random position between 0-8 inclusive
     */
    private int getRandomPos(){
        Random random = new Random();
        return random.nextInt((8)+1) + 0;
    }
}
