package br.edu.fateczl.crudbiblioteca;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.edu.fateczl.crudbiblioteca.controller.LivroController;
import br.edu.fateczl.crudbiblioteca.controller.RevistaController;
import br.edu.fateczl.crudbiblioteca.model.Exemplar;
import br.edu.fateczl.crudbiblioteca.model.Livro;
import br.edu.fateczl.crudbiblioteca.model.Revista;
import br.edu.fateczl.crudbiblioteca.persistence.LivroDao;
import br.edu.fateczl.crudbiblioteca.persistence.RevistaDao;

public class ExemplarFragment extends Fragment {

    private View view;
    private RadioButton rbRevistaEx, rbLivroEx;
    private EditText etCodigoEx, etNomeEx, etQtdPagsEx, etIsbnEx, etEdicaoEx, etIssnEx;
    private Button btnInsertEx, btnUpdateEx, btnDeleteEx, btnFindOneEx, btnFindAllEx;
    private TextView tvFindAllEx;
    private LivroController lCont;
    private RevistaController rCont;


    public ExemplarFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_exemplar, container, false);

        rbRevistaEx = view.findViewById(R.id.rbRevistaEx);
        rbLivroEx = view.findViewById(R.id.rbLivroEx);
        rbLivroEx.setChecked(true);
        etCodigoEx = view.findViewById(R.id.etCodigoEx);
        etNomeEx = view.findViewById(R.id.etNomeEx);
        etQtdPagsEx = view.findViewById(R.id.etQtdPagsEx);
        etIsbnEx = view.findViewById(R.id.etIsbnEx);
        etEdicaoEx = view.findViewById(R.id.etEdicaoEx);
        etIssnEx = view.findViewById(R.id.etIssnEx);
        btnInsertEx = view.findViewById(R.id.btnInsertEx);
        btnUpdateEx = view.findViewById(R.id.btnUpdateEx);
        btnDeleteEx = view.findViewById(R.id.btnDeleteEx);
        btnFindOneEx = view.findViewById(R.id.btnFindOneEx);
        btnFindAllEx = view.findViewById(R.id.btnFindAllEx);
        tvFindAllEx = view.findViewById(R.id.tvFindAllEx);
        tvFindAllEx.setMovementMethod(new ScrollingMovementMethod());

        updateActivity();
        
        for (RadioButton radioButton : Arrays.asList(rbLivroEx, rbRevistaEx)) {
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    ExemplarFragment.this.updateActivity();
                }
            });
        }

        lCont = new LivroController(new LivroDao(view.getContext()));
        rCont = new RevistaController(new RevistaDao(view.getContext()));

        btnInsertEx.setOnClickListener(op -> acaoInsert());
        btnUpdateEx.setOnClickListener(op -> acaoUpdate());
        btnDeleteEx.setOnClickListener(op -> acaodelete());
        btnFindOneEx.setOnClickListener(op -> acaoFindOne());
        btnFindAllEx.setOnClickListener(op -> acaoFindAll());

        return view;
    }

    private void updateActivity() {
        if(rbLivroEx.isChecked()){
            etIssnEx.setText("");
            etIssnEx.setEnabled(false);
            etIsbnEx.setEnabled(true);
            etEdicaoEx.setEnabled(true);
        } else{
            etIsbnEx.setText("");
            etEdicaoEx.setText("");
            etIsbnEx.setEnabled(false);
            etEdicaoEx.setEnabled(false);
            etIssnEx.setEnabled(true);
        }
    }

    private void acaoInsert() {
        try{
            if(rbLivroEx.isChecked()){
                Livro exemplar = montaLivro();
                lCont.insert(exemplar);
            } else{
                Revista exemplar = montaRevista();
                rCont.insert(exemplar);
            }
            Toast.makeText(view.getContext(), "Exemplar inserido com sucesso", Toast.LENGTH_LONG).show();
        }catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoUpdate() {
        try{
            if(rbLivroEx.isChecked()){
                Livro exemplar = montaLivro();
                lCont.update(exemplar);
            } else{
                Revista exemplar = montaRevista();
                rCont.update(exemplar);
            }
            Toast.makeText(view.getContext(), "Exemplar atualizado com sucesso", Toast.LENGTH_LONG).show();
        }catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaodelete() {
        try{
            if(rbLivroEx.isChecked()){
                Livro exemplar = montaLivro();
                lCont.delete(exemplar);
            } else{
                Revista exemplar = montaRevista();
                rCont.delete(exemplar);
            }
            Toast.makeText(view.getContext(), "Exemplar excluido com sucesso", Toast.LENGTH_LONG).show();
        }catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoFindOne() {
        try{
            if(rbLivroEx.isChecked()){
                Livro exemplar = montaLivro();
                exemplar = lCont.findOne(exemplar);
                if(exemplar.getNome() != null){
                    preencheCampos(exemplar);
                    Toast.makeText(view.getContext(), "Exemplar não encontrado", Toast.LENGTH_LONG).show();
                    limpaCampos();
                }
            } else{
                Revista exemplar = montaRevista();
                exemplar = rCont.findOne(exemplar);
                if(exemplar.getNome() != null){
                    preencheCampos(exemplar);
                    Toast.makeText(view.getContext(), "Exemplar não encontrado", Toast.LENGTH_LONG).show();
                    limpaCampos();
                }
            }
        }catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void acaoFindAll() {
        try{
            List<Exemplar> exemplares = new ArrayList<>();
            exemplares.addAll(lCont.findAll());
            exemplares.addAll(rCont.findAll());
            StringBuffer buffer = new StringBuffer();

            for(Exemplar ex: exemplares){
                buffer.append(ex.toString() + "\n");
            }
            tvFindAllEx.setText(buffer.toString());
        }catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Livro montaLivro(){
        Livro ex = new Livro();
        ex.setCodigo(Integer.parseInt(etCodigoEx.getText().toString()));
        ex.setNome(etNomeEx.getText().toString());
        ex.setQtdPaginas(Integer.parseInt(etQtdPagsEx.getText().toString()));
        ex.setIsbn(etIsbnEx.getText().toString());
        ex.setEdicao(Integer.parseInt(etEdicaoEx.getText().toString()));
        return ex;
    }

    private Revista montaRevista(){
        Revista ex = new Revista();
        ex.setCodigo(Integer.parseInt(etCodigoEx.getText().toString()));
        ex.setNome(etNomeEx.getText().toString());
        ex.setQtdPaginas(Integer.parseInt(etQtdPagsEx.getText().toString()));
        ex.setIssn(etIssnEx.getText().toString());
        return ex;
    }

    private void preencheCampos(Exemplar ex){
        if(rbLivroEx.isChecked()){
            Livro exAtual = new Livro();
            etCodigoEx.setText(String.valueOf(exAtual.getCodigo()));
            etNomeEx.setText(exAtual.getNome());
            etQtdPagsEx.setText(String.valueOf(exAtual.getQtdPaginas()));
            etIsbnEx.setText(exAtual.getIsbn());
            etEdicaoEx.setText(String.valueOf(exAtual.getEdicao()));
        } else{
            Revista exAtual = new Revista();
            etCodigoEx.setText(String.valueOf(exAtual.getCodigo()));
            etNomeEx.setText(exAtual.getNome());
            etQtdPagsEx.setText(String.valueOf(exAtual.getQtdPaginas()));
            etIsbnEx.setText(exAtual.getIssn());
        }
    }

    private void limpaCampos(){
        etCodigoEx.setText("");
        etNomeEx.setText("");
        etQtdPagsEx.setText("");
        etIsbnEx.setText("");
        etEdicaoEx.setText("");
        etIssnEx.setText("");
    }



}