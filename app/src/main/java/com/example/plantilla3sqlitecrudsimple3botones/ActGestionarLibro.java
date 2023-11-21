package com.example.plantilla3sqlitecrudsimple3botones;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.plantilla3sqlitecrudsimple3botones.controladores.LibroBD;
import com.example.plantilla3sqlitecrudsimple3botones.modelos.Libro;

public class ActGestionarLibro extends AppCompatActivity implements View.OnClickListener{

    Context context;
    EditText edttitulo, edtsubtitulo,edtisbn,edtautor,edtaniopublicacion,edtprecio;
    Button btnguardar, btnactualizar,btnborrar;
    int id=0;
    LibroBD libroBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_gestionar_libro);
        init();
    }

    public void init(){
        context = getApplicationContext();
        edttitulo = findViewById(R.id.ges_txttitulo);
        edtsubtitulo = findViewById(R.id.ges_txtsubtitulo);
        edtisbn = findViewById(R.id.ges_txtisbn);
        edtautor = findViewById(R.id.ges_txtautor);
        edtaniopublicacion = findViewById(R.id.ges_txtAnioPublicacion);
        edtprecio = findViewById(R.id.ges_txtprecio);

        btnguardar = findViewById(R.id.ges_btnguardar);
        btnactualizar=findViewById(R.id.ges_btnactualizar);
        btnborrar=findViewById(R.id.ges_btnborrar);

        Intent intent = getIntent();
        Bundle bolsa= intent.getExtras();
        id=bolsa.getInt("id");
        if (id != 0){
            edttitulo.setText(bolsa.getString("titulo"));
            edtsubtitulo.setText(bolsa.getString("subtitulo"));
            edtisbn.setText(bolsa.getString("isbn"));
            edtautor.setText(bolsa.getString("autor"));
            edtaniopublicacion.setText(bolsa.getInt("anio"));
            edtprecio.setText(bolsa.getDouble("precio") + "");
            btnguardar.setEnabled(false);
        }else{
            btnactualizar.setEnabled(false);
            btnborrar.setEnabled(false);
        }
    }
    private void limpiarCampos(){
        id=0;
        edttitulo.setText("");
        edtsubtitulo.setText("");
        edtisbn.setText("");
        edtautor.setText("");
        edtaniopublicacion.setText("");
        edtprecio.setText("");

    }

    private Libro llenarDatosLibro(){
        Libro libro = new Libro();
        String tit= edttitulo.getText().toString();
        String subtit= edtsubtitulo.getText().toString();
        String isbn= edtisbn.getText().toString();
        String aut= edtautor.getText().toString();
        String anip= edtaniopublicacion.getText().toString();
        String prec= edtprecio.getText().toString();

        libro.setId(id);
        libro.setTitulo(tit);
        libro.setSubTitulo(subtit);
        libro.setIsbn(isbn);
        libro.setAutor(aut);
        libro.setAnioPublicacion(Integer.parseInt(anip));
        libro.setPrecio(Double.parseDouble(prec));

        return libro;

    }

    private void guardar(){
        libroBD = new LibroBD(context, "LibrosBD.db", null,1);
        Libro libro= llenarDatosLibro();

        if (id == 0){
            libroBD.agregar(libro);
            Toast.makeText(context, "Libro Guardado",Toast.LENGTH_LONG).show();
        }else{
            libroBD.actualizar(id, libro);
            btnactualizar.setEnabled(false);
            btnborrar.setEnabled(false);
            Toast.makeText(context, "Libro Actualizado",Toast.LENGTH_LONG).show();
        }
    }

    private void borrar(){
        libroBD = new LibroBD(context, "LibrosBD.db", null,1);
        Libro libro= llenarDatosLibro();

        if (id == 0){

            Toast.makeText(context, "No es posible borrar",Toast.LENGTH_LONG).show();
        }else{
            libroBD.borrar(id);
            limpiarCampos();
            btnguardar.setEnabled(true);
            btnactualizar.setEnabled(false);
            btnborrar.setEnabled(false);
            Toast.makeText(context, "Se borro el registro",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick( View v) {
        procesarClick(v.getId());

    }

    private void procesarClick(@IdRes int viewId){
        if (viewId == R.id.ges_btnguardar) {
            guardar();
        } else if (viewId == R.id.ges_btnactualizar) {
            guardar();
        } else if (viewId == R.id.ges_btnborrar) {
            borrar();
        }
    }
}