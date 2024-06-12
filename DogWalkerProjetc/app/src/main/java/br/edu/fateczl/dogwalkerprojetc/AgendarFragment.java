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

public class AgendarFragment extends Fragment {

    private View view;
    private EditText etLocalAg, etHoraAg, etDataAg, etNomePet1Ag, etNomePet2Ag, etNomePet3Ag;
    private RadioButton rbUmPetAg,rbDoisPetsAg, rbTresPetsAg, rbTrintaMinAg, rbUmaHora, rbDuasHoras, rbDinheiroAg, rbCartaoAg, rbPixAg;
    private Button btnCalcValorAg, btnConfirmaAg;
    private TextView tvValorAg;

    public AgendarFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_agendar, container, false);

        etLocalAg = view.findViewById(R.id.etLocalAg);
        etHoraAg = view.findViewById(R.id.etHoraAg);
        etDataAg = view.findViewById(R.id.etDataAg);
        rbUmPetAg = view.findViewById(R.id.rbUmpetAg);
        rbDoisPetsAg = view.findViewById(R.id.rbDoisPetsAg);
        rbTresPetsAg = view.findViewById(R.id.rbTresPetsAg);
        rbTrintaMinAg = view.findViewById(R.id.rbTrintaMinAg);
        rbUmaHora = view.findViewById(R.id.rbUmaHoraAg);
        rbDuasHoras = view.findViewById(R.id.rbDuasHorasAg);
        rbDinheiroAg = view.findViewById(R.id.rbDinheiroAg);
        rbCartaoAg = view.findViewById(R.id.rbCartaoAg);
        rbPixAg = view.findViewById(R.id.rbPixAg);
        etNomePet1Ag = view.findViewById(R.id.etNomePet1Ag);
        etNomePet2Ag = view.findViewById(R.id.etNomePet2Ag);
        etNomePet3Ag = view.findViewById(R.id.etNomePet3Ag);
        btnCalcValorAg = view.findViewById(R.id.btnCalcValorAg);
        btnConfirmaAg = view.findViewById(R.id.btnConfirmaAg);
        tvValorAg = view.findViewById(R.id.tvValorAg);
        tvValorAg.setMovementMethod(new ScrollingMovementMethod());

        return view;
    }
}