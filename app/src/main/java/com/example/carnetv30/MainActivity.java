package com.example.carnetv30;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;

import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.Map;public class MainActivity extends AppCompatActivity {
EditText apellido;
Button mostrar, crear;
TextView id,apellidos,nombre,cargo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        crear=findViewById(R.id.btnCrear);
        apellido=findViewById(R.id.txtApellido);
        mostrar=findViewById(R.id.btnBuscar);
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inicia la actividad "Crear"
                Intent intentCrear = new Intent(MainActivity.this, Crear.class);
                startActivity(intentCrear);
            }
        });
        mostrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try{
                    leer(apellido.getText().toString());
                    contar(apellido.getText().toString());
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });}


    public void leer(final String apellido) {
        String url = "http://10.40.16.23:80/Conexiones/mostrarPorApellido.php?apellido=" + "'"+apellido+"'";
        id=findViewById(R.id.txtId1);
        apellidos=findViewById(R.id.txtApellido2);
        nombre=findViewById(R.id.txtNombre1);
        cargo=findViewById(R.id.txtCargo1);
        StringRequest respuesta = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null && !response.isEmpty()) {
                    try {
                        JSONObject datos = new JSONObject(response);
                        String lectura = datos.getString("ID") + datos.getString("Apellido") +
                                datos.getString("Nombre") + datos.getString("Cargo");
                        id.setText("ID:"+datos.getString("ID"));
                        apellidos.setText("APELLIDO: "+datos.getString("Apellido"));
                        nombre.setText("NOMBRE: "+datos.getString("Nombre"));
                        cargo.setText("CARGO: "+datos.getString("Cargo"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("JSONException", "Error al procesar JSON: " + e.getMessage());
                        Toast.makeText(MainActivity.this, "Error al procesar JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "La respuesta está vacía o nula", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error al ejecutar: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(this).add(respuesta);
    }


    public void contar(final String apellido) {
        if (apellido != null && !apellido.isEmpty()) {
            char primeraLetra = apellido.charAt(0);
            String primeraLetraMayuscula = String.valueOf(Character.toUpperCase(primeraLetra));
            String url = "http://10.40.16.23:80/Conexiones/mostrarCuenta.php?letra="  + primeraLetraMayuscula ;
            StringRequest respuesta = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response != null && !response.isEmpty()) {
                        try {
                            JSONObject datos = new JSONObject(response);
                            String lectura = datos.getString("cuenta");
                            Toast.makeText(MainActivity.this, "Existe: " + lectura + " coincidencias con la letra " + primeraLetraMayuscula, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("JSONException", "Error al procesar JSON: " + e.getMessage());
                            Toast.makeText(MainActivity.this, "Error al procesar JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "La respuesta está vacía o nula", Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, "Error al ejecutar: " + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            Volley.newRequestQueue(this).add(respuesta);
            Toast.makeText(MainActivity.this, "La primera letra del apellido es: " + primeraLetraMayuscula, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "El apellido es nulo o vacío", Toast.LENGTH_LONG).show();
        }
    }

}