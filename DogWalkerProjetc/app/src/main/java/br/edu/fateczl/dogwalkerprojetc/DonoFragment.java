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

public class DonoFragment extends Fragment {

    private View view;
    private EditText etNomeDono, etCEPDono, etFoneDono, etEmailDono;
    private Button btnInsertDono, btnUpdateDono, btnDeleteDono, btnFindOneDono;
    private TextView tvFindOneDono;

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
        btnDeleteDono = view.findViewById(R.id.btnDeleteDono);
        btnFindOneDono = view.findViewById(R.id.btnFindOneDono);
        tvFindOneDono = view.findViewById(R.id.tvFindOneDono);
        tvFindOneDono.setMovementMethod(new ScrollingMovementMethod());

        return view;
    }
}