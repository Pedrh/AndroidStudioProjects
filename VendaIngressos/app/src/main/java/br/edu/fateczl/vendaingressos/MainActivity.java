package br.edu.fateczl.vendaingressos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.edu.fateczl.vendaingressos.model.Ingresso;
import br.edu.fateczl.vendaingressos.model.IngressoVip;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbIngresso;
    private RadioButton rbVip;
    private EditText etId;
    private EditText etValor;
    private EditText etTaxa;
    private EditText etFunc;
    private Button btnComprar;
    private Ingresso ingresso;

    @SuppressLint("MissingInflatedId")
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

        rbIngresso = findViewById(R.id.rbIngresso);
        rbIngresso.setChecked(true);
        rbVip = findViewById(R.id.rbVip);
        etId = findViewById(R.id.etId);
        etValor = findViewById(R.id.etValor);
        etTaxa = findViewById(R.id.etTaxa);
        etFunc = findViewById(R.id.etFunc);
        btnComprar = findViewById(R.id.btnComprar);


        onUserInteraction();
        btnComprar.setOnClickListener(op -> comprar());
    }

    @Override
    public void onUserInteraction() {
        if(rbIngresso.isChecked()){
            etFunc.setText("");
            etFunc.setEnabled(false);
        } else{
            etFunc.setEnabled(true);
        }
    }

    private void comprar() {
        String tipo = "";
        float taxa = Float.parseFloat(etTaxa.getText().toString());
        float valor = Float.parseFloat(etValor.getText().toString());
        String id = etId.getText().toString();
        String func = "";

        if(rbIngresso.isChecked()){
            ingresso = new Ingresso();
            tipo = "ingresso";
        } else{
            ingresso = new IngressoVip();
            func = etFunc.getText().toString();
            tipo = "Vip";
        }

        ingresso.setId(id);
        ingresso.setValor(valor);
        float resultado = ingresso.valorFinal(taxa);


        Bundle bundle = new Bundle();

        bundle.putString("id",id);
        bundle.putFloat("valor",valor);
        bundle.putFloat("taxa", taxa);
        bundle.putString("tipo", tipo);
        bundle.putString("func", func);
        bundle.putFloat("resultado",resultado);

        mostrarSaida(bundle);

    }

    private void mostrarSaida(Bundle bundle) {
        Intent i = new Intent(this, saidaIngresso.class);
        i.putExtras(bundle);
        this.startActivity(i);
        this.finish();
    }



}