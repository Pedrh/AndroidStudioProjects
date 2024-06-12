package br.edu.fateczl.dogwalkerprojetc.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

import br.edu.fateczl.dogwalkerprojetc.model.Agendar;
import br.edu.fateczl.dogwalkerprojetc.model.Dono;
import br.edu.fateczl.dogwalkerprojetc.model.Walker;

public class AgendarDao implements IAgendarDao, ICRUDDao<Agendar>{
    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;

    public AgendarDao(Context context) {
        this.context = context;
    }

    @Override
    public AgendarDao open() throws SQLException {
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
    public void insert(Agendar agendar) throws SQLException {
        ContentValues cv = getContentValues(agendar);
        database.insert("Agendamento", null, cv);
    }

    @Override
    public int update(Agendar agendar) throws SQLException {
        ContentValues cv = getContentValues(agendar);
        String[] whereArgs = {agendar.getDataEncontro()};
        int ret = database.update("Agendamento", cv, "dataEncontro = ?", whereArgs);
        return ret;
    }

    @Override
    public void delete(Agendar agendar) throws SQLException {
        String[] whereArgs = {agendar.getDataEncontro()};
        database.delete("Aluguel", "dataEncontro = ?", whereArgs);
    }

    @SuppressLint("Range")
    @Override
    public Agendar findOne(Agendar agendar) throws SQLException {
        String sql =
                "SELECT " +
                        "d.CodigoUsuario as codigoDono, d.Nome as nomeDono, d.telefone as foneDono," +
                        "d.cep as cep, d.email as email, " +
                        "w.CodigoUsuario as codigoWalker, w.Nome as nomeWalker, w.telefone as foneWalker, w.anosExperiencia as anosExperiencia, " +
                        "ag.dataEncontro as dataEncontro, ag.localEncontro as localEncontro, ag.horaEncontro as horaEncontro," +
                        "ag.qtdPasseio as qtdPasseio, ag.tempoPasseio as tempoPasseio, ag.formaPagto as formaPagto " +
                        "FROM Dono d INNER JOIN Agendamento ag on e.CodigoUsuario = ag.CodigoDono " +
                        "INNER JOIN Walker w on w.CodigoUsuario = ag.CodigoWalker AND ag.dataEncontro = ?";
        String[] whereArgs = {agendar.getDataEncontro()};
        Cursor cursor = database.rawQuery(sql, whereArgs);
        if(cursor != null){
            cursor.moveToNext();
        }
        if(!cursor.isAfterLast()){
            Dono dono = new Dono();
            dono.setCodigo(cursor.getInt(cursor.getColumnIndex("CodigoDono")));
            dono.setNome(cursor.getString(cursor.getColumnIndex("nomeDono")));
            dono.setTelefone(cursor.getInt(cursor.getColumnIndex("foneDono")));
            dono.setCep(cursor.getInt(cursor.getColumnIndex("cep")));
            dono.setEmail(cursor.getString(cursor.getColumnIndex("email")));

            Walker walker = new Walker();
            walker.setCodigo(cursor.getInt(cursor.getColumnIndex("CodigoWalker")));
            walker.setNome(cursor.getString(cursor.getColumnIndex("nomeWalker")));
            walker.setTelefone(cursor.getInt(cursor.getColumnIndex("foneWalker")));
            walker.setAnosExperiencia(cursor.getInt(cursor.getColumnIndex("anosExperiencia")));

            agendar.setDataEncontro(cursor.getString(cursor.getColumnIndex("dataEncontro")));
            agendar.setLocalEncontro(cursor.getString(cursor.getColumnIndex("localEncontro")));
            agendar.setHoraEncontro(cursor.getString(cursor.getColumnIndex("horaEncontro")));
            agendar.setQtdPasseio(cursor.getInt(cursor.getColumnIndex("qtdPasseio")));
            agendar.setTmpPasseio(cursor.getInt(cursor.getColumnIndex("tempoPasseio")));
            agendar.setFormaPagto(cursor.getString(cursor.getColumnIndex("formaPagto")));
            agendar.setDono(dono);
            agendar.setWalker(walker);
        }
        cursor.close();
        return agendar;
    }

    private ContentValues getContentValues(Agendar ag){
        ContentValues contentValues =new ContentValues();
        contentValues.put("CodigoDono", ag.getDono().getCodigo());
        contentValues.put("CodigoWalker", ag.getWalker().getCodigo());
        contentValues.put("QtdPasseio", ag.getQtdPasseio());
        contentValues.put("TempoPasseio", ag.getTmpPasseio());
        contentValues.put("FormaPagto", ag.getFormaPagto());
        contentValues.put("HoraEncontro", ag.getHoraEncontro());
        contentValues.put("LocalEncontro", ag.getLocalEncontro());
        contentValues.put("DataEncontro", ag.getDataEncontro());
        return contentValues;
    }
}
