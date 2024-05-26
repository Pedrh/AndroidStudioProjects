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
import br.edu.fateczl.geometriafragments.controller.OperacaoRetangulo;
import br.edu.fateczl.geometriafragments.model.Retangulo;

public class RetanguloFragment extends Fragment {
    private View view;
    private EditText etBaseRet;
    private EditText etAlturaRet;
    private Button btnAreaRet;
    private Button btnPerimetroRet;
    private TextView tvFinalRet;

    public RetanguloFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_retangulo, container, false);

        etBaseRet = view.findViewById(R.id.etBaseRet);
        etAlturaRet = view.findViewById(R.id.etAlturaRet);
        btnAreaRet = view.findViewById(R.id.btnAreaRet);
        btnPerimetroRet = view.findViewById(R.id.btnPerimetroRet);
        tvFinalRet = view.findViewById(R.id.tvFinalRet);
        tvFinalRet.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


        btnAreaRet.setOnClickListener(op -> calcArea());
        btnPerimetroRet.setOnClickListener(op -> calcPerimetro());

        return view;
    }

    private void calcArea() {
        Retangulo ret = new Retangulo();
        ret.setBase(Float.parseFloat(etBaseRet.getText().toString()));
        ret.setAltura(Float.parseFloat(etAlturaRet.getText().toString()));
        IGeometriaController<Retangulo> op = new OperacaoRetangulo();
        float resultado = op.calcularArea(ret);

        String msg = "Area = " + resultado + "cm";
        tvFinalRet.setText(msg);
        limparCampos();
    }


    private void calcPerimetro() {
        Retangulo ret = new Retangulo();
        ret.setBase(Float.parseFloat(etBaseRet.getText().toString()));
        ret.setAltura(Float.parseFloat(etAlturaRet.getText().toString()));
        IGeometriaController<Retangulo> op = new OperacaoRetangulo();
        float resultado = op.calcularPerimetro(ret);

        String msg = "Perimetro = " + resultado + "cm";
        tvFinalRet.setText(msg);
        limparCampos();
    }

    private void limparCampos() {
        etBaseRet.setText("");
        etAlturaRet.setText("");
    }
}