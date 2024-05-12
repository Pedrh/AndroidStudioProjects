package br.edu.fateczl.calcularsalarioprofessor;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.edu.fateczl.calcularsalarioprofessor.model.Professor;
import br.edu.fateczl.calcularsalarioprofessor.model.ProfessorHorista;
import br.edu.fateczl.calcularsalarioprofessor.model.ProfessorTitular;

public class MainActivity extends AppCompatActivity {

    private RadioButton titular;
    private EditText nome;
    private EditText idade;
    private EditText matricula;
    private EditText anosInst;
    private EditText salario;
    private EditText horaAula;
    private EditText valorHora;
    private TextView tvResultado;
    private Button btnCalc;



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

        titular = findViewById(R.id.rbTitular);
        titular.setChecked(true);
        nome = findViewById(R.id.etNome);
        idade = findViewById(R.id.etIdade);
        matricula = findViewById(R.id.etMatricula);
        anosInst = findViewById(R.id.etAnosInst);
        salario = findViewById(R.id.etSalario);
        horaAula = findViewById(R.id.etHoraAula);
        valorHora = findViewById(R.id.etValorHora);
        tvResultado = findViewById(R.id.tvResultado);
        btnCalc = findViewById(R.id.btnCalc);

        onUserInteraction();
        btnCalc.setOnClickListener(op -> calcularSalario());
    }


    public void onUserInteraction() {
        if (titular.isChecked()) {
            horaAula.setText("");
            valorHora.setText("");
            horaAula.setEnabled(false);
            valorHora.setEnabled(false);
            anosInst.setEnabled(true);
            salario.setEnabled(true);
        } else {
            anosInst.setText("");
            salario.setText("");
            anosInst.setEnabled(false);
            salario.setEnabled(false);
            horaAula.setEnabled(true);
            valorHora.setEnabled(true);
        }
    }

    private void calcularSalario() {
        if(titular.isChecked()){
            ProfessorTitular prof = new ProfessorTitular();

            prof.setNome(nome.getText().toString());
            prof.setMatricula(matricula.getText().toString());
            prof.setIdade(Integer.parseInt(idade.getText().toString()));
            prof.setAnosIsnt(Integer.parseInt(anosInst.getText().toString()));
            prof.setSalario(Double.parseDouble(salario.getText().toString()));

            double salFinal = prof.calcSalario();
            String msg = "R$ " + salFinal;

            tvResultado.setText(msg);
        } else{
            ProfessorHorista prof = new ProfessorHorista();
            prof.setNome(nome.getText().toString());
            prof.setMatricula(matricula.getText().toString());
            prof.setIdade(Integer.parseInt(idade.getText().toString()));
            prof.setHoraAula(Integer.parseInt(horaAula.getText().toString()));
            prof.setValorHora(Double.parseDouble(valorHora.getText().toString()));

            double salFinal = prof.calcSalario();
            String msg = "R$ " + salFinal;

            tvResultado.setText(msg);
        }
    }
}
