package com.example.ecommerce.apiservices;

import com.example.ecommerce.models.Tutorials;

import retrofit2.Call;
import retrofit2.http.GET;

/*
 Created by ♕ A-Cis Sapkota on 7/7/2020.
*/
 public interface TutorialApi {
     @GET("tutorial")
    Call<Tutorials> getTutorials();


}
