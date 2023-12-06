package com.example.manantur2;

public class MainModel {
    String Nombre;
    String ApellidoPaterno;
    String ApellidoMaterno;
    String Email;
    String Password;


    public MainModel() {
    }

    public MainModel(String nombre, String apellidoPaterno, String apellidoMaterno, String email, String password) {
        this.Nombre = nombre;
        this.ApellidoPaterno = apellidoPaterno;
        this.ApellidoMaterno = apellidoMaterno;
        this.Email = email;
        this.Password = password;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        ApellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        ApellidoMaterno = apellidoMaterno;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

/*
    String Evento;
    String Lugar;
    String Fecha;
    String Descripcion;
    String Organizador;
    String Foto;

    public MainModel(String evento, String lugar, String fecha, String descripcion, String organizador, String foto) {
        Evento = evento;
        Lugar = lugar;
        Fecha = fecha;
        Descripcion = descripcion;
        Organizador = organizador;
        Foto = foto;
    }

    public String getEvento() {
        return Evento;
    }

    public void setEvento(String evento) {
        Evento = evento;
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

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }*/
}
