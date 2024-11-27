package com.pablovicente.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> someActivityResultLauncher;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa el ImageView
        imageView = findViewById(R.id.imageView);

        // Registra el lanzador para manejar el resultado
        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        // Obtén la URI de la imagen seleccionada
                        Uri uri = result.getData().getData();
                        // Establece la imagen seleccionada en el ImageView
                        imageView.setImageURI(uri);
                    }
                }
        );
    }

    // Método para abrir la galería
    public void openSomeActivityForResult(View view) {
        // Crea un Intent para seleccionar contenido
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*"); // Permitir todos los formatos de imagen
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true); // Solo archivos locales
        // Lanza la actividad para obtener el resultado
        someActivityResultLauncher.launch(intent);
    }
}

