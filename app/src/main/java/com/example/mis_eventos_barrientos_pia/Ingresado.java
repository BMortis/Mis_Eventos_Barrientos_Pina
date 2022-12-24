package com.example.mis_eventos_barrientos_pia;

import java.io.Serializable;

public class Ingresado implements Serializable {
    private int id;
    private String nombre_usuario;

    public Ingresado(int id, String nombre_usuario) {
        this.id = id;
        this.nombre_usuario = nombre_usuario;
    }

    public Ingresado(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    @Override
    public String toString() {
        return "Ingresado{" +
                "id=" + id +
                ", nombre_usuario='" + nombre_usuario + '\'' +
                '}';
    }
}
