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
import br.edu.fateczl.natacaofragments.controller.OperacaoComum;
import br.edu.fateczl.natacaofragments.model.AtletaComum;
import br.edu.fateczl.natacaofragments.model.AtletaSenior;

public class AtletaComumFragment extends Fragment {
    private View view;
    private EditText etNomeComum;
    private EditText etDataNascComum;
    private EditText etBairroComum;
    private EditText etAcademiaComum;
    private EditText etRecordeComum;
    private Button btnCadastrarComum;
    private TextView tvListaComum;

    public AtletaComumFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_atleta_comum, container, false);

        etNomeComum = view.findViewById(R.id.etNomeComum);
        etDataNascComum = view.findViewById(R.id.etDataNascComum);
        etBairroComum = view.findViewById(R.id.etBairroComum);
        etAcademiaComum = view.findViewById(R.id.etAcademiaComum);
        etRecordeComum = view.findViewById(R.id.etRecordeComum);
        btnCadastrarComum = view.findViewById(R.id.btnCadastrarSen);
        tvListaComum = view.findViewById(R.id.tvListaSen);
        tvListaComum.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        btnCadastrarComum.setOnClickListener(op -> cadastrar());

        return view;

    }

    @SuppressLint("NewApi")
    private void cadastrar() {
        AtletaComum comum = new AtletaComum();
        comum.setNome(etNomeComum.getText().toString());
        comum.setDataNasc(LocalDate.parse(etDataNascComum.getText().toString()));
        comum.setBairro(etBairroComum.getText().toString());
        comum.setAcademia(etAcademiaComum.getText().toString());
        comum.setRecorde(Integer.parseInt(etRecordeComum.getText().toString()));

        IOperacao<AtletaComum> op = new OperacaoComum();
        op.cadastrar(comum);
        List<AtletaComum> lista = op.listar();

        StringBuffer buffer =  new StringBuffer();
        for(AtletaComum c: lista){
            buffer.append(c.toString() + "\n");
        }

        tvListaComum.setText(buffer.toString());
        limparCampos();
    }

    private void limparCampos() {
        etNomeComum.setText("");
        etDataNascComum.setText("");
        etBairroComum.setText("");
        etAcademiaComum.setText("");
        etAcademiaComum.setText("");
    }
}