package br.edu.fateczl.timecrud.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.timecrud.model.Jogador;
import br.edu.fateczl.timecrud.model.Time;

public class JogadorDao implements IJogadorDao, ICRUDDao<Jogador> {
    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;

    public JogadorDao(Context context){
        this.context = context;
    }

    @Override
    public JogadorDao open() throws SQLException {
        gDao = new GenericDao(context);
        database = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(Jogador jogador) throws SQLException {
        ContentValues contentValues = getContentValues(jogador);
        database.insert("jogador", null, contentValues);
    }

    @Override
    public int update(Jogador jogador) throws SQLException {
        ContentValues contentValues = getContentValues(jogador);
        int ret = database.update("jogador", contentValues, "id = " + jogador.getId(),null);
        return ret;
    }

    @Override
    public void delete(Jogador jogador) throws SQLException {
        database.delete("jogador", "id = " + jogador.getId(), null);
    }

    @SuppressLint("Range")
    @Override
    public Jogador findOne(Jogador jogador) throws SQLException {
        String sql = "SELECT j.id as id, j.nome as nomeJog, j.dataNasc as dataNasc, j.altura as altura, " +
                "j.peso as peso, t.codigo as codigo, t.nome as nomeTime, t.cidade as cidade FROM jogador j, time t " +
                "WHERE j.codigoTime = t.codigo AND j.id = " + jogador.getId();
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        if(!cursor.isAfterLast()){
            Time time = new Time();
            time.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            time.setNome(cursor.getString(cursor.getColumnIndex("nomeTime")));
            time.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));

            jogador.setId(cursor.getInt(cursor.getColumnIndex("id")));
            jogador.setNome(cursor.getString(cursor.getColumnIndex("nomeJog")));
            jogador.setDataNasc(cursor.getString(cursor.getColumnIndex("dataNasc")));
            jogador.setAltura(cursor.getFloat(cursor.getColumnIndex("altura")));
            jogador.setPeso(cursor.getFloat(cursor.getColumnIndex("peso")));
            jogador.setTime(time);
        }
        cursor.close();
        return jogador;
    }

    @SuppressLint("Range")
    @Override
    public List<Jogador> findAll() throws SQLException {
        List<Jogador> jogadores = new ArrayList<>();
        String sql = "SELECT j.id as id, j.nome as nomeJog, j.dataNasc as dataNasc, j.altura as altura, " +
                "j.peso as peso, t.codigo as codigo, t.nome as nomeTime, t.cidade as cidade FROM jogador j, time t " +
                "WHERE j.codigoTime = t.codigo";
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()){
            Jogador jogador = new Jogador();
            Time time = new Time();
            time.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            time.setNome(cursor.getString(cursor.getColumnIndex("nomeTime")));
            time.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));


            jogador.setId(cursor.getInt(cursor.getColumnIndex("id")));
            jogador.setNome(cursor.getString(cursor.getColumnIndex("nomeJog")));
            jogador.setDataNasc(cursor.getString(cursor.getColumnIndex("dataNasc")));
            jogador.setAltura(cursor.getFloat(cursor.getColumnIndex("altura")));
            jogador.setPeso(cursor.getFloat(cursor.getColumnIndex("peso")));
            jogador.setTime(time);

            jogadores.add(jogador);
            cursor.moveToNext();
        }
        cursor.close();
        return jogadores;
    }

    private ContentValues getContentValues(Jogador j){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", j.getId());
        contentValues.put("nome", j.getNome());
        contentValues.put("dataNasc", j.getDataNasc());
        contentValues.put("altura", j.getAltura());
        contentValues.put("peso", j.getPeso());
        contentValues.put("codigoTime", j.getTime().getCodigo());
        return contentValues;
    }

}
