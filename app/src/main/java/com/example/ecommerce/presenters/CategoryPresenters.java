package com.example.ecommerce.presenters;

import com.example.ecommerce.apiservices.ApiClient;
import com.example.ecommerce.apiservices.CategoryApi;
import com.example.ecommerce.helpers.ShowToast;
import com.example.ecommerce.models.Categories;
import com.example.ecommerce.utilis.UtilitiesFunctions;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 Created by ♕ A-Cis Sapkota on 7/7/2020.
*/
 public class CategoryPresenters {
    private WeakReference<View> view;

    public CategoryPresenters(CategoryPresenters.View view) {
        this.view = new WeakReference<>(view);

    }

    private CategoryPresenters.View getView() throws NullPointerException {
        if (view != null)
            return view.get();
        else
            throw new NullPointerException("View is unavailable");
    }


    public interface View {
        void onCategoryResponseSuccess(Categories categories);

        void onCategoryResponseFailure(String message);

    }

    public void categories() {
        CategoryApi categoryApi = ApiClient.getClient().create(CategoryApi.class);
        categoryApi.getCategory().enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if (response.isSuccessful()) {
                    if (getView() != null) {
                        getView().onCategoryResponseSuccess(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                ShowToast.withLongMessage("NO Internet Connection.\nTry Again");
                if (getView() != null) {
                    getView().onCategoryResponseFailure(UtilitiesFunctions.handleApiError(t));
                }
            }
        });


    }
}
