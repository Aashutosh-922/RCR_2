package com.example.rcr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class login_activity extends AppCompatActivity {
    EditText editTextTextEmailAddress, editTextTextPassword;
    Button button3;
    String username,password;
    FirebaseAuth mAuth;
    private CheckBox checkBox;


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);

        checkBox = findViewById(R.id.checkBox);
        mAuth = FirebaseAuth.getInstance();

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(v -> {

            username = editTextTextEmailAddress.getText().toString();
            password = editTextTextPassword.getText().toString();
            SharedPreferences preferences = getSharedPreferences("CheckBox", MODE_PRIVATE);
            String CheckBox = preferences.getString("remember", "");
            if (CheckBox.equals("true")) {

                Intent intent = new Intent(login_activity.this, Index.class);
                startActivity(intent);
            } else if (CheckBox.equals("false")) {
                Toast.makeText(this, "please Sign In BRO!!", Toast.LENGTH_SHORT).show();
            }


            if (TextUtils.isEmpty(username)) {
                editTextTextEmailAddress.setError("Please enter email");
                return;
            }
            if (TextUtils.isEmpty(password)) {
                editTextTextPassword.setError("Please enter password");
            } else {
                mAuth.signInWithEmailAndPassword(username, password).addOnSuccessListener(authResult -> {
                    Intent intent3 = new Intent(login_activity.this, Index.class);
                    intent3.putExtra("User",username);
                    startActivity(intent3);
                }).addOnFailureListener(e -> Toast.makeText(login_activity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show());
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("CheckBox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "true");

                    editor.apply();
                    Toast.makeText(login_activity.this, "Checked", Toast.LENGTH_SHORT).show();
                } else if (!buttonView.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("CheckBox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                    Toast.makeText(login_activity.this, "UnChecked", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }


}
