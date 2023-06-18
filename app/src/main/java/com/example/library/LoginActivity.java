package com.example.library;

import androidx.annotation.NonNull;
<<<<<<< HEAD
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Context;
=======
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

>>>>>>> origin/master
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
<<<<<<< HEAD
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
=======


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
>>>>>>> origin/master
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
<<<<<<< HEAD
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
=======
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences, preferences;

    TextInputLayout userName, password;
    MaterialButton loginBtn;
>>>>>>> origin/master


    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        initComponents();
<<<<<<< HEAD
    }

    protected void initComponents() {
        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("veriler", Context.MODE_PRIVATE);
=======
//        bottomNav = findViewById(R.id.bottomNav);
//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().
//                findFragmentById(R.id.navHostFragment);
//        NavigationUI.setupWithNavController(bottomNav,navHostFragment.getNavController());

    }

    protected void initComponents() {
        sharedPreferences = getSharedPreferences("veriler", MODE_PRIVATE);
>>>>>>> origin/master
        preferences = getPreferences(MODE_PRIVATE);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.LoginBtn);
<<<<<<< HEAD
        signBtn = findViewById(R.id.signUp);
=======
>>>>>>> origin/master
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameInput = userName.getEditText().getText().toString();
                String passwordInput = password.getEditText().getText().toString();
<<<<<<< HEAD

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

=======
                Boolean userHas = userHas(usernameInput);
                Boolean userWhoHasPassword = false;
                if (userHas) {
                    userWhoHasPassword = correctPassword(usernameInput, passwordInput);
                }
                if (userWhoHasPassword) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username", usernameInput);
//                    editor.putString("", txtSoyisim.getText().toString());
//                    editor.putInt("yas", Integer.parseInt(txtYas.getText().toString()));
                    editor.commit(); // editor.apply();

                    Intent startApp = new Intent(LoginActivity.this, MainActivity.class);
                }

            }
        });
    }

    protected boolean userHas(String user) {
        final boolean[] hasUser = {false};
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users/" + user);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Anahtar mevcut
                    hasUser[0] = true;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        return hasUser[0];
    }

    protected boolean correctPassword(String user, String pass) {
        final boolean[] hasUser = {false};
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users/" + user + "/password");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                hasUser[0] = pass.equals((String) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Veri okuma hatası: " + error.getMessage());
            }
        });

        return hasUser[0];
    }
>>>>>>> origin/master


}
