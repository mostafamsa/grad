package com.example.mostafaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    EditText emailId, password,usernam,agee,heights,weights;
    EditText Editphone;

    private  FirebaseAuth mFirebaseAuth;
    Button btnSignUp;
    TextView tv;
    int flag2=0;
    int flag4=0;
    int flag1=0;   int flag3=0;
    int flag5=0;
    int flag6=0;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAuth = FirebaseAuth.getInstance();

        tv=(TextView)findViewById(R.id.returnLogin);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, login.class);
                startActivity(i);
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference().child("user");

        emailId = findViewById(R.id.emaill);
        password = findViewById(R.id.password);
        usernam=findViewById(R.id.user);
        btnSignUp = findViewById(R.id.btn);
        Editphone=findViewById(R.id.mobile_num);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = emailId.getText().toString();
                final String pwd = password.getText().toString();
                final String mobile = Editphone.getText().toString();
                final String name = usernam.getText().toString();



                if(name.isEmpty() && mobile.isEmpty() &&pwd.isEmpty() &&email.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Fields are empty",Toast.LENGTH_SHORT).show();


                }
                if (email.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter email",Toast.LENGTH_SHORT).show();

                }
                else if(!email.isEmpty())
                {
                    flag3=1;
                }

                if (pwd.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter password",Toast.LENGTH_SHORT).show();

                }
                if (!pwd.isEmpty()){
                    flag2=1;
                }

                if (mobile.isEmpty()){
                    Toast.makeText(MainActivity.this,"please enter  valid mobile",Toast.LENGTH_SHORT).show();

                }

                if (mobile.length() >8 ){
                    flag1=1;

                }


                if (name.isEmpty() ){
                    Toast.makeText(MainActivity.this,"Please enter name ",Toast.LENGTH_SHORT).show();
                }

                else {


                    if (flag1 == 1 && flag3==1 && flag2 == 1 ) {


                        //connect with firebase Auth
                        mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this,
                                            "Login unsuccessful: " + task.getException().getMessage(), //ADD THIS
                                            Toast.LENGTH_SHORT).show();

                                } else {

                                    String user_id = mFirebaseAuth.getCurrentUser().getUid();
                                    DatabaseReference current_user_db = mDatabase.child(user_id);
                                    current_user_db.child("email").setValue(email);
                                    current_user_db.child("password").setValue(pwd);
                                    current_user_db.child("Mobile").setValue(mobile);

                                    current_user_db.child("username").setValue(name);


                                    FirebaseUser user = mFirebaseAuth.getCurrentUser();

                                    Intent i = new Intent(MainActivity.this, login.class);
                                    startActivity(i);
                                }

                            }

                        });

                    }
                }
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
    }


}