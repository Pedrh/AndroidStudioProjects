package br.edu.fateczl.calculaidade;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText etDia;
    private EditText etMes;
    private EditText etAno;
    private TextView tvIdade;


    @RequiresApi(api = Build.VERSION_CODES.O)
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

        etDia =findViewById(R.id.etDia);
        etDia.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etMes = findViewById(R.id.etMes);
        etMes.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etAno = findViewById(R.id.etAno);
        etAno.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvIdade = findViewById(R.id.tvIdade);
        tvIdade.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        Button btnCalc = findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(op -> calcularIdade());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void calcularIdade(){
        int diaNasc = Integer.parseInt(etDia.getText().toString());
        int mesNasc = Integer.parseInt(etMes.getText().toString());
        int anoNasc = Integer.parseInt(etAno.getText().toString());

        int diaAtual = LocalDate.now().getDayOfMonth();
        int mesAtual = LocalDate.now().getMonthValue();
        int anoAtual = LocalDate.now().getYear();

        if(diaAtual < diaNasc){
            mesAtual -= 1;
            switch (mesAtual){
                case 0 | 1 | 3 | 5 | 7 | 8 | 10 | 12:
                    diaAtual += 31;
                break;

                case 2:
                    if(anoAtual % 4 == 0 ){
                        if(anoAtual % 100 != 0){
                            diaAtual += 29;
                        } else{
                            diaAtual += 28;
                            break;
                        }
                    } else if(anoAtual % 400 == 0){
                        diaAtual += 29;
                    } else{
                        diaAtual += 28;
                    }
                break;

                default:
                    diaAtual +=30;
                break;
            }
        }

        if(mesAtual < mesNasc){
            anoAtual -= 1;
            mesAtual += 12;
        }

        int diaIdade = diaAtual - diaNasc;
        int mesIdade = mesAtual - mesNasc;
        int anoIdade = anoAtual - anoNasc;

        String idade = getString(R.string.idade) + anoIdade + " anos, " + mesIdade + "m, " + diaIdade + "d" ;
        tvIdade.setText(idade);
    }
}