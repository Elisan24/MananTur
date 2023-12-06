


package com.example.manantur2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity8 extends AppCompatActivity {
    RecyclerView recyclerView;

    MainAdapter mainAdapter;

    private ImageView imageView16;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            // Si el usuario está autenticado, obtener el UID
            String userID = user.getUid();

        }


        recyclerView=(RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Usuarios").orderByChild("creationTimestamp"), MainModel.class)
                        .build();


        mainAdapter=new MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);

        imageView16 = findViewById(R.id.imageView16);



        imageView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity8.this, MainActivity9.class));
            }

        });


    }



    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();

}

    public void abreActivity6(View view) {
        Intent intent = new Intent(view.getContext(), MainActivity6.class);
        view.getContext().startActivity(intent);
    }
}


























/*
package com.example.manantur2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity8 extends AppCompatActivity {

    private SharedPreferences preferences;
    private static final String PREFS_NAME = "ExpensesPrefs";
    private static final String EXPENSES_KEY = "expenses";

    RecyclerView recyclerView;
    MainAdapter mainAdapter;

    private ImageView imageView16;

    private FirebaseAuth mAuth;
    private DatabaseReference usersDatabase;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        mAuth = FirebaseAuth.getInstance();
        usersDatabase = FirebaseDatabase.getInstance().getReference("Usuarios");

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            userID = user.getUid();
        } else {
            Toast.makeText(this, "Error de Autenticación", Toast.LENGTH_SHORT).show();
            return;  // Salir del método si no hay usuario autenticado
        }

        // Modificar la consulta para obtener solo los datos del usuario actual
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(usersDatabase.child(userID), MainModel.class)
                        .build();

        mainAdapter = new MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);

        imageView16 = findViewById(R.id.imageView16);

        imageView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity8.this, MainActivity9.class));
            }
        });

        // Mostrar datos específicos del usuario autenticado
        mostrarDatos(userID);
    }

    private void mostrarDatos(String userID) {
        DatabaseReference userRef = usersDatabase.child(userID);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Usuario usuario = dataSnapshot.getValue(Usuario.class);
                    if (usuario != null) {
                        // Aquí puedes usar los datos del usuario según sea necesario
                        // Por ejemplo, puedes mostrarlos en la interfaz de usuario o realizar alguna acción
                        Toast.makeText(MainActivity8.this, "Datos del usuario: " + usuario.getNombre(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar errores de lectura de la base de datos
                Toast.makeText(MainActivity8.this, "Error al obtener datos del usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }
}
*/
