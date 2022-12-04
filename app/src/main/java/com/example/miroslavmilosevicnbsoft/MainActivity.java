package com.example.miroslavmilosevicnbsoft;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<ModelClass> list;
    private RecyclerView recyclerView;
    private ImageView img;

    private static String Json_Url="https://makeup-api.herokuapp.com/api/v1/products.json?brand=maybelline";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init()
    {
        recyclerView=findViewById(R.id.recyclerviewId);
        list=new ArrayList<>();

        GetData getData=new GetData();
        getData.execute();

        initForward();
    }

    public class GetData extends AsyncTask<String,String,String> {




        @Override
        protected String doInBackground(String... strings) {

            String current="";
            try{
                URL url;
                HttpURLConnection urlConnection=null;
                try {
                    url=new URL(Json_Url);
                    urlConnection=(HttpURLConnection) url.openConnection();

                    InputStream inputStream=urlConnection.getInputStream();
                    InputStreamReader inputStreamReader=new InputStreamReader(inputStream);

                    int data=inputStreamReader.read();
                    while (data != -1)
                    {
                        current+=(char) data;
                        data=inputStreamReader.read();
                    }
                    return current;

                }catch (MalformedURLException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                    if (urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return current;
        }

        @Override
        protected void onPostExecute(String s){

            try{
                JSONObject jsonObject=new JSONObject(s);

                JSONArray jsonArray=jsonObject.getJSONArray("");

                for(int i=0;i<jsonArray.length();i++){

                    JSONObject jsonObject1=jsonArray.getJSONObject(i);

                    ModelClass modelClass=new ModelClass();

                    modelClass.setBrand(jsonObject1.getString("brand"));
                    modelClass.setName(jsonObject1.getString("name"));
                    modelClass.setPrice(jsonObject1.getString("price"));
                    modelClass.setImg(jsonObject1.getString("image_link"));

                    list.add(modelClass);
                }


            }catch (JSONException e)
            {
                e.printStackTrace();
            }

            PutDataIntoRecyclerView(list);
        }
    }

    private void PutDataIntoRecyclerView(List<ModelClass> list){

        CustomAdaptor adaptor=new CustomAdaptor(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptor);
    }


    private void initForward() {
        img=findViewById(R.id.imgId);

        if (!list.isEmpty()) {
            img.setOnClickListener(v -> {
                Intent intent = new Intent((Context) list, SecondActivity.class);
                startActivity(intent);
            });
        }
    }

}