package com.example.rcr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class login_activity extends AppCompatActivity {
    EditText editTextTextEmailAddress, editTextTextPassword;
    ImageButton button3;
    String username,password;
    FirebaseAuth mAuth;
//    WebView webview;
//    private CheckBox checkBox;


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        editTextTextEmailAddress=findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword=findViewById(R.id.editTextTextPassword);

//        webview.getSettings().setJavaScriptEnabled(true);
//        webview.setWebViewClient(new WebViewClient());
//        webview.loadUrl("");
        button3 = findViewById(R.id.imageButton2);
        button3.setOnClickListener(v -> {

            username = editTextTextEmailAddress.getText().toString();
            password = editTextTextPassword.getText().toString();



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


    }


}
