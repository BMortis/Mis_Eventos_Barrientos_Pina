package com.example.mis_eventos_barrientos_pia;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;

//IMPORTACIONES VERSION
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ListaEventosActivity extends AppCompatActivity {
    Button btnAgregarEvento;
    ListView lvListaEventos;
    TextView tvEventosAg;
    private Evento[] listaEventos;
    private ArrayAdapter<Evento> adaptadorEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_eventos);

        referencias();
        eventos();
        tvEventosAg.setText("Eventos de "+consultaBuscarUltimo());
        llenarListView();
    }

    private void llenarListView(){
        listaEventos = consultaBuscarEventos();
        adaptadorEventos = new ArrayAdapter<Evento>(this, android.R.layout.simple_list_item_1, listaEventos);
        lvListaEventos.setAdapter(adaptadorEventos);
    }

    private Evento[] consultaBuscarEventos(){
        Evento[] e;
        e = new Evento[5];
        try {
            AdminstradorBD adminbd = new AdminstradorBD(this, "BDAPP", null, 1);
            SQLiteDatabase miBD = adminbd.getWritableDatabase();

            Cursor c = miBD.rawQuery("SELECT * FROM eventos", null);
            if(c.moveToFirst()){
                for (int i = 0; i< e.length; i++){
                    Evento even = new Evento(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5));
                    e[i] = even;
                }
            }else{
                Log.e("TAG_", "Error en la Query de Cursor"+ c.getCount());
            }

            miBD.close();
        }catch (Exception ex){
            Log.e("TAG_", ex.toString());
        }
        return e;
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

    private void agregarNuevoEvento(){
        Intent pantallaNuevoEvento = new Intent(this, CrearEventoActivity.class);
        startActivity(pantallaNuevoEvento);
    }

    private void referencias(){
        btnAgregarEvento = findViewById(R.id.btnAgregarEvento);
        lvListaEventos = findViewById(R.id.lvListaEventos);
        tvEventosAg = findViewById(R.id.tvEventosAg);
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