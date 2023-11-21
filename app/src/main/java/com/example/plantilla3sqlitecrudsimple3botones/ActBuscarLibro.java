package com.example.plantilla3sqlitecrudsimple3botones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.plantilla3sqlitecrudsimple3botones.R;
import com.example.plantilla3sqlitecrudsimple3botones.controladores.LibroBD;
import com.example.plantilla3sqlitecrudsimple3botones.modelos.Libro;

public class ActBuscarLibro extends AppCompatActivity implements View.OnClickListener{

    Context context;
    EditText edtTitulo;
    Button btnBuscar;
    LibroBD libroBD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_buscar_libro);
        init();
    }

    public void init(){
        context= getApplicationContext();
        edtTitulo=findViewById(R.id.bus_txttitulo);
        btnBuscar=findViewById(R.id.bus_btnbuscar);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bus_btnbuscar){
            String titulo=edtTitulo.getText().toString();
            Libro libro= buscarLibro(titulo);
            if (libro != null){
                Bundle bolsa= new Bundle();
                bolsa.putInt("id", libro.getId());
                bolsa.putString("titulo", libro.getTitulo());
                bolsa.putString("subtitulo", libro.getSubTitulo());
                bolsa.putString("isbn", libro.getIsbn());
                bolsa.putString("autor", libro.getAutor());
                bolsa.putInt("anio_publicacion", libro.getAnioPublicacion());
                bolsa.putDouble("precio", libro.getPrecio());

                Intent intent= new Intent(context, ActGestionarLibro.class);
                intent.putExtras(bolsa);
                startActivity(intent);
            }else{
                Toast.makeText(context, "No existe el libro con ese titulo", Toast.LENGTH_LONG).show();
            }
        }
    }

    public Libro buscarLibro(String titulo) {
        libroBD = new LibroBD(context, "LibrosBD.db", null,1);
        Libro libro= libroBD.elemento(titulo);
        return libro;
    }
}