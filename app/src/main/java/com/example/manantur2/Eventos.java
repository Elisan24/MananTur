package com.example.manantur2;

import android.graphics.Bitmap;

public class Eventos {
    public String id;
    public String Nombre;
    public String Lugar;
    public String Fecha;
    public String Foto;
    public String Descripcion;
    public String Organizador;


    public Eventos(String nombre, String lugar, String fecha, String foto, Bitmap imageUrl, String descripcion, String organizador) {
    }

    public Eventos(String id, String nombre, String lugar, String fecha, String foto, String descripcion, String organizador) {
        this.id = id;
        Nombre = nombre;
        Lugar = lugar;
        Fecha = fecha;
        Foto = foto;
        Descripcion = descripcion;
        Organizador = organizador;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String lugar) {
        Lugar = lugar;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getOrganizador() {
        return Organizador;
    }

    public void setOrganizador(String organizador) {
        Organizador = organizador;
    }
}


