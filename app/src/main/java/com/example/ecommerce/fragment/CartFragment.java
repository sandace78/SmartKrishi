package com.example.ecommerce.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.R;
import com.example.ecommerce.activities.CheckoutActivity;
import com.example.ecommerce.helpers.AppFragment;
import com.example.ecommerce.helpers.AppRecyclerViewAdapter;
import com.example.ecommerce.helpers.DefineClassType;
import com.example.ecommerce.models.Cart;
import com.example.ecommerce.presenters.CartPresenters;
import com.google.gson.GsonBuilder;

import java.util.List;


public class CartFragment extends AppFragment implements View.OnClickListener, CartPresenters.View {

    private RecyclerView rvCart;
    private CartRecyclerViewAdapter cartRecyclerViewAdapter;
    private CartPresenters cartPresenters;
    Button btnCheckout;
    List<Cart> cartList;
    double totalPrice;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cart, container, false);
        initializedView(view);
        initializedListners();
        prepareRecyclerView();
        return view;
    }

    @Override
    protected void initializedView(View view) {
        rvCart=view.findViewById(R.id.rvCart);
        btnCheckout=view.findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(this);

    }

    @Override
    protected void initializedListners() {
        cartPresenters=new CartPresenters(this);
        cartPresenters.getCart();

    }
    private void prepareRecyclerView() {
        cartRecyclerViewAdapter=new CartRecyclerViewAdapter();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        rvCart.setLayoutManager(linearLayoutManager);
        rvCart.setAdapter(cartRecyclerViewAdapter);

    }
    @Override
    public void onCartResponseSuccess(List<Cart> cart) {
        cartList=cart;
        cartRecyclerViewAdapter.add(cart);
        cartRecyclerViewAdapter.notifyDataSetChanged();
        Log.e( "onCartResponseSuccess: ", new GsonBuilder().create().toJson(cart));

    }

    @Override
    public void onCartResponseFailure(String message) {

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getActivity(), CheckoutActivity.class);
        intent.putExtra("totalPrice",totalPrice);
        startActivity(intent);

    }

    public class CartRecyclerViewAdapter extends AppRecyclerViewAdapter {
        Cart carts;


        @Override
        public void add(Object object) {

            carts=  DefineClassType.getType(object,Cart.class);
            Log.e( "add: ", new GsonBuilder().create().toJson(carts));
            Log.e( "add: ", new GsonBuilder().create().toJson(object));

        }

        @Override
        public void clear() {

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_product,parent,false);
            return new VHHeader(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            VHHeader vhHeader= (VHHeader) holder;
            carts=cartList.get(position);
            vhHeader.tvProductName.setText(carts.getProductName());
            vhHeader.tvQty.setText(String.valueOf(carts.getQty()));
            vhHeader.tvProductPrice.setText(String.valueOf(carts.getPrice()));
            totalPrice+=carts.getPrice();



        }

        @Override
        public int getItemCount() {
            if (cartList != null) {
                return cartList.size();
            }
            return 0;

        }
    }
    private class VHHeader extends RecyclerView.ViewHolder {
        private TextView tvProductName,tvShowDetails,tvProductPrice,tvQty;
        private ImageView imgProductImage;
        public VHHeader(View itemView) {
            super(itemView);
            tvProductName=itemView.findViewById(R.id.tvProductName);
            tvProductPrice=itemView.findViewById(R.id.tvProductPrice);
            tvQty=itemView.findViewById(R.id.tvQty);
            tvShowDetails=itemView.findViewById(R.id.tvShowDetails);
            imgProductImage=itemView.findViewById(R.id.imgProduct);




        }
    }
}