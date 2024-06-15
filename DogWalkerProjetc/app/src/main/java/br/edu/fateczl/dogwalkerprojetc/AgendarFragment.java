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

import br.edu.fateczl.dogwalkerprojetc.controller.AgendarController;
import br.edu.fateczl.dogwalkerprojetc.controller.DonoController;
import br.edu.fateczl.dogwalkerprojetc.controller.PagamentoAgendarController;
import br.edu.fateczl.dogwalkerprojetc.controller.WalkerController;
import br.edu.fateczl.dogwalkerprojetc.model.Agendar;
import br.edu.fateczl.dogwalkerprojetc.model.Dono;
import br.edu.fateczl.dogwalkerprojetc.model.Walker;
import br.edu.fateczl.dogwalkerprojetc.persistence.AgendarDao;
import br.edu.fateczl.dogwalkerprojetc.persistence.DonoDao;
import br.edu.fateczl.dogwalkerprojetc.persistence.WalkerDao;

public class AgendarFragment extends Fragment {

    private View view;
    private EditText etLocalAg, etHoraAg, etDataAg;
    private RadioButton rbUmPetAg,rbDoisPetsAg, rbTresPetsAg, rbTrintaMinAg, rbUmaHora, rbDuasHoras, rbDinheiroAg, rbCartaoAg, rbPixAg;
    private Button btnCalcValorAg, btnConfirmaAg, btnCancelarAg, btnFindOneAg;
    private TextView tvSaidaAg;
    AgendarController aCont;
    DonoController dCont;
    WalkerController wCont;
    PagamentoAgendarController pc;

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
        btnCalcValorAg = view.findViewById(R.id.btnCalcValorAg);
        btnConfirmaAg = view.findViewById(R.id.btnConfirmaAg);
        btnCancelarAg = view.findViewById(R.id.btnCancelarAg);
        btnFindOneAg = view.findViewById(R.id.btnFindOneAg);
        tvSaidaAg = view.findViewById(R.id.tvSaidaAg);
        tvSaidaAg.setMovementMethod(new ScrollingMovementMethod());

        aCont = new AgendarController(new AgendarDao(view.getContext()));
        dCont = new DonoController(new DonoDao(view.getContext()));
        wCont = new WalkerController(new WalkerDao(view.getContext()));

        btnCalcValorAg.setOnClickListener(op -> acaoCalc());
        btnConfirmaAg.setOnClickListener(op -> acaoInsert());
        btnFindOneAg.setOnClickListener(op -> acaoFindOne());
        btnCancelarAg.setOnClickListener(op -> acaoDelete());
        pc = new PagamentoAgendarController();

        return view;
    }


    private void acaoCalc() {
        //So permite a operação de um pet caso o dono esteja logado
        Dono dono = encontraDono();
        if(dono.getNome() == null){
            Toast.makeText(view.getContext(), "Necessário cadastrar dono antes", Toast.LENGTH_LONG).show();
            limpaCampos();
        } else {
            boolean todosPreenchidos = camposPreenchdios();
            if (todosPreenchidos) {
                Agendar ag = montaAgenda(dono);
                float valorFinal = pc.calcularValorFinal(ag);
                String msg = "Valor final do passeio: R$" + valorFinal;
                tvSaidaAg.setText(msg);
            } else {
                Toast.makeText(view.getContext(), "Necessário preencher todos os campos", Toast.LENGTH_LONG).show();
                limpaCampos();
            }
        }
    }

    private void acaoInsert() {
        try{
            //So permite a operação de um pet caso o dono esteja logado
            Dono dono = encontraDono();
            if(dono.getNome() == null){
                Toast.makeText(view.getContext(), "Necessário cadastrar dono antes", Toast.LENGTH_LONG).show();
                limpaCampos();
            } else{
                Agendar ag = montaAgenda(dono);
                aCont.insert(ag);
                Toast.makeText(view.getContext(), ag.toString(), Toast.LENGTH_LONG).show();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoFindOne(){
        try{
            //So permite a operação de um pet caso o dono esteja logado
            Dono dono = encontraDono();
            if(dono.getNome() == null){
                Toast.makeText(view.getContext(), "Necessário cadastrar dono antes", Toast.LENGTH_LONG).show();
                limpaCampos();
            } else {
                Agendar ag = montaAgenda(dono);
                ag = aCont.findOne(ag);
                if (ag.getLocalEncontro() == null) {
                    Toast.makeText(view.getContext(), "Nenhum passeio encontrado", Toast.LENGTH_LONG).show();
                    limpaCampos();
                } else {
                    preencheCampos(ag);
                    String msg = "Walker: " + ag.getWalker().toString();
                    tvSaidaAg.setText(msg);
                }
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void acaoDelete() {
        try{
            //So permite a operação de um pet caso o dono esteja logado
            Dono dono = encontraDono();
            if(dono.getNome() == null){
                Toast.makeText(view.getContext(), "Necessário cadastrar dono antes", Toast.LENGTH_LONG).show();
                limpaCampos();
            } else{
                Agendar ag = montaAgenda(dono);
                aCont.delete(ag);
                Toast.makeText(view.getContext(), "Passeio cancelado", Toast.LENGTH_LONG).show();
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private Agendar montaAgenda( Dono dono){
        Agendar ag = new Agendar();
        ag.setDataEncontro(etDataAg.getText().toString());
        ag.setHoraEncontro(etHoraAg.getText().toString());
        ag.setLocalEncontro(etLocalAg.getText().toString());
        ag.setTmpPasseio(calculaTempoPasseio());
        ag.setQtdPasseio(calculaQtdPets());
        ag.setFormaPagto(defineFormaPagto());
        ag.setDono(dono);

        try{
            int walkerCodigo = (int) ((Math.random() * 2) + 2.1);
            Walker walker = new Walker();
            walker.setCodigo(walkerCodigo);
            walker = wCont.findOne(walker);
            ag.setWalker(walker);

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return ag;
    }

    private void limpaCampos(){
        etDataAg.setText("");
        etLocalAg.setText("");
        etHoraAg.setText("");
        rbTrintaMinAg.setChecked(true);
        rbUmPetAg.setChecked(true);
        rbDinheiroAg.setChecked(true);
    }

    private void preencheCampos(Agendar ag) {
        etDataAg.setText(ag.getDataEncontro());
        etLocalAg.setText(ag.getLocalEncontro());
        etHoraAg.setText(ag.getHoraEncontro());
        preencheQtdPets(ag);
        preencheTempoPasseio(ag);
        preencheFormaPagto(ag);
    }



    private int calculaTempoPasseio() {
        int tempo = 0;
        if(rbTrintaMinAg.isChecked()){
            tempo = 30;
        }
        if (rbUmaHora.isChecked()){
            tempo = 1;
        }
        if (rbDuasHoras.isChecked()){
            tempo = 2;
        }
        return tempo;
    }

    private void preencheTempoPasseio(Agendar ag){
        int qtd = ag.getTmpPasseio();
        if(qtd == 30){
            rbTrintaMinAg.setChecked(true);
        }
        if (qtd == 1){
            rbUmaHora.setChecked(true);
        }
        if (qtd == 2){
            rbDuasHoras.setChecked(true);
        }
    }

    private int calculaQtdPets() {
        int qtd = 0;
        if(rbUmPetAg.isChecked()){
            qtd = 1;
        }
        if (rbDoisPetsAg.isChecked()){
            qtd = 2;
        }
        if (rbTresPetsAg.isChecked()){
            qtd = 3;
        }

        return qtd;
    }

    private void preencheQtdPets( Agendar ag){
        int qtd = ag.getQtdPasseio();
        if(qtd == 1){
            rbUmPetAg.setChecked(true);
        }
        if (qtd == 2){
            rbDoisPetsAg.setChecked(true);
        }
        if (qtd == 3){
            rbTresPetsAg.setChecked(true);
        }
    }

    private String defineFormaPagto(){
        String forma = "";
        if(rbPixAg.isChecked()){
            forma = "pix";
        }
        if (rbCartaoAg.isChecked()){
            forma = "cartao";
        }
        if (rbDinheiroAg.isChecked()){
            forma = "dinheiro";
        }

        return forma;
    }

    private void preencheFormaPagto(Agendar ag){
        String forma = ag.getFormaPagto();
        if(forma.equals("pix")){
            rbPixAg.setChecked(true);
        }
        if (forma.equals("cartao")){
            rbCartaoAg.setChecked(true);
        }
        if (forma.equals("dinheiro")){
            rbDinheiroAg.setChecked(true);
        }
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

    private boolean camposPreenchdios(){
        boolean todosPreenchidos = false;
        if(!etLocalAg.getText().toString().isEmpty()){
            if(!etDataAg.getText().toString().isEmpty()){
                if(!etHoraAg.getText().toString().isEmpty()){
                    todosPreenchidos = true;
                }
            }
        }
        return todosPreenchidos;
    }




}