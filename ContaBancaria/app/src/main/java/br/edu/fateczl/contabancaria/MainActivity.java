package br.edu.fateczl.contabancaria;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.edu.fateczl.contabancaria.controller.ContaEspecialController;
import br.edu.fateczl.contabancaria.controller.ContaFactory;
import br.edu.fateczl.contabancaria.controller.ContaPoupancaController;
import br.edu.fateczl.contabancaria.model.ContaBancaria;
import br.edu.fateczl.contabancaria.model.ContaEspecial;
import br.edu.fateczl.contabancaria.model.ContaPoupanca;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbPoupanca;
    private RadioButton rbEspecial;
    private EditText etNome;
    private EditText etNumConta;
    private EditText etSaldo;
    private EditText etValor;
    private EditText etDiaRend;
    private EditText etTaxaRend;
    private EditText etLimite;
    private TextView tvResultado;
    private Button btnSacar;
    private Button btnDepositar;
    private Button btnRecalcular;
    private Button btnMostrar;
    private Button btnNovoCliente;
    private ContaFactory contaF;
    private ContaBancaria conta;

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

        rbPoupanca = findViewById(R.id.rbPoupanca);
        rbPoupanca.setChecked(true);
        rbEspecial = findViewById(R.id.rbEspecial);
        etNome = findViewById(R.id.etNome);
        etNumConta = findViewById(R.id.etNumConta);
        etSaldo = findViewById(R.id.etSaldo);
        etValor = findViewById(R.id.etValor);
        etDiaRend = findViewById(R.id.etDiaRend);
        etTaxaRend = findViewById(R.id.etTaxaRend);
        etLimite = findViewById(R.id.etLimite);
        tvResultado = findViewById(R.id.tvResultado);
        btnSacar = findViewById(R.id.btnSacar);
        btnDepositar = findViewById(R.id.btnDepositar);
        btnRecalcular = findViewById(R.id.btnRecalc);
        btnMostrar = findViewById(R.id.btnMostrar);
        btnNovoCliente = findViewById(R.id.btnNovoCliente);

        onUserInteraction();
        btnNovoCliente.setOnClickListener(op -> conta = criacaoConta());
        btnDepositar.setOnClickListener(op -> depositar());
        btnSacar.setOnClickListener(op -> sacar());
        btnMostrar.setOnClickListener(op -> mostrarCliente());
        btnRecalcular.setOnClickListener(op -> recalcSaldo());

    }



    @Override
    public void onUserInteraction() {
        if(rbPoupanca.isChecked()){
            etLimite.setText("");
            etLimite.setEnabled(false);
            etDiaRend.setEnabled(true);
            etTaxaRend.setEnabled(true);
            btnRecalcular.setEnabled(true);
        } else{
            etDiaRend.setText("");
            etTaxaRend.setText("");
            etDiaRend.setEnabled(false);
            etTaxaRend.setEnabled(false);
            btnRecalcular.setEnabled(false);
            etLimite.setEnabled(true);
        }
    }

    private void depositar() {
        float valor = Float.parseFloat(etValor.getText().toString());
        float saldoFinal = conta.depositar(valor);

        String  msg = getString(R.string.depositoFeito) + saldoFinal;
        tvResultado.setText(msg);
    }

    private void sacar() {
        String msg;
        float valor = Float.parseFloat(etValor.getText().toString());
        try{
            float saldoFinal = conta.sacar(valor);
            msg = getString(R.string.saqueFeito) + saldoFinal;
            tvResultado.setText(msg);
        } catch (ArithmeticException e){
            msg = e.getMessage();
            tvResultado.setText(msg);
        }
    }

    private void mostrarCliente() {
        String msg;
        try{
            msg = conta.toString();
            tvResultado.setText(msg);
        } catch (Exception e){
            msg = "Nenhum cliente cadastrado";
            tvResultado.setText(msg);
        }

    }

    private void recalcSaldo() {
        float valorRend = Float.parseFloat(etTaxaRend.getText().toString());
        ContaPoupanca contaP = new ContaPoupanca();
        contaP = (ContaPoupanca) conta;
        float saldoFinal = contaP.recalcSaldo(valorRend);

        String  msg = getString(R.string.depositoFeito) + saldoFinal;
        tvResultado.setText(msg);
    }


    private ContaBancaria criacaoConta(){
        String nome = etNome.getText().toString();
        int numConta = Integer.parseInt(etNumConta.getText().toString());
        float saldo = Float.parseFloat(etSaldo.getText().toString());
        ContaBancaria contaB;

        if(rbPoupanca.isChecked()){
            contaF = new ContaPoupancaController();
            contaB =  new ContaPoupanca();
            int diaRend = Integer.parseInt(etDiaRend.getText().toString());

            contaF.setDiaRend(diaRend);
            contaB = contaF.criarConta(nome, numConta, saldo);

        } else{
            contaF = new ContaEspecialController();
            contaB = new ContaEspecial();
            float limite = Float.parseFloat(etLimite.getText().toString());

            contaF.setLimite(limite);
            contaB = contaF.criarConta(nome, numConta, saldo);
        }

        String  msg = "Novo cliente cadastrado";
        tvResultado.setText(msg);
        return contaB;
    }
}