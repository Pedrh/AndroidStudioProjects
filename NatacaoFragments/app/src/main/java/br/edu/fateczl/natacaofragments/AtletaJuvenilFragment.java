package br.edu.fateczl.natacaofragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.List;

import br.edu.fateczl.natacaofragments.controller.IOperacao;
import br.edu.fateczl.natacaofragments.controller.OperacaoJuvenil;
import br.edu.fateczl.natacaofragments.model.AtletaJuvenil;


public class AtletaJuvenilFragment extends Fragment {

    private View view;
    private EditText etNomeJuv;
    private EditText etDataNascJuv;
    private EditText etBairroJuv;
    private EditText etPraticaJuv;
    private Button btnCadastrarJuv;
    private TextView tvListaJuv;

    public AtletaJuvenilFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_atleta_juvenil, container, false);

        etNomeJuv = view.findViewById(R.id.etNomeJuv);
        etDataNascJuv = view.findViewById(R.id.etDataNascJuv);
        etBairroJuv = view.findViewById(R.id.etBairroJuv);
        etPraticaJuv = view.findViewById(R.id.etPraticaJuv);
        btnCadastrarJuv = view.findViewById(R.id.btnCadastrarJuv);
        tvListaJuv = view.findViewById(R.id.tvListaJuv);
        tvListaJuv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        btnCadastrarJuv.setOnClickListener(op -> cadastrar());

        return view;
    }

    private void cadastrar() {
        AtletaJuvenil juv = new AtletaJuvenil();
        juv.setNome(etNomeJuv.getText().toString());
        juv.setDataNasc(etDataNascJuv.getText().toString());
        juv.setBairro(etBairroJuv.getText().toString());
        juv.setAnosPratica(Integer.parseInt(etPraticaJuv.getText().toString()));

        IOperacao<AtletaJuvenil> op = new OperacaoJuvenil();
        op.cadastrar(juv);
        List<AtletaJuvenil> lista = op.listar();

        StringBuffer buffer =  new StringBuffer();
        for(AtletaJuvenil j: lista){
            buffer.append(j.toString() + "\n");
        }

        tvListaJuv.setText(buffer.toString());
        limparCampos();
    }

    private void limparCampos() {
        etNomeJuv.setText("");
        etDataNascJuv.setText("");
        etBairroJuv.setText("");
        etPraticaJuv.setText("");
    }
}