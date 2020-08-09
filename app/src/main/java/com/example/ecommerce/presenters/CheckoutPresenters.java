package com.example.ecommerce.presenters;

import android.util.Log;

import com.example.ecommerce.apiservices.ApiClient;
import com.example.ecommerce.apiservices.CheckoutApi;
import com.example.ecommerce.utilis.Utilities;
import com.example.ecommerce.utilis.UtilitiesFunctions;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.lang.ref.WeakReference;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 Created by ♕ A-Cis Sapkota on 7/7/2020.
*/
 public class CheckoutPresenters {
    private WeakReference<View> view;

    public CheckoutPresenters(CheckoutPresenters.View view) {
        this.view = new WeakReference<>(view);

    }

    private CheckoutPresenters.View getView() throws NullPointerException {
        if (view != null)
            return view.get();
        else
            throw new NullPointerException("View is unavailable");
    }


    public interface View {
        void onCheckoutResponseSuccess(ResponseBody responseBody);

        void onCheckoutResponseFailure(String message);

    }

    public void getCart(String deliveryAddress,String paymentType,double price) {
        int userID= Utilities.getUserInfo().getDetails().getId();
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("userId",userID);
        jsonObject.addProperty("total_price",price);
        jsonObject.addProperty("pay_type",paymentType);
        jsonObject.addProperty("deliveryAddress",deliveryAddress);
        CheckoutApi checkoutApi=ApiClient.getClient().create(CheckoutApi.class);
        checkoutApi.order(jsonObject).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("onResponse: ", new GsonBuilder().create().toJson(jsonObject));
                if (response.isSuccessful()) {
                    if (getView() != null) {
                        getView().onCheckoutResponseSuccess(response.body());
                        Log.e( "checkout: ", new GsonBuilder().create().toJson(response.body()));
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (getView() != null) {
                    getView().onCheckoutResponseFailure(UtilitiesFunctions.handleApiError(t));
                }
            }
        });




    }
}
