package br.edu.fateczl.crudbiblioteca.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GenericDao extends SQLiteOpenHelper {

    private static final String DATABASE = "BIBLIOTECA.db";
    private static final int DATABASE_VER = 1;

    private static final String CREATE_TABLE_ALUNO =
            "CREATE TABLE Aluno (" +
                    "Ra INT(10) PRIMARY KEY," +
                    "Nome VARCHAR(100)," +
                    "Email VARCHAR(50));";
    public static final String CREATE_TABLE_EXEMPLAR =
            "CREATE TABLE Exemplar(" +
                    "Codigo INT(10) PRIMARY KEY," +
                    "Nome VARCHAR(50)," +
                    "qtdPaginas INT(10));";
    public static final String CREATE_TABLE_LIVRO =
            "CREATE TABLE Livro(" +
                    "ExemplarCodigo INT PRIMARY KEY," +
                    "Isbn CHAR(13)," +
                    "Edicao INT(10)," +
                    "FOREIGN KEY (ExemplarCodigo) REFERENCES Exemplar(Codigo) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_REVISTA =
            "CREATE TABLE Revista(" +
                    "ExemplarCodigo INT PRIMARY KEY," +
                    "Issn CHAR(8)," +
                    "FOREIGN KEY (ExemplarCodigo) REFERENCES Exemplar(Codigo) ON DELETE CASCADE);";
    public static final String CREATE_TABLE_ALUGUEL =
            "CREATE TABLE Aluguel(" +
                    "ExemplarCodigo INT," +
                    "AlunoRa INT," +
                    "dataRetirada VARCHAR(100)," +
                    "dataDevolucao VARCHAR(100)," +
                    "PRIMARY KEY (ExemplarCodigo, AlunoRa, dataRetirada)," +
                    "FOREIGN KEY (ExemplarCodigo) REFERENCES Exemplar(Codigo) ON DELETE CASCADE," +
                    "FOREIGN KEY (AlunoRa) REFERENCES Aluno(Ra) ON DELETE CASCADE);";

    public GenericDao(Context context){
        super(context, DATABASE, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_ALUNO);
        sqLiteDatabase.execSQL(CREATE_TABLE_EXEMPLAR);
        sqLiteDatabase.execSQL(CREATE_TABLE_LIVRO);
        sqLiteDatabase.execSQL(CREATE_TABLE_REVISTA);
        sqLiteDatabase.execSQL(CREATE_TABLE_ALUGUEL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int antigaVersao, int novaVersao) {
        if(novaVersao > antigaVersao){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Aluguel");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Revisa");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Livro");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Exemplar");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Aluno");
            onCreate(sqLiteDatabase);
        }
    }
}
