package br.edu.fateczl.calculodecombustivel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etGasolina;
    private EditText etEtanol;
    private TextView tvComparacao;


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

        etGasolina = findViewById(R.id.etGasolina);
        etGasolina.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etEtanol = findViewById(R.id.etEtanol);
        etEtanol.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvComparacao = findViewById(R.id.tvComparacao);
        tvComparacao.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Button btnComparar = findViewById(R.id.btnComparar);

        btnComparar.setOnClickListener(op -> comparar());
    }

    public void comparar(){
        double gas = Double.parseDouble(etGasolina.getText().toString());
        double eta = Double.parseDouble(etEtanol.getText().toString());
        double comparacao = gas * 0.7;
        if(eta <= comparacao){
            String msg = getString(R.string.melhorEtanol);
            tvComparacao.setText(msg);
        } else{
            String msg = getString(R.string.melhorGasolina);
            tvComparacao.setText(msg);
        }

        etGasolina.setText("");
        etEtanol.setText("");
    }


}