package com.example.manantur2;

public class Usuario {
    public String Nombre;
    public String ApellidoPaterno;
    public String ApellidoMaterno;
    public String Email;
    public String Password;


    public Usuario() {
    }

    public Usuario(String nombre, String apellidoPaterno, String apellidoMaterno, String email, String password) {
        this.Nombre = nombre;
        this.ApellidoPaterno = apellidoPaterno;
        this.ApellidoMaterno = apellidoMaterno;
        this.Email = email;
        this.Password = password;
    }
}
