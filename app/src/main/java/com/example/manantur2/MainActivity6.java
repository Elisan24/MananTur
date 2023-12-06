package com.example.manantur2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity6 extends AppCompatActivity {

    RecyclerView recyclerView;
    MainAdapter2  mainAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        setupBottomNavigation();

        recyclerView=(RecyclerView) findViewById(R.id.rv2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Query query = FirebaseDatabase.getInstance().getReference().child("Eventos").orderByChild("creationTimestamp");

        /*FirebaseRecyclerOptions<MainModel2> options =
                new FirebaseRecyclerOptions.Builder<MainModel2 >()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Eventos"), MainModel2.class)
                        .build();*/

        FirebaseRecyclerOptions<MainModel2> options =
                new FirebaseRecyclerOptions.Builder<MainModel2>()
                        .setQuery(query, MainModel2.class)
                        .build();

        mainAdapter2 = new MainAdapter2(options);
        recyclerView.setAdapter(mainAdapter2);
    }


    // Método para configurar el menú de navegación
    private void setupBottomNavigation() {
        // Aquí puedes agregar la lógica del menú de navegación si es necesario
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.home) {
                    // Código para manejar la selección de "Inicio"
                    startActivity(new Intent(MainActivity6.this, MainActivity6.class));
                    return true;
                } else if (itemId == R.id.action_profile) {
                    startActivity(new Intent(MainActivity6.this, MainActivity8.class));
                    return true;
                } else if (itemId == R.id.action_agregar) {
                    startActivity(new Intent(MainActivity6 .this, MainActivity7.class));
                    return true;
                } else if (itemId == R.id.action_close) {
                    logoutAndRedirectToLogin();
                    return true;
                }
                return false;
            }
        });
    }
    private void logoutAndRedirectToLogin() {
        FirebaseAuth.getInstance().signOut();
        // Redirige al usuario a la pantalla de inicio de sesión
        Intent intent = new Intent(MainActivity6.this, MainActivity.class);
        startActivity(intent);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        finish();  // Cierra la actividad actual
    }
    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter2.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter2.stopListening(); // Cambiado de startListening() a stopListening()
    }

}