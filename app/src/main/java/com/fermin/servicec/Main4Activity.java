package com.fermin.servicec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main4Activity extends AppCompatActivity {
    private Button sgtguardar,stgeliminar,sgtactulizar,sgtelistar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        sgtguardar=findViewById(R.id.bntabajo_agregar);
        sgtactulizar=findViewById(R.id.bntabajo_actualizar);
        stgeliminar=findViewById(R.id.bntabajo_eliminar);
        sgtelistar=findViewById(R.id.bntabajo_listar);

        sgtguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main4Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        stgeliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main4Activity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
        sgtelistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main4Activity.this,Main3Activity.class);
                startActivity(intent);
            }
        });
        sgtactulizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main4Activity.this,Main4Activity.class);
                startActivity(intent);
            }
        });
    }
}
