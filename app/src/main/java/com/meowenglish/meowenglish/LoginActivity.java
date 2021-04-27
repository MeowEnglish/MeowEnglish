package com.meowenglish.meowenglish;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity
{
    private EditText edLogin, edPassword, edName, edGender, edOccupation;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        init();
    }
    private void init()
    {
        edLogin = findViewById(R.id.edLogin);
        edPassword = findViewById(R.id.edPassword);
    }

    public void onClickSignUp(View view)
    {

    }

    public void onClickSignIn(View view)
    {

    }
}
