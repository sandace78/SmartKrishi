package com.example.ecommerce.apiservices;

import com.example.ecommerce.models.Products;

import retrofit2.Call;
import retrofit2.http.GET;

/*
 Created by ♕ A-Cis Sapkota on 7/7/2020.
*/
 public interface ProductApi {
     @GET("product/list")
    Call<Products> getProduct();


}
