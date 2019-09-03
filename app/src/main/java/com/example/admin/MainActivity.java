package com.example.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    LinearLayout profile,spaces,bookings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookings=findViewById(R.id.bookings);
        bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openbooking();
            }
        });
        profile=findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openprofile();
            }
        });
        spaces=findViewById(R.id.spaces);
        spaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSpaces();
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
    }

  public void   openSpaces()
  {
      Intent intent=new Intent(this,Spaces.class);
      startActivity(intent);
  }
  public void openprofile(){
      Intent intent=new Intent(this,Profile.class);
      startActivity(intent);
  }
  public void openbooking(){
        Intent intent=new Intent(this,MyBookings.class);
        startActivity(intent);
  }
    public void Signout(View view){
        firebaseAuth.signOut();
        Intent intent=new Intent(this,Login.class);
        startActivity(intent);
        finishAffinity();
    }
}
