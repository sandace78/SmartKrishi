package com.example.ecommerce.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.ecommerce.R;
import com.example.ecommerce.helpers.AppActivity;
import com.example.ecommerce.helpers.EditTextToString;
import com.example.ecommerce.helpers.ShowToast;
import com.example.ecommerce.presenters.CheckoutPresenters;
import com.rengwuxian.materialedittext.MaterialEditText;

import okhttp3.ResponseBody;

public class CheckoutActivity extends AppActivity implements CheckoutPresenters.View, View.OnClickListener, AdapterView.OnItemSelectedListener {
CheckoutPresenters checkoutPresenters;
Button btnCheckout;
double totalPrice;
MaterialEditText etAddress,etPhoneNumber,etEmail;
    String[] paymentType = { "Cash On Delivery", "Digital Wallet", "Credit Card"};
    String SelectedPaymentType ="Cash On Delivery";
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        initializedView();
        initializedListners();
    }

    @Override
    protected void initializedView() {
        checkoutPresenters=new CheckoutPresenters(this);
        btnCheckout=findViewById(R.id.btnCheckout);
        etAddress=findViewById(R.id.etAddress);
        etPhoneNumber=findViewById(R.id.etPhoneNumber);
        etEmail=findViewById(R.id.etEmail);
        spinner=findViewById(R.id.spinner);
        btnCheckout.setOnClickListener(this);
        totalPrice=getIntent().getDoubleExtra("totalPrice",0);
        Log.e( "initializedView: ", totalPrice+"");

    }

    @Override
    protected void initializedListners() {
        ArrayAdapter arrayAdapter=new ArrayAdapter(CheckoutActivity.this,android.R.layout.simple_spinner_item, paymentType);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onCheckoutResponseSuccess(ResponseBody responseBody) {
        Intent intent=new Intent(CheckoutActivity.this,Dashboard.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onCheckoutResponseFailure(String message) {
        ShowToast.withLongMessage(message);

    }

    @Override
    public void onClick(View v) {
        checkoutPresenters.getCart(EditTextToString.getString(etAddress),SelectedPaymentType,totalPrice);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        SelectedPaymentType = paymentType[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}