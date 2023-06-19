package com.example.library;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private SharedPreferences sharedPreferences, preferences;

    TextInputLayout userName, password;
    MaterialButton loginBtn, signBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        initComponents();
    }

    protected void initComponents() {
        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("veriler", Context.MODE_PRIVATE);
        preferences = getPreferences(MODE_PRIVATE);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.LoginBtn);
        signBtn = findViewById(R.id.signUp);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameInput = userName.getEditText().getText().toString();
                String passwordInput = password.getEditText().getText().toString();

                mAuth.signInWithEmailAndPassword(usernameInput, passwordInput)
                        .addOnCompleteListener(LoginActivity.this, new
                                OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            updateUI(user);
                                            setSharedPreferences(usernameInput);
                                        } else {
                                            Log.w("asd", "Giriş başarısız: ", task.getException());
                                            Toast.makeText(LoginActivity.this, "Giriş başarısız.", Toast.LENGTH_SHORT).show();
                                                    updateUI(null);
                                        }
                                    }
                                });


            }
        });
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameInput = userName.getEditText().getText().toString();
                String passwordInput = password.getEditText().getText().toString();

                mAuth.createUserWithEmailAndPassword(usernameInput, passwordInput)
                        .addOnCompleteListener(LoginActivity.this, new
                                OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            updateUI(user);
                                            setSharedPreferences(usernameInput);
                                        } else {
                                            Log.w("TAG", "Kayıt başarısız: ", task.getException());
                                            Toast.makeText(LoginActivity.this, "Kayıt başarısız.", Toast.LENGTH_SHORT).show();
                                                    updateUI(null);
                                        }
                                    }
                                });
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Kullanici daha once giris yapmis mi kontrol et; buna gore arayuzu hazirla
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    public void updateUI(FirebaseUser user) {
        if (user != null) {
            Toast.makeText(this, user.getEmail() + " giriş yaptı",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Henüz giriş yapmadınız!",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void setSharedPreferences(String usernameInput){
        String editted= usernameInput.substring(0,usernameInput.indexOf("@"));
        SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", editted);
                    editor.commit();
                    editor.apply();
    }



}
