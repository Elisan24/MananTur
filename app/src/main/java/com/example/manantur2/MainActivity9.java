package com.example.manantur2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

public class MainActivity9 extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    private ImageView imageView;
    private ImageView imageViewSelected;
    private EditText editTextFecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);


        /*DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidasVentana);


        int ancho = medidasVentana.widthPixels;
        int alto = medidasVentana.heightPixels;*/

        editTextFecha = findViewById(R.id.editTextFecha);

        imageView = findViewById(R.id.imageView18);
        imageViewSelected = findViewById(R.id.imageViewSelected);
    }

    public void abrirCalendario(View view) {
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

                // Muestra la imagen en el segundo ImageView
                imageViewSelected.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void abreActivity8(View view) {

        Intent intent = new Intent(view.getContext(), MainActivity8.class);
        view.getContext().startActivity(intent);
    }
}