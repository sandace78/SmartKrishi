package com.example.ecommerce.apiservices;

import com.example.ecommerce.models.Customers;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/*
 Created by ♕ A-Cis Sapkota on 7/7/2020.
*/
 public interface CustomerApi {
         @POST("customer/save")
    Call<Customers> getCategory(@Body JsonObject jsonObject);



}
