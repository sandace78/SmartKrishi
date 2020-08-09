package com.example.ecommerce.presenters;

import android.util.Log;

import com.example.ecommerce.apiservices.ApiClient;
import com.example.ecommerce.apiservices.ProductApi;
import com.example.ecommerce.models.Products;
import com.example.ecommerce.utilis.UtilitiesFunctions;
import com.google.gson.GsonBuilder;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 Created by ♕ A-Cis Sapkota on 7/7/2020.
*/
 public class ProductPresenters {
    private WeakReference<View> view;

    public ProductPresenters(ProductPresenters.View view) {
        this.view = new WeakReference<>(view);

    }

    private ProductPresenters.View getView() throws NullPointerException {
        if (view != null)
            return view.get();
        else
            throw new NullPointerException("View is unavailable");
    }


    public interface View {
        void onProductResponseSuccess(Products products);

        void onProductResponseFailure(String message);

    }

    public void products() {
        ProductApi productApi=ApiClient.getClient().create(ProductApi.class);
        productApi.getProduct().enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if (response.isSuccessful()) {
                    if (getView() != null) {
                        getView().onProductResponseSuccess(response.body());
                        Log.e( "onResponse: ", new GsonBuilder().create().toJson(response.body()));
                    }

                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                if (getView() != null) {
                    getView().onProductResponseFailure(UtilitiesFunctions.handleApiError(t));
                }
            }
        });



    }
}
