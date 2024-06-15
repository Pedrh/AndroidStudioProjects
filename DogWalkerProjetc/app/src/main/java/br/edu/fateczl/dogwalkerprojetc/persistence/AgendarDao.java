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
        database.delete("Agendamento", "dataEncontro = ?", whereArgs);
    }

    @SuppressLint("Range")
    @Override
    public Agendar findOne(Agendar agendar) throws SQLException {
        String sql =
                "SELECT " +
                        "ud.Codigo as CodigoDono, ud.Nome as NomeDono, ud.Telefone as FoneDono, " +
                        "d.Cep as Cep, d.Email as Email, " +
                        "uw.Codigo as CodigoWalker, uw.Nome as NomeWalker, uw.Telefone as FoneWalker, w.AnosExperiencia as AnosExperiencia, " +
                        "ag.DataEncontro as DataEncontro, ag.LocalEncontro as LocalEncontro, ag.HoraEncontro as HoraEncontro," +
                        "ag.QtdPasseio as QtdPasseio, ag.TempoPasseio as TempoPasseio, ag.FormaPagto as FormaPagto " +
                        "FROM Dono d INNER JOIN Usuario ud ON d.CodigoUsuario = ud.Codigo INNER JOIN " +
                        "Agendamento ag on d.CodigoUsuario = ag.CodigoDono " +
                        "INNER JOIN Walker w on w.CodigoUsuario = ag.CodigoWalker INNER JOIN " +
                        "Usuario uw ON w.CodigoUsuario = uw.Codigo AND ag.dataEncontro = ?";
        String[] whereArgs = {agendar.getDataEncontro()};
        Cursor cursor = database.rawQuery(sql, whereArgs);
        if(cursor != null){
            cursor.moveToNext();
        }
        Agendar agedamentoEncontrado = new Agendar();
        if(!cursor.isAfterLast()){
            Dono dono = new Dono();
            dono.setCodigo(cursor.getInt(cursor.getColumnIndex("CodigoDono")));
            dono.setNome(cursor.getString(cursor.getColumnIndex("NomeDono")));
            dono.setTelefone(cursor.getString(cursor.getColumnIndex("FoneDono")));
            dono.setCep(cursor.getInt(cursor.getColumnIndex("Cep")));
            dono.setEmail(cursor.getString(cursor.getColumnIndex("Email")));

            Walker walker = new Walker();
            walker.setCodigo(cursor.getInt(cursor.getColumnIndex("CodigoWalker")));
            walker.setNome(cursor.getString(cursor.getColumnIndex("NomeWalker")));
            walker.setTelefone(cursor.getString(cursor.getColumnIndex("FoneWalker")));
            walker.setAnosExperiencia(cursor.getInt(cursor.getColumnIndex("AnosExperiencia")));

            agedamentoEncontrado.setDataEncontro(cursor.getString(cursor.getColumnIndex("DataEncontro")));
            agedamentoEncontrado.setLocalEncontro(cursor.getString(cursor.getColumnIndex("LocalEncontro")));
            agedamentoEncontrado.setHoraEncontro(cursor.getString(cursor.getColumnIndex("HoraEncontro")));
            agedamentoEncontrado.setQtdPasseio(cursor.getInt(cursor.getColumnIndex("QtdPasseio")));
            agedamentoEncontrado.setTmpPasseio(cursor.getInt(cursor.getColumnIndex("TempoPasseio")));
            agedamentoEncontrado.setFormaPagto(cursor.getString(cursor.getColumnIndex("FormaPagto")));
            agedamentoEncontrado.setDono(dono);
            agedamentoEncontrado.setWalker(walker);
        }
        cursor.close();
        return agedamentoEncontrado;
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
