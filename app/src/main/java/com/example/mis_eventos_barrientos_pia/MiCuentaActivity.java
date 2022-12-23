package com.example.mis_eventos_barrientos_pia;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MiCuentaActivity extends AppCompatActivity {
    Button btnSalir, btnVer, btnCambiar, btnEliminar;
//    TextView tvVerEventos, tvCambiarContra, tvEliminarCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_cuenta);

        referencias();
        eventos();

    }

    private void verEventos(){
        Intent pantallaListaEventos = new Intent(this, ListaEventosActivity.class);
        startActivity(pantallaListaEventos);
    }

    private void referencias(){
        btnCambiar = findViewById(R.id.btnCambiar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnVer = findViewById(R.id.btnVer);
        btnSalir = findViewById(R.id.btnSalir);
    }

    private void eventos(){
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {verEventos();

            }
        });
    }


}