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

public class PlayerSignupActivity extends AppCompatActivity {

    TextView alreadyRegistered;
    EditText PlayerEmail, PlayerPassword, PlayerConfirmPassword;
    Button btnRegister;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_signup);

        alreadyRegistered = (TextView)findViewById(R.id.tvAlreadyRegistered);
        PlayerEmail = findViewById(R.id.etPlayerEmail);
        PlayerPassword = findViewById(R.id.etPlayerPassword);
        PlayerConfirmPassword = findViewById(R.id.etPlayerConfirmPassword);
        btnRegister = findViewById(R.id.btnPlayerRegister);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                implementAuth();
            }
        });
    }
    private void implementAuth(){
       String email = PlayerEmail.getText().toString();
       String password = PlayerPassword.getText().toString();
       String confirmPassword = PlayerConfirmPassword.getText().toString();

       if(!email.matches(emailPattern)){ //if email not match the email pattern

           PlayerEmail.setError("Check your email address!");
       }
       else if(password.isEmpty() || password.length()<8){ //if password less than 8 digit or it is empty

           PlayerPassword.setError("Password must be 8 digit");
       }
       else if(!password.equals(confirmPassword)){ // if passwords not match each other

           PlayerConfirmPassword.setError("Password must match both field");
       }
       else{ // every things is correct register the user
           progressDialog.setMessage("Please wait until the registration is finish");
           progressDialog.setTitle("Registration");
           progressDialog.setCanceledOnTouchOutside(false);
           progressDialog.show();
              //create authorization  with user email & password
           mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){
                       progressDialog.dismiss();
                       goToNextActivity(); // after register successfully go to the next activity
                       Toast.makeText(PlayerSignupActivity.this,"User registered Successfully", Toast.LENGTH_SHORT).show();
                   }
                   else {
                       progressDialog.dismiss();
                       Toast.makeText(PlayerSignupActivity.this,""+task.getException(), Toast.LENGTH_SHORT).show();
                   }
               }
           });
       }
    }
    public void goToNextActivity() {
        Intent intent = new Intent(this, PlayerLoginActivity.class);
        startActivity(intent);
    }
    // if already registered go to sign in
    public void goLogin(View view) {
        Intent intent = new Intent(this, PlayerLoginActivity.class);
        startActivity(intent);
    }
}