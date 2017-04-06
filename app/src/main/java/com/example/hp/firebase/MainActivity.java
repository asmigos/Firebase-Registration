package com.example.hp.firebase;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
  private  Button ad;
    private EditText email,pass;
    private TextView sign;
private ProgressDialog pb;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
firebaseAuth=FirebaseAuth.getInstance();
//        if(firebaseAuth.getCurrentUser()!=null){
//            //start profile activity directly
//            finish();
//            startActivity(new Intent(this, Profile.class));
//        }


        ad=(Button)findViewById(R.id.add);
        email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.pass);
        sign=(TextView)findViewById(R.id.sign);
        pb=new ProgressDialog(this);
        ad.setOnClickListener(this);
        sign.setOnClickListener(this);

    }

    private void registerUser(){
        String em= email.getText().toString().trim();
        String pw= pass.getText().toString().trim();

        if(TextUtils.isEmpty(em)){
            Toast.makeText(this,"please enter the email",Toast.LENGTH_SHORT).show();
       return;
        }

        if(TextUtils.isEmpty(pw)){
            Toast.makeText(this,"please enter the 6 digit password",Toast.LENGTH_SHORT).show();
            return;
        }
        pb.setMessage("you req is being processed.. please wait....!! ");
        pb.show();


        firebaseAuth.createUserWithEmailAndPassword(em,pw)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                pb.dismiss();
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"you are successfully registered",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(MainActivity.this, Profile.class));


                }else {
                    Toast.makeText(MainActivity.this,"you are failed to register",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v == ad) {
              registerUser();

        }
        if(v == sign){
            // will open login activity here
            finish();
            startActivity(new Intent(MainActivity.this,Login.class));

        }

    }
}
