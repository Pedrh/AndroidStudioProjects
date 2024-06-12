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
import android.widget.Spinner;
import android.widget.TextView;

public class PetFragment extends Fragment {

    private View view;
    private EditText etIdPet, etNomePet, etRacaPet;
    private RadioButton rbFilhotePet, rbAdultoPet, rbSeniorPet, rbPequenoPet, rbMedioPet, rbGrandePet;
    private Spinner spDonopet;
    private Button btnInsertPet, btnUpdatePet, btnDeletePet, btnFindOnePet, btnFindAllPet;
    private TextView tvFindAllPet;

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
        rbAdultoPet = view.findViewById(R.id.rbAdultoPet);
        rbSeniorPet = view.findViewById(R.id.rbSeniorPet);
        rbPequenoPet = view.findViewById(R.id.rbPequenoPet);
        rbMedioPet = view.findViewById(R.id.rbMedioPet);
        rbGrandePet = view.findViewById(R.id.rbGrandePet);
        btnInsertPet = view.findViewById(R.id.btnInsertPet);
        btnUpdatePet = view.findViewById(R.id.btnUpdatePet);
        btnDeletePet = view.findViewById(R.id.btnDeletePet);
        btnFindOnePet = view.findViewById(R.id.btnFindOnePet);
        btnFindAllPet = view.findViewById(R.id.btnFindAllPet);
        tvFindAllPet = view.findViewById(R.id.tvFindAllPet);
        tvFindAllPet.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }
}