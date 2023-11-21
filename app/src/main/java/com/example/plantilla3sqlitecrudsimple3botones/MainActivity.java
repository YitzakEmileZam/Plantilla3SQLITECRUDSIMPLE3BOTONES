package com.example.plantilla3sqlitecrudsimple3botones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Context context;
    Button btnListar,btnRegistrar,btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        btnRegistrar = findViewById(R.id.btnregistrar);
        btnBuscar = findViewById(R.id.btnbuscar);
        btnListar = findViewById(R.id.btnlistar);

        btnRegistrar.setOnClickListener(this);
        btnBuscar.setOnClickListener(this);
        btnListar.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int id= v.getId();
        if (id == R.id.btnregistrar){
            Toast.makeText(this , "Registrar", Toast.LENGTH_LONG).show();
            Intent intent= new Intent(MainActivity.this, ActGestionarLibro.class);
            Bundle bolsa = new Bundle();
            bolsa.putInt("id",0);
            intent.putExtras(bolsa);
            startActivity(intent);

        }else if (id == R.id.btnbuscar){
            Toast.makeText(this , "Buscar", Toast.LENGTH_LONG).show();
            Intent intent2= new Intent(MainActivity.this, ActBuscarLibro.class);
            startActivity(intent2);

        }else if (id == R.id.btnlistar){
            Toast.makeText(this, "Listar", Toast.LENGTH_LONG).show();
            Intent intent3= new Intent(MainActivity.this, ActListarLibro.class);
            startActivity(intent3);
        }
    }

    /*
    @Override
    public void onClick(View v) {
        switch (v.getId() ){
            case R.id.btnregistrar:
                break;
            case R.id.btnbuscar:
                break;
            case R.id.btnlistar:
                break;
        }
    }*/


}