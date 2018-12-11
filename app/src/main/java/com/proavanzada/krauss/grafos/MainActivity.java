package com.proavanzada.krauss.grafos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.proavanzada.krauss.grafos.model.Nodes;
import com.proavanzada.krauss.grafos.model.Web;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner spOrigen;
    private Spinner spDestino;
    private Button btnRuta, btn, btn_arista;

    private String origen;
    private String destino;
    Web web = new Web();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spOrigen = findViewById(R.id.sp_origen);
        spDestino = findViewById(R.id.sp_destino);

        btnRuta = findViewById(R.id.btn_cargar);
        btn = findViewById(R.id.button_agrD);
        btn_arista = findViewById(R.id.agr_ruta);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Agregar.class);
                startActivity(intent);
            }
        });

        btn_arista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, agregar_arista.class);
                startActivity(intent);
            }
        });

        actualizar();
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void accionBoton() {
        web.rutaEco(getOrigen(), getDestino());
        web.rutaRapida(getOrigen(), getDestino());
        web.costMin();
        web.distMin();
        Intent intent = new Intent(MainActivity.this, Resultado.class);
        startActivity(intent);
    }

    public void actualizar() {
        Nodes.getInstancia().getLlaves().clear();
        final ArrayList<String> nodo = web.nodes();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nodo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOrigen.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nodo);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDestino.setAdapter(adapter2);

        spOrigen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setOrigen(nodo.get(position));
                Log.i(parent.toString(), nodo.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spDestino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setDestino(nodo.get(position));
                Log.i(parent.toString(), nodo.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(v.toString(), "ingreso al otro main");
                accionBoton();

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        actualizar();
    }

}
