package com.example.manantur2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        setupBottomNavigation();
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
                    startActivity(new Intent(MainActivity5.this, MainActivity3.class));
                    return true;
                } else if (itemId == R.id.action_profile) {
                    startActivity(new Intent(MainActivity5.this, MainActivity8.class));
                    return true;
                } else if (itemId == R.id.action_agregar) {
                    startActivity(new Intent(MainActivity5.this, MainActivity7.class));
                    return true;
                } else if (itemId == R.id.action_close) {
                    startActivity(new Intent(MainActivity5  .this, MainActivity.class));
                    return true;
                }
                return false;
            }
        });
    }

    public void abreActivity6(View view) {
        Intent intent = new Intent(view.getContext(), MainActivity6.class);
        view.getContext().startActivity(intent);
    }
}