package com.example.tourloginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
EditText etuser,etfather,etcontact,etpass,etcnic,etconfirmpass;
Button btnSignup;
ProgressBar progressBar;
FirebaseAuth firebaseAuth;
DatabaseReference databaseRefrence;
FirebaseDatabase firebaseDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etuser=findViewById(R.id.etUser);
        etfather=findViewById(R.id.etFathername);
        etcontact=findViewById(R.id.etContact);
        etcnic=findViewById(R.id.etCnic);
        etconfirmpass=findViewById(R.id.etConfirmPassword);
        etpass=findViewById(R.id.etPassword);
        progressBar=findViewById(R.id.progressBar);
        btnSignup=findViewById(R.id.btnSignup);
        firebaseAuth=FirebaseAuth.getInstance();

        databaseRefrence=FirebaseDatabase.getInstance().getReference("Tourists");


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String fatherinfo = etfather.getText().toString().trim();
                final String contactinfo = etcontact.getText().toString().trim();
                final String cnicinfo = etcnic.getText().toString().trim();
                final String email = etuser.getText().toString().trim();
                final String password = etpass.getText().toString().trim();
                String confirmPassword = etconfirmpass.getText().toString().trim();

                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(signup.this, "Enter Emial", Toast.LENGTH_SHORT).show();
                    etuser.requestFocus();
                return;
                }
                else if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(signup.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    etpass.requestFocus();
                    return;
                }
                else if (TextUtils.isEmpty(confirmPassword))
                {
                    Toast.makeText(signup.this, "Please Confirm Password", Toast.LENGTH_SHORT).show();
                    etconfirmpass.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(fatherinfo))
                {
                    Toast.makeText(signup.this, "Enter FatherName", Toast.LENGTH_SHORT).show();
                    etfather.requestFocus();
                    return;
                }  if (TextUtils.isEmpty(cnicinfo))
                {
                    Toast.makeText(signup.this, "Enter CNIC", Toast.LENGTH_SHORT).show();
                    etcnic.requestFocus();
                    return;
                }  if (TextUtils.isEmpty(contactinfo))
                {
                    Toast.makeText(signup.this, "Please Enter Contact", Toast.LENGTH_SHORT).show();
                    etcontact.requestFocus();
                    return;
                }

                else if (password.length()<6)
                {
                    Toast.makeText(signup.this, "Password Too Short", Toast.LENGTH_SHORT).show();
                }

                if(password.equals(confirmPassword))
                {

                    progressBar.setVisibility(View.VISIBLE);

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);




                                    if (task.isSuccessful()) {
                                        Tourist_info touristInfo=new Tourist_info(
                                                email ,password , cnicinfo,contactinfo,fatherinfo
                                        );


                                        FirebaseDatabase.getInstance().getReference("Tourists")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(touristInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {


                                                Toast.makeText(signup.this, "Registration Successfull", Toast.LENGTH_SHORT).show();

                                            }
                                        });








                                        Intent intent = new Intent(signup.this, login.class);
                                        startActivity(intent);
                                        Toast.makeText(signup.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(signup.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
            }
        });


    }
}
