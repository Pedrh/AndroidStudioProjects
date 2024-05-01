package br.edu.fateczl.duracaocombustivel;

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

    private EditText etConsumo;
    private EditText etRestante;
    private TextView tvAutonomia;
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

        etConsumo = findViewById(R.id.etConsumo);
        etConsumo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etRestante = findViewById(R.id.etLitrosRestantes);
        etRestante.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvAutonomia = findViewById(R.id.tvAutonomia);
        tvAutonomia.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Button btnCalc = findViewById(R.id.btnCalc);

        btnCalc.setOnClickListener(op -> calcularAutonomia());
    }

    public void calcularAutonomia(){
        double consumo =Double.parseDouble(etConsumo.getText().toString());
        double restante = Double.parseDouble(etRestante.getText().toString());
        double autonomia = consumo * restante * 1000;

        String msg =getString(R.string.autonomia) + " " + autonomia + "m";
        tvAutonomia.setText(msg);

        etRestante.setText("");
        etConsumo.setText("");
    }
}