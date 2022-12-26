package com.example.mis_eventos_barrientos_pia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
//IMPORTACION GABO
//import android.support.annotation.Nullable;
//IMPORTACION POR VERSION
import androidx.annotation.Nullable;

public class AdminstradorBD extends SQLiteOpenHelper {


    public AdminstradorBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String[] tablas = {
                    "CREATE TABLE cuentas(nombre_usuario text primary key, nombre text, apellido text, contrasenia text, pregunta text);",
                    "CREATE TABLE ingresados(id integer primary key AUTOINCREMENT,usuario text);",
                    "CREATE TABLE eventos(titulo text primary key, fechaEvento text, importancia text, observacion text, lugar text, tiempoAviso text);"

                    // ...
            };
            /*, FOREIGN KEY(nombre_usuario) text REFERENCES cuentas(nombre_usuario*/
            for (String tabla : tablas) {
                db.execSQL(tabla);
            }
        }catch (Exception e){
            Log.e("TAG_", "Error"+e.toString());
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            String[] tablas = {
                    "CREATE TABLE cuentas(nombre_usuario text primary key, nombre text, apellido text, contrasenia text, pregunta text);",
                    "CREATE TABLE ingresados(id integer primary key AUTOINCREMENT,nombre_usuario text);",
                    // ...
            };
            for (String tabla : tablas) {
                db.execSQL(tabla);
            }
        }catch (Exception e){
            Log.e("TAG_", "Error"+e.toString());
        }
    }
}
