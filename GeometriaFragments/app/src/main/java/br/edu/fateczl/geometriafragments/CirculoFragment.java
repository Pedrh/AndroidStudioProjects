package br.edu.fateczl.geometriafragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.edu.fateczl.geometriafragments.controller.IGeometriaController;
import br.edu.fateczl.geometriafragments.controller.OperacaoCirculo;
import br.edu.fateczl.geometriafragments.model.Circulo;

public class CirculoFragment extends Fragment {
    private View view;
    private EditText etRaioCir;
    private Button btnAreaCir;
    private Button btnPerimetroCir;
    private TextView tvFinalCir;

    public CirculoFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_circulo, container, false);

        etRaioCir = view.findViewById(R.id.etRaioCir);
        btnAreaCir = view.findViewById(R.id.btnAreaCir);
        btnPerimetroCir = view.findViewById(R.id.btnPerimetroCir);
        tvFinalCir = view.findViewById(R.id.tvFinalCir);
        tvFinalCir.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        btnAreaCir.setOnClickListener(op -> calcArea());
        btnPerimetroCir.setOnClickListener(op -> calcPerimetro());

        return view;
    }

    private void calcArea() {
        Circulo c = new Circulo();
        c.setRaio(Float.parseFloat(etRaioCir.getText().toString()));
        IGeometriaController<Circulo> op = new OperacaoCirculo();
        float resultado = op.calcularArea(c);

        String msg = "Area = " + resultado + "cm";
        tvFinalCir.setText(msg);
        limparCampos();
    }


    private void calcPerimetro() {
        Circulo c = new Circulo();
        c.setRaio(Float.parseFloat(etRaioCir.getText().toString()));
        IGeometriaController<Circulo> op = new OperacaoCirculo();
        float resultado = op.calcularPerimetro(c);

        String msg = "Perimetro = " + resultado + "cm";
        tvFinalCir.setText(msg);
        limparCampos();
    }

    private void limparCampos() {
        etRaioCir.setText("");
    }
}