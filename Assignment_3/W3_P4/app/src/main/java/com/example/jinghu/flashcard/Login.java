package com.example.jinghu.flashcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    public static final String USERNAME = "com.example.jinghu.USERNAME";
    String correct_user = "Jim";
    String correct_pass = "pass1234";

    Button login_button;
    EditText login_username;
    EditText login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_button = findViewById(R.id.login_button);
        login_username = findViewById(R.id.login_username);
        login_password = findViewById(R.id.login_password);
    }

    public void tryLogin(View view) {
        if (login_username.getText().toString().equals(correct_user)
                && login_password.getText().toString().equals(correct_pass)) {
            Intent intent = new Intent(this, flashcard.class);
            intent.putExtra(USERNAME, login_username.getText().toString());
            startActivity(intent);
        }
    }
}
