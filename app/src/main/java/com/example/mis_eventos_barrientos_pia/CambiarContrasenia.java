package com.example.mis_eventos_barrientos_pia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CambiarContrasenia extends AppCompatActivity {
    private TextView tvUser;
    private TextInputLayout tilAntigua, tilNueva, tilRepetir;
    private Button btnConfirmar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_contrasenia);
        referencias();
        eventos();
        tvUser.setText(SacarUsuarioLogeado());

    }

    private void ConfirmarContrasenia(){
        try {
            AdminstradorBD adminbd = new AdminstradorBD(this, "BDAPP", null, 1);
            SQLiteDatabase miBD = adminbd.getWritableDatabase();

            if (validarCampos()) {
                String nueva = tilNueva.getEditText().getText().toString();
                String[] parametros = {nueva};
                miBD.execSQL("UPDATE cuentas SET contrasenia = ?", parametros);
                Toast.makeText(this, "Contraseña cambiada correctamente", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            Log.e("TAG_", ex.toString() );
        }
    }


/* CREO QUE ESTE METODO FUE INNECESARIO
    private Cuenta ConsultaUsuario(){
        Cuenta cuenta = new Cuenta();
        String nombre = SacarUsuarioLogeado();
        try {
            AdminstradorBD adminbd = new AdminstradorBD(this, "BDAPP", null, 1);
            SQLiteDatabase miBD = adminbd.getWritableDatabase();

            Cursor c = miBD.rawQuery("SELECT * FROM cuentas WHERE nombre_usuario = '"+nombre+"'", null);
            if(c.moveToFirst()){
                String nombre_usuario = c.getString(0);
                nombre = c.getString(1);
                String apellido = c.getString(2);
                String contrasenia = c.getString(3);
                int pregunta = Integer.parseInt(c.getString(4));
                cuenta = new Cuenta(nombre_usuario, nombre, apellido, contrasenia, pregunta);
            }else{
                Log.e("TAG_", "Error en la Query de Cursor"+ c.getCount());
            }

            miBD.close();
        }catch (Exception ex){
            Log.e("TAG_", ex.toString());
        }
        return cuenta;
    }
*/
    private boolean validarCampos(){
        boolean confirma = false, si = false;
        do {
            if (tilAntigua.getEditText().getText().toString().isEmpty() || tilNueva.getEditText().getText().toString().isEmpty() || tilRepetir.getEditText().getText().toString().isEmpty()) {
                if (tilAntigua.getEditText().getText().toString().isEmpty()) {
                    tilAntigua.getEditText().setError("Debe Llenar este campo");
                }
                if (tilNueva.getEditText().getText().toString().isEmpty()) {
                    tilNueva.getEditText().setError("Debe Llenar este campo");
                }
                if (tilRepetir.getEditText().getText().toString().isEmpty()) {
                    tilRepetir.getEditText().setError("Debe Llenar este campo");
                }
            } else {
                if (tilNueva.getEditText().getText().toString().equals(tilRepetir.getEditText().getText().toString())) {
                    if (tilAntigua.getEditText().getText().toString().equals(tilNueva.getEditText().getText().toString())){
                        tilNueva.getEditText().setError("CONTRASEÑA DEBE SER DISTINTA A LA ANTERIOR!!");
                    }else {
                        si = true;
                    }
                }else {
                    tilRepetir.getEditText().setError("Debe ser igual a la NUEVA CONTRASEÑA!!");
                }
            }
        }while (!si);
        return confirma;
    }

    private String SacarUsuarioLogeado(){
        String nombre_usuario = "";
        try{
            AdminstradorBD adminbd = new AdminstradorBD(this, "BDAPP", null,1);
            SQLiteDatabase miBD = adminbd.getWritableDatabase();

            Cursor c = miBD.rawQuery("SELECT * FROM ingresados ORDER BY id DESC", null);
            if(c.moveToFirst()){
                nombre_usuario = c.getString(1);
            }else{
                Log.e("TAG_", "Error en la Query de Cursor"+ c.getCount());
            }

            miBD.close();

        }catch (Exception ex){
            Log.e("TAG_", ex.toString());
        }

        return nombre_usuario;
    }

    private void referencias(){
        tvUser = findViewById(R.id.tvUsuario);
        tilAntigua = findViewById(R.id.tilAntigua);
        tilNueva = findViewById(R.id.tilNueva);
        tilRepetir = findViewById(R.id.tilRepetir);
        btnConfirmar = findViewById(R.id.btnConfirmar);

    }
    private void eventos(){
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmarContrasenia();
            }
        });
    }
}