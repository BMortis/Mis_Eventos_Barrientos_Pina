package com.example.mis_eventos_barrientos_pia;

//IMPORTACIONES DISTINTAS POR VERSION
import androidx.appcompat.app.AppCompatActivity;


//IMPORTACIONES GABO
//import android.support.v7.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class CrearEventoActivity extends AppCompatActivity {

    TextInputLayout tilTitulo, tilFechaEvento, tilObservacion, tilLugar;
    Spinner spnTiempoAviso, spnImportancia;
    Button btnCrear, btnDescartar;
    //Spinners
    private String[] listaImportancia;
    ArrayAdapter<String> AdaptadorImportancia;
    private String [] listaTiempos;
    ArrayAdapter<String> AdaptadorTiempos;

    ArrayList<Evento> eventos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);

        referencias();
        eventos();
        llenadoArray();

        AdaptadorImportancia = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaImportancia);
        spnImportancia.setAdapter(AdaptadorImportancia);
        AdaptadorTiempos = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaTiempos);
        spnTiempoAviso.setAdapter(AdaptadorTiempos);


    }

    private void guardarEvento(){
        String titulo, fechaEvento, importancia, observacion, lugar, tiempoAviso;
        boolean tituloOk = true;
        titulo = tilTitulo.getEditText().getText().toString();
        fechaEvento = tilFechaEvento.getEditText().getText().toString();
        importancia = spnImportancia.getSelectedItem().toString();
        observacion = tilObservacion.getEditText().getText().toString();
        lugar = tilLugar.getEditText().getText().toString();
        tiempoAviso = spnTiempoAviso.getSelectedItem().toString();
        eventos = new ArrayList<Evento>();


        for(Evento e : eventos){
            if(e.getTitulo().equals(titulo)){
                tituloOk = false;
                break;
            }
        }
        if (titulo.isEmpty()) {
            tilTitulo.setError("Debe ingresar un Titulo Valido");
//
        }
        else{
            if(tituloOk){
                Evento evento = new Evento(titulo, fechaEvento, importancia, observacion, lugar, tiempoAviso);
                eventos.add(evento);
                guardarBaseDatosEvento(evento);
                Toast.makeText(CrearEventoActivity.this, "Guardado exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }
            else{
                tilTitulo.setError("Titulo de Evento ya existe.");
            }
        }
    }

    private void guardarBaseDatosEvento(Evento evento) {
        try{
            AdminstradorBD adminbd = new AdminstradorBD(this, "BDAPP", null,1);
            SQLiteDatabase miBD = adminbd.getWritableDatabase();

            ContentValues reg =new ContentValues();
            reg.put("Titulo", evento.getTitulo());
            reg.put("Fecha de Evento", evento.getFecha_evento());
            reg.put("Importancias", evento.getImportancia());
            reg.put("Lugar", evento.getLugar());
            reg.put("Observacion", evento.getObservacion());
            reg.put("Tiempo de Aviso", evento.getTiempo_aviso());

            miBD.insert("eventos", null,reg);

            miBD.close();

        }catch (Exception ex){
            Log.e("TAG_", ex.toString());
        }


    }

    private void referencias(){
        tilFechaEvento = findViewById(R.id.tilFechaEvento);
        tilTitulo = findViewById(R.id.tilTitulo);
        tilObservacion = findViewById(R.id.tilObservacion);
        tilLugar = findViewById(R.id.tilLugar);
        spnTiempoAviso = findViewById(R.id.spnTiempoAviso);
        spnImportancia = findViewById(R.id.spnImportancia);
        btnCrear = findViewById(R.id.btnCrear);
        btnDescartar = findViewById(R.id.btnDescartar);

        listaImportancia = new String[4];
        listaTiempos = new String[4];

    }

    private void eventos(){
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarEvento();
            }
        });
    }

    private void llenadoArray(){
        listaImportancia[0] ="Importancia";
        listaImportancia[1] ="Baja";
        listaImportancia[2] ="Media";
        listaImportancia[3] ="Alta";

        listaTiempos[0] ="Tiempo Recordatorios";
        listaTiempos[1] ="1 Hora";
        listaTiempos[2] ="2 Horas";
        listaTiempos[3] ="3 Horas";

    }


}