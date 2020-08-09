package com.example.ecommerce.activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.ecommerce.R;
import com.example.ecommerce.fragment.CartFragment;
import com.example.ecommerce.fragment.HomeFragment;
import com.example.ecommerce.fragment.ProfileFragment;
import com.example.ecommerce.fragment.TrendingFragment;
import com.example.ecommerce.helpers.AppActivity;
import com.fxn.BubbleTabBar;
import com.fxn.OnBubbleClickListener;

public class Dashboard extends AppActivity implements OnBubbleClickListener {

    private BubbleTabBar bubbleTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initializedView();
        initializedListners();

    }



    @Override
    protected void initializedView() {

        bubbleTabBar=findViewById(R.id.bubbleTabBar);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new HomeFragment()).commit();


    }

    @Override
    protected void initializedListners() {

        bubbleTabBar.addBubbleListener(this);





    }




    @Override
    public void onBubbleClick(int i) {
        Fragment selectedFragment=null;
        switch (i){
            case R.id.favourite:
//                selectedFragment=new FeesFragment();
                selectedFragment=new HomeFragment();
                break;
            case    R.id.cart:
                selectedFragment=new CartFragment();
                break;
            case  R.id.home:
                selectedFragment=new HomeFragment();
                break;

            case  R.id.trending:
                selectedFragment=new TrendingFragment();
                break;

            case R.id.profile:
                selectedFragment=new ProfileFragment();
                break;

        }

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,selectedFragment).commit();



    }


}
