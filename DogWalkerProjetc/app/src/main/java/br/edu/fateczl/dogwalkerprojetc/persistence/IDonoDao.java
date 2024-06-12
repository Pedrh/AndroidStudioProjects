package br.edu.fateczl.dogwalkerprojetc.persistence;

import java.sql.SQLException;

public interface IDonoDao {
    public DonoDao open() throws SQLException;
    public void close();
}
