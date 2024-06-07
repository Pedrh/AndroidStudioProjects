package br.edu.fateczl.crudbiblioteca.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.crudbiblioteca.model.Aluno;
import br.edu.fateczl.crudbiblioteca.persistence.AlunoDao;

public class AlunoController implements IController<Aluno> {

    private final AlunoDao aDao;

    public AlunoController(AlunoDao aDao) {
        this.aDao = aDao;
    }

    @Override
    public void insert(Aluno aluno) throws SQLException {
        if(aDao.open() == null){
            aDao.open();
        }
        aDao.insert(aluno);
        aDao.close();
    }

    @Override
    public void update(Aluno aluno) throws SQLException {
        if(aDao.open() == null){
            aDao.open();
        }
        aDao.update(aluno);
        aDao.close();
    }

    @Override
    public void delete(Aluno aluno) throws SQLException {
        if(aDao.open() == null){
            aDao.open();
        }
        aDao.delete(aluno);
        aDao.close();
    }

    @Override
    public Aluno findOne(Aluno aluno) throws SQLException {
        if(aDao.open() == null){
            aDao.open();
        }
        return aDao.findOne(aluno);
    }

    @Override
    public List<Aluno> findAll() throws SQLException {
        if(aDao.open() == null){
            aDao.open();
        }
        return aDao.findAll();
    }
}
