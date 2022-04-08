//package com.example.rcr;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.Objects;
//
//public class Profile extends AppCompatActivity {
//    DatabaseReference reference;
//    TextView name;
//    String userID;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile);
//        name=findViewById(R.id.textView83);
//        userID=getIntent().getStringExtra("Userid");
//        reference= FirebaseDatabase.getInstance().getReference("Users");
//        reference.addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.getValue()!=null)
//                {
//                    name.setText(Objects.requireNonNull(snapshot.child(userID).child("Name").getValue()).toString());
//                }
//                else
//                {
//                    //do nothing
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(Profile.this, "Umaan is great", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//
//
//    }
//}