package com.example.ecommerce.apiservices;

import com.example.ecommerce.models.Token;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/*
 Created by ♕ A-Cis Sapkota on 7/7/2020.
*/
 public interface TokenApi {
     @POST("oauth/token")
     @FormUrlEncoded
    Call<Token> getToken(
            @Field("grant_type") String string,
            @Field("username") String username,
            @Field("password") String password
     );


}
