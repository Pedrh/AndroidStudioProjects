package br.edu.fateczl.natacaofragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.List;

import br.edu.fateczl.natacaofragments.controller.IOperacao;
import br.edu.fateczl.natacaofragments.controller.OperacaoSenior;
import br.edu.fateczl.natacaofragments.model.AtletaJuvenil;
import br.edu.fateczl.natacaofragments.model.AtletaSenior;

public class AtletaSeniorFragment extends Fragment {

    private View view;
    private EditText etNomeSen;
    private EditText etDataNascSen;
    private EditText etBairroSen;
    private CheckBox cbCardiacoSen;
    private Button btnCadastrarSen;
    private TextView tvListaSen;

    public AtletaSeniorFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_atleta_senior, container, false);

        etNomeSen = view.findViewById(R.id.etNomeSen);
        etDataNascSen = view.findViewById(R.id.etDataNascSen);
        etBairroSen = view.findViewById(R.id.etBairroSen);
        cbCardiacoSen = view.findViewById(R.id.cbCardiacoSen);
        cbCardiacoSen.setChecked(false);
        btnCadastrarSen = view.findViewById(R.id.btnCadastrarSen);
        tvListaSen = view.findViewById(R.id.tvListaSen);
        tvListaSen.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        btnCadastrarSen.setOnClickListener(op -> cadastrar());

        return view;
    }

    @SuppressLint("NewApi")
    private void cadastrar() {
        AtletaSenior sen = new AtletaSenior();
        sen.setNome(etNomeSen.getText().toString());
        sen.setDataNasc(LocalDate.parse(etDataNascSen.getText().toString()));
        sen.setBairro(etBairroSen.getText().toString());
        if(cbCardiacoSen.isChecked()){
            sen.setCardiaco(true);
        } else{
            sen.setCardiaco(false);
        }

        IOperacao<AtletaSenior> op = new OperacaoSenior();
        op.cadastrar(sen);
        List<AtletaSenior> lista = op.listar();

        StringBuffer buffer =  new StringBuffer();
        for(AtletaSenior s: lista){
            buffer.append(s.toString() + "\n");
        }
        tvListaSen.setText(buffer.toString());
        limparCampos();
    }

    private void limparCampos() {
        etNomeSen.setText("");
        etDataNascSen.setText("");
        etBairroSen.setText("");
        cbCardiacoSen.setChecked(false);
    }
}