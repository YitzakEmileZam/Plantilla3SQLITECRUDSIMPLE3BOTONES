package com.example.plantilla3sqlitecrudsimple3botones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.plantilla3sqlitecrudsimple3botones.controladores.LibroBD;
import com.example.plantilla3sqlitecrudsimple3botones.controladores.SelectListener;
import com.example.plantilla3sqlitecrudsimple3botones.modelos.Libro;

import java.util.ArrayList;
import java.util.List;

public class ActListarLibro extends AppCompatActivity implements SelectListener {

    ListView listView;
    ArrayList<String> nombresLibros;
    ArrayList<Integer>idLibros;
    LibroBD libroBD;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_listar_libro);
        init();
    }

    private void init(){
        context= this.getApplicationContext();
        libroBD = new LibroBD(context, "LibrosBD.db",null,1);

        listView = findViewById(R.id.listaLibros);
        llenarListView();
    }

    private void llenarListView() {
        nombresLibros= new ArrayList<String>();
        idLibros= new ArrayList<Integer>();

        List<Libro> listaLibros = libroBD.lista();
        for (int i=0; i<listaLibros.size(); i++){
            Libro l = listaLibros.get(i);
            nombresLibros.add(l.getTitulo());
            idLibros.add(l.getId());

        }
        ArrayAdapter<String> adapter=
                new ArrayAdapter<String>(
                        context, android.R.layout.simple_list_item_1,nombresLibros
                );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Libro libro= libroBD.elemento(idLibros.get(position));
                Bundle bolsa = new Bundle();
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
            }
        });
    }

    @Override
    public void onItemClick(String titulo) {


    }
}