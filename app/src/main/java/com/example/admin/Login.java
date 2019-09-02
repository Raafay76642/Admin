package com.example.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    EditText Semail,Spass;
    Button blogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Semail=(EditText)findViewById(R.id.loginEmail);
        Spass=findViewById(R.id.LoginPassword);
        blogin=findViewById(R.id.blogin);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        if(firebaseAuth.getCurrentUser() != null)
        {
            Intent intent=new Intent(Login.this,MainActivity.class);
            startActivity(intent);
            this.finish();


        }
    }
    public void opensignup(View view){
        Intent intent=new Intent(this,signup.class);
        startActivity(intent);

    }
    public void Signin(View view){
        String Email = Semail.getText().toString().trim();
        String Pass = Spass.getText().toString().trim();
        if( TextUtils.isEmpty(Email))
        {
            //email is empty
            Toast.makeText(this,"Email Can't be left Blank",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(Pass))
        {
            //email is empty
            Toast.makeText(this,"Enter the Password",Toast.LENGTH_LONG).show();
            return;
        }
        //both the edit text are not empty
        progressDialog.setMessage("logging in ...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    String id=firebaseAuth.getCurrentUser().getUid();
                    Intent intent=new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                else {
                    Toast.makeText(Login.this,"Wrong Credentials",Toast.LENGTH_LONG).show();
                }
            }

        });
    }

}
