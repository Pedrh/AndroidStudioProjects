package br.edu.fateczl.dogwalkerprojetc.persistence;

import java.sql.SQLException;

public interface IAgendarDao {
    public AgendarDao open() throws SQLException;
    public void close();
}
