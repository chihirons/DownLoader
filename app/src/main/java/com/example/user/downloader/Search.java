package com.example.user.downloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

//        ArrayList<String> list = new ArrayList<>();
//
//        list.add("A");
//        list.add("B");
//        list.add("C");
//        list.add("D");
//        list.add("E");
//        list.add("F");
//        list.add("G");
//        list.add("H");
//        list.add("I");
//        list.add("J");
//        list.add("K");
//        list.add("L");
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                getApplicationContext(),android.R.layout.simple_list_item_1, list);
        GridView gridView = findViewById(R.id.grid);
        gridView.setAdapter(new SearchAdapter(this));
       // gridView.setAdapter();
    }
}
