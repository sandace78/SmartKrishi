package com.example.ecommerce.presenters;

import android.util.Log;

import com.example.ecommerce.apiservices.ApiClient;
import com.example.ecommerce.apiservices.CustomerApi;
import com.example.ecommerce.models.Customers;
import com.example.ecommerce.utilis.Utilities;
import com.example.ecommerce.utilis.UtilitiesFunctions;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 Created by ♕ A-Cis Sapkota on 7/7/2020.
*/
 public class ProfilePresenters {
    private WeakReference<View> view;

    public ProfilePresenters(ProfilePresenters.View view) {
        this.view = new WeakReference<>(view);

    }

    private ProfilePresenters.View getView() throws NullPointerException {
        if (view != null)
            return view.get();
        else
            throw new NullPointerException("View is unavailable");
    }


    public interface View {
        void onProfileResponseSuccess(Customers customers);

        void onProfileResponseFailure(String message);

    }

    public void profile(String fName,String mName,String lName,String Number) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("id", Utilities.getUserInfo().getDetails().getId());
        jsonObject.addProperty("firstName",fName);
        jsonObject.addProperty("middleName",mName);
        jsonObject.addProperty("lastName",lName);
        jsonObject.addProperty("contactNumber",Number);

        CustomerApi customerApi=ApiClient.getClient().create(CustomerApi.class);
        customerApi.getCategory(jsonObject).enqueue(new Callback<Customers>() {
            @Override
            public void onResponse(Call<Customers> call, Response<Customers> response) {
                if (response.isSuccessful()) {
                    if (getView() != null) {
                        Log.e( "onResponse: ",new GsonBuilder().create().toJson(response.body()) );
                        getView().onProfileResponseSuccess(response.body());
                        Utilities.saveUserInfo(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Customers> call, Throwable t) {
                if (getView() != null) {
                    getView().onProfileResponseFailure(UtilitiesFunctions.handleApiError(t));
                }
            }
        });




    }
}
