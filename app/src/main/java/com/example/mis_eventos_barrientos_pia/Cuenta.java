package com.example.mis_eventos_barrientos_pia;

import java.util.ArrayList;

public class Cuenta {
    private String nombre;
    private String apellido;
    private String nombre_usuario;
    private String contrasenia;
    private Integer pregunta;


    //Relaciones de clases
    private ArrayList<Evento> losEventos = new ArrayList<>();

    //Este es para agregar los eventos a la lista losEventos:
    public boolean agregarEvento(Evento e){
        losEventos.add(e);
        return true;
    }

    //Para retornarnos los eventos registrados en la cuenta, crearemos un arreglo que se llamará Evento[] y le daremos
    //el tamaño de la lista losEventos;

    public Evento[] obtenerEventos(){
        Evento[] eventos = new Evento[losEventos.size()];
        return eventos;
    }

   // private ArrayList<Cuenta> losUsuarios = new ArrayList<>();

    //region Constructores
    public Cuenta(){}

    public Cuenta(String nombre, String apellido, String nombre_usuario, String contrasenia, Integer pregunta){
       this.nombre = nombre;
       this.apellido = apellido;
       this.nombre_usuario = nombre_usuario;
       this.contrasenia = contrasenia;
       this.pregunta = pregunta;
    }
    //endregion

    //region Getters and Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Integer getPregunta() {
        return pregunta;
    }

    public void setPregunta(Integer pregunta) {
        this.pregunta = pregunta;
    }



    public ArrayList<Evento> getLosEventos() {
        return losEventos;
    }

    public void setLosEventos(ArrayList<Evento> losEventos) {
        this.losEventos = losEventos;
    }
}




