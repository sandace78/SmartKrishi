package com.example.ecommerce.apiservices;

import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/*
 Created by ♕ A-Cis Sapkota on 7/7/2020.
*/
 public interface CheckoutApi {
     @POST("order/checkout_order")
    Call<ResponseBody> order(@Body JsonObject jsonObject);
}
