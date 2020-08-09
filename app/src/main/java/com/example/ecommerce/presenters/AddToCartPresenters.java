package com.example.ecommerce.presenters;

import android.util.Log;

import com.example.ecommerce.apiservices.ApiClient;
import com.example.ecommerce.apiservices.AddToCartApi;
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
 public class AddToCartPresenters {
    private WeakReference<View> view;

    public AddToCartPresenters(AddToCartPresenters.View view) {
        this.view = new WeakReference<>(view);

    }

    private AddToCartPresenters.View getView() throws NullPointerException {
        if (view != null)
            return view.get();
        else
            throw new NullPointerException("View is unavailable");
    }


    public interface View {
        void onCartResponseSuccess(ResponseBody responseBody);

        void onCartResponseFailure(String message);

    }

    public void cart(int productId,int userId,int qty,double price) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("productId",productId);
        jsonObject.addProperty("userId",userId);
        jsonObject.addProperty("qty",qty);
        jsonObject.addProperty("price",price);
        AddToCartApi cartApi=ApiClient.getClient().create(AddToCartApi.class);
        cartApi.addCart(jsonObject).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e( "onResponse: ",new GsonBuilder().create().toJson(jsonObject));
                if (response.isSuccessful()) {
                    if (getView() != null) {
                        Log.e( "onResponse: ",new GsonBuilder().create().toJson(response.body()) );
                        getView().onCartResponseSuccess(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (getView() != null) {
                    getView().onCartResponseFailure(UtilitiesFunctions.handleApiError(t));
                }
            }
        });



    }
}
