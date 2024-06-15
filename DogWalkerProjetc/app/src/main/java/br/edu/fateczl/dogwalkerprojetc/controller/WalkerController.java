package br.edu.fateczl.dogwalkerprojetc.controller;

import java.sql.SQLException;
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
    public Walker findOne(Walker walker) throws SQLException, NullPointerException {
        if(wDao.open() == null){
            wDao.open();
        }
        walker = wDao.findOne(walker);
        if(walker.getNome() == null){
            throw  new NullPointerException("Walker não encontrado");
        }
        return walker;
    }

    @Override
    public List<Walker> findAll() throws SQLException {
        if(wDao.open() == null){
            wDao.open();
        }
        return wDao.findAll();
    }
}
