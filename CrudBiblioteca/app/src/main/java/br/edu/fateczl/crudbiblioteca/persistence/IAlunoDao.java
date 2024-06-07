package br.edu.fateczl.crudbiblioteca.persistence;

import java.sql.SQLException;

public interface IAlunoDao {

    public AlunoDao open() throws SQLException;
    public void close();
}
