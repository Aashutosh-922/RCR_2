package com.example.rcr;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class users extends AppCompatActivity {
    DatabaseReference databaseReference4,reference;
//    DataSnapshot snapshot;
//    ActivityReadDataBinding binding;
    Spinner spinner2;
    TextView textView;
    EditText borrow,returned;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        spinner2=findViewById(R.id.spinner2);
        Button button4= findViewById(R.id.button4);
        textView=findViewById(R.id.textView34);
        borrow=findViewById(R.id.editTextNumber4);
        returned=findViewById(R.id.editTextNumber5);
        databaseReference4= FirebaseDatabase.getInstance().getReference("Components");
        reference=FirebaseDatabase.getInstance().getReference("Components");
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
        spinner2.setAdapter(myAdapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String comp=spinner2.getSelectedItem().toString();
                databaseReference4= FirebaseDatabase.getInstance().getReference("Components").child(comp).child("Available");

               databaseReference4.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       if (snapshot.getValue()!=null)
                       textView.setText(Objects.requireNonNull(snapshot.getValue()).toString());
                       else
                       {
                           // do nothing
                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });

//                Query check=databaseReference4.child(comp).orderByChild("Available").equalTo("Available");
//
//                check.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull  DataSnapshot snapshot) {
//                        if (snapshot.exists()){
//                            for (DataSnapshot Components: snapshot.getChildren()){
//                                String comp=spinner2.getAutofillValue(String.class);
//                            }
//                            Spinner spinner2=findViewById(R.id.spinner2);
//
//                            TextView textView82=findViewById(R.id.textView34);
//                            String valuefromdatabase= Objects.requireNonNull(snapshot.child(comp).child("Available").getValue()).toString();
//                            textView82.setText(valuefromdatabase);
//                        }
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(users.this,error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comp=spinner2.getSelectedItem().toString();
                Query checkcomp=FirebaseDatabase.getInstance().getReference("Components").orderByChild(comp);
                checkcomp.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String comp=spinner2.getSelectedItem().toString();
                        String borrowednum=borrow.getText().toString();
                        String returnednum=returned.getText().toString();
                        int Br=Integer.parseInt(borrowednum);
                        int Rt=Integer.parseInt(returnednum);

                        reference.child(comp).child("Working").setValue(ServerValue.increment(Rt));
                        reference.child(comp).child("Available").setValue(ServerValue.increment(Rt));
                        reference.child(comp).child("Working").setValue(ServerValue.increment(-Br));
                        reference.child(comp).child("Available").setValue(ServerValue.increment(-Br));


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(users.this,error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}