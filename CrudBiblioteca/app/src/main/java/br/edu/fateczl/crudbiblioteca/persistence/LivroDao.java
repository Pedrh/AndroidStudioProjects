package br.edu.fateczl.crudbiblioteca.persistence;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.crudbiblioteca.model.Aluguel;
import br.edu.fateczl.crudbiblioteca.model.Livro;

public class LivroDao implements ILivroDao, ICRUDDao<Livro> {
    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;

    public LivroDao(Context context) {
        this.context = context;
    }

    @Override
    public LivroDao open() throws SQLException {
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
    public void insert(Livro livro) throws SQLException {
        ContentValues cvExemp = getCvExemplar(livro);
        ContentValues cvLivro = getCvLivro(livro);
        database.insert("Exemplar", null, cvExemp);
        database.insert("Livro", null, cvLivro);
    }


    @Override
    public int update(Livro livro) throws SQLException {
        ContentValues cvExemp = getCvExemplar(livro);
        ContentValues cvLivro = getCvLivro(livro);
        int ret;
        ret = database.update("Exemplar", cvExemp, "Codigo = " + livro.getCodigo(), null);
        ret += database.update("Livro", cvLivro, "Codigo = " + livro.getCodigo(), null);
        return ret;
    }

    @Override
    public void delete(Livro livro) throws SQLException {
        database.delete("Exemplar", "Codigo = " + livro.getCodigo(), null);
    }

    @SuppressLint("Range")
    @Override
    public Livro findOne(Livro livro) throws SQLException {
        String sql =
                "SELECT e.Codigo as codigo, e.Nome as nome, e.qtdPaginas as qtdPaginas, " +
                        "l.Isbn as isbn, l.Edicao as edicao FROM Exemplar e INNER JOIN Livro l " +
                        "on e.Codigo = l.ExemplarCodigo AND l.ExemplarCodigo = " + livro.getCodigo();
        Cursor cursor = database.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToNext();
        }

        if(!cursor.isAfterLast()){
            livro.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            livro.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            livro.setQtdPaginas(cursor.getInt(cursor.getColumnIndex("qtdPaginas")));
            livro.setIsbn(cursor.getString(cursor.getColumnIndex("isbn")));
            livro.setEdicao(cursor.getInt(cursor.getColumnIndex("edicao")));
        }
        cursor.close();
        return livro;
    }

    @SuppressLint("Range")
    @Override
    public List<Livro> findAll() throws SQLException {
        List<Livro> livros = new ArrayList<>();
        String sql =
                "SELECT e.Codigo as codigo, e.Nome as nome, e.qtdPaginas as qtdPaginas, " +
                        "l.Isbn as isbn, l.Edicao as edicao FROM Exemplar e INNER JOIN Livro l " +
                        "on e.Codigo = l.ExemplarCodigo";
        Cursor cursor = database.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToNext();
        }

        while(!cursor.isAfterLast()){
            Livro livro = new Livro();
            livro.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            livro.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            livro.setQtdPaginas(cursor.getInt(cursor.getColumnIndex("qtdPaginas")));
            livro.setIsbn(cursor.getString(cursor.getColumnIndex("isbn")));
            livro.setEdicao(cursor.getInt(cursor.getColumnIndex("edicao")));

            livros.add(livro);
            cursor.moveToNext();
        }
        cursor.close();
        return livros;
    }



    private ContentValues getCvExemplar(Livro livro){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Codigo", livro.getCodigo());
        contentValues.put("Nome", livro.getNome());
        contentValues.put("qtdPaginas", livro.getQtdPaginas());
        return contentValues;
    }

    private ContentValues getCvLivro(Livro livro){
        ContentValues contentValues = new ContentValues();
        contentValues.put("ExemplarCodigo", livro.getCodigo());
        contentValues.put("Isbn", livro.getIsbn());
        contentValues.put("Edicao", livro.getEdicao());

        return contentValues;
    }

}
