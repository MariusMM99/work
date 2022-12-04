package com.example.miroslavmilosevicnbsoft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private List<ModelClass> list=null;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
    }
    private void init(){
        parseIntent();
        initListeners();
    }

    private void parseIntent(){
        recyclerView=findViewById(R.id.recyclerviewId);
        Intent intent=getIntent();
        if (intent.getExtras()!=null)
        {
            list=(List<ModelClass>) intent;
        }

        PutDataIntoRecyclerView(list);
    }
    private void PutDataIntoRecyclerView(List<ModelClass> list){

        CustomAdaptor adaptor=new CustomAdaptor(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptor);
    }
    private void initListeners(){

    }
}