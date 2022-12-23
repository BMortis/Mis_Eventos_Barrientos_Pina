package com.example.mis_eventos_barrientos_pia;

//IMPORTACIONES DISTINTAS POR VERSION
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//IMPORTACIONES GABO
//import android.support.v7.app.AppCompatActivity;
//import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.util.ArrayList;

public class RegistrarmeActivity extends AppCompatActivity {
    TextInputLayout tilUsuario, tilContra, tilNombre, tilApellido, tilPregunta;
    Button btnGuardar, btnCancelar;

    //Cuentas
    private ArrayList<Cuenta> lasCuentas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarme);

        referencias();
        eventos();

    }

    //TODO: VALIDAR DATOS VACIOS O REPETIDOS
    private void guardarCuenta(){
        String nombre_usuario, nombre, apellido, contrasenia = "";
        Integer pregunta;
        boolean nombre_usuario_ok =true;

        nombre_usuario = tilUsuario.getEditText().getText().toString();
        nombre = tilNombre.getEditText().getText().toString();
        apellido = tilApellido.getEditText().getText().toString();
        contrasenia = tilContra.getEditText().getText().toString();
        pregunta = Integer.parseInt(tilPregunta.getEditText().getText().toString());

        lasCuentas = new ArrayList<Cuenta>();
        for(Cuenta c : lasCuentas){
            if(c.getNombre_usuario().equals(nombre_usuario)){
                nombre_usuario_ok = false;
                break;
            }
        }
        if (nombre_usuario.isEmpty()) {
            tilUsuario.setError("Debe ingresar un nombre de usuario");
//        }
//        if (nombre.isEmpty()) {
//            tilNombre.setError("Debe ingresar un nombre");
//        }
//        if (apellido.isEmpty()) {
//            tilApellido.setError("Debe ingresar un apellido");
//        }
//        if (contrasenia.isEmpty()) {
//            tilContra.setError("Debe ingresar una constraseña");
//        }
        //if (tilPregunta.getEditText().getText().toString()) {
        //    tilPregunta.setError("Debe ingresar una constraseña");
       }
        else{
            if(nombre_usuario_ok){
                Cuenta cuenta = new Cuenta(nombre,apellido,nombre_usuario,contrasenia,pregunta);
                lasCuentas.add(cuenta);
                guardarBaseDatos(cuenta);
                Toast.makeText(RegistrarmeActivity.this, "Grabado exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }
            else{
                tilUsuario.setError("Nombre de usuario ya existe.");
        }
    }}

    private void guardarBaseDatos(Cuenta cuenta) {
        try{
            AdminstradorBD adminbd = new AdminstradorBD(this, "BDAPP", null,1);
            SQLiteDatabase miBD = adminbd.getWritableDatabase();

            ContentValues reg =new ContentValues();
            reg.put("nombre_usuario", cuenta.getNombre_usuario());
            reg.put("contrasenia", cuenta.getContrasenia());
            reg.put("nombre", cuenta.getNombre());
            reg.put("apellido", cuenta.getApellido());
            reg.put("pregunta", cuenta.getPregunta());

            miBD.insert("cuentas", null,reg);

            miBD.close();

        }catch (Exception ex){
            Log.e("TAG_", ex.toString());
        }


    }

    private void referencias(){
        tilNombre = findViewById(R.id.tilNombre);
        tilApellido = findViewById(R.id.tilApellido);
        tilUsuario = findViewById(R.id.tilUsuario);
        tilContra = findViewById(R.id.tilContra);
        tilPregunta = findViewById(R.id.tilPregunta);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnGuardar = findViewById(R.id.btnGuardar);

    }
    private void eventos(){
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarCuenta();
            }
        });
    }
}