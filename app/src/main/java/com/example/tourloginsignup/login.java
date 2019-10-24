package com.example.tourloginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText mail,pwd;
    Button btnLogin;
    TextView tvSignup;
    private FirebaseAuth firebaseAuth;
    int currentItem = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

      mail=findViewById(R.id.tvUser);
      pwd=findViewById(R.id.tvPassword);
        btnLogin=findViewById(R.id.btnLogin);
        tvSignup=findViewById(R.id.tvSignup);
        firebaseAuth=FirebaseAuth.getInstance();
        Spinner spinner = findViewById(R.id.spinner);

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(login.this,signup.class);
                startActivity(intentMain);
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (currentItem == position){
                    return;
                }else if(position ==2){
                    Intent i =new Intent(login.this,signup.class);
                    startActivity(i);
                }else
                {
                    Intent i =new Intent(login.this,GuideInformation.class);
                    startActivity(i);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });











      btnLogin.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            String email= mail.getText().toString().trim();
              String password= pwd.getText().toString().trim();

              if (TextUtils.isEmpty(email))
              {
                  Toast.makeText(login.this, "Please Enter Emial", Toast.LENGTH_SHORT).show();
                  mail.requestFocus();
              }
              if (TextUtils.isEmpty(password))
              {
                  Toast.makeText(login.this, "Please Enter Emial", Toast.LENGTH_SHORT).show();
                  pwd.requestFocus();
              }
              if (password.length()<6)
              {
                  Toast.makeText(login.this, "Password Too Short! Enter Valid Password", Toast.LENGTH_SHORT).show();

              }


              firebaseAuth.signInWithEmailAndPassword(email, password)
                      .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                              if (task.isSuccessful()) {
                                startActivity(new Intent(getApplicationContext(),Tourist_Dashboard.class));
                                  Toast.makeText(login.this, "Login SuccessFull", Toast.LENGTH_SHORT).show();

                              } else {
                                  Toast.makeText(login.this, "Register First! Account Not Found", Toast.LENGTH_SHORT).show();

                              }

                          }
                      });


























          }
      });


    }
}
