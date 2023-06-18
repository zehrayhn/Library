    package com.example.library;

    import static android.content.Context.MODE_PRIVATE;

    import android.content.Context;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.os.Bundle;

    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;
    import androidx.fragment.app.Fragment;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.ListView;

    import com.firebase.ui.database.FirebaseRecyclerOptions;
    import com.google.android.material.button.MaterialButton;
    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.ValueEventListener;

    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;


    public class ProfileFragment extends Fragment {
    String userName;
    MaterialButton exitButton;
    private ListView  listAdd,listEnd;
    private SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        sharedPreferences = getContext().getSharedPreferences("veriler", Context.MODE_PRIVATE);
        userName = sharedPreferences.getString("username", "no login");;
        listAdd = view.findViewById(R.id.listAdd);
        listEnd = view.findViewById(R.id.listEnd);
        exitButton = view.findViewById(R.id.exitBtn);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("username");
                editor.apply();
//                Intent intent =new Intent(view.getContext(),LoginActivity.class);
//                startActivity(intent);
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users/"+userName+"/addBook");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Veri değiştiğinde bu metot çağırılır
                List<String> data = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String value = snapshot.getValue(String.class);
                    data.add(value);
                }
                Log.e("Aley",data.toString());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data);
                listAdd.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Veri okuma hatası: " + error.getMessage());
            }


        });

        DatabaseReference ref2 = database.getReference("users/"+userName+"/endBook");
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Veri değiştiğinde bu metot çağırılır
                List<String> data = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String value = snapshot.getValue(String.class);
                    data.add(value);
                }
                Log.e("Aley",data.toString());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data);
                listEnd.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Veri okuma hatası: " + error.getMessage());
            }


        });



        return  view;


    }


    }