package com.example.mis_eventos_barrientos_pia;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;

//IMPORTACIONES VERSION
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ListaEventosActivity extends AppCompatActivity {
    Button btnAgregarEvento;
    ListView lvListaEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_eventos);

        referencias();
        eventos();

    }

    private void agregarNuevoEvento(){
        Intent pantallaNuevoEvento = new Intent(this, CrearEventoActivity.class);
        startActivity(pantallaNuevoEvento);
    }

    private void referencias(){
        btnAgregarEvento = findViewById(R.id.btnAgregarEvento);
        lvListaEventos = findViewById(R.id.lvListaEventos);
    }

    private void eventos(){
        btnAgregarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarNuevoEvento();
            }
        });
    }
}