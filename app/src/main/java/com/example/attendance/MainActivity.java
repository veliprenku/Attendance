package com.example.attendance;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.attendance.LoginTask;
import com.example.attendance.R;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView statusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);
        statusTextView = findViewById(R.id.statusTextView);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
    }

    public void attemptLogin() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        new LoginTask(new LoginTask.LoginTaskListener() {
            @Override
            public void onLoginSuccess() {
                statusTextView.setText("Login Successful");
            }

            @Override
            public void onLoginFailed() {
                statusTextView.setText("Login Failed");
            }
        }).execute(username, password);
    }
}
