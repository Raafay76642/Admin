package com.example.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
EditText Email,Pass,Rpasss;
Button Signup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Email=findViewById(R.id.email);
        Pass=findViewById(R.id.Mypassword);
        Rpasss=findViewById(R.id.MypasswordRe);
        Signup=findViewById(R.id.signup);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


    }
    public void register(View view){
        String email = Email.getText().toString().trim();
        String pass = Pass.getText().toString();
        String repass = Rpasss.getText().toString();
        if(TextUtils.isEmpty(email))
        {
            //email is empty
            Toast.makeText(this,"Email Can't be left Blank",Toast.LENGTH_LONG).show();
            return ;

        }
        if(TextUtils.isEmpty(pass))
        {
            //email is empty
            Toast.makeText(this,"Pass Can't be left Blank",Toast.LENGTH_LONG).show();
            return ;

        }
        if(TextUtils.isEmpty(repass))
        {
            //email is empty
            Toast.makeText(this,"Repass Can't be left Blank",Toast.LENGTH_LONG).show();
            return ;

        }
        if(!pass.equals(repass))
        {
            //email is empty
            Toast.makeText(this,"Password Don't Match",Toast.LENGTH_LONG).show();
            return ;

        }
        progressDialog.setMessage("Registring...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
//                            id= FirebaseAuth.getInstance().getCurrentUser().getUid();

//                            Doctor_Model doctorModel =new Doctor_Model(Name,Gender,Country,Age,id,email,dep,fee,role);
//                            mref.child(id).setValue(doctorModel);
                            Toast.makeText(signup.this,"Successfully registered",Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            openSignin();
                            finish();

                        }else{
                            //display some message here
                            Toast.makeText(signup.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();



                    }
                });
    }

void openSignin(){
    Intent intent=new Intent(this,Login.class);
    startActivity(intent);
}
   public void openSignin(View view){
        Intent intent=new Intent(this,Login.class);
        startActivity(intent);
    }
}
