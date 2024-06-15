package br.edu.fateczl.dogwalkerprojetc.controller;

import java.sql.SQLException;
import java.util.List;

public interface IControllerFindAll<T> {
    public List<T> findAll() throws SQLException;
}
