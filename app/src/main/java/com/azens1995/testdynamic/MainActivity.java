package com.azens1995.testdynamic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.widget.Toast;

import com.azens1995.testdynamic.adapter.DataAdapter;
import com.azens1995.testdynamic.model.Data;
import com.azens1995.testdynamic.rest.DataClient;
import com.azens1995.testdynamic.rest.ServiceGenerator;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        loadJSON();
    }

    private void loadJSON() {
        DataClient client = ServiceGenerator.createService(DataClient.class);
        retrofit2.Call call = client.getData();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(retrofit2.Call call, Response response) {
                List<Data> data = (List<Data>) response.body();
                recyclerView.setAdapter(new DataAdapter(MainActivity.this, data));

            }

            @Override
            public void onFailure(retrofit2.Call call, Throwable t) {
                Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
