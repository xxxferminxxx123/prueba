package com.fermin.servicec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.model.Progress;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText codigo,descripcion,categoria,precio;
    private Button guardar,stgeliminar,sgtactulizar,sgtelistar,sgtguardar;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidNetworking.initialize(getApplicationContext());

        codigo=findViewById(R.id.edt_codigo);
        descripcion=findViewById(R.id.edt_Descr);
        progressBar =findViewById(R.id.progressBar);
        categoria=findViewById(R.id.edt_Categoria);
        precio=findViewById(R.id.edt_prec);
        guardar=findViewById(R.id.btn_guardar);

        sgtguardar=findViewById(R.id.bntabajo_agregar);
        sgtactulizar=findViewById(R.id.bntabajo_actualizar);
        stgeliminar=findViewById(R.id.bntabajo_eliminar);
        sgtelistar=findViewById(R.id.bntabajo_listar);

        sgtguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        stgeliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
        sgtelistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Main3Activity.class);
                startActivity(intent);
            }
        });
        sgtactulizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Main4Activity.class);
                startActivity(intent);
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarProducto();
            }
        });
    }
    private void guardarProducto(){
        if(validarCampos()){
            String stringcodigo= codigo.getText().toString();
            String stringdescripcion= descripcion.getText().toString();
            String stringcatergoricategoria=categoria.getText().toString();
            String stringprecio=precio.getText().toString();

            Map <String,String>datos=new HashMap<>();
            datos.put("codigo",stringcodigo);
            datos.put("descripcion",stringdescripcion);
            datos.put("precio",stringprecio);
            datos.put("categoria",stringcatergoricategoria);
            JSONObject jsonData= new JSONObject(datos);

            AndroidNetworking.post("https://rocky-tundra-81911.herokuapp.com/Producto_INSERTAR_POST.php")
                    .addJSONObjectBody(jsonData)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            esProgressBar();
                            try {
                                String estado = response.getString("estado");
                                Toast.makeText(MainActivity.this,estado,Toast.LENGTH_SHORT).show();
                            }catch (JSONException e){
                                Toast.makeText(MainActivity.this,"Error: "+e.getMessage(),Toast.LENGTH_SHORT ).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(MainActivity.this,"Error: "+anError.getErrorDetail(),Toast.LENGTH_SHORT).show();
                        }
                    });
        }else
        {
            Toast.makeText(MainActivity.this,"Nose puede instar producto con producto vacios",Toast.LENGTH_SHORT).show();
        }
        progressBar.setProgress(0);

    }

    //devuelve verdadero si esque no ahy campos vacios
    //devuelve falso si es que hay como minimo un campo vacio

    private boolean validarCampos(){
        return !codigo.getText().toString().trim().isEmpty()
                && !precio.getText().toString().trim().isEmpty()
                && !descripcion.getText().toString().trim().isEmpty()
                && !categoria.getText().toString().trim().isEmpty();
    }
    private void esProgressBar() {

        progressBar.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 40) {
                    progressStatus += 10;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }



    }




















