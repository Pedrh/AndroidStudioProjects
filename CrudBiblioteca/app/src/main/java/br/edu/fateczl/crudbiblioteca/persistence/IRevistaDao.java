package br.edu.fateczl.crudbiblioteca.persistence;

import java.sql.SQLException;

public interface IRevistaDao {
    public RevistaDao open()throws SQLException;
    public void close();
}
