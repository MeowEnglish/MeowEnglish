package com.meowenglish.meowenglish;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private Button edSignIn, edSignUp/*создать акк*/, onClickSignUp /*зарегестрироваться*/;
    private Spinner edGender;
    ArrayAdapter<String> adapter;
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
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Gender);
        edGender.setAdapter(adapter);

        edOccupation = findViewById(R.id.edOccupation);
        edSignIn = findViewById(R.id.edSignIn);
        edSignUp = findViewById(R.id.edSignUp);
        onClickSignUp = findViewById(R.id.onClickSignUp);
        mAuth = FirebaseAuth.getInstance();
        disableRegistInter();
    }

    /*Кнопка регистрации*/
    public void onClickSignUp(View view)
    {

    }

    /*Кнопка создания аккаунта*/
    public void onClickCreateAcc(View view)
    {
        enableRegistInter();
    }

    /*Кнопка войти*/
    public void onClickSignIn(View view)
    {
        Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(homeIntent);
        finish();
    }


    /*Убирает интерфейс регистрации и включает интрефейс входа*/
    private void disableRegistInter()
    {
        edLogin.setVisibility(View.VISIBLE);
        edEmail.setVisibility(View.GONE);
        edPasswordSignUp.setVisibility(View.GONE);
        edPassword.setVisibility(View.VISIBLE);
        edName.setVisibility(View.GONE);
        edGender.setVisibility(View.GONE);
        edOccupation.setVisibility(View.GONE);
        edSignIn.setVisibility(View.VISIBLE);
        edSignUp.setVisibility(View.VISIBLE);
        onClickSignUp.setVisibility(View.GONE);
    }

    /*Убирает интерфейс входа и включает интрефейс регистрации*/
    private void enableRegistInter()
    {
        edLogin.setVisibility(View.GONE);
        edEmail.setVisibility(View.VISIBLE);
        edPasswordSignUp.setVisibility(View.VISIBLE);
        edPassword.setVisibility(View.GONE);
        edName.setVisibility(View.VISIBLE);
        edGender.setVisibility(View.VISIBLE);
        edOccupation.setVisibility(View.VISIBLE);
        edSignIn.setVisibility(View.GONE);
        edSignUp.setVisibility(View.GONE);
        onClickSignUp.setVisibility(View.VISIBLE);
    }
}
