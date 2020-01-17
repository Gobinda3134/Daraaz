package com.example.daraaz.API;

import com.example.daraaz.Model.products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductAPI {


    @GET("daraz_products")
    Call <List <products>> getallProduct();
}
