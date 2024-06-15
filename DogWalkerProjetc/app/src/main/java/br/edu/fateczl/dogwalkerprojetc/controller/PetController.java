package br.edu.fateczl.dogwalkerprojetc.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.dogwalkerprojetc.model.Pet;
import br.edu.fateczl.dogwalkerprojetc.persistence.PetDao;

public class PetController implements  IController<Pet>, IControllerFindAll<Pet> {

    private final PetDao pDao;

    public PetController(PetDao pDao) {
        this.pDao = pDao;
    }

    @Override
    public void insert(Pet pet) throws SQLException {
        if(pDao.open() == null){
            pDao.open();
        }
        pDao.insert(pet);
        pDao.close();
    }

    @Override
    public void update(Pet pet) throws SQLException {
        if(pDao.open() == null){
            pDao.open();
        }
        pDao.update(pet);
        pDao.close();
    }

    @Override
    public void delete(Pet pet) throws SQLException {
        if(pDao.open() == null){
            pDao.open();
        }
        pDao.delete(pet);
        pDao.close();
    }

    @Override
    public Pet findOne(Pet pet) throws SQLException {
        if(pDao.open() == null){
            pDao.open();
        }
        pet = pDao.findOne(pet);
        if(pet.getNome() == null){
            throw  new NullPointerException("Pet não encontrado");
        }
        return pet;
    }

    @Override
    public List<Pet> findAll() throws SQLException {
        if(pDao.open() == null){
            pDao.open();
        }
        return pDao.findAll();
    }
}
