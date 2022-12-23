package com.example.mis_eventos_barrientos_pia;

//IMPORTACIONES DISTINTAS POR VERSION
import androidx.appcompat.app.AppCompatActivity;


//IMPORTACIONES GABO
//import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

public class CrearEventoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);

        Cuenta cuenta = new Cuenta();

        cuenta.agregarEvento(new Evento());
    }
}