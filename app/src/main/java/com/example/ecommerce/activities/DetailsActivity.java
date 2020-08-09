package com.example.ecommerce.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecommerce.R;
import com.example.ecommerce.helpers.AppActivity;
import com.example.ecommerce.helpers.ShowToast;
import com.example.ecommerce.models.Products;
import com.example.ecommerce.presenters.AddToCartPresenters;
import com.example.ecommerce.utilis.Utilities;
import com.google.gson.GsonBuilder;

import okhttp3.ResponseBody;

public class DetailsActivity extends AppActivity implements AddToCartPresenters.View, View.OnClickListener {
    AddToCartPresenters addToCartPresenters;
    TextView tvBuyNow,tvName,tvProductDetails;
    TextView tvPrice;
    ImageView imgProduct;
    Products.Detail detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle b=getIntent().getExtras();
         detail= (Products.Detail) b.getSerializable("product");
        initializedView();
        initializedListners();


    }

    @Override
    protected void initializedView() {
        tvBuyNow=findViewById(R.id.tvBuyNow);
        tvPrice=findViewById(R.id.tvPrice);
        imgProduct=findViewById(R.id.imgProduct);
        tvName=findViewById(R.id.tvName);
        tvProductDetails=findViewById(R.id.tvProductDetails);

    }

    @Override
    protected void initializedListners() {
        tvBuyNow.setOnClickListener(this);
        tvPrice.setText(String.valueOf(detail.getCurrentPrice()));
        tvProductDetails.setText(detail.getDescriptionEnglish());
        tvName.setText(detail.getNameinEnglish());

        addToCartPresenters =new AddToCartPresenters(this);


    }

    @Override
    public void onCartResponseSuccess(ResponseBody responseBody) {
        Log.e( "onCartResponseSuccess: ", new GsonBuilder().create().toJson(responseBody));
        ShowToast.withLongMessage("Added Successfully");

    }

    @Override
    public void onCartResponseFailure(String message) {
        Log.e( "onCartResponseFailure: ", message);

    }

    @Override
    public void onClick(View v) {
        int userID= Utilities.getUserInfo().getDetails().getId();
        addToCartPresenters.cart(detail.getId(),userID,detail.getQuantity(),detail.getCurrentPrice());

    }
}