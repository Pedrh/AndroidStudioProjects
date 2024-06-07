package br.edu.fateczl.crudbiblioteca.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.crudbiblioteca.model.Livro;
import br.edu.fateczl.crudbiblioteca.persistence.LivroDao;

public class LivroController implements IController<Livro>{

    private final LivroDao lDao;

    public LivroController(LivroDao lDao) {
        this.lDao = lDao;
    }

    @Override
    public void insert(Livro livro) throws SQLException {
        if(lDao.open() == null){
            lDao.open();
        }
        lDao.insert(livro);
        lDao.close();
    }

    @Override
    public void update(Livro livro) throws SQLException {
        if(lDao.open() == null){
            lDao.open();
        }
        lDao.update(livro);
        lDao.close();
    }

    @Override
    public void delete(Livro livro) throws SQLException {
        if(lDao.open() == null){
            lDao.open();
        }
        lDao.delete(livro);
        lDao.close();
    }

    @Override
    public Livro findOne(Livro livro) throws SQLException {
        if(lDao.open() == null){
            lDao.open();
        }
        return lDao.findOne(livro);
    }

    @Override
    public List<Livro> findAll() throws SQLException {
        if(lDao.open() == null){
            lDao.open();
        }
        return lDao.findAll();
    }
}
