package com.example.sharedpreferences.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "app.db";
    private static final int DB_VERSION = 1;
    public static final String TABELA_PRODUTO = "produtos";
    public Database(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA_PRODUTO +
                " (" + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nome TEXT UNIQUE,"
                + "descricao TEXT,"
                + "preco NUMBER)"; db.execSQL(sql);
    }
    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_PRODUTO); onCreate(db);
    }
}
