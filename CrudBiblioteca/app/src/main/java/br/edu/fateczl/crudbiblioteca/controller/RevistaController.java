package br.edu.fateczl.crudbiblioteca.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.crudbiblioteca.model.Revista;
import br.edu.fateczl.crudbiblioteca.persistence.RevistaDao;

public class RevistaController implements IController<Revista>{

    private final RevistaDao rDao;

    public RevistaController(RevistaDao rDao) {
        this.rDao = rDao;
    }

    @Override
    public void insert(Revista revista) throws SQLException {
        if(rDao.open() == null){
            rDao.open();
        }
        rDao.insert(revista);
        rDao.close();
    }

    @Override
    public void update(Revista revista) throws SQLException {
        if(rDao.open() == null){
            rDao.open();
        }
        rDao.update(revista);
        rDao.close();
    }

    @Override
    public void delete(Revista revista) throws SQLException {
        if(rDao.open() == null){
            rDao.open();
        }
        rDao.delete(revista);
        rDao.close();
    }

    @Override
    public Revista findOne(Revista revista) throws SQLException {
        if(rDao.open() == null){
            rDao.open();
        }
        return rDao.findOne(revista);
    }

    @Override
    public List<Revista> findAll() throws SQLException {
        if(rDao.open() == null){
            rDao.open();
        }
        return rDao.findAll();
    }
}
