package edu.zahra.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PlayerLoginActivity extends AppCompatActivity {

    TextView playerSignup;
    EditText playerEmail, playerPassword;
    Button btnLogin;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_login);

        playerSignup = (TextView)findViewById(R.id.tvRegester);
        playerEmail = findViewById(R.id.etPlayerEmail);
        playerPassword = findViewById(R.id.etPlayerPassword);
        btnLogin = findViewById(R.id.btnLogin);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
           implementLogin();
           }
       });


        //go to register activity to create account
        playerSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayerLoginActivity.this, PlayerSignupActivity.class);
                startActivity(intent);
            }
        });
    }
    private void implementLogin() {
        String email = playerEmail.getText().toString();
        String password = playerPassword.getText().toString();

        if(!email.matches(emailPattern)){ //if email not match the email pattern

            playerEmail.setError("Check your email address!");
        }
        else if(password.isEmpty() || password.length()<8){ //if password less than 8 digit or it is empty

            playerPassword.setError("Password must be 8 digit");
        }
        else{ // every things is correct register the user
            progressDialog.setMessage("Please wait until the login is finish");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        goToNextActivity(); // when login button clicked move the next activity to display available playground list
                        Toast.makeText(PlayerLoginActivity.this,"User login Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(PlayerLoginActivity.this,""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    public void goToNextActivity() {
        Intent intent = new Intent(this, PlaygroundListActivity.class);
        startActivity(intent);
    }


}