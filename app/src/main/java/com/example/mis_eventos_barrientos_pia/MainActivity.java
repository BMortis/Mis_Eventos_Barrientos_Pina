package com.example.mis_eventos_barrientos_pia;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
//IMPORTACIONES DISTINTAS POR VERSION
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

//IMPORTACIONES GABO
//import android.support.v7.app.AppCompatActivity;
//import android.support.design.widget.TextInputLayout;


public class MainActivity extends AppCompatActivity {
    private TextInputLayout tilNombreUsuario, tilContrasenia;
    private Button btnIngresar, btnRegistrarme;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        referencias();
        eventos();
    }

    private void paginaRegistrarse(){
    Intent pantallaRegistro = new Intent(this, RegistrarmeActivity.class);
    startActivity(pantallaRegistro);
    }

    private void miCuenta(){
        // TODO: VALIDACION DE USUARIO CONTRA BD
        // TODO: GUARDAR ULTIMO USUARIO EN UNA TABLA PARA MOSTRARLO EN LA NUEVA ENTRADA

        Intent pantallaMiCuenta = new Intent(this, MiCuentaActivity.class);
        startActivity(pantallaMiCuenta);
    }

    private void consultaSQL(){
        try {


            AdminstradorBD adminbd = new AdminstradorBD(this, "BDAPP", null, 1);
            SQLiteDatabase miBD = adminbd.getWritableDatabase();





        }catch (Exception e){
            Log.e("TAG_", e.toString());
        }
    }

    private void referencias(){
        tilNombreUsuario = findViewById(R.id.tilNombreUsuario);
        tilContrasenia = findViewById(R.id.tilConstrasenia);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnRegistrarme = findViewById(R.id.btnRegistrarme);
    }

    private void eventos(){
        btnRegistrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {paginaRegistrarse();

            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {miCuenta();

            }
        });
    }

}
