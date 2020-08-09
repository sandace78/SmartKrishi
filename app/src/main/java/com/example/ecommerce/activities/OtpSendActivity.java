package com.example.ecommerce.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ecommerce.R;
import com.example.ecommerce.helpers.AppActivity;

public class OtpSendActivity extends AppActivity implements View.OnClickListener {
    private Button btn_submit;
    private TextView textView;
    private EditText etNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpsend);
        initializedView();
        initializedListners();

    }

    @Override
    protected void initializedView() {
        btn_submit=findViewById(R.id.btn_submit_sendotp);
        textView=findViewById(R.id.cut_Text);
        etNumber=findViewById(R.id.etNumber);




    }

    @Override
    protected void initializedListners() {

        btn_submit.setOnClickListener(this);
//        textView.setPaintFlags(textView.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit_sendotp:
                Intent intent=new Intent(OtpSendActivity.this,OtpVerifyActivity.class);
                intent.putExtra("numbers",etNumber.getText().toString());
                intent.putExtra("lat",getIntent().getDoubleExtra("lat",0));
                intent.putExtra("lon",getIntent().getDoubleExtra("lon",0));

                 startActivity(intent);

                break;
        }

    }

}
