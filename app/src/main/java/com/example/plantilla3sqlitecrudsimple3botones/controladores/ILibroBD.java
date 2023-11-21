package com.example.plantilla3sqlitecrudsimple3botones.controladores;

import com.example.plantilla3sqlitecrudsimple3botones.modelos.Libro;

import java.util.List;

public interface ILibroBD {
    Libro elemento(int id);//devuelve el elemento dado su id
    Libro elemento(String title);//devuelve el elemento dado su titulo

    List<Libro> lista();//devuelve una lista con todos los elementos registrados
    void agregar(Libro book);//añade el elemento indicado
    void actualizar(int id, Libro book);//actualiza datos correspondientes al id del elemento
    void borrar(int id);//elimina el elemento indicado con el id
}
