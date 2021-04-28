package com.meowenglish.meowenglish;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity
{
    String[] Gender = { "Мужской", "Женский", "Свой"};

    private EditText edLogin, edEmail, edPasswordSignUp, edPassword, edName, edOccupation;

    private Spinner edGender;
    private FirebaseAuth mAuth;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser User = mAuth.getCurrentUser();
    }

    private void init()
    {
        edLogin = findViewById(R.id.edLogin);
        edEmail = findViewById(R.id.edEmail);
        edPasswordSignUp = findViewById(R.id.edPasswordSignUp);
        edPassword = findViewById(R.id.edPassword);
        edName = findViewById(R.id.edName);
        edGender = (Spinner) findViewById(R.id.edGender);
        edOccupation = findViewById(R.id.edOccupation);
        edPassword = findViewById(R.id.edPassword);
        mAuth = FirebaseAuth.getInstance();
    }

    public void onClickSignUp(View view)
    {

    }

    public void onClickCreateAcc(View view)
    {

    }

    public void onClickSignIn(View view)
    {

    }
}
