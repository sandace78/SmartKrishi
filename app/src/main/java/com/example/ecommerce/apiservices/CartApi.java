package com.example.ecommerce.apiservices;

import com.example.ecommerce.models.Cart;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/*
 Created by ♕ A-Cis Sapkota on 7/7/2020.
*/
 public interface CartApi {
     @POST("Cart/getCartsByUserId")
    Call<List<Cart>> getCartItem(@Body JsonObject jsonObject);
}
