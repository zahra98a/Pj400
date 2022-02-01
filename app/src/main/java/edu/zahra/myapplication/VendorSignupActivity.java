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

public class VendorSignupActivity extends AppCompatActivity {

    TextView alreadyRegistered;
    EditText vendorEmail, vendorPassword, vendorConfirmPassword;
    Button btnRegister;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_signup);

        alreadyRegistered = (TextView)findViewById(R.id.tvAlreadyRegistered);
        vendorEmail = findViewById(R.id.etVendorEmail);
        vendorPassword = findViewById(R.id.etVendorPassword);
        vendorConfirmPassword = findViewById(R.id.etVendorConfirmPassword);
        btnRegister = findViewById(R.id.btnVendorRegister);
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
        String email = vendorEmail.getText().toString();
        String password = vendorPassword.getText().toString();
        String confirmPassword = vendorConfirmPassword.getText().toString();

        if(!email.matches(emailPattern)){ //if email not match the email pattern

            vendorEmail.setError("Check your email address!");
        }
        else if(password.isEmpty() || password.length()<8){ //if password less than 8 digit or it is empty

            vendorPassword.setError("Password must be 8 digit");
        }
        else if(!password.equals(confirmPassword)){ // if passwords not match each other

            vendorConfirmPassword.setError("Password must match both field");
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
                        Toast.makeText(VendorSignupActivity.this,"User registered Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(VendorSignupActivity.this,""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    public void goToNextActivity() {
        Intent intent = new Intent(this, VendorLoginActivity.class);
        startActivity(intent);
    }
   //if already signup go to login activity
    public void goLogin(View view) {
        Intent intent = new Intent(this, VendorLoginActivity.class);
        startActivity(intent);
    }
}