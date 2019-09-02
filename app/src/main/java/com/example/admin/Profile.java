package com.example.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    DatabaseReference mref;
    String id;
    private ProgressDialog mProgressBarsaving;
    EditText oname,pname,mloc,address;
    String mloclangitude,mloclatitude;
    SharedPreferences readData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        oname=findViewById(R.id.oname);
        pname=findViewById(R.id.pname);
        mloc=findViewById(R.id.mLoc);
        address=findViewById(R.id.eaddress);
        mref= FirebaseDatabase.getInstance().getReference();
        firebaseAuth=FirebaseAuth.getInstance();
        mProgressBarsaving = new ProgressDialog(this);
        getData();

        // Rafay Code
        readData= getSharedPreferences("DataParkedAdmin", MODE_PRIVATE);
        mloclatitude = readData.getString("ParkingAdminlatitude","");
        mloclangitude = readData.getString("ParkingAdminlongitude","");
        Toast.makeText(Profile.this,mloclangitude+mloclatitude,
                Toast.LENGTH_LONG).show();
    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
    public void Update(View view){
        mref= mref.child("Admin Parkings");
        mProgressBarsaving.setMessage("Saving. . .!");
        mProgressBarsaving.show();
        String Oname= oname.getText().toString();
        String Pname= pname.getText().toString();
        String Mloc= mloc.getText().toString();
        String Address=address.getText().toString();
        if(TextUtils.isEmpty(Oname))
        {
            //email is empty
            Toast.makeText(this,"Owner's name Can't be left Blank",Toast.LENGTH_LONG).show();
            return ;

        }
        if(TextUtils.isEmpty(Pname))
        {
            //email is empty
            Toast.makeText(this,"Parking name Can't be left Blank",Toast.LENGTH_LONG).show();
            return ;

        }
        if(TextUtils.isEmpty(Mloc))
        {
            //email is empty
            Toast.makeText(this,"Please select location",Toast.LENGTH_LONG).show();
            return ;

        }
        if(TextUtils.isEmpty(Address))
        {
            //email is empty
            Toast.makeText(this,"Address Can't be left Blank",Toast.LENGTH_LONG).show();
            return ;

        }
        id=FirebaseAuth.getInstance().getCurrentUser().getUid();
        mref.child(id).child("Owner_name").setValue(Oname);
        mref.child(id).child("P_name").setValue(Pname);
        mref.child(id).child("Longi").setValue(Mloc);
        mref.child(id).child("Address").setValue(Address);
        mProgressBarsaving.dismiss();


    }
    public void getData(){
        id=firebaseAuth.getInstance().getCurrentUser().getUid();
        mProgressBarsaving.setMessage("Please Wait");
        mProgressBarsaving.show();
        mref.child("Admin Parkings").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists())
            {
                Model_class model_class= dataSnapshot.getValue(Model_class.class);
                 oname.setText(model_class.getOwner_name());
                pname.setText(model_class.getP_name());
                mloc.setText(model_class.getLati());
                address.setText(model_class.getAddress());
                mProgressBarsaving.dismiss();
            }
            else {
                Toast.makeText(Profile.this, "Complete Your Profile please", Toast.LENGTH_SHORT).show();
                mProgressBarsaving.dismiss();
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }
    public void opendroplocation(View view)
    {
        Intent intent=new Intent(this,DropLocation.class);
        startActivity(intent);

    }
}
