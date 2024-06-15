package br.edu.fateczl.dogwalkerprojetc;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.dogwalkerprojetc.controller.DonoController;
import br.edu.fateczl.dogwalkerprojetc.controller.PetController;
import br.edu.fateczl.dogwalkerprojetc.model.Agendar;
import br.edu.fateczl.dogwalkerprojetc.model.Dono;
import br.edu.fateczl.dogwalkerprojetc.model.Pet;
import br.edu.fateczl.dogwalkerprojetc.persistence.DonoDao;
import br.edu.fateczl.dogwalkerprojetc.persistence.PetDao;

public class PetFragment extends Fragment {

    private View view;
    private EditText etIdPet, etNomePet, etRacaPet;
    private RadioButton rbFilhotePet, rbAdultoPet, rbSeniorPet, rbPequenoPet, rbMedioPet, rbGrandePet;
    private Button btnInsertPet, btnUpdatePet, btnDeletePet, btnFindOnePet, btnFindAllPet;
    private TextView tvShowAllPet;

    private PetController pCont;
    private DonoController dCont;

    public PetFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pet, container, false);

        etIdPet =view.findViewById(R.id.etIdPet);
        etNomePet =view.findViewById(R.id.etNomePet);
        etRacaPet =view.findViewById(R.id.etRacaPet);
        rbFilhotePet = view.findViewById(R.id.rbFilhotePet);
        rbFilhotePet.setChecked(true);
        rbAdultoPet = view.findViewById(R.id.rbAdultoPet);
        rbSeniorPet = view.findViewById(R.id.rbSeniorPet);
        rbPequenoPet = view.findViewById(R.id.rbPequenoPet);
        rbPequenoPet.setChecked(true);
        rbMedioPet = view.findViewById(R.id.rbMedioPet);
        rbGrandePet = view.findViewById(R.id.rbGrandePet);
        btnInsertPet = view.findViewById(R.id.btnInsertPet);
        btnUpdatePet = view.findViewById(R.id.btnUpdatePet);
        btnDeletePet = view.findViewById(R.id.btnDeletePet);
        btnFindOnePet = view.findViewById(R.id.btnFindOnePet);
        btnFindAllPet = view.findViewById(R.id.btnFindAllPet);
        tvShowAllPet = view.findViewById(R.id.tvShowAllPet);
        tvShowAllPet.setMovementMethod(new ScrollingMovementMethod());

        pCont = new PetController(new PetDao(view.getContext()));
        dCont = new DonoController(new DonoDao(view.getContext()));

        btnInsertPet.setOnClickListener(op -> acaoInsert());
        btnUpdatePet.setOnClickListener(op -> acaoUpdate());
        btnDeletePet.setOnClickListener(op -> acaoDelete());
        btnFindOnePet.setOnClickListener(op -> acaoFindOne());
        btnFindAllPet.setOnClickListener(op -> acaoFindAll());
        return view;
    }

    private void acaoInsert() {
        try{
            //So permite a operação de um pet caso o dono esteja logado
            Dono dono = encontraDono();
            if(dono.getNome() == null){
                Toast.makeText(view.getContext(), "Necessário cadastrar dono antes", Toast.LENGTH_LONG).show();
                limpaCampos();
            } else{
                boolean todosPreenchidos = camposPreenchidos();
                if (todosPreenchidos) {
                    Pet pet = montaPet(dono);
                    pCont.insert(pet);
                    Toast.makeText(view.getContext(), "Pet inserido com sucesso", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(view.getContext(), "Necessário preencher todos os campos", Toast.LENGTH_LONG).show();
                }
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoUpdate() {
        try{
            //So permite a operação de um pet caso o dono esteja logado
            Dono dono = encontraDono();
            if(dono.getNome() == null){
                Toast.makeText(view.getContext(), "Necessário cadastrar dono antes", Toast.LENGTH_LONG).show();
                limpaCampos();
            } else{
                boolean todosPreenchidos = camposPreenchidos();
                if (todosPreenchidos) {
                    Pet pet = montaPet(dono);
                    Pet petExiste = pCont.findOne(pet);
                    if (petExiste.getNome() == null) {
                        Toast.makeText(view.getContext(), "Pet não encontrado", Toast.LENGTH_LONG).show();
                        limpaCampos();
                    } else {
                        pCont.update(pet);
                        Toast.makeText(view.getContext(), "Pet atualizado com sucesso", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(view.getContext(), "Necessário preencher todos os campos", Toast.LENGTH_LONG).show();
                }
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoDelete() {
        try{
            //So permite a operação de um pet caso o dono esteja logado
            Dono dono = encontraDono();
            if(dono.getNome() == null){
                Toast.makeText(view.getContext(), "Necessário cadastrar dono antes", Toast.LENGTH_LONG).show();
                limpaCampos();
            } else{

                boolean todosPreenchidos = camposPreenchidos();
                if (todosPreenchidos) {
                    Pet pet = montaPet(dono);
                    pet = pCont.findOne(pet);
                    if (pet.getNome() == null) {
                        Toast.makeText(view.getContext(), "Pet não encontrado", Toast.LENGTH_LONG).show();
                        limpaCampos();
                    } else {
                        pCont.delete(pet);
                        Toast.makeText(view.getContext(), "Pet deletado com sucesso", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(view.getContext(), "Necessário preencher todos os campos", Toast.LENGTH_LONG).show();
                }


            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoFindOne() {
        try{
            //So permite a operação de um pet caso o dono esteja logado
            Dono dono = encontraDono();
            if(dono.getNome() == null){
                Toast.makeText(view.getContext(), "Necessário cadastrar dono antes", Toast.LENGTH_LONG).show();
                limpaCampos();
            } else{
                Pet pet = montaPet(dono);
                pet = pCont.findOne(pet);
                if(pet.getNome() == null){
                    Toast.makeText(view.getContext(), "Pet não encontrado", Toast.LENGTH_LONG).show();
                    limpaCampos();
                } else{
                    preencheCampos(pet);
                }

            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void acaoFindAll() {
        try{
            //So permite a operação de um pet caso o dono esteja logado.
            Dono dono = encontraDono();
            if(dono.getNome() == null){
                Toast.makeText(view.getContext(), "Necessário cadastrar dono antes", Toast.LENGTH_LONG).show();
                limpaCampos();
            } else{
                List<Pet> pets = pCont.findAll();
                StringBuffer buffer = new StringBuffer();
                for(Pet p:pets){
                    buffer.append(p.toString() + "\n");
                }
                tvShowAllPet.setText(buffer.toString());
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Pet montaPet(Dono dono){
        Pet p = new Pet();
        if(etIdPet.getText().toString().isEmpty()){
            p.setId(0);
        } else{
            p.setId(Integer.parseInt(etIdPet.getText().toString()));
        }
        p.setNome(etNomePet.getText().toString());
        p.setRaca(etRacaPet.getText().toString());
        p.setPorte(definePorte());
        p.setIdade(defineIdade());
        p.setRaca(etRacaPet.getText().toString());
        p.setDono(dono);

        return p;
    }


    private void preencheCampos(Pet p){
        etIdPet.setText(String.valueOf(p.getId()));
        etNomePet.setText(p.getNome());
        etRacaPet.setText(p.getRaca());
        selecionaPorte(p);
        selecionaIdade(p);
    }

    private void limpaCampos(){
        etNomePet.setText("");
        etIdPet.setText("");
        etRacaPet.setText("");
        rbPequenoPet.setChecked(true);
        rbFilhotePet.setChecked(true);
        tvShowAllPet.setText("");
    }

    private void selecionaPorte(Pet p) {
        String porte = p.getPorte();
        if(porte.equals("pequeno")){
            rbPequenoPet.setChecked(true);
        }
        if(porte.equals("medio")){
            rbMedioPet.setChecked(true);
        }
        if(porte.equals("grande")){
            rbGrandePet.setChecked(true);
        }
    }

    private void selecionaIdade(Pet p) {
        String idade = p.getIdade();
        if(idade.equals("filhote")){
            rbFilhotePet.setChecked(true);
        }
        if(idade.equals("adulto")){
            rbAdultoPet.setChecked(true);
        }
        if(idade.equals("senior")){
            rbSeniorPet.setChecked(true);
        }
    }


    private String definePorte() {
        String porte = "";
        if(rbPequenoPet.isChecked()){
            porte = "pequeno";
        }
        if(rbMedioPet.isChecked()){
            porte = "medio";
        }
        if(rbGrandePet.isChecked()){
            porte = "grande";
        }

        return porte;
    }

    private String defineIdade() {
        String idade = "";
        if(rbFilhotePet.isChecked()){
            idade = "filhote";
        }
        if(rbAdultoPet.isChecked()){
            idade = "adulto";
        }
        if(rbSeniorPet.isChecked()){
            idade = "senior";
        }

        return idade;
    }

    private Dono encontraDono(){
        Dono dono = new Dono();
        try{
            dono.setCodigo(1);
            dono = dCont.findOne(dono);
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return dono;
    }

    private boolean camposPreenchidos(){
        boolean todosPreenchidos = false;
        if(!etIdPet.getText().toString().isEmpty()){
            if(!etNomePet.getText().toString().isEmpty()){
                if(!etRacaPet.getText().toString().isEmpty()){
                    todosPreenchidos = true;
                }
            }
        }

        return todosPreenchidos;
    }
}