package br.edu.fateczl.criacaodedados;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioButton umDado;
    private RadioButton doisDados;
    private Spinner spQtdFaces;
    private Button btnGerar;
    private TextView tvDadoUm;
    private TextView tvDadoDois;
    private TextView tvDadoTres;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        umDado = findViewById(R.id.rbUmDado);
        umDado.setChecked(true);
        doisDados = findViewById(R.id.rbDoisDados);
        spQtdFaces = findViewById(R.id.spQtdFaces);
        btnGerar = findViewById(R.id.btnGerar);
        tvDadoUm = findViewById(R.id.tvDadoUm);
        tvDadoDois = findViewById(R.id.tvDadoDois);

        preencheSpinner();
        btnGerar.setOnClickListener(op -> gerarFaces());
    }

    private void preencheSpinner() {
        List<Integer> lista = new ArrayList<>();

        lista.add(4);
        lista.add(6);
        lista.add(8);
        lista.add(10);
        lista.add(12);
        lista.add(20);
        lista.add(100);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQtdFaces.setAdapter(adapter);
    }

    private void gerarFaces() {
        tvDadoUm.setText("");
        tvDadoDois.setText("");
        tvDadoTres.setText("");

        if(umDado.isChecked()){
            Integer qtdFaces = (Integer) spQtdFaces.getSelectedItem();
            int face = (int) (Math.random() * qtdFaces) + 1;
            String d1 = "D1: " + face;

            tvDadoUm.setText(d1);
        } else if(doisDados.isChecked()){
            Integer qtdFaces = (Integer) spQtdFaces.getSelectedItem();
            int face1 = (int) (Math.random() * qtdFaces) + 1;
            String d1 = "D1: " + face1;
            int face2 = (int) (Math.random() * qtdFaces) + 1;
            String d2 = "D2: " + face2;

            tvDadoUm.setText(d1);
            tvDadoDois.setText(d2);
        } else{
            Integer qtdFaces = (Integer) spQtdFaces.getSelectedItem();
            int face1 = (int) (Math.random() * qtdFaces) + 1;
            String d1 = "D1: " + face1;
            int face2 = (int) (Math.random() * qtdFaces) + 1;
            String d2 = "D2: " + face2;
            int face3 = (int) (Math.random() * qtdFaces) + 1;
            String d3 = "D3: " + face3;

            tvDadoUm.setText(d1);
            tvDadoDois.setText(d2);
            tvDadoTres.setText(d3);
        }
    }
}