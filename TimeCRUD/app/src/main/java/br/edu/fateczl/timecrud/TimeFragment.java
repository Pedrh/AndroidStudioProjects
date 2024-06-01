package br.edu.fateczl.timecrud;

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

import br.edu.fateczl.timecrud.controller.TimeController;
import br.edu.fateczl.timecrud.model.Time;
import br.edu.fateczl.timecrud.persistence.TimeDao;

public class TimeFragment extends Fragment {

    private View view;
    private EditText etCodigoTime, etNomeTime, etCidadeTime;
    private Button btnInsertTime, btnUpdateTime, btnDeleteTime, btnFindOneTime, btnFindAllTime;
    private TextView tvFindAllTime;
    private TimeController tCont;

    public TimeFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_time, container, false);

        etCodigoTime = view.findViewById(R.id.etCodigoTime);
        etNomeTime = view.findViewById(R.id.etNomeTime);
        etCidadeTime = view.findViewById(R.id.etCidadeTime);
        btnInsertTime = view.findViewById(R.id.btnInsertTime);
        btnUpdateTime = view.findViewById(R.id.btnUpdateTime);
        btnDeleteTime = view.findViewById(R.id.btnDeleteTime);
        btnFindOneTime = view.findViewById(R.id.btnFindOneTime);
        btnFindAllTime = view.findViewById(R.id.btnFindAllTime);
        tvFindAllTime = view.findViewById(R.id.tvFindAllTime);
        tvFindAllTime.setMovementMethod(new ScrollingMovementMethod());
        tCont = new TimeController(new TimeDao(view.getContext()));

        btnInsertTime.setOnClickListener(op -> acaoInsert());
        btnUpdateTime.setOnClickListener(op -> acaoUpdate());
        btnDeleteTime.setOnClickListener(op -> acaoDelete());
        btnFindOneTime.setOnClickListener(op -> acaoFindOne());
        btnFindAllTime.setOnClickListener(op -> acaoFindAll());


        return view;
    }

    private void acaoInsert() {
        Time time = montaTime();
        try {
            tCont.insert(time);
            Toast.makeText(view.getContext(), "Time inserido com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoUpdate() {
        Time time = montaTime();
        try {
            tCont.update(time);
            Toast.makeText(view.getContext(), "Time atualizado com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoDelete() {
        Time time = montaTime();
        try {
            tCont.delete(time);
            Toast.makeText(view.getContext(), "Time deletado com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoFindOne() {
        Time time = montaTime();
        try {
            time = tCont.findOne(time);
            if(time.getNome() != null){
                preencheCampos(time);
            }else {
                Toast.makeText(view.getContext(), "Time não encontrado", Toast.LENGTH_LONG).show();
                limpaCampos();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void acaoFindAll() {
        try{
            List<Time> times = tCont.findAll();
            StringBuffer buffer = new StringBuffer();
            for(Time t: times){
                buffer.append(t.toString() + "\n");
            }
            tvFindAllTime.setText(buffer.toString());
        } catch(SQLException e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    private Time montaTime(){
        Time t = new Time();
        t.setCodigo(Integer.parseInt(etCodigoTime.getText().toString()));
        t.setNome(etNomeTime.getText().toString());
        t.setCidade(etCidadeTime.getText().toString());

        return t;
    }

    private void preencheCampos(Time t){
        etCodigoTime.setText(String.valueOf(t.getCodigo()));
        etNomeTime.setText(t.getNome());
        etCidadeTime.setText(t.getCidade());
    }

    private void limpaCampos(){
        etCodigoTime.setText("");
        etNomeTime.setText("");
        etCidadeTime.setText("");
    }
}