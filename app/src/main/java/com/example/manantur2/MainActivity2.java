package com.example.manantur2;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    EditText emailEditText, passwordEditText;
    Button button;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.editTextTextEmailAddress2);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Verifica si los campos están vacíos antes de intentar iniciar sesión
                if (validateFields()) {
                    // Ambos campos están llenos, intenta iniciar sesión
                    signIn();
                }
            }
        });
    }

    private boolean validateFields() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            // Muestra un mensaje si uno o ambos campos están vacíos
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void signIn() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Inicio de sesión exitoso, redirige a MainActivity3
                        Intent intent = new Intent(MainActivity2.this, MainActivity6.class);
                        startActivity(intent);
                        finish(); // Opcional: para cerrar la actividad actual
                    } else {
                        // Si el inicio de sesión falla, muestra un mensaje de error
                        Toast.makeText(MainActivity2.this, "Error en el inicio de sesión", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void abreActivity4(View view) {
        Intent intent = new Intent(view.getContext(), MainActivity4.class);
        view.getContext().startActivity(intent);
    }


}
