package com.example.plantilla3sqlitecrudsimple3botones.controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.plantilla3sqlitecrudsimple3botones.modelos.Libro;

import java.util.ArrayList;
import java.util.List;

public class LibroBD extends SQLiteOpenHelper implements ILibroBD {
    Context contexto;
    private List<Libro> libroList = new ArrayList<>();


    public LibroBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context,name, factory, version);
        this.contexto= context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql= "CREATE  TABLE libros (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "titulo TEXT," +
                "subtitulo TEXT, " +
                "isbn TEXT, " +
                "autor TEXT, " +
                "anio INTEGER, " +
                "precio REAL)";
        sqLiteDatabase.execSQL(sql);
        String insert = "INSERT INTO libros VALUES (null," +
                "'Como programar en java', " +
                "'Más de 80 ejemplos', " +
                "'965821450', " +
                "'Emile Fandres', 2008, 14500)";
        sqLiteDatabase.execSQL(insert);
        insert = "INSERT INTO libros VALUES(null, " +
                "'El gran libro'," +
                "'Android con ejemplos'," +
                "'16513213'," +
                "'Kira Perez', 2020,168000)";
        sqLiteDatabase.execSQL(insert);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public Libro elemento(int id) {
        SQLiteDatabase database = getReadableDatabase();
        String sql= "SELECT * FROM libros WHERE _id = " + id;
        Cursor cursor = database.rawQuery(sql, null);

        try {
            if (cursor.moveToNext())
                return extraerLibro(cursor);
            else
                return null;

        }catch (Exception e){
            Log.d("TAG","ERROR ELEMENTO(id) LIBROBD" + e.getMessage());
            throw e;
        }finally {
            if (cursor != null) cursor.close();
        }

    }

    //orden respecto al modelo Libro
    private Libro extraerLibro(Cursor cursor) {
        Libro libro= new Libro();
        libro.setId(cursor.getInt(0));
        libro.setTitulo(cursor.getString(1));
        libro.setSubTitulo(cursor.getString(2));
        libro.setIsbn(cursor.getString(3));
        libro.setAutor(cursor.getString(4));
        libro.setAnioPublicacion(cursor.getInt(5));
        libro.setPrecio(cursor.getDouble(6));

        return libro;
    }

    @Override
    public Libro elemento(String title) {
        SQLiteDatabase database = getReadableDatabase();
        String sql= "SELECT * FROM libros WHERE titulo='"+title+"'";
        Cursor cursor = database.rawQuery(sql, null);

        try {
            if (cursor.moveToNext())
                return extraerLibro(cursor);
            else
                return null;

        }catch (Exception e){
            Log.d("TAG","ERROR ELEMENTO(titulo) LIBROBD" + e.getMessage());
            throw e;
        }finally {
            if (cursor != null) cursor.close();
        }
    }

    @Override
    public List<Libro> lista() {
        SQLiteDatabase database = getReadableDatabase();
        String sql="SELECT * FROM libros ORDER BY titulo ASC";
        Cursor cursor= database.rawQuery(sql, null);

        if (cursor.moveToFirst()){
            do {
                libroList.add(
                        new Libro(cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getInt(5),
                                cursor.getDouble(6))

                );
            }while (cursor.moveToNext());
        }
        cursor.close();
        return libroList;
    }

    @Override
    public void agregar(Libro book) {
        SQLiteDatabase database =getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("titulo",book.getTitulo());
        values.put("subtitulo",book.getSubTitulo());
        values.put("isbn",book.getIsbn());
        values.put("autor",book.getAutor());
        values.put("anio",book.getAnioPublicacion());
        values.put("precio",book.getPrecio());
        database.insert("libros", null,values);
    }

    @Override
    public void actualizar(int id, Libro book) {
        SQLiteDatabase database =getWritableDatabase();
        String[] parametros={String.valueOf(id)};


        ContentValues values= new ContentValues();
        values.put("titulo",book.getTitulo());
        values.put("subtitulo",book.getSubTitulo());
        values.put("isbn",book.getIsbn());
        values.put("autor",book.getAutor());
        values.put("anio",book.getAnioPublicacion());
        values.put("precio",book.getPrecio());

        database.update("libros",values,"_id=?",parametros);
    }

    @Override
    public void borrar(int id) {
        SQLiteDatabase database =getWritableDatabase();
        String[] parametros={String.valueOf(id)};

        database.delete("libros", "_id=?", parametros);
    }
}

