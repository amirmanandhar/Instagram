package com.amir.instagram.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amir.instagram.LoginApi;
import com.amir.instagram.R;
import com.amir.instagram.Url;
import com.amir.instagram.activities.RegisterIIActivity;
import com.amir.instagram.models.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegIIFragment extends Fragment {

    Button btnNext;
    EditText email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reg_ii, container, false);

        btnNext = view.findViewById(R.id.btnRegEmail);
        email = view.findViewById(R.id.etRegEmail);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerWithEmail(email.getText().toString());
            }
        });

        return view;
    }

    private void registerWithEmail(String email) {

        Intent finalRegWithEmail = new Intent(getContext(), RegisterIIActivity.class);
        finalRegWithEmail.putExtra("regWithEmail", email);
        RegisterIIActivity.phoneOrEmail = 2;
        startActivity(finalRegWithEmail);

    }

}
