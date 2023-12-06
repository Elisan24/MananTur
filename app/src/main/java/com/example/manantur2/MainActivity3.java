package com.example.manantur2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // Configura el menú de navegación
        setupBottomNavigation();
    }

    // Método para configurar el menú de navegación
    protected void setupBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.home) {
                    // Código para manejar la selección de "Inicio"
                    startActivity(new Intent(MainActivity3.this, MainActivity3.class));
                    return true;
                } else if (itemId == R.id.action_profile) {
                    startActivity(new Intent(MainActivity3.this, MainActivity8.class));
                    return true;
                } else if (itemId == R.id.action_agregar) {
                    startActivity(new Intent(MainActivity3.this, MainActivity7.class));
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
        Intent intent = new Intent(MainActivity3.this, MainActivity.class);
        startActivity(intent);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        finish();  // Cierra la actividad actual
    }



    public void abreActivity5(View view) {
        Intent intent = new Intent(view.getContext(), MainActivity5.class);
        view.getContext().startActivity(intent);
    }
}
