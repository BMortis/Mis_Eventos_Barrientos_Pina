package com.example.mis_eventos_barrientos_pia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
            db.execSQL("create table cuentas(nombre_usuario text primary key, nombre text, apellido text, contrasenia text, pregunta text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
