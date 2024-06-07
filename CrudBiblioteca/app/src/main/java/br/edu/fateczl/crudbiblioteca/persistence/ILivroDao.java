package br.edu.fateczl.crudbiblioteca.persistence;

import java.sql.SQLException;

public interface ILivroDao {
    public LivroDao open() throws SQLException;
    public void close();
}
