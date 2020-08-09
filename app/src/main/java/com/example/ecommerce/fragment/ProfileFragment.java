package com.example.ecommerce.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ecommerce.R;
import com.example.ecommerce.helpers.AppFragment;
import com.example.ecommerce.helpers.EditTextToString;
import com.example.ecommerce.helpers.ShowToast;
import com.example.ecommerce.models.Customers;
import com.example.ecommerce.presenters.ProfilePresenters;
import com.example.ecommerce.utilis.Utilities;
import com.rengwuxian.materialedittext.MaterialEditText;


public class ProfileFragment extends AppFragment implements View.OnClickListener, ProfilePresenters.View {

    MaterialEditText etFirstName,etMiddleName,etLastName,etPhoneNumber;
    Button btnUpdate;
    ProfilePresenters profilePresenters;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        initializedView(view);
        initializedListners();
        return view;
    }

    @Override
    protected void initializedView(View view) {
        etFirstName=view.findViewById(R.id.etFirstName);
        etLastName=view.findViewById(R.id.etLastName);
        etMiddleName=view.findViewById(R.id.etMiddleName);
        etPhoneNumber=view.findViewById(R.id.etPhoneNumber);
        btnUpdate=view.findViewById(R.id.btnUpdate);
        profilePresenters=new ProfilePresenters(this);



    }

    @Override
    protected void initializedListners() {
        btnUpdate.setOnClickListener(this);
        etFirstName.setText(Utilities.getUserInfo().getDetails().getFirstName());
        etMiddleName.setText(Utilities.getUserInfo().getDetails().getMiddleName());
        etLastName.setText(Utilities.getUserInfo().getDetails().getLastName());
        etPhoneNumber.setText(Utilities.getUserInfo().getDetails().getContactNumber());


    }

    @Override
    public void onClick(View v) {
        profilePresenters.profile(EditTextToString.getString(etFirstName),
                EditTextToString.getString(etMiddleName),
                EditTextToString.getString(etLastName),
                EditTextToString.getString(etPhoneNumber));

    }



    @Override
    public void onProfileResponseSuccess(Customers customers) {
        ShowToast.withLongMessage("ProfileUpdated");
        Intent intent=new Intent(getActivity(),Dashboard.class);
        startActivity(intent);
    }

    @Override
    public void onProfileResponseFailure(String message) {

    }
}