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

public class DonoDao implements ICRUDDao<Dono>, IDonoDao {
    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;

    public DonoDao(Context context) {
        this.context = context;
    }

    @Override
    public DonoDao open() throws SQLException {
        gDao = new GenericDao(context);
        database = gDao.getWritableDatabase();
        database.execSQL("PRAGMA FOREIGN_KEYS=ON;");
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }
    @Override
    public void insert(Dono dono) throws SQLException {
        ContentValues cvUsuario = getCvUsuario(dono);
        ContentValues cvDono = getCvDono(dono);
        database.insert("Usuario", null, cvUsuario);
        database.insert("Dono", null, cvDono);
    }

    @Override
    public int update(Dono dono) throws SQLException {
        ContentValues cvUsuario = getCvUsuario(dono);
        ContentValues cvDono = getCvDono(dono);
        int ret;
        ret = database.update("Usuario", cvUsuario, "Codigo = " + 1, null);
        ret += database.update("Dono", cvDono, "CodigoUsuario = " + 1, null);
        return ret;
    }

    @Override
    public void delete(Dono dono) throws SQLException {
        ContentValues cvUsuario = getCvUsuario(dono);
        ContentValues cvDono = getCvDono(dono);
        database.delete("Dono", "CodigoUsuario = " + 1, null);
        database.delete("Usuario", "Codigo = " + 1, null);
    }

    @SuppressLint("Range")
    @Override
    public Dono findOne(Dono dono) throws SQLException  {
        String sql = "SELECT u.nome as nome, u.telefone as telefone, d.cep as cep, d.email as email FROM Dono d INNER JOIN Usuario u" +
                " ON u.codigo = d.CodigoUsuario AND d.CodigoUsuario = 1";
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToNext();
        }
        if(!cursor.isAfterLast()){
            dono.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            dono.setCep(cursor.getInt(cursor.getColumnIndex("cep")));
            dono.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            dono.setEmail(cursor.getString(cursor.getColumnIndex("email")));
        }
        cursor.close();
        return dono;
    }

    private ContentValues getCvUsuario(Dono dono){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Nome", dono.getNome());
        contentValues.put("Codigo", 1);
        contentValues.put("Telefone", dono.getTelefone());

        return contentValues;
    }

    private ContentValues getCvDono(Dono dono){
        ContentValues contentValues = new ContentValues();
        contentValues.put("CodigoUsuario", 1);
        contentValues.put("Cep", dono.getCep());
        contentValues.put("Email", dono.getEmail());

        return contentValues;
    }
}
