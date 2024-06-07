package br.edu.fateczl.crudbiblioteca;

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
import java.util.List;

import br.edu.fateczl.crudbiblioteca.controller.AlunoController;
import br.edu.fateczl.crudbiblioteca.model.Aluno;
import br.edu.fateczl.crudbiblioteca.persistence.AlunoDao;

public class AlunoFragment extends Fragment {

    private View view;
    private EditText etRaAl, etnomeAl, etEmailAl;
    private Button btnInsertAl, btnUpdateAl, btnDeleteAl, btnFindOneAl, btnFindAllAl;
    private TextView tvFindAllAl;
    private AlunoController aCont;


    public AlunoFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_aluno, container, false);

        etRaAl = view.findViewById(R.id.etRaAl);
        etnomeAl = view.findViewById(R.id.etNomeAl);
        etEmailAl = view.findViewById(R.id.etEmailAl);
        btnInsertAl = view.findViewById(R.id.btnInsertAl);
        btnUpdateAl = view.findViewById(R.id.btnUpdateAl);
        btnDeleteAl = view.findViewById(R.id.btnDeleteAl);
        btnFindOneAl = view.findViewById(R.id.btnFindOneAl);
        btnFindAllAl = view.findViewById(R.id.btnFindAllAl);
        tvFindAllAl = view.findViewById(R.id.tvFindAllAl);
        tvFindAllAl.setMovementMethod(new ScrollingMovementMethod());

        aCont = new AlunoController(new AlunoDao(view.getContext()));

        btnInsertAl.setOnClickListener(op -> acaoInsert());
        btnUpdateAl.setOnClickListener(op -> acaoUpdate());
        btnDeleteAl.setOnClickListener(op -> acaoDelete());
        btnFindOneAl.setOnClickListener(op -> acaoFindOne());
        btnFindAllAl.setOnClickListener(op -> acaoFindAll());

        return view;
    }

    private void acaoInsert() {
        Aluno aluno = montaAluno();
        try {
            aCont.insert(aluno);
            Toast.makeText(view.getContext(), "Aluno inserido com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoUpdate() {
        Aluno aluno = montaAluno();
        try {
            aCont.update(aluno);
            Toast.makeText(view.getContext(), "Aluno atualizado com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoDelete() {
        Aluno aluno = montaAluno();
        try {
            aCont.delete(aluno);
            Toast.makeText(view.getContext(), "Aluno excluido com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoFindOne() {
        Aluno aluno = montaAluno();
        try {
            aluno = aCont.findOne(aluno);
            if(aluno.getNome() != null){
                preencheCampos(aluno);
            } else{
                Toast.makeText(view.getContext(), "Aluno não encontrado", Toast.LENGTH_LONG).show();
                limpaCampos();
            }

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void acaoFindAll() {
        try {
            List<Aluno> alunos = aCont.findAll();
            StringBuffer buffer = new StringBuffer();
            for(Aluno al: alunos){
                buffer.append(al.toString() + "\n");
            }
            tvFindAllAl.setText(buffer.toString());
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Aluno montaAluno(){
        Aluno al = new Aluno();
        al.setRa(Integer.parseInt(etRaAl.getText().toString()));
        al.setNome(etnomeAl.getText().toString());
        al.setEmail(etEmailAl.getText().toString());

        return al;
    }

    private void preencheCampos(Aluno al){
        etRaAl.setText(String.valueOf(al.getRa()));
        etnomeAl.setText(al.getNome());
        etEmailAl.setText(al.getEmail());
    }

    private void limpaCampos(){
        etRaAl.setText("");
        etnomeAl.setText("");
        etEmailAl.setText("");
    }
}