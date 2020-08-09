package com.example.ecommerce.presenters;

import android.util.Log;

import com.example.ecommerce.apiservices.ApiClientToken;
import com.example.ecommerce.apiservices.TokenApi;
import com.example.ecommerce.helpers.ShowToast;
import com.example.ecommerce.models.Token;
import com.example.ecommerce.utilis.UtilitiesFunctions;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 Created by ♕ A-Cis Sapkota on 7/7/2020.
*/
 public class TokenPresenters {
        private WeakReference<View> view;

        public TokenPresenters(TokenPresenters.View view) {
            this.view = new WeakReference<>(view);

        }

        private TokenPresenters.View getView() throws NullPointerException {
            if (view != null)
                return view.get();
            else
                throw new NullPointerException("View is unavailable");
        }


        public interface View {
            void onResponseSuccess(Token token);
            void onResponseFailure(String message);

        }

        public  void token(){
            TokenApi tokenApi= ApiClientToken.getClient().create(TokenApi.class);
            tokenApi.getToken("password","admin","123456").enqueue(new Callback<Token>() {
                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {
                    if (response.isSuccessful()) {
                        if (getView() != null) {
                            getView().onResponseSuccess(response.body());
                        }
                    }
                }

                @Override
                public void onFailure(Call<Token> call, Throwable t) {
                    ShowToast.withLongMessage("NO Internet Connection.\nTry Again");
                    Log.e( "onFailure: ",t.toString() );
                    if(getView()!=null){
                        getView().onResponseFailure(UtilitiesFunctions.handleApiError(t));
                    }
                }
            });




    }

}
