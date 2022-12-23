package com.example.mis_eventos_barrientos_pia;

//IMPORTACIONES DISTINTAS POR VERSION
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

//IMPORTACIONES GABO
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ListaUsuariosActivity extends AppCompatActivity {
    ListView ltListaUsuarios;
    TextView tvLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);
    }


    private void referencia(){
        ltListaUsuarios = findViewById(R.id.ltListaUsuarios);
        tvLista =findViewById(R.id.tvLista);
    }
}