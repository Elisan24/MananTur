package com.example.manantur2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }

    public void abreActivity3(View view) {
        // Obtén los datos del formulario
        String nombre = ((EditText) findViewById(R.id.editTextText2)).getText().toString().trim();
        String apellidoPaterno = ((EditText) findViewById(R.id.editTextText3)).getText().toString().trim();
        String apellidoMaterno = ((EditText) findViewById(R.id.editTextText4)).getText().toString().trim();
        String email = ((EditText) findViewById(R.id.editTextTextEmailAddress)).getText().toString().trim();
        String password = ((EditText) findViewById(R.id.editTextText6)).getText().toString().trim();

        // Verifica que todos los campos estén llenos
        if (nombre.isEmpty() || apellidoPaterno.isEmpty() || apellidoMaterno.isEmpty() || email.isEmpty() || password.isEmpty()) {
            // Muestra un mensaje indicando que todos los campos deben llenarse
            Toast.makeText(this, "Todos los campos deben llenarse", Toast.LENGTH_SHORT).show();
        } else {
            // Todos los campos están llenos, procede con el registro
            registrarUsuarioEnFirebase(nombre, apellidoPaterno, apellidoMaterno, email, password);
        }
    }

    private void registrarUsuarioEnFirebase(String nombre, String apellidoPaterno, String apellidoMaterno, String email, String password) {
        // Registra el usuario en Firebase Authentication
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Registro exitoso, ahora puedes guardar más información en la base de datos de Firebase
                            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            guardarDatosEnFirebase(uid, nombre, apellidoPaterno, apellidoMaterno, email, password);

                            // Muestra un mensaje indicando que el registro fue exitoso
                            Toast.makeText(MainActivity4.this, "Registro exitoso", Toast.LENGTH_SHORT).show();

                            // Redirige a MainActivity2 después del registro exitoso
                            Intent intent = new Intent(MainActivity4.this, MainActivity.class);
                            startActivity(intent);
                            finish();  // Cierra la actividad actual
                        } else {
                            // El registro falló, muestra un mensaje de error
                            String errorMessage = task.getException().getMessage();
                            if (errorMessage.contains("The email address is already in use")) {
                                // El correo electrónico ya está registrado
                                Toast.makeText(MainActivity4.this, "El correo electrónico ya está registrado", Toast.LENGTH_SHORT).show();
                            } else {
                                // Otro error
                                Toast.makeText(MainActivity4.this, "Error en el registro: " + errorMessage, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void guardarDatosEnFirebase(String userId, String nombre, String apellidoPaterno, String apellidoMaterno, String email, String password) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Usuarios");
        Usuario usuario = new Usuario(nombre, apellidoPaterno, apellidoMaterno, email, password);
        databaseReference.child(userId).setValue(usuario);
    }
}
