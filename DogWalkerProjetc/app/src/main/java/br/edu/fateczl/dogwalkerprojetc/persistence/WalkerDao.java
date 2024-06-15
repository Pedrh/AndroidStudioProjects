package br.edu.fateczl.dogwalkerprojetc.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.dogwalkerprojetc.model.Walker;

public class WalkerDao implements ICRUDDao<Walker>, IWalkerDao, IFindAllDao {
    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;

    public WalkerDao(Context context) {
        this.context = context;
    }

    @Override
    public WalkerDao open() throws SQLException {
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
    public void insert(Walker walker) throws SQLException {
        ContentValues cvUsuario = getCvUsuario(walker);
        ContentValues cvWalker = getCvWalker(walker);
        database.insert("Usuario", null, cvUsuario);
        database.insert("Walker", null, cvWalker);
    }

    @Override
    public int update(Walker walker) throws SQLException {
        ContentValues cvUsuario = getCvUsuario(walker);
        ContentValues cvWalker = getCvWalker(walker);
        int ret;
        ret = database.update("Usuario", cvUsuario, "Codigo = " + walker.getCodigo(), null);
        ret += database.update("Walker", cvWalker, "CodigoUsuario = " + walker.getCodigo(), null);
        return ret;
    }

    @Override
    public void delete(Walker walker) throws SQLException {
        ContentValues cvUsuario = getCvUsuario(walker);
        ContentValues cvWalker = getCvWalker(walker);
        database.delete("Walker", "CodigoUsuario = " + walker.getCodigo(), null);
        database.delete("Usuario", "Codigo = " + walker.getCodigo(), null);
    }

    @SuppressLint("Range")
    @Override
    public Walker findOne(Walker walker) throws SQLException {
        String sql = "SELECT u.codigo as codigo, u.Nome as Nome, u.Telefone as Telefone, w.AnosExperiencia as AnosExperiencia" +
                " FROM Walker w INNER JOIN Usuario u ON u.Codigo = w.CodigoUsuario AND w.CodigoUsuario =" + walker.getCodigo();
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToNext();
        }
        Walker walkerEncontrado = new Walker();
        if(!cursor.isAfterLast()){
            walkerEncontrado.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            walkerEncontrado.setNome(cursor.getString(cursor.getColumnIndex("Nome")));
            walkerEncontrado.setTelefone(cursor.getString(cursor.getColumnIndex("Telefone")));
            walkerEncontrado.setAnosExperiencia(cursor.getInt(cursor.getColumnIndex("AnosExperiencia")));
        }
        cursor.close();
        return walkerEncontrado;
    }

    @SuppressLint("Range")
    @Override
    public List<Walker> findAll() throws SQLException {
        List<Walker> walkers =new ArrayList<>();
        String sql = "SELECT u.Nome as Nome, u.Telefone as Telefone, w.AnosExperiencia as AnosExperiencia" +
                " FROM Walker w INNER JOIN Usuario u ON u.Codigo = w.CodigoUsuario";
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToNext();
        }
        while(!cursor.isAfterLast()){
            Walker walker = new Walker();
            walker.setNome(cursor.getString(cursor.getColumnIndex("Nome")));
            walker.setTelefone(cursor.getString(cursor.getColumnIndex("Telefone")));
            walker.setAnosExperiencia(cursor.getInt(cursor.getColumnIndex("AnosExperiencia")));

            walkers.add(walker);
            cursor.moveToNext();
        }
        cursor.close();
        return walkers;
    }

    private ContentValues getCvUsuario(Walker walker){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Nome", walker.getNome());
        contentValues.put("Codigo", walker.getCodigo());
        contentValues.put("Telefone", walker.getTelefone());

        return contentValues;
    }

    private ContentValues getCvWalker(Walker walker){
        ContentValues contentValues = new ContentValues();
        contentValues.put("CodigoUsuario", walker.getCodigo());
        contentValues.put("AnosExperiencia", walker.getAnosExperiencia());

        return contentValues;
    }
}
