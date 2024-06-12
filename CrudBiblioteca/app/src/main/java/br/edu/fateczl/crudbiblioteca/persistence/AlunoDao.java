package br.edu.fateczl.crudbiblioteca.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.crudbiblioteca.model.Aluno;

public class AlunoDao implements IAlunoDao, ICRUDDao<Aluno> {
    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;

    public AlunoDao(Context context) {
        this.context = context;
    }

    @Override
    public AlunoDao open() throws SQLException {
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
    public void insert(Aluno aluno) throws SQLException {
        ContentValues contentValues = getContentValues(aluno);
        database.insert("Aluno", null, contentValues);
    }

    @Override
    public int update(Aluno aluno) throws SQLException {
        ContentValues contentValues = getContentValues(aluno);
        int ret = database.update("Aluno", contentValues, "Ra = " + aluno.getRa(), null);
        return ret;
    }

    @Override
    public void delete(Aluno aluno) throws SQLException {
        database.delete("Aluno", "Ra = " + aluno.getRa(), null);
    }

    @SuppressLint("Range")
    @Override
    public Aluno findOne(Aluno aluno) throws SQLException {
        String sql= "SELECT Ra, Nome, Email FROM aluno WHERE Ra = " + aluno.getRa();
            Cursor cursor = database.rawQuery(sql, null);
            if(cursor != null){
                cursor.moveToNext();
            }
            if(!cursor.isAfterLast()){
            aluno.setRa(cursor.getInt(cursor.getColumnIndex("Ra")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("Nome")));
            aluno.setEmail(cursor.getString(cursor.getColumnIndex("Email")));
        }
        cursor.close();
        return aluno;
    }

    @SuppressLint("Range")
    @Override
    public List<Aluno> findAll() throws SQLException {
        List<Aluno> alunos = new ArrayList<>();
        String sql= "SELECT Ra, Nome, Email FROM aluno";
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToNext();
        }
        while(!cursor.isAfterLast()){
            Aluno aluno = new Aluno();
            aluno.setRa(cursor.getInt(cursor.getColumnIndex("Ra")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("Nome")));
            aluno.setEmail(cursor.getString(cursor.getColumnIndex("Email")));

            alunos.add(aluno);
            cursor.moveToNext();
        }
        cursor.close();
        return alunos;
    }

    private static ContentValues getContentValues(Aluno aluno) {
            ContentValues contentValues = new ContentValues();
        contentValues.put("ra", aluno.getRa());
        contentValues.put("nome", aluno.getNome());
        contentValues.put("email", aluno.getEmail());
        return contentValues;
    }
}
