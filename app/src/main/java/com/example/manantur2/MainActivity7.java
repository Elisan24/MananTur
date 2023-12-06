package com.example.manantur2;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;

public class MainActivity7 extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
        private ImageView imageViewSelected;
    private DatabaseReference databaseReference;
    private EditText editTextNombre, editTextLugar, editTextFecha, editTextDescripcion, editTextOrganizador;
    private Button buttonGuardar;
    private String imageUrl; // Variable para almacenar la URL de la imagen

    private StorageReference storageReference;
    private String storagePath = "pet/";
    private Bitmap selectedImageBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("Eventos");

        // Solicitar permisos si es necesario (para dispositivos con Android 6.0 y superior)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PICK_IMAGE);
            }
        }

        // Inicializa las vistas
        editTextNombre = findViewById(R.id.editTextText4);
        editTextLugar = findViewById(R.id.editTextText5);
        editTextFecha = findViewById(R.id.editTextFechaa);
        editTextDescripcion = findViewById(R.id.editTextText);
        editTextOrganizador = findViewById(R.id.editTextText7);
        imageViewSelected = findViewById(R.id.imageViewSelected);
        buttonGuardar = findViewById(R.id.button5);

        // Configura el clic del botón para guardar en la base de datos
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarEnFirebase();
            }
        });
    }

    public void abrirCalendario(View view) {
        // Código para abrir el calendario
        // ...

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // El mes es devuelto en base 0, así que suma 1
                month = month + 1;

                String selectedDate = dayOfMonth + "/" + month + "/" + year;
                editTextFecha.setText(selectedDate);
            }
        },
                year, month, day);

        datePickerDialog.show();
    }

    public void abrirGaleria(View view) {
        // Código para abrir la galería
        // ...

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            try {
                // Convierte la URI a un Bitmap
                InputStream imageStream = getContentResolver().openInputStream(selectedImageUri);
                Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);


                // Muestra la imagen en imageViewSelected
                imageViewSelected.setImageBitmap(selectedImageBitmap);

                // Cierra el InputStream después de usarlo
                if (imageStream != null) {
                    imageStream.close();
                }

                // Sube la imagen a Firebase Storage y obtén la URL
                subirImagenAFirebaseStorage(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void subirImagenAFirebaseStorage(Bitmap bitmap) {
        // Crea una referencia al nodo donde se almacenarán las imágenes en Firebase Storage
        StorageReference storageRef = storageReference.child(storagePath);

        // Crea un identificador único para la imagen
        String imageFileName = "Foto" + System.currentTimeMillis() + ".jpg";
        StorageReference imageRef = storageRef.child(imageFileName);

        // Convierte el bitmap a bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        // Sube la imagen
        UploadTask uploadTask = imageRef.putBytes(data);
        uploadTask.addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                // Si la carga de la imagen fue exitosa, obtén la URL de la imagen
                imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    imageUrl = uri.toString();
                    // No es necesario llamar a guardarEnFirebase() aquí,
                    // ya que se llama en el clic del botón "Guardar"
                });
            } else {
                // Si la carga de la imagen falla, muestra un mensaje de error
                mostrarMensaje("Error al subir la imagen a Firebase Storage");
            }
        });
    }

    private void guardarEnFirebase() {
        // Obtiene los valores de los campos
        String nombre = editTextNombre.getText().toString().trim();
        String lugar = editTextLugar.getText().toString().trim();
        String fecha = editTextFecha.getText().toString().trim();
        String descripcion = editTextDescripcion.getText().toString().trim();
        String organizador = editTextOrganizador.getText().toString().trim();

        // Verifica si los campos son válidos
        if (!nombre.isEmpty() && !lugar.isEmpty() && !fecha.isEmpty() && !descripcion.isEmpty() && !organizador.isEmpty() && imageUrl != null) {
            // Crea un nuevo nodo en la base de datos con un ID único
            String eventoId = databaseReference.push().getKey();

            // Crea un objeto HashMap para representar el evento
            HashMap<String, Object> eventoMap = new HashMap<>();
            eventoMap.put("Evento", nombre);
            eventoMap.put("Lugar", lugar);
            eventoMap.put("Fecha", fecha);
            eventoMap.put("Foto", imageUrl);
            eventoMap.put("Descripcion", descripcion);
            eventoMap.put("Organizador", organizador);

            // Guarda el objeto en la base de datos
            databaseReference.child(eventoId).updateChildren(eventoMap);

            // Muestra un mensaje de éxito
            mostrarMensaje("Se registró correctamente.");

            // Redirige a MainActivity3
            startActivity(new Intent(MainActivity7.this, MainActivity6.class));
            finish(); // Esto asegura que no puedas volver a esta actividad presionando el botón "Atrás"
        } else {
            // Muestra un mensaje de error si algún campo está vacío
            mostrarMensaje("Todos los campos deben ser completados.");
        }
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    public void abreActivity3(View view) {
        Intent intent = new Intent(view.getContext(), MainActivity6.class);
        view.getContext().startActivity(intent);
    }
}
