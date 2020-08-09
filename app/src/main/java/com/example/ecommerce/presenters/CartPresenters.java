package com.example.ecommerce.presenters;

import android.util.Log;

import com.example.ecommerce.apiservices.ApiClient;
import com.example.ecommerce.apiservices.CartApi;
import com.example.ecommerce.models.Cart;
import com.example.ecommerce.utilis.Utilities;
import com.example.ecommerce.utilis.UtilitiesFunctions;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 Created by ♕ A-Cis Sapkota on 7/7/2020.
*/
 public class CartPresenters {
    private WeakReference<View> view;

    public CartPresenters(CartPresenters.View view) {
        this.view = new WeakReference<>(view);

    }

    private CartPresenters.View getView() throws NullPointerException {
        if (view != null)
            return view.get();
        else
            throw new NullPointerException("View is unavailable");
    }


    public interface View {
        void onCartResponseSuccess(List<Cart> cart);

        void onCartResponseFailure(String message);

    }

    public void getCart() {
        int userID= Utilities.getUserInfo().getDetails().getId();
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("userId",userID);
        CartApi cartApi=ApiClient.getClient().create(CartApi.class);
        cartApi.getCartItem(jsonObject).enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {
                    if (getView() != null) {
                        getView().onCartResponseSuccess(response.body());
                        Log.e( "onResponse: ", new GsonBuilder().create().toJson(response.body()));
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                if (getView() != null) {
                    getView().onCartResponseFailure(UtilitiesFunctions.handleApiError(t));
                }
            }
        });




    }
}
