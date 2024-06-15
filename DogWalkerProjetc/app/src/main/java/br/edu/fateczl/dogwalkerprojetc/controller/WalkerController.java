package br.edu.fateczl.dogwalkerprojetc.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.dogwalkerprojetc.model.Walker;
import br.edu.fateczl.dogwalkerprojetc.persistence.WalkerDao;

public class WalkerController implements IController<Walker>, IControllerFindAll<Walker> {

    private final WalkerDao wDao;

    public WalkerController(WalkerDao wDao) {
        this.wDao = wDao;
    }

    @Override
    public void insert(Walker walker) throws SQLException {
        if(wDao.open() == null){
            wDao.open();
        }
        wDao.insert(walker);
        wDao.close();
    }

    @Override
    public void update(Walker walker) throws SQLException {
        if(wDao.open() == null){
            wDao.open();
        }
        wDao.update(walker);
        wDao.close();
    }

    @Override
    public void delete(Walker walker) throws SQLException {
        if(wDao.open() == null){
            wDao.open();
        }
        wDao.delete(walker);
        wDao.close();
    }

    @Override
    public Walker findOne(Walker walker) throws SQLException {
        if(wDao.open() == null){
            wDao.open();
        }
        return wDao.findOne(walker);
    }

    @Override
    public List<Walker> findAll() throws SQLException {
        if(wDao.open() == null){
            wDao.open();
        }
        return wDao.findAll();
    }

    public void geraWalkers() throws SQLException {
        //Esse método é construído para substituir as ações da empresa que gerencia o banco de dados do app
        //Na implantação final, a empresa pode adicionar e remover walkers conforme a necessidade
        //Esse método apenas gera dados para popular o banco como forma temporária de inserção

        Walker walker = new Walker();
        walker.setCodigo(2);
        walker = findOne(walker);
        if(walker.getNome() == null){
            List<Walker> walkers = new ArrayList<>();

            Walker w1 = new Walker();
            w1.setCodigo(2);
            w1.setNome("Rosana");
            w1.setAnosExperiencia(3);
            w1.setTelefone("(11)93484-1274");

            Walker w2 = new Walker();
            w2.setCodigo(3);
            w2.setNome("Emerson");
            w2.setAnosExperiencia(1);
            w2.setTelefone("(11)97562-4102");

            Walker w3 = new Walker();
            w3.setCodigo(4);
            w3.setNome("George");
            w3.setAnosExperiencia(10);
            w3.setTelefone("(11)96352-8754");

            Walker w4 = new Walker();
            w4.setCodigo(5);
            w4.setNome("Ana");
            w4.setAnosExperiencia(6);
            w4.setTelefone("(11)97451-2222");

            walkers.add(w1);
            walkers.add(w2);
            walkers.add(w3);
            walkers.add(w4);

            for(Walker w: walkers){
                insert(w);
            }
        }


    }
}
