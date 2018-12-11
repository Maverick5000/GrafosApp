package com.proavanzada.krauss.grafos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.proavanzada.krauss.grafos.model.Nodes;

import java.util.ArrayList;

public class Resultado extends AppCompatActivity {

    private TextView costo, tiempo, rutaC, rutaT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        costo = findViewById(R.id.TVprecio);
        tiempo = findViewById(R.id.TVtiempo);
        rutaC = findViewById(R.id.TVrutaC);
        rutaT = findViewById(R.id.TVrutaT);

        ArrayList s = Nodes.getInstancia().getLlaves();

        costo.setText("Precio: " + Nodes.getInstancia().getCosto());
        tiempo.setText("Duración: " + Nodes.getInstancia().getTiempo());
        String res = "";
        ArrayList<String> rutaCosto = Nodes.getInstancia().getRutaEco();
        for (String ruta : rutaCosto) {
            res += ruta + " - ";
        }


        rutaC.setText(res.substring(0, res.length() - 2));

        String res2 = "";
        ArrayList<String> rutaTiempo = Nodes.getInstancia().getRutaEco();
        for (String ruta : rutaTiempo) {
            res2 += ruta + " - ";
        }


        rutaT.setText(res2.substring(0, res2.length() - 2));
    }
}
