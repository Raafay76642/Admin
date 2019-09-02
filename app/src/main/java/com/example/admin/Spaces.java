package com.example.admin;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class Spaces extends AppCompatActivity {
EditText Cfee,Bfee,Park_s;
    FirebaseAuth firebaseAuth;
    DatabaseReference mref;
    String id;
    private ProgressDialog mProgressBarsaving;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spaces);
        Cfee=findViewById(R.id.cfee);
        Bfee=findViewById(R.id.bfee);
        Park_s=findViewById(R.id.park_s);
        firebaseAuth=FirebaseAuth.getInstance();
        mProgressBarsaving = new ProgressDialog(this);
        getdata();
    }

    @Override
    public void onBackPressed() {
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
    public void UpdateS(View view){
        mref= FirebaseDatabase.getInstance().getReference("Admin Parkings");
        mProgressBarsaving.setMessage("Saving. . .!");
        mProgressBarsaving.show();
        String cfee= Cfee.getText().toString();
        String bfee= Bfee.getText().toString();
        String parkS= Park_s.getText().toString();
        if(TextUtils.isEmpty(cfee))
        {
            //email is empty
            Toast.makeText(this,"Please enter the missing information.",Toast.LENGTH_LONG).show();
            return ;

        }
        if(TextUtils.isEmpty(bfee))
        {
            //email is empty
            Toast.makeText(this,"Please enter the missing information.",Toast.LENGTH_LONG).show();
            return ;

        }
        if(TextUtils.isEmpty(parkS))
        {
            //email is empty
            Toast.makeText(this,"Please enter the missing information.",Toast.LENGTH_LONG).show();
            return ;

        }

        id= FirebaseAuth.getInstance().getCurrentUser().getUid();
        Model_class model_class=new Model_class(parkS,bfee,cfee);
        mref.child(id).child("C_fee").setValue(cfee);
        mref.child(id).child("B_fee").setValue(bfee);
        mref.child(id).child("P_space").setValue(parkS);
        getdata();
        Toast.makeText(this, "Updated!", Toast.LENGTH_SHORT).show();
        mProgressBarsaving.dismiss();


    }
    public void getdata(){

        mref= FirebaseDatabase.getInstance().getReference("Admin Parkings");
        id= FirebaseAuth.getInstance().getCurrentUser().getUid();
        mProgressBarsaving.setMessage("Please Wait");
        mProgressBarsaving.show();
        mref.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Model_class model_class= dataSnapshot.getValue(Model_class.class);
                    Cfee.setText(model_class.getC_fee());
                    Bfee.setText(model_class.getB_fee());
                    Park_s.setText(model_class.getP_space());
                    mProgressBarsaving.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
