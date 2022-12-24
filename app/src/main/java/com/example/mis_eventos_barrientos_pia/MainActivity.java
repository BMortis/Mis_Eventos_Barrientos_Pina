package com.example.mis_eventos_barrientos_pia;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
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
        // TODO: GUARDAR ULTIMO USUARIO EN UNA TABLA PARA MOSTRARLO EN LA NUEVA ENTRADA

        if (consultaLoginSQL(tilNombreUsuario.getEditText().getText().toString(), tilContrasenia.getEditText().getText().toString())){
            Toast.makeText(this, "LOGIN CORRECTO!!!!", Toast.LENGTH_SHORT).show();
            consultaGuardarUltimo(tilNombreUsuario.getEditText().getText().toString());

            String ultimo = consultaBuscarUltimo();
            Intent pantallaMiCuenta = new Intent(this, MiCuentaActivity.class);
            tilNombreUsuario.getEditText().setText(ultimo);
            tilContrasenia.getEditText().setText("");
            startActivity(pantallaMiCuenta);
        }else {
            Toast.makeText(this, "Nombre o Contraseña Incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    private void consultaGuardarUltimo(String nombre_usuario){
        try {
            AdminstradorBD adminbd = new AdminstradorBD(this, "BDAPP", null, 1);
            SQLiteDatabase miBD = adminbd.getWritableDatabase();

            miBD.rawQuery("INSERT INTO ingresados(usuario) VALUES('"+nombre_usuario+"')", null);

            miBD.close();
        }catch (Exception ex){
            Log.e("TAG_", ex.toString());
        }
    }

    private String consultaBuscarUltimo(){
        String ultimo = "";
        try {
            AdminstradorBD adminbd = new AdminstradorBD(this, "BDAPP", null, 1);
            SQLiteDatabase miBD = adminbd.getWritableDatabase();

            Cursor c = miBD.rawQuery("SELECT * FROM ingresados ORDER BY id DESC", null);
            if(c.moveToFirst()){
                ultimo = c.getString(1);
            }else{
                Log.e("TAG_", "Error en la Query de Cursor"+ c.getCount());
            }

            miBD.close();
        }catch (Exception ex){
            Log.e("TAG_", ex.toString());
        }
        return ultimo;
    }


    private boolean consultaLoginSQL(String nombre, String contra){
        try {
            boolean siono = false;

            AdminstradorBD adminbd = new AdminstradorBD(this, "BDAPP", null, 1);
            SQLiteDatabase miBD = adminbd.getWritableDatabase();
            Cursor c = miBD.rawQuery("SELECT * FROM cuentas WHERE nombre_usuario = '"+nombre+"' AND contrasenia = '"+contra+"'", null);
            if (c!=null && c.getCount()>0){
                siono = true;
            }
            else {
                siono = false;

            }
            miBD.close();
            return siono;
        }catch (Exception e){
            Log.e("TAG_", e.toString());
            return false;
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
