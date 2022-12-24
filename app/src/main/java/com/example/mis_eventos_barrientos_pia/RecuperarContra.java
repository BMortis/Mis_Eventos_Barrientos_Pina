package com.example.mis_eventos_barrientos_pia;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class RecuperarContra extends AppCompatActivity {
    TextView tvRecuperaTitulo, tvTituloPregunta, tvPregunta;
    TextInputLayout tilRespuesta, tilNuevaContra;
    Button btnConfirmarRespuesta, btnVolver;
    String nombre_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contra);

        referencias();
        eventos();
        tvRecuperaTitulo.setText(nombre_usuario+" eres tu ?");
    }

    private void confirmacionRecupera(){
        if (tilRespuesta.getEditText().getText().toString().isEmpty()){
            tilRespuesta.getEditText().setError("Debe responder la pregunta!!");
        }else {
            if (tilNuevaContra.getEditText().getText().toString().isEmpty()){
                tilNuevaContra.getEditText().setError("Debe Ingresar Nueva Contraseña");
            }else {
                int pregunta = consultaPregunta(nombre_usuario);
                if (pregunta == Integer.parseInt(tilRespuesta.getEditText().getText().toString())) {
                    ConfirmarContrasenia();
                    Toast.makeText(this, "Contraseña cambiada correctamente", Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    tilRespuesta.getEditText().setError("RESPUESTA INCORRECTA");
                    Toast.makeText(this, "INTENTA NUEVAMENTE!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void ConfirmarContrasenia(){
        try {
            AdminstradorBD adminbd = new AdminstradorBD(this, "BDAPP", null, 1);
            SQLiteDatabase miBD = adminbd.getWritableDatabase();

            String nueva = tilNuevaContra.getEditText().getText().toString();
            String[] parametros = {nueva, nombre_usuario};
            Cursor c = miBD.rawQuery("SELECT * FROM cuentas WHERE nombre_usuario = '"+nombre_usuario+"' ", null);
            if (c.moveToFirst()){
                String contrasenia = c.getString(3);
                if (contrasenia.equals(nueva)){
                    Toast.makeText(this, "La Contraseña debe ser DISTINTA A LA ANTERIOR", Toast.LENGTH_SHORT).show();
                }else {
                    miBD.execSQL("UPDATE cuentas SET contrasenia = ? WHERE nombre_usuario = ?", parametros);
                    Toast.makeText(this, "Contraseña cambiada Correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception ex){
            Log.e("TAG_", ex.toString() );
        }
    }

    private int consultaPregunta(String nombre){
        int pregunta = 0;
        try {
            AdminstradorBD adminbd = new AdminstradorBD(this, "BDAPP", null, 1);
            SQLiteDatabase miBD = adminbd.getWritableDatabase();
            Cursor c = miBD.rawQuery("SELECT * FROM cuentas WHERE nombre_usuario = '"+nombre+"' ", null);
            if (c.moveToFirst()){
                pregunta = c.getInt(4);
            }
            miBD.close();

        }catch (Exception e){
            Log.e("TAG_", e.toString());
            Log.e("TAG_", "AQUI ESTOY");

        }
        return pregunta;
    }
    private void referencias(){
        tvRecuperaTitulo = findViewById(R.id.tvRecuperaTitulo);
        tvTituloPregunta = findViewById(R.id.tvTituloPregunta);
        tvPregunta = findViewById(R.id.tvPregunta);
        tilRespuesta = findViewById(R.id.tilRespuesta);
        tilNuevaContra = findViewById(R.id.tilNuevaContrasena);
        btnConfirmarRespuesta = findViewById(R.id.btnConfirmarRespuesta);
        btnVolver = findViewById(R.id.btnVolver);
        nombre_usuario = getIntent().getExtras().getString("nombre_usuario");
    }

    private void eventos(){
        btnConfirmarRespuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmacionRecupera();
            }
        });
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}