package com.example.library;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences, preferences;

    TextInputLayout userName, password;
    MaterialButton loginBtn;


    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        initComponents();
//        bottomNav = findViewById(R.id.bottomNav);
//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().
//                findFragmentById(R.id.navHostFragment);
//        NavigationUI.setupWithNavController(bottomNav,navHostFragment.getNavController());

    }

    protected void initComponents() {
        sharedPreferences = getSharedPreferences("veriler", MODE_PRIVATE);
        preferences = getPreferences(MODE_PRIVATE);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.LoginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameInput = userName.getEditText().getText().toString();
                String passwordInput = password.getEditText().getText().toString();
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


}
