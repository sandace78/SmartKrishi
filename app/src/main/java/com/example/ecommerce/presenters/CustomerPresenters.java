package com.example.ecommerce.presenters;

import com.example.ecommerce.apiservices.ApiClient;
import com.example.ecommerce.apiservices.CustomerApi;
import com.example.ecommerce.models.Customers;
import com.example.ecommerce.utilis.Utilities;
import com.example.ecommerce.utilis.UtilitiesFunctions;
import com.google.gson.JsonObject;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 Created by ♕ A-Cis Sapkota on 7/7/2020.
*/
 public class CustomerPresenters {
    private WeakReference<View> view;

    public CustomerPresenters(CustomerPresenters.View view) {
        this.view = new WeakReference<>(view);

    }

    private CustomerPresenters.View getView() throws NullPointerException {
        if (view != null)
            return view.get();
        else
            throw new NullPointerException("View is unavailable");
    }


    public interface View {
        void onCustomerResponseSuccess(Customers customers);

        void onCustomerResponseFailure(String message);

    }

    public void customer(String Number,double lat,double lon) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("firstName","");
        jsonObject.addProperty("middleName","");
        jsonObject.addProperty("lastName","");
        jsonObject.addProperty("contactNumber",Number);
        jsonObject.addProperty("latitude",lat);
        jsonObject.addProperty("longitude",lon);

        CustomerApi customerApi=ApiClient.getClient().create(CustomerApi.class);
        customerApi.getCategory(jsonObject).enqueue(new Callback<Customers>() {
            @Override
            public void onResponse(Call<Customers> call, Response<Customers> response) {
                if (response.isSuccessful()) {
                    if (getView() != null) {
                        getView().onCustomerResponseSuccess(response.body());
                        Utilities.saveUserInfo(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Customers> call, Throwable t) {
                if (getView() != null) {
                    getView().onCustomerResponseFailure(UtilitiesFunctions.handleApiError(t));
                }
            }
        });



    }
}
