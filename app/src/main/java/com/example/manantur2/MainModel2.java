package com.example.manantur2;

public class MainModel2 {
    String Evento;
    String Lugar;
    String Fecha;
    String Descripcion;
    String Organizador;
    String Foto;
    long creationTimestamp;

    MainModel2(){

    }



   public MainModel2(String evento, String lugar, String fecha, String descripcion, String organizador, String foto, long creationTimestamp) {
        this.Evento = evento;
        this.Lugar = lugar;
        this.Fecha = fecha;
        this.Descripcion = descripcion;
        this.Organizador = organizador;
        this.Foto = foto;
        this.creationTimestamp = creationTimestamp;
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
        this.Descripcion = descripcion;
    }

    public String getOrganizador() {
        return Organizador;
    }

    public void setOrganizador(String organizador) {
        this.Organizador = organizador;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }
    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }
}
