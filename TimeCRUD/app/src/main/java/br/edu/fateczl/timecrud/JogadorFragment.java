package br.edu.fateczl.timecrud;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.timecrud.controller.JogadorController;
import br.edu.fateczl.timecrud.controller.TimeController;
import br.edu.fateczl.timecrud.model.Jogador;
import br.edu.fateczl.timecrud.model.Time;
import br.edu.fateczl.timecrud.persistence.JogadorDao;
import br.edu.fateczl.timecrud.persistence.TimeDao;

public class JogadorFragment extends Fragment {

    private View view;
    private EditText etIdJog, etDataNascJog, etNomeJog, etAlturaJog, etPesoJog;
    private Button btnInsertJog, btnUpdateJog, btnDeleteJog, btnFindOneJog, btnFindAllJog;
    private Spinner spTimeJog;
    private TextView tvFinAllJog;
    private JogadorController jCont;
    private TimeController tCont;
    private List<Time> times;

    public JogadorFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_jogador, container, false);

        etIdJog = view.findViewById(R.id.etIdJog);
        etDataNascJog = view.findViewById(R.id.etDataNascJog);
        etNomeJog = view.findViewById(R.id.etNomeJog);
        etAlturaJog = view.findViewById(R.id.etAlturaJog);
        etPesoJog = view.findViewById(R.id.etPesoJog);
        btnInsertJog = view.findViewById(R.id.btnInsertJog);
        btnUpdateJog = view.findViewById(R.id.btnUpdateJog);
        btnDeleteJog = view.findViewById(R.id.btnDeleteJog);
        btnFindOneJog = view.findViewById(R.id.btnFindOneJog);
        btnFindAllJog = view.findViewById(R.id.btnFindAllJog);
        spTimeJog = view.findViewById(R.id.spTimeJog);
        tvFinAllJog = view.findViewById(R.id.tvFindAllJog);
        tvFinAllJog.setMovementMethod(new ScrollingMovementMethod());

        jCont = new JogadorController(new JogadorDao(view.getContext()));
        tCont = new TimeController(new TimeDao(view.getContext()));
        preencheSpinner();

        btnInsertJog.setOnClickListener(op -> acaoInsert());
        btnUpdateJog.setOnClickListener(op -> acaoUpdate());
        btnDeleteJog.setOnClickListener(op -> acaoDelete());
        btnFindOneJog.setOnClickListener(op -> acaoFindOne());
        btnFindAllJog.setOnClickListener(op -> acaoFindAll());

        return view;
    }

    private void acaoInsert() {
        int spPos = spTimeJog.getSelectedItemPosition();
        if(spPos > 0){
            Jogador jogador = montaJogador();
            try {
                jCont.insert(jogador);
                Toast.makeText(view.getContext(), "Jogador inserido com sucesso", Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            limpaCampos();
        } else{
            Toast.makeText(view.getContext(), "Selecione um Time", Toast.LENGTH_LONG).show();
        }
    }

    private void acaoUpdate() {
        int spPos = spTimeJog.getSelectedItemPosition();
        if(spPos > 0){
            Jogador jogador = montaJogador();
            try {
                jCont.update(jogador);
                Toast.makeText(view.getContext(), "Jogador atualizado com sucesso", Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            limpaCampos();
        } else{
            Toast.makeText(view.getContext(), "Selecione um Time", Toast.LENGTH_LONG).show();
        }
    }

    private void acaoDelete() {
        Jogador jogador = montaJogador();
        try {
            jCont.delete(jogador);
            Toast.makeText(view.getContext(), "Jogador deletado com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoFindOne() {
        Jogador jogador = montaJogador();
        try {
            times = tCont.findAll();
            jogador = jCont.findOne(jogador);
            if(jogador.getNome() != null){
                preencheCampos(jogador);
            } else{
                Toast.makeText(view.getContext(), "Jogador não encontrado", Toast.LENGTH_LONG).show();
                limpaCampos();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void acaoFindAll() {
        try {
            List<Jogador> jogadores = jCont.findAll();
            StringBuffer buffer = new StringBuffer();
            for(Jogador j: jogadores){
                buffer.append(j.toString() + "\n");
            }
            tvFinAllJog.setText(buffer.toString());
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void preencheSpinner() {
        Time t0 = new Time();
        t0.setCodigo(0);
        t0.setNome("Selecione um Time");
        t0.setCidade("");

        try {
            times = tCont.findAll();
            times.add(0, t0);


            ArrayAdapter ad = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, times);
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spTimeJog.setAdapter(ad);

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    private Jogador montaJogador(){
        Jogador j = new Jogador();
        j.setId(Integer.parseInt(etIdJog.getText().toString()));
        j.setNome(etNomeJog.getText().toString());
        j.setDataNasc(etDataNascJog.getText().toString());
        if(etAlturaJog.getText().toString().isEmpty()){
            j.setAltura(0);
        } else{
            j.setAltura(Float.parseFloat(etAlturaJog.getText().toString()));
        }
        if(etPesoJog.getText().toString().isEmpty()){
            j.setPeso(0);
        } else{
            j.setPeso(Float.parseFloat(etPesoJog.getText().toString()));
        }
        j.setTime((Time) spTimeJog.getSelectedItem());

        return j;
    }

    private void limpaCampos(){
        etIdJog.setText("");
        etNomeJog.setText("");
        etDataNascJog.setText("");
        etAlturaJog.setText("");
        etPesoJog.setText("");
        spTimeJog.setSelection(0);
    }

    public void preencheCampos(Jogador j){
        etIdJog.setText(String.valueOf(j.getId()));
        etNomeJog.setText(j.getNome());
        etDataNascJog.setText(j.getDataNasc());
        etAlturaJog.setText(String.valueOf(j.getAltura()));
        etPesoJog.setText(String.valueOf(j.getPeso()));

        int cont = 1;
        for(Time t: times){
            if(t.getCodigo() ==j.getTime().getCodigo()){
                spTimeJog.setSelection(cont);
            }else{
                cont++;

            }
        }
        if(cont > times.size()){
            spTimeJog.setSelection(0);
        }
    }
}