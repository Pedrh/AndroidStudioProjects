package br.edu.fateczl.crudbiblioteca.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.crudbiblioteca.model.Livro;
import br.edu.fateczl.crudbiblioteca.model.Revista;

public class RevistaDao implements IRevistaDao, ICRUDDao<Revista> {

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;

    public RevistaDao(Context context) {
        this.context = context;
    }

    @Override
    public RevistaDao open() throws SQLException {
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
    public void insert(Revista revista) throws SQLException {
        ContentValues cvExemp = getCvExemplar(revista);
        ContentValues cvRevista = getCvRevista(revista);
        database.insert("Exemplar", null, cvExemp);
        database.insert("Revista", null, cvRevista);
    }

    @Override
    public int update(Revista revista) throws SQLException {
        ContentValues cvExemp = getCvExemplar(revista);
        ContentValues cvRevista = getCvRevista(revista);
        int ret;
        ret = database.update("Exemplar", cvExemp, "Codigo = " + revista.getCodigo(), null);
        ret += database.update("Revista", cvRevista, "Codigo = " + revista.getCodigo(), null);
        return ret;
    }

    @Override
    public void delete(Revista revista) throws SQLException {
        database.delete("Exemplar", "Codigo = " + revista.getCodigo(), null);
    }

    @SuppressLint("Range")
    @Override
    public Revista findOne(Revista revista) throws SQLException {
        String sql =
                "SELECT e.Codigo as codigo, e.Nome as nome, e.qtdPaginas as qtdPaginas, " +
                        "r.Issn as issn FROM Exemplar e INNER JOIN Revista r " +
                        "on e.Codigo = r.ExemplarCodigo AND r.ExemplarCodigo = " + revista.getCodigo();
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToNext();
        }
        if(!cursor.isAfterLast()){
            revista.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            revista.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            revista.setQtdPaginas(cursor.getInt(cursor.getColumnIndex("qtdPaginas")));
            revista.setIssn(cursor.getString(cursor.getColumnIndex("issn")));
        }

        cursor.close();
        return revista;
    }

    @SuppressLint("Range")
    @Override
    public List<Revista> findAll() throws SQLException {
        List<Revista> revistas = new ArrayList<>();
        String sql =
                "SELECT e.Codigo as codigo, e.Nome as nome, e.qtdPaginas as qtdPaginas, " +
                        "r.Issn as issn FROM Exemplar e INNER JOIN Revista r " +
                        "on e.Codigo = r.ExemplarCodigo";
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToNext();
        }
        while(!cursor.isAfterLast()){
            Revista revista = new Revista();
            revista.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            revista.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            revista.setQtdPaginas(cursor.getInt(cursor.getColumnIndex("qtdPaginas")));
            revista.setIssn(cursor.getString(cursor.getColumnIndex("issn")));

            revistas.add(revista);
        }

        cursor.close();
        return revistas;
    }

    private ContentValues getCvExemplar(Revista revista){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Codigo", revista.getCodigo());
        contentValues.put("Nome", revista.getNome());
        contentValues.put("qtdPaginas", revista.getQtdPaginas());
        return contentValues;
    }

    private ContentValues getCvRevista(Revista revista){
        ContentValues contentValues = new ContentValues();
        contentValues.put("ExemplarCodigo", revista.getCodigo());
        contentValues.put("Issn", revista.getIssn());

        return contentValues;
    }

}
