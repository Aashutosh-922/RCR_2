package com.example.rcr;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, login_activity.class);
            startActivity(intent);
        });
        Button button2=findViewById(R.id.button2);

        button2.setOnClickListener(view -> {
            Intent sign= new Intent(MainActivity.this,SignupActivity.class);
            startActivity(sign);
        });
    }
}