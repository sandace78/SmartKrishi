package com.example.ecommerce.presenters;

import com.example.ecommerce.apiservices.ApiClient;
import com.example.ecommerce.apiservices.TutorialApi;
import com.example.ecommerce.helpers.ShowToast;
import com.example.ecommerce.models.Tutorials;
import com.example.ecommerce.utilis.UtilitiesFunctions;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 Created by ♕ A-Cis Sapkota on 7/7/2020.
*/
 public class TutorialsPresenters {
        private WeakReference<View> view;

        public TutorialsPresenters(TutorialsPresenters.View view) {
            this.view = new WeakReference<>(view);

        }

        private TutorialsPresenters.View getView() throws NullPointerException {
            if (view != null)
                return view.get();
            else
                throw new NullPointerException("View is unavailable");
        }


        public interface View {
            void onResponseSuccess(Tutorials tutorials);
            void onResponseFailure(String message);

        }

        public  void tutorials(){
            TutorialApi tutorialApi= ApiClient.getClient().create(TutorialApi.class);
            tutorialApi.getTutorials().enqueue(new Callback<Tutorials>() {
                @Override
                public void onResponse(Call<Tutorials> call, Response<Tutorials> response) {
                    if (response.isSuccessful()) {
                        if (getView() != null) {
                            getView().onResponseSuccess(response.body());
                        }
                    }
                }

                @Override
                public void onFailure(Call<Tutorials> call, Throwable t) {
                        ShowToast.withLongMessage("NO Internet Connection.\nTry Again");
                    if(getView()!=null){
                        getView().onResponseFailure(UtilitiesFunctions.handleApiError(t));
                    }
                }
            });



    }

}
