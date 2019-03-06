package com.tecnoactive.awake;


public class Vecino {

    private String edificio;
    private String direccion;
    private String nombre;
    private String telefono;

    public Vecino(String edificio, String direccion, String nombre, String telefono) {
        this.edificio = edificio;
        this.direccion = direccion;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
