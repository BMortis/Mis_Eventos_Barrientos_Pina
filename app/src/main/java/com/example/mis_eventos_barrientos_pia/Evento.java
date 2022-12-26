package com.example.mis_eventos_barrientos_pia;

//NO SE PARA QUE SIRVE ESTO
//import android.support.annotation.DimenRes;

import java.util.ArrayList;
import java.util.Date;

public class Evento {
    private String titulo;
    private String fecha_evento;
    private String importancia;
    private String observacion;
    private String lugar;
    private String tiempo_aviso;




    //region Constructores
    public Evento(){}

    public Evento(String titulo, String fecha_evento, String importancia, String observacion, String lugar,
                  String tiempo_aviso){
        this.titulo = titulo;
        this.fecha_evento = fecha_evento;
        this.importancia = importancia;
        this.observacion = observacion;
        this.lugar = lugar;
        this.tiempo_aviso = tiempo_aviso;
    }

    //endregion

    //region Getters and Setters

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha_evento() {
        return fecha_evento;
    }

    public void setFecha_evento(String fecha_evento) {
        this.fecha_evento = fecha_evento;
    }

    public String getImportancia() {
        return importancia;
    }

    public void setImportancia(String importancia) {
        this.importancia = importancia;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getTiempo_aviso() {
        return tiempo_aviso;
    }

    public void setTiempo_aviso(String tiempo_aviso) {
        this.tiempo_aviso = tiempo_aviso;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "titulo='" + titulo + '\'' +
                ", fecha_evento='" + fecha_evento + '\'' +
                ", importancia='" + importancia + '\'' +
                '}';
    }

    //endregion
}


