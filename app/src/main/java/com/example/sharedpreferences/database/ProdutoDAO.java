package com.example.sharedpreferences.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sharedpreferences.model.Produto;

public class ProdutoDAO {

    private SharedPreferences preferences;

    public ProdutoDAO(Context context) {
        Database con = new Database(context);
        preferences = con.getPreferences();
    }

    // INSERT
    public boolean inserir(Produto produto) {

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("NOME", produto.getNome());
        editor.putString("DESCRICAO", produto.getDescricao());
        editor.putString("PRECO", produto.getPreco().toString());

        editor.apply();

        return true;
    }

    // BUSCA
    public boolean busca() {

        return preferences.contains("NOME");
    }
}