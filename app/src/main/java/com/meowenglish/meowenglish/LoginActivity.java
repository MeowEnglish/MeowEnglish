package com.meowenglish.meowenglish;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.badge.BadgeDrawable;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity
{
    String[] Gender = { "Мужской", "Женский", "Свой "};

    private EditText edLogin, edEmail, edPasswordSignUp, edPassword, edName, edOccupation;
    private Spinner edGender;
    ArrayAdapter<String> adapter;
    FirebaseUser User;

    private ViewGroup signInLayout;
    private ViewGroup logInLayout;

    private FirebaseAuth mAuth;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        init();
        Toast.makeText(this,"Тест", Toast.LENGTH_SHORT);
    }

    @Override
    protected void onStart() {
        super.onStart();
        User = mAuth.getCurrentUser();
        if  (User != null)
        {
            finishLogin();
        }
    }

    public void finishLogin()
    {
        User = mAuth.getCurrentUser();
        if (User.isEmailVerified()) {
            Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(homeIntent);
            finish();
        }
    }


    private void init()
    {
        signInLayout = findViewById(R.id.signInLayout);
        logInLayout = findViewById(R.id.logInLayout);

        edLogin = findViewById(R.id.edLogin);
        edEmail = findViewById(R.id.edEmail);
        edPasswordSignUp = findViewById(R.id.edPasswordSignUp);
        edPassword = findViewById(R.id.edPassword);
        edName = findViewById(R.id.edName);
        edOccupation = findViewById(R.id.edOccupation);

        edGender = (Spinner) findViewById(R.id.edGender);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Gender);
        edGender.setAdapter(adapter);

        mAuth = FirebaseAuth.getInstance();
        disableRegistInter();
    }

    /*Кнопка регистрации*/
    public void onClickSignUp(View view)
    {
        if (!TextUtils.isEmpty(edEmail.getText().toString()) && !TextUtils.isEmpty(edName.getText().toString())
                && !TextUtils.isEmpty(edPasswordSignUp.getText().toString()))
        {
            mAuth.createUserWithEmailAndPassword(edEmail.getText().toString(), edPasswordSignUp.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(getApplicationContext(), "Регистрация прошла успешно", Toast.LENGTH_SHORT).show();
                        SendEmailVerif();
                        disableRegistInter();
                        Toast.makeText(getApplicationContext(), "Регистрация прошла успешно. Подтвердите Email", Toast.LENGTH_SHORT).show();
                        disableRegistInter();
                        SendEmailVerif();
                        edLogin.setText(edEmail.getText());
                        edPassword.setText(edPasswordSignUp.getText());
                        edRepeatSendEmail.setVisibility(View.VISIBLE);
                        databaseReference = FirebaseDatabase.getInstance().getReference();
                        user = new User(edName.getText().toString(), edEmail.getText().toString());
                        databaseReference.push().setValue(user);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Регестрация не удалась", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {
            Toast.makeText(this,"Заполните все поля", Toast.LENGTH_SHORT).show();
        }

    }

    /*Кнопка создания аккаунта*/
    public void onClickCreateAcc(View view)
    {
        enableRegistInter();
<<<<<<< HEAD
=======
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onClickSendPasswordReset(View view)
    {
        if (!TextUtils.isEmpty(edLogin.getText().toString()))
        {
            User = mAuth.getCurrentUser();
            mAuth.sendPasswordResetEmail(edLogin.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>()
            {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Письмо с инструкцией отправлено на почту", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Ошибка восстановления", Toast.LENGTH_SHORT).show();
                            }
                            edLogin.setVisibility(View.VISIBLE);
                            edPassword.setVisibility(View.VISIBLE);
                            edSignIn.setVisibility(View.VISIBLE);
                            edSignUp.setVisibility(View.VISIBLE);
                            sendPasswordReset.setVisibility(View.GONE);
                            PasswordReset.setVisibility(View.VISIBLE);
                            actionBar.setDisplayHomeAsUpEnabled(false);
                        }
                    });
        }
        else {
            Toast.makeText(this, "Введите логин (Email)", Toast.LENGTH_SHORT).show();
        }

    }

    // Reset password
    public void onClickPasswordReset(View view)
    {
        edRepeatSendEmail.setVisibility(View.GONE);
        edLogin.setVisibility(View.VISIBLE);
        edEmail.setVisibility(View.GONE);
        edPasswordSignUp.setVisibility(View.GONE);
        edPassword.setVisibility(View.GONE);
        edName.setVisibility(View.GONE);
        edGender.setVisibility(View.GONE);
        edOccupation.setVisibility(View.GONE);
        edSignIn.setVisibility(View.GONE);
        edSignUp.setVisibility(View.GONE);
        onCSignUp.setVisibility(View.GONE);
        PasswordReset.setVisibility(View.GONE);
        sendPasswordReset.setVisibility(View.VISIBLE);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                disableRegistInter();
                actionBar.setDisplayHomeAsUpEnabled(false);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
>>>>>>> f6295596df7d8932a99f58559efb419018bb917a
    }

    /*Кнопка войти*/
    public void onClickSignIn(View view)
    {
        if (!TextUtils.isEmpty(edLogin.getText().toString()) && !TextUtils.isEmpty(edPassword.getText().toString()))
        {
            User = mAuth.getCurrentUser();
            if (User != null && User.getEmail().toString() == edLogin.getText().toString()) {
                if (!User.isEmailVerified())
                    Toast.makeText(getApplicationContext(), "Подтвердите электронную почту", Toast.LENGTH_SHORT).show();
<<<<<<< HEAD
=======
                    Toast.makeText(getApplicationContext(), "Если вы уже подтвердили Email и не можете войти, подождите немного. База данных обновляется", Toast.LENGTH_SHORT).show();
                    edRepeatSendEmail.setVisibility(View.VISIBLE);
                } else {
                    mAuth.signInWithEmailAndPassword(edLogin.getText().toString(), edPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Вход выполнен", Toast.LENGTH_SHORT).show();
                                finishLogin();
                            } else {
                                Toast.makeText(getApplicationContext(), "Ошибка входа", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
>>>>>>> f6295596df7d8932a99f58559efb419018bb917a
            }
            else {
                mAuth.signInWithEmailAndPassword(edLogin.getText().toString(), edPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Вход выполнен", Toast.LENGTH_SHORT).show();
                            finishLogin();
                        } else {
                            Toast.makeText(getApplicationContext(), "Ошибка входа", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
        else{
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
        }

    }

    private void SendEmailVerif() {
        if (User != null)
        {
            User.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Проверь свой Email, для подтверждения аккаунта", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Ошибка, письмо не отправлено", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    /*Убирает интерфейс регистрации и включает интрефейс входа*/
    private void disableRegistInter()
    {
        signInLayout.setVisibility(View.VISIBLE);
        logInLayout.setVisibility(View.GONE);
    }

    /*Убирает интерфейс входа и включает интрефейс регистрации*/
    private void enableRegistInter()
    {
        signInLayout.setVisibility(View.GONE);
        logInLayout.setVisibility(View.VISIBLE);
    }
}
