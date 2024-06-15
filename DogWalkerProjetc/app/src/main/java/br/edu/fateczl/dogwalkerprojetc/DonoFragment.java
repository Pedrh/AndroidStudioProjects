package br.edu.fateczl.dogwalkerprojetc;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

import br.edu.fateczl.dogwalkerprojetc.controller.DonoController;
import br.edu.fateczl.dogwalkerprojetc.model.Dono;
import br.edu.fateczl.dogwalkerprojetc.persistence.DonoDao;

public class DonoFragment extends Fragment {

    private View view;
    private EditText etNomeDono, etCEPDono, etFoneDono, etEmailDono;
    private Button btnInsertDono, btnUpdateDono, btnFindOneDono;
    private TextView tvCadastradoDono;
    private DonoController dCont;

    public DonoFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dono, container, false);

        etNomeDono = view.findViewById(R.id.etNomeDono);
        etCEPDono = view.findViewById(R.id.etCEPDono);
        etFoneDono = view.findViewById(R.id.etFoneDono);
        etEmailDono = view.findViewById(R.id.etEmailDono);
        btnInsertDono = view.findViewById(R.id.btnInsertDono);
        btnUpdateDono = view.findViewById(R.id.btnUpdateDono);
        btnFindOneDono = view.findViewById(R.id.btnFindOneDono);
        tvCadastradoDono = view.findViewById(R.id.tvCadastradoDono);
        identificarCadastro();

        dCont = new DonoController(new DonoDao(view.getContext()));

        btnInsertDono.setOnClickListener(op -> acaoInsert());
        btnUpdateDono.setOnClickListener(op -> acaoUpdate());
        btnFindOneDono.setOnClickListener(op -> acaoFindOne());
        return view;
    }

    private void identificarCadastro() {
        String msg = "";
        Dono dono = new Dono();
        try{
            dono.setCodigo(1);
            dono = dCont.findOne(dono);
            if(dono.getNome() != null){
                msg = "Usuário logado";
            } else{
                msg = "Usuário não cadastrado";
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        tvCadastradoDono.setText(msg);
    }

    private void acaoInsert() {
        Dono dono = montaDono();
        try{
            if(tvCadastradoDono.getText().toString().contains("cadastrado")){
                dCont.insert(dono);
                Toast.makeText(view.getContext(), "Usuário efetuou cadastro", Toast.LENGTH_LONG).show();
                tvCadastradoDono.setText("Usuário logado");
            } else {
                acaoUpdate();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoUpdate() {
        Dono dono = montaDono();
        try{
            if(tvCadastradoDono.getText().toString().contains("cadastrado")){
               acaoInsert();
            } else {
                dCont.update(dono);
                Toast.makeText(view.getContext(), "Usuário atualizado com sucesso", Toast.LENGTH_LONG).show();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoFindOne() {
        Dono dono = montaDono();
        try{
            dono = dCont.findOne(dono);
            if(dono.getNome() == null){
                Toast.makeText(view.getContext(), "Dono não cadastrado", Toast.LENGTH_LONG).show();
                limpaCampos();
            } else{
                preencheCampos(dono);
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Dono montaDono(){
        Dono d = new Dono();
        d.setNome(etNomeDono.getText().toString());
        d.setEmail(etEmailDono.getText().toString());
        if(etCEPDono.getText().toString().isEmpty()){
            d.setCep(0);
        } else{
            d.setCep(Integer.parseInt(etCEPDono.getText().toString()));
        }
        d.setTelefone(etFoneDono.getText().toString());
        d.setCodigo(1);

        return d;
    }

    private void preencheCampos(Dono d){
        etNomeDono.setText(d.getNome());
        etEmailDono.setText(d.getEmail());
        etCEPDono.setText(String.valueOf(d.getCep()));
        etFoneDono.setText(String.valueOf(d.getTelefone()));
    }

    private void limpaCampos(){
        etNomeDono.setText("");
        etEmailDono.setText("");
        etCEPDono.setText("");
        etFoneDono.setText("");
    }



}