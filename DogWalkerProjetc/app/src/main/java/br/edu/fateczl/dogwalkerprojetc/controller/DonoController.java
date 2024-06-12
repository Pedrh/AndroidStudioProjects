package br.edu.fateczl.dogwalkerprojetc.controller;

import java.sql.SQLException;

import br.edu.fateczl.dogwalkerprojetc.model.Dono;
import br.edu.fateczl.dogwalkerprojetc.persistence.DonoDao;

public class DonoController implements IController<Dono> {
    private final DonoDao dDao;

    public DonoController(DonoDao dDao) {
        this.dDao = dDao;
    }

    @Override
    public void insert(Dono dono) throws SQLException {
        if(dDao.open() == null){
            dDao.open();
        }
        dDao.insert(dono);
        dDao.close();
    }

    @Override
    public void update(Dono dono) throws SQLException {
        if(dDao.open() == null){
            dDao.open();
        }
        dDao.update(dono);
        dDao.close();
    }

    @Override
    public void delete(Dono dono) throws SQLException {
        if(dDao.open() == null){
            dDao.open();
        }
        dDao.insert(dono);
        dDao.close();
    }

    @Override
    public Dono findOne(Dono dono) throws SQLException {
        if(dDao.open() == null){
            dDao.open();
        }
        return dDao.findOne(dono);
    }
}
