package com.example.carnetv30;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Crear extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView foto;
    Button capturar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);
        foto = findViewById(R.id.imgFoto);
        capturar=findViewById(R.id.btnCapturar);
        // Agrega un OnClickListener a tu botón de captura
        capturar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inicia la actividad "Crear"
                capturarFoto();
            }
        });
        findViewById(R.id.btnCapturar).setOnClickListener(view -> capturarFoto());
    }

    private void capturarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null && extras.containsKey("data")) {

                Bitmap imageBitmap = (Bitmap) extras.get("data");

                foto.setImageBitmap(imageBitmap);
            }
        }}
}

/*


    // Tu código existente...

    public void cargar() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Asegúrate de que hay una aplicación de cámara disponible para manejar la acción
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        } else {
            Toast.makeText(this, "No se encontró una aplicación de cámara", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // La foto ha sido capturada, puedes acceder a la imagen en el intent data
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            // Puedes hacer algo con la imagen capturada, por ejemplo, mostrarla en un ImageView
            // imageView.setImageBitmap(imageBitmap);
        }
    }

 */