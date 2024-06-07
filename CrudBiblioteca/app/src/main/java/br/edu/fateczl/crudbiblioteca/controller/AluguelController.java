package br.edu.fateczl.crudbiblioteca.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.crudbiblioteca.model.Aluguel;
import br.edu.fateczl.crudbiblioteca.persistence.AluguelDao;

public class AluguelController implements IController<Aluguel> {

    private final AluguelDao agDao;

    public AluguelController(AluguelDao agDao) {
        this.agDao = agDao;
    }

    @Override
    public void insert(Aluguel aluguel) throws SQLException {
        if(agDao.open() == null){
            agDao.open();
        }
        agDao.insert(aluguel);
        agDao.close();
    }

    @Override
    public void update(Aluguel aluguel) throws SQLException {
        if(agDao.open() == null){
            agDao.open();
        }
        agDao.update(aluguel);
        agDao.close();
    }

    @Override
    public void delete(Aluguel aluguel) throws SQLException {
        if(agDao.open() == null){
            agDao.open();
        }
        agDao.delete(aluguel);
        agDao.close();
    }

    @Override
    public Aluguel findOne(Aluguel aluguel) throws SQLException {
        if(agDao.open() == null){
            agDao.open();
        }
        return agDao.findOne(aluguel);
    }

    @Override
    public List<Aluguel> findAll() throws SQLException {
        if(agDao.open() == null){
            agDao.open();
        }
        return agDao.findAll();
    }
}
