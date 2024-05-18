package br.edu.fateczl.vendaingressos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.edu.fateczl.vendaingressos.model.Ingresso;
import br.edu.fateczl.vendaingressos.model.IngressoVip;

public class saidaIngresso extends AppCompatActivity {

    private TextView tvSaida;
    private Button btnVoltar;
    private Ingresso ingresso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_saida_ingresso);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvSaida = findViewById(R.id.tvSaida);
        btnVoltar = findViewById(R.id.btnVoltar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();



        String tipo = bundle.getString("tipo");
        String func = "";

        if(tipo.equals("Vip")){
            ingresso = new IngressoVip();
            func = bundle.getString("func");
        } else{
            ingresso = new Ingresso();
            func = "Não cadastrado";
        }


        ingresso.setId(bundle.getString("id"));
        ingresso.setValor(bundle.getFloat("valor"));
        float resultado = bundle.getFloat("resultado");
        float taxa = bundle.getFloat("taxa");

        String msg = tipo.toUpperCase() + "\n\nid: " + ingresso.getId() + "\nvalor inicial do ingresso: " + ingresso.getValor() +
                "\ntaxa: " + taxa + "%\nFuncao: " + func + "\n---------------" + "\nValor final: " + resultado;
        tvSaida.setText(msg);

        btnVoltar.setOnClickListener(op -> voltar());
    }

    private void voltar() {
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
        this.finish();
    }
}