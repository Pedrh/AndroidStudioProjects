package br.edu.fateczl.crudbiblioteca.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.crudbiblioteca.model.Aluguel;
import br.edu.fateczl.crudbiblioteca.model.Aluno;
import br.edu.fateczl.crudbiblioteca.model.Exemplar;
import br.edu.fateczl.crudbiblioteca.model.Revista;

public class AluguelDao implements IAluguelDao, ICRUDDao<Aluguel> {
    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;

    public AluguelDao(Context context) {
        this.context = context;
    }

    @Override
    public AluguelDao open() throws SQLException {
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
    public void insert(Aluguel aluguel) throws SQLException {
        ContentValues contentValues = getContentValues(aluguel);
        database.insert("Aluguel", null, contentValues);
    }

    @Override
    public int update(Aluguel aluguel) throws SQLException {
        String whereClause = "dataRetirada = ?";
        String[] whereArgs = {aluguel.getDataRetirada()};

        ContentValues contentValues = getContentValues(aluguel);
        int ret = database.update("Aluguel", contentValues, whereClause, whereArgs);
        return ret;
    }

    @Override
    public void delete(Aluguel aluguel) throws SQLException {
        String whereClause = "dataRetirada = ?";
        String[] whereArgs = {aluguel.getDataRetirada()};
        database.delete("Aluguel", whereClause, whereArgs);
    }

    @SuppressLint("Range")
    @Override
    public Aluguel findOne(Aluguel aluguel) throws SQLException {
        String sql =
                "SELECT " +
                        "e.Codigo as codigo, e.Nome as nomeEx, e.qtdPaginas as qtdPaginas, " +
                        "al.Ra as ra, al.Nome as nomeAl, al.Email as email, " +
                        "ag.dataRetirada as dataRetirada, ag.dataDevolucao as dataDevolucao " +
                        "FROM Exemplar e INNER JOIN Aluguel ag on e.Codigo = ag.ExemplarCodigo " +
                        "INNER JOIN Aluno al on al.Ra = ag.AlunoRa AND ag.dataRetirada = ?";
        String[] whereArgs = {aluguel.getDataRetirada()};
        Cursor cursor = database.rawQuery(sql, whereArgs);
        if(cursor != null){
            cursor.moveToNext();
        }
        if(!cursor.isAfterLast()){

            Exemplar exemplar = new Revista();
            exemplar.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            exemplar.setNome(cursor.getString(cursor.getColumnIndex("nomeEx")));
            exemplar.setQtdPaginas(cursor.getInt(cursor.getColumnIndex("qtdPaginas")));

            Aluno aluno = new Aluno();
            aluno.setRa(cursor.getInt(cursor.getColumnIndex("ra")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nomeAl")));
            aluno.setEmail(cursor.getString(cursor.getColumnIndex("email")));

            aluguel.setExemplar(exemplar);
            aluguel.setAluno(aluno);
            aluguel.setDataRetirada(cursor.getString(cursor.getColumnIndex("dataRetirada")));
            aluguel.setDataDevolucao(cursor.getString(cursor.getColumnIndex("dataDevolucao")));
        }

        cursor.close();
        return aluguel;
    }

    @SuppressLint("Range")
    @Override
    public List<Aluguel> findAll() throws SQLException {
        List<Aluguel> alugueis = new ArrayList<>();
        String sql =
                "SELECT " +
                        "e.Codigo as codigo, e.Nome as nomeEx, e.qtdPaginas as qtdPaginas, " +
                        "al.Ra as ra, al.Nome as nomeAl, al.Email as email, " +
                        "ag.dataRetirada as dataRetirada, ag.dataDevolucao as dataDevolucao " +
                        "FROM Exemplar e INNER JOIN Aluguel ag on e.Codigo = ag.ExemplarCodigo " +
                        "INNER JOIN Aluno al on al.Ra = ag.AlunoRa";
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToNext();
        }
        while(!cursor.isAfterLast()){

            Exemplar exemplar = new Revista();
            exemplar.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            exemplar.setNome(cursor.getString(cursor.getColumnIndex("nomeEx")));
            exemplar.setQtdPaginas(cursor.getInt(cursor.getColumnIndex("qtdPaginas")));

            Aluno aluno = new Aluno();
            aluno.setRa(cursor.getInt(cursor.getColumnIndex("ra")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nomeAl")));
            aluno.setEmail(cursor.getString(cursor.getColumnIndex("email")));

            Aluguel aluguel = new Aluguel();
            aluguel.setExemplar(exemplar);
            aluguel.setAluno(aluno);
            aluguel.setDataRetirada(cursor.getString(cursor.getColumnIndex("dataRetirada")));
            aluguel.setDataDevolucao(cursor.getString(cursor.getColumnIndex("dataDevolucao")));

            alugueis.add(aluguel);
            cursor.moveToNext();
        }

        cursor.close();
        return alugueis;
    }

    private ContentValues getContentValues(Aluguel aluguel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ExemplarCodigo", aluguel.getExemplar().getCodigo());
        contentValues.put("AlunoRa", aluguel.getAluno().getRa());
        contentValues.put("dataRetirada", aluguel.getDataRetirada().toString());
        contentValues.put("dataDevolucao", aluguel.getDataDevolucao().toString());

        return contentValues;

    }
}
