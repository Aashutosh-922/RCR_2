package com.example.rcr;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Index extends AppCompatActivity {
String checkadmin;
FirebaseAuth mAuth;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.Team:
                Intent intent = new Intent(Index.this,Team.class);
                startActivity(intent);
                return true;
            case R.id.Home:
                Intent home = new Intent(getApplicationContext(),Index.class);
                startActivity(home);
                return true;
            case R.id.admin:
                checkadmin=getIntent().getStringExtra("User");
                if (checkadmin.equals("2020bec045@sggs.ac.in")){
                    Intent Admin= new Intent(getApplicationContext(),component_data.class);
                    startActivity(Admin);
                    return true;
                }
                else{
                    Toast.makeText(getApplicationContext(), "Invalid ID", Toast.LENGTH_SHORT).show();
                    return false;
                }
            case R.id.AboutRNXG:
                Intent RNXG=new Intent(getApplicationContext(),AboutRNXG.class);
                startActivity(RNXG);
                return true;
            case R.id.Aboutsggs:
                Intent SGGS=new Intent(getApplicationContext(),AboutSGGS.class);
                startActivity(SGGS);
                return true;
//            case R.id.User:
//                Intent profile=new Intent(getApplicationContext(),Profile.class);
//                startActivity(profile);
//                return true;

        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ImageButton website= findViewById(R.id.imageButton3);
        ImageButton compdata=findViewById(R.id.imageButton5);
        ImageButton facebook=findViewById(R.id.imageButton9);
        ImageButton instagram=findViewById(R.id.imageButton6);
        ImageButton linkedin=findViewById(R.id.imageButton7);
        ImageButton Achievement=findViewById(R.id.imageButton);

        compdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent3 = new Intent(Index.this,users.class);
                startActivity(intent3);
            }
        });

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://rnxgsggs.co.in/home"));
                startActivity(intent);
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fb= new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/rnxgsggs/"));
                startActivity(fb);
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent insta= new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/sggs_rnxg/"));
                startActivity(insta);
            }
        });

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent linked_in= new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/company/rnxg/"));
                startActivity(linked_in);
            }
        });

        Achievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Achieve_1= new Intent(Index.this,Achivements.class);
                startActivity(Achieve_1);
            }
        });

    }

    public void signout(MenuItem item) {
        Intent logout=new Intent(getApplicationContext(),login_activity.class);
        startActivity(logout);
        finish();
    }
}