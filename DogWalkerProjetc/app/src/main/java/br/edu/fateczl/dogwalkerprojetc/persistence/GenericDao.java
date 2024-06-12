package br.edu.fateczl.dogwalkerprojetc.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GenericDao extends SQLiteOpenHelper {
    private static final String DATABASE = "DOGWALKER.db";
    private static final int DATABASE_VER = 1;
    private static final String CREATE_USUARIO=
            "CREATE TABLE Usuario(" +
                    "Nome VARCHAR(100)," +
                    "Codigo INT(10) PRIMARY KEY," +
                    "Telefone INT(10));";
    private static final String CREATE_DONO =
            "CREATE TABLE Dono(" +
                    "CodigoUsuario INT(10),"+
                    "Cep INT(10)," +
                    "Email VARCHAR(100)," +
                    "PRIMARY KEY (CodigoUsuario, Cpf)," +
                    "FOREIGN KEY (CodigoUsuario) REFERENCES Usuario(Codigo) ON DELETE CASCADE);";
    private static final String CREATE_WALKER =
            "CREATE TABLE Walker(" +
                    "CodigoUsuario INT(10) PRIMARY KEY,"+
                    "AnosExperiencia INT(10)," +
                    "FOREIGN KEY (CodigoUsuario) REFERENCES Usuario(Codigo) ON DELETE CASCADE);";
    private static final String CREATE_PET =
            "CREATE TABLE Pet(" +
                    "Nome VARCHAR(100)," +
                    "id INT(10) PRIMARY KEY," +
                    "raca VARCHAR(80)," +
                    "porte VARCHAR(80)," +
                    "idade VARCHAR(80)," +
                    "FOREIGN KEY (Cpf) REFERENCES Dono(Cpf));";
    private static final String CREATE_AGENDAMENTO =
            "CREATE TABLE Pet(" +
                    "CodigoDono INT(10)," +
                    "CodigoWalker INT(10)," +
                    "DataEncontro VARCHAR(100) PRIMARY KEY," +
                    "LocalEncontro VARCHAR(100),"+
                    "HoraEncontro INT(10),"+
                    "QtdPasseio INT(10),"+
                    "TempoPasseio INT(10),"+
                    "FormaPagto VARCHAR(80),"+
                    "FOREIGN KEY (CodigoDono) REFERENCES Dono(CodigoUsuario)," +
                    "FOREIGN KEY (CodigoWalker) REFERENCES Walker(CodigoUsuario));";
    public GenericDao(Context context){
        super(context, DATABASE, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USUARIO);
        db.execSQL(CREATE_WALKER);
        db.execSQL(CREATE_DONO);
        db.execSQL(CREATE_PET);
        db.execSQL(CREATE_AGENDAMENTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            db.execSQL("DROP TABLE IF EXISTS Agendamento");
            db.execSQL("DROP TABLE IF EXISTS Dono");
            db.execSQL("DROP TABLE IF EXISTS Walker");
            db.execSQL("DROP TABLE IF EXISTS Usuario");
            db.execSQL("DROP TABLE IF EXISTS Pet");
            onCreate(db);
        }
    }
}
