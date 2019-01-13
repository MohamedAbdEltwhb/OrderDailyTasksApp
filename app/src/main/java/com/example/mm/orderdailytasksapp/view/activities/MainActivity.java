package com.example.mm.orderdailytasksapp.view.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.mm.orderdailytasksapp.R;
import com.example.mm.orderdailytasksapp.models.SQLiteHelper.DbHelper;
import com.example.mm.orderdailytasksapp.view.adaptor.MainAdaptor;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton mFloatingActionButton;
    RecyclerView recyclerView;

    private SQLiteDatabase mSqLiteDatabase;
    private DbHelper mDbHelper;

    MainAdaptor mMainAdaptor;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new DbHelper(this);
        mSqLiteDatabase = mDbHelper.getWritableDatabase();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        cursor = mDbHelper.getAllGuests();
        mMainAdaptor = new MainAdaptor(getApplicationContext(), cursor);
        recyclerView.setAdapter(mMainAdaptor);



        mFloatingActionButton = findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateNote.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
