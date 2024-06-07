package br.edu.fateczl.crudbiblioteca;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
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

import br.edu.fateczl.crudbiblioteca.controller.AluguelController;
import br.edu.fateczl.crudbiblioteca.controller.AlunoController;
import br.edu.fateczl.crudbiblioteca.controller.LivroController;
import br.edu.fateczl.crudbiblioteca.controller.RevistaController;
import br.edu.fateczl.crudbiblioteca.model.Aluguel;
import br.edu.fateczl.crudbiblioteca.model.Aluno;
import br.edu.fateczl.crudbiblioteca.model.Exemplar;
import br.edu.fateczl.crudbiblioteca.model.Revista;
import br.edu.fateczl.crudbiblioteca.persistence.AluguelDao;
import br.edu.fateczl.crudbiblioteca.persistence.AlunoDao;
import br.edu.fateczl.crudbiblioteca.persistence.LivroDao;
import br.edu.fateczl.crudbiblioteca.persistence.RevistaDao;

public class AluguelFragment extends Fragment {

    private View view;
    private EditText etDataRetiradaAg, etDataDevolucaoAg;
    private Spinner spAlunoAg, spExemplarAg;
    private Button btnInsertAg, btnUpdateAg, btnDeleteAg, btnFindOneAg, btnFindAllAg;
    private TextView tvFindAllAg;
    private AluguelController agCont;
    private AlunoController alCont;
    private LivroController lCont;
    private RevistaController rCont;
    private List<Aluno> alunos;
    private List<Exemplar> exemplares;



    public AluguelFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_aluguel, container, false);

        etDataRetiradaAg = view.findViewById(R.id.etDataRetiradaAg);
        etDataDevolucaoAg = view.findViewById(R.id.etDataDevolucaoAg);
        spAlunoAg = view.findViewById(R.id.spAlunoAg);
        spExemplarAg = view.findViewById(R.id.spExemplarAg);
        btnInsertAg = view.findViewById(R.id.btnInsertAg);
        btnUpdateAg = view.findViewById(R.id.btnUpdateAg);
        btnDeleteAg = view.findViewById(R.id.btnDeleteAg);
        btnFindOneAg = view.findViewById(R.id.btnFindOneAg);
        btnFindAllAg = view.findViewById(R.id.btnFindAllAg);
        tvFindAllAg = view.findViewById(R.id.tvFindAllAg);
        tvFindAllAg.setMovementMethod(new ScrollingMovementMethod());

        agCont = new AluguelController(new AluguelDao(view.getContext()));
        alCont = new AlunoController(new AlunoDao(view.getContext()));
        lCont = new LivroController(new LivroDao(view.getContext()));
        rCont = new RevistaController(new RevistaDao(view.getContext()));
        preencheSpinners();

        btnInsertAg.setOnClickListener(op -> acaoInsert());
        btnUpdateAg.setOnClickListener(op -> acaoUpdate());
        btnDeleteAg.setOnClickListener(op -> acaoDelete());
        btnFindOneAg.setOnClickListener(op -> acaoFindOne());
        btnFindAllAg.setOnClickListener(op -> acaoFindAll());

        return view;
    }

    private void acaoInsert() {
        int spAlPos = spAlunoAg.getSelectedItemPosition();
        int spExPos = spExemplarAg.getSelectedItemPosition();

        if(spAlPos > 0){
            if(spExPos > 0){
                Aluguel aluguel = new Aluguel();
                try {
                    agCont.insert(aluguel);
                    Toast.makeText(view.getContext(), "Aluguel inserido com sucesso", Toast.LENGTH_LONG).show();
                } catch (SQLException e) {
                    Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                limpaCampos();
            } else{
                Toast.makeText(view.getContext(), "Selecione um Exemplar", Toast.LENGTH_LONG).show();
            }
        } else{
            Toast.makeText(view.getContext(), "Selecione um Aluno", Toast.LENGTH_LONG).show();
        }

    }

    private void acaoUpdate() {
        int spAlPos = spAlunoAg.getSelectedItemPosition();
        int spExPos = spExemplarAg.getSelectedItemPosition();

        if(spAlPos > 0){
            if(spExPos > 0){
                Aluguel aluguel = new Aluguel();
                try {
                    agCont.update(aluguel);
                    Toast.makeText(view.getContext(), "Aluguel atualizado com sucesso", Toast.LENGTH_LONG).show();
                } catch (SQLException e) {
                    Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                limpaCampos();
            } else{
                Toast.makeText(view.getContext(), "Selecione um Exemplar", Toast.LENGTH_LONG).show();
            }
        } else{
            Toast.makeText(view.getContext(), "Selecione um Aluno", Toast.LENGTH_LONG).show();
        }
    }

    private void acaoDelete() {
        Aluguel aluguel = new Aluguel();
        try {
            agCont.delete(aluguel);
            Toast.makeText(view.getContext(), "Aluguel deletado com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoFindOne() {
        Aluguel aluguel = new Aluguel();
        try {
            alunos = alCont.findAll();

            exemplares = new ArrayList<>();
            exemplares.addAll(lCont.findAll());
            exemplares.addAll(rCont.findAll());

            aluguel = agCont.findOne(aluguel);

            if(aluguel.getDataRetirada() != null){
                preencheCampos(aluguel);
            } else{
                Toast.makeText(view.getContext(), "Aluguel não encontrado", Toast.LENGTH_LONG).show();
                limpaCampos();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void acaoFindAll() {
        try {
            List<Aluguel> alugueis = agCont.findAll();
            StringBuffer buffer = new StringBuffer();

            for(Aluguel ag: alugueis){
                buffer.append(ag.toString() + "\n");
            }
            tvFindAllAg.setText(buffer.toString());
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void preencheSpinners(){
        Aluno al0 = new Aluno();
        al0.setRa(0);
        al0.setNome("Selecione um aluno");
        al0.setEmail("");

        Exemplar e0 = new Revista();
        e0.setCodigo(0);
        e0.setNome("Selecione um exemplar");
        e0.setQtdPaginas(0);

        try {
            alunos = alCont.findAll();
            alunos.add(0, al0);

            exemplares = new ArrayList<>();
            exemplares.addAll(lCont.findAll());
            exemplares.addAll(rCont.findAll());
            exemplares.add(0, e0);


            ArrayAdapter adAl = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, alunos);
            adAl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spAlunoAg.setAdapter(adAl);

            ArrayAdapter adEx = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, exemplares);
            adEx.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spExemplarAg.setAdapter(adEx);

        } catch (SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private Aluguel montaAluguel(){
        Aluguel ag = new Aluguel();
        ag.setAluno((Aluno) spAlunoAg.getSelectedItem());
        ag.setExemplar((Exemplar) spExemplarAg.getSelectedItem());
        ag.setDataRetirada(etDataRetiradaAg.getText().toString());
        ag.setDataDevolucao(etDataDevolucaoAg.getText().toString());

        return ag;
    }

    private void limpaCampos(){
        spAlunoAg.setSelection(0);
        spExemplarAg.setSelection(0);
        etDataRetiradaAg.setText("");
        etDataDevolucaoAg.setText("");
    }

    private void preencheCampos(Aluguel ag){
        int cont = 1;
        for(Aluno al: alunos){
            if(al.getRa() == ag.getAluno().getRa()){
                spAlunoAg.setSelection(cont);
            } else{
                cont++;
            }
        }
        if(cont > alunos.size()){
            spAlunoAg.setSelection(0);
        }

        cont = 1;
        for(Exemplar e: exemplares){
            if(e.getCodigo() == ag.getExemplar().getCodigo()){
                spExemplarAg.setSelection(cont);
            } else{
                cont++;
            }
        }
        if(cont > exemplares.size()){
            spExemplarAg.setSelection(0);
        }

        etDataRetiradaAg.setText(ag.getDataRetirada());
        etDataDevolucaoAg.setText(ag.getDataDevolucao());
    }
}