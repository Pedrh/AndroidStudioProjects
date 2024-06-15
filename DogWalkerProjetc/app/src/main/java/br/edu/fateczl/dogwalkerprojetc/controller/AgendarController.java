package br.edu.fateczl.dogwalkerprojetc.controller;

import java.sql.SQLException;

import br.edu.fateczl.dogwalkerprojetc.model.Agendar;
import br.edu.fateczl.dogwalkerprojetc.persistence.AgendarDao;

public class AgendarController implements IController<Agendar> {

    private AgendarDao aDao;

    public AgendarController(AgendarDao aDao) {
        this.aDao = aDao;
    }

    @Override
    public void insert(Agendar agendar) throws SQLException {
        if(aDao.open() == null){
            aDao.open();
        }
        aDao.insert(agendar);
        aDao.close();
    }

    @Override
    public void update(Agendar agendar) throws SQLException {
        if(aDao.open() == null){
            aDao.open();
        }
        aDao.update(agendar);
        aDao.close();
    }

    @Override
    public void delete(Agendar agendar) throws SQLException {
        if(aDao.open() == null){
            aDao.open();
        }
        aDao.delete(agendar);
        aDao.close();
    }

    @Override
    public Agendar findOne(Agendar agendar) throws SQLException {
        if(aDao.open() == null){
            aDao.open();
        }
        return aDao.findOne(agendar);
    }


}
