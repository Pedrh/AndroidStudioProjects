package br.edu.fateczl.dogwalkerprojetc.persistence;

import java.sql.SQLException;

public interface IWalkerDao {
    public WalkerDao open() throws SQLException;
    public void close();

}
