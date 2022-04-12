package com.example.rcr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.KeyEventDispatcher;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.nearby.messages.internal.Update;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
//import com.example.android.rcr.databinding;

public class component_data extends AppCompatActivity {
    DatabaseReference databaseReference;
    FirebaseDatabase rootnode;
    EditText count;
    Button KK;
    EditText working,nonworking;





    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component_data);
        rootnode=FirebaseDatabase.getInstance();
        working=findViewById(R.id.editTextNumber2);
        nonworking=findViewById(R.id.editTextNumber3);
        databaseReference=rootnode.getReference("Components");
        Spinner spinner=findViewById(R.id.spinner);
        count=findViewById(R.id.editTextNumber);
        KK=findViewById(R.id.button7);
        KK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = spinner.getSelectedItem().toString();
                String number2=working.getText().toString();
                String number3=nonworking.getText().toString();
                String number=count.getText().toString();
                databaseReference.child(text).child("Available").setValue(number);
                databaseReference.child(text).child("Working").setValue(number2);
                databaseReference.child(text).child("Non-Working").setValue(number3);

            }
        });


        List<String> Component = new ArrayList<>();
        Component.add(0, "Select component:");
        Component.add("7-Segment Green");
        Component.add("Arduino Mega");
        Component.add("Arduino Nano");
        Component.add("Bluetooth Module");
        Component.add("DC Motor");
        Component.add("IR sensor");
        Component.add("LCD Displays");
        Component.add("Motor Driver");
        Component.add("Servo Motor");
        Component.add("UltraSonic sensor");
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Component);
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter2);





    }
}