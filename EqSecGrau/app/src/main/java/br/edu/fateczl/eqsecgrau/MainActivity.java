package br.edu.fateczl.eqsecgrau;

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

    private EditText etA;
    private EditText etB;
    private EditText etC;
    private TextView tvDelta;
    private TextView tvX1;
    private TextView tvX2;
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

        etA = findViewById(R.id.etA);
        etA.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etB = findViewById(R.id.etB);
        etB.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etC = findViewById(R.id.etC);
        etC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvDelta = findViewById(R.id.tvDelta);
        tvDelta.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Button btnCalc = findViewById(R.id.btnCalc);

        btnCalc.setOnClickListener(op -> calc());
    }

    private void calc(){
        double a = Double.parseDouble(etA.getText().toString());
        double b = Double.parseDouble(etB.getText().toString());
        double c = Double.parseDouble(etC.getText().toString());
        double x1, x2;

        if(a==0){
            if(b==0){
                String msg = getString(R.string.equacao_invalida);
                tvDelta.setText(msg);

                return;
            }
            x2 = -b / c;
            String msg = getString(R.string.equacao_linear);
            tvDelta.setText(msg);
            return;
        }


        double delta = (b * b) - (4 * a * c);

        if (delta == 0 ){
            String res1 = getString(R.string.delta) + " " + delta;
            tvDelta.setText(res1);

            x1 = ((b * -1) + Math.sqrt(delta)) / 2 * a;
            x2 = x1;

            String res2 = getString(R.string.x1) + " " + x1;
            tvX1.setText(res2);
            String res3 = getString(R.string.x2) + " " + x2;
            tvX2.setText(res3);
        } else if (delta < 0) {
            String msg = getString(R.string.delta_negativo);
            tvDelta.setText(msg);
        } else{
            String res1 = getString(R.string.delta) + " " + delta;
            tvDelta.setText(res1);

            x1 = (-b + Math.sqrt(delta)) / 2 * a;
            x2 = (-b - Math.sqrt(delta)) / 2 * a;

            String res2 = getString(R.string.x1) + " " + x1;
            tvX1.setText(res2);
            String res3 = getString(R.string.x2) + " " + x2;
            tvX2.setText(res3);
        }

    }
}