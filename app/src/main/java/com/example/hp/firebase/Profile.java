package com.example.hp.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {

    private Button ad,save;
    private TextView logn;
private EditText name,address;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth=FirebaseAuth.getInstance();
//        if(firebaseAuth.getCurrentUser()!=null){
//            //start profile activity directly
//            finish();
//            startActivity(new Intent(Profile.this, Login.class));
//        }
        FirebaseUser user = firebaseAuth.getCurrentUser();
        db= FirebaseDatabase.getInstance().getReference();

        name=(EditText)findViewById(R.id.name);
        address=(EditText)findViewById(R.id.address);
        ad=(Button)findViewById(R.id.logout);
        save=(Button)findViewById(R.id.save);
        logn=(TextView)findViewById(R.id.login);
        logn.setText("Welcome "+user.getEmail());


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();

            }
        });
        ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Profile.this,Login.class);
                startActivity(i);
            }
        });
    }
    private void saveUserInformation(){

      String nm=name.getText().toString().trim();
        String add=address.getText().toString().trim();
        UserInformation userInformation=new UserInformation(nm,add);
        FirebaseUser user=firebaseAuth.getCurrentUser();
        db.child(user.getUid()).setValue(userInformation);
        Toast.makeText(this, "Information Saved..", Toast.LENGTH_SHORT).show();
    }
}
