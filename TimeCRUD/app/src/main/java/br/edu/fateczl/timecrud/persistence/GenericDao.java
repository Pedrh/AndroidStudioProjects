package br.edu.fateczl.timecrud.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GenericDao extends SQLiteOpenHelper {

    private static final String DATABASE = "ESPORTIVO.DB";
    private static final int DATABASE_VER = 1;
    private static final String CREATE_TABLE_TIME =
            "CREATE TABLE time (" +
                    "codigo INT PRIMARY KEY, " +
                    "nome VARCHAR(50), " +
                    "cidade VARCHAR(80));";
    private static final String CREATE_TABLE_JOGADOR =
            "CREATE TABLE jogador (" +
                    "id INT PRIMARY KEY, " +
                    "nome VARCHAR(100), " +
                    "dataNasc VARCHAR(10), " +
                    "altura DECIMAL(4, 2), " +
                    "peso DECIMAR(4, 1), " +
                    "codigoTime INT," +
                    "FOREIGN KEY (codigoTime) REFERENCES time(codigo));";
    public GenericDao(Context context){
        super(context, DATABASE, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_TIME);
        sqLiteDatabase.execSQL(CREATE_TABLE_JOGADOR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int antigaVersao, int novaVersao) {
        if(antigaVersao > novaVersao){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS jogador");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS time");
            onCreate(sqLiteDatabase);
        }
    }
}
