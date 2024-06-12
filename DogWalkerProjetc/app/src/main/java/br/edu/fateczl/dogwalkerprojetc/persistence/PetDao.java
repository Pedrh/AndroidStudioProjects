package br.edu.fateczl.dogwalkerprojetc.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.dogwalkerprojetc.model.Dono;
import br.edu.fateczl.dogwalkerprojetc.model.Pet;

public class PetDao implements ICRUDDao<Pet>, IPetDao, IFindAllDao {
    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;

    public PetDao(Context context) {
        this.context = context;
    }

    @Override
    public PetDao open() throws SQLException {
        gDao = new GenericDao(context);
        database = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }
    @Override
    public void insert(Pet pet) throws SQLException {
        ContentValues cv = getContentValues(pet);
        database.insert("Pet", null, cv);
    }

    @Override
    public int update(Pet pet) throws SQLException {
        ContentValues cv = getContentValues(pet);
        int ret = database.update("Pet", cv, "id = " + pet.getId(), null);
        return ret;
    }

    @Override
    public void delete(Pet pet) throws SQLException {
        database.delete("Pet", "id = " + pet.getId(), null);
    }

    @SuppressLint("Range")
    @Override
    public Pet findOne(Pet pet) throws SQLException {
        String sql = "SELECT d.CodigoUsuario as CodigoUsuario, d.nome as nomeDono, d.cpf as cpf, d.cep as cep, d.telefone as telefone, d.email as email, " +
                "p.nome as nomePet, p.id as id, p.raca as raca, p.porte as porte, p.idade as idade " +
                "FROM Dono d INNER JOIN Pet p ON p.id = 1 AND WHERE id = " + pet.getId();
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToNext();
        }
        if(!cursor.isAfterLast()){
            Dono dono = new Dono();
            dono.setCodigo(cursor.getInt(cursor.getColumnIndex("CodigoUsuario")));
            dono.setNome(cursor.getString(cursor.getColumnIndex("nomeDono")));
            dono.setCep(cursor.getInt(cursor.getColumnIndex("cep")));
            dono.setTelefone(cursor.getInt(cursor.getColumnIndex("telefone")));
            dono.setEmail(cursor.getString(cursor.getColumnIndex("email")));

            pet.setId(cursor.getInt(cursor.getColumnIndex("id")));
            pet.setNome(cursor.getString(cursor.getColumnIndex("nomePet")));
            pet.setRaca(cursor.getString(cursor.getColumnIndex("raca")));
            pet.setPorte(cursor.getString(cursor.getColumnIndex("porte")));
            pet.setIdade(cursor.getString(cursor.getColumnIndex("idade")));
            pet.setDono(dono);

        }
        cursor.close();
        return pet;
    }

    @SuppressLint("Range")
    @Override
    public List<Pet> findAll() throws SQLException {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT d.CodigoUsuario as CodigoUsuario, d.nome as nomeDono, d.cpf as cpf, d.cep as cep, d.telefone as telefone, d.email as email, " +
                "p.nome as nomePet, p.id as id, p.raca as raca, p.porte as porte, p.idade as idade " +
                "FROM Dono d INNER JOIN Pet p ON d.cpf = p.id";
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToNext();
        }
        if(!cursor.isAfterLast()){
            Dono dono = new Dono();
            dono.setCodigo(cursor.getInt(cursor.getColumnIndex("CodigoUsuario")));
            dono.setNome(cursor.getString(cursor.getColumnIndex("nomeDono")));
            dono.setCpf(cursor.getInt(cursor.getColumnIndex("cpf")));
            dono.setCep(cursor.getInt(cursor.getColumnIndex("cep")));
            dono.setTelefone(cursor.getInt(cursor.getColumnIndex("telefone")));
            dono.setEmail(cursor.getString(cursor.getColumnIndex("email")));

            Pet pet = new Pet();
            pet.setId(cursor.getInt(cursor.getColumnIndex("id")));
            pet.setNome(cursor.getString(cursor.getColumnIndex("nomePet")));
            pet.setRaca(cursor.getString(cursor.getColumnIndex("raca")));
            pet.setPorte(cursor.getString(cursor.getColumnIndex("porte")));
            pet.setIdade(cursor.getString(cursor.getColumnIndex("idade")));
            pet.setDono(dono);

            pets.add(pet);
            cursor.moveToNext();
        }
        cursor.close();
        return pets;
    }

    private ContentValues getContentValues(Pet pet){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", pet.getNome());
        contentValues.put("id", pet.getId());
        contentValues.put("raca", pet.getRaca());
        contentValues.put("idade", pet.getIdade());
        contentValues.put("porte", pet.getNome());
        contentValues.put("nome", pet.getDono().getCpf());

        return contentValues;
    }
}
