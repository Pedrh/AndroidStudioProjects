package br.edu.fateczl.dogwalkerprojetc.persistence;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.dogwalkerprojetc.model.Pet;

public interface IFindAllDao<T> {
    public List<T> findAll() throws SQLException;
}
