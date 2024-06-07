package br.edu.fateczl.crudbiblioteca.persistence;

import java.sql.SQLException;

public interface IAluguelDao {
    public AluguelDao open()throws SQLException;
    public void close();
}
