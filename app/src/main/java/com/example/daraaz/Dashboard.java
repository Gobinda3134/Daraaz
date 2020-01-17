package com.example.daraaz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.daraaz.API.ProductAPI;
import com.example.daraaz.Model.products;
import com.example.daraaz.URL.url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity {
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        recyclerView = findViewById(R.id.recyclerView);
        getProduct();
    }

    private void getProduct() {
        ProductAPI retrofitProductAPI = url.getRetrofit().create(ProductAPI.class);
        Call <List <products>> ProductsCall = retrofitProductAPI.getallProduct();
        ProductsCall.enqueue(new Callback <List<products>>() {
            @Override
            public void onResponse(Call<List<products>> call, Response <List<products>> response) {
                System.out.println("Product list " + response.body());
                ProductsAdapter recyclerviewAdapter = new ProductsAdapter(getApplicationContext(), response.body());
                RecyclerView.LayoutManager mlayoutManager = new GridLayoutManager(getApplicationContext(), 3);
                recyclerView.setLayoutManager(mlayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(recyclerviewAdapter);
                recyclerviewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<products>> call, Throwable t) {

            }
        });
    }
}
