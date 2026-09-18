package com.example.sharedpreferences.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sharedpreferences.model.Produto;

public class ProdutoDAO {
    private SQLiteDatabase db;
    private Database con;
    public ProdutoDAO(Context context) {
        con = new Database(context);
    }
    // INSERT
    public boolean inserir(Produto produto) {
        db = con.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(
                "nome", produto.getNome());
        values.put("" +
                "descricao", produto.getDescricao());
        values.put("" +
                "preco", produto.getPreco());
        long resultado = db.insert(Database.TABELA_PRODUTO, null, values);
        return resultado != -1;
    }
    // Busca
    public boolean busca() {
        db = con.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " +
                        Database.TABELA_PRODUTO,
                null);
        boolean existe = cursor.getCount() > 0; cursor.close(); return existe;
    }
}