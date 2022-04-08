package com.example.rcr;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class SignupActivity extends AppCompatActivity {
    public EditText mail;
    public EditText name;
    public EditText password;
    public EditText confirm;
    Spinner domain,Year,Branch ;
    FirebaseAuth Auth;
    FirebaseDatabase database;
    DatabaseReference reference;
    String Name,Email,Password,confirmation,domain_selected,branch_selected,year_selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name = findViewById(R.id.editTextTextPersonName);
        mail = findViewById(R.id.editTextTextEmailAddress2);
        confirm = findViewById(R.id.editTextNumberPassword2);
        password = findViewById(R.id.editTextNumberPassword);

        database=FirebaseDatabase.getInstance();
        reference=database.getReference();
        Auth = FirebaseAuth.getInstance();

        Branch = findViewById(R.id.spinner3);


        List<String> branch = new ArrayList<>();
        branch.add(0, "Select branch:");
        branch.add("IT");
        branch.add("CSE");
        branch.add("ECE");
        branch.add("Electrical");
        branch.add("Chemical");
        branch.add("Mechanical");
        branch.add("Textile");
        branch.add("Production");

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, branch);
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Branch.setAdapter(myAdapter1);

        Branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select branch:")) {
                    //Umaan is great
                }
                else{
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected:" + item, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Spinner Branch = findViewById(R.id.spinner3);
                Branch.setSelection(0);
            }
        });

        Year = findViewById(R.id.spinner4);

        List<String> year = new ArrayList<>();
        year.add(0, "Select Year");
        year.add("FY");
        year.add("SY");
        year.add("TY");

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, year);
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Year.setAdapter(myAdapter2);

        Year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Year")){
                    //No ones gonna know
                }
                else {
                    String item1 = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected" + item1, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Spinner Year=findViewById(R.id.spinner4);
                Year.setSelection(0);
            }
        });
        domain = findViewById(R.id.spinner5);

        List<String> Domain = new ArrayList<>();
        Domain.add(0, "Select Field of Interest");
        Domain.add("Software");
        Domain.add("Electronics");
        Domain.add("Mechanical");

        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Domain);
        myAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        domain.setAdapter(myAdapter3);

        domain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Select Field of Interest")){

                }
                else  {
                    String item3 = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "selected " + item3, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Spinner domain=findViewById(R.id.spinner5);
                domain.setSelection(0);
            }
        });

        Button signup = findViewById(R.id.register);

        signup.setOnClickListener(v -> {
             Name = name.getText().toString().trim();
             Email = mail.getText().toString().trim();
             Password = password.getText().toString().trim();
             confirmation = confirm.getText().toString().trim();
             domain_selected= domain.getSelectedItem().toString();
             branch_selected=Branch.getSelectedItem().toString();
             year_selected= Year.getSelectedItem().toString();


            if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(Email) || TextUtils.isEmpty(Password) || TextUtils.isEmpty(confirmation))
                Toast.makeText(SignupActivity.this, "You missed something:", Toast.LENGTH_SHORT).show();
            else if (!Password.equals(confirmation))
                Toast.makeText(SignupActivity.this, "The entered and confirmation password doesn't match.", Toast.LENGTH_SHORT).show();
            else if (Password.length() < 6)
                password.setError("The password length should be greater than 6.");



            Auth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user=Auth.getCurrentUser();
                    Toast.makeText(SignupActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    if (user != null) {
                       user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                           @Override
                           public void onSuccess(Void unused) {
                               reference.child("Users").child(Name).child("Name").setValue(Name);
                               reference.child("Users").child(Name).child("Email").setValue(Email);
                               reference.child("Users").child(Name).child("Password").setValue(Password);
                               reference.child("Users").child(Name).child("Branch").setValue(branch_selected);
                               reference.child("Users").child(Name).child("Year").setValue(year_selected);
                               reference.child("Users").child(Name).child("Domain").setValue(domain_selected);
//                               Intent user= new Intent(SignupActivity.this,Profile.class);
//                               user.putExtra("Userid",Name);

                               Toast.makeText(SignupActivity.this, "Email verification has been sent.", Toast.LENGTH_SHORT).show(); 
                           }
                       }).addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Toast.makeText(SignupActivity.this, "Email is not verified.", Toast.LENGTH_SHORT).show();
                           }
                       });
                    }
                   
                } else
                    Toast.makeText(SignupActivity.this, "Registration unsuccessful", Toast.LENGTH_SHORT).show();
            });


        });
    }
}