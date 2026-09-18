package com.example.sharedpreferences.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sharedpreferences.model.Produto;

import java.util.ArrayList;

public class ProdutoDAO {

    private SharedPreferences preferences;

    public ProdutoDAO(Context context) {
        Database con = new Database(context);
        preferences = con.getPreferences();
    }

    // INSERT
    public boolean inserir(Produto produto) {

        // Pega o próximo ID
        int id = preferences.getInt("ULTIMO_ID", 0) + 1;

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("NOME_" + id, produto.getNome());
        editor.putString("DESCRICAO_" + id, produto.getDescricao());
        editor.putString("PRECO_" + id, produto.getPreco().toString());

        // Salva o último ID utilizado
        editor.putInt("ULTIMO_ID", id);

        editor.apply();

        return true;
    }

    // BUSCA TODOS OS PRODUTOS
    public ArrayList<Produto> listar() {

        ArrayList<Produto> produtos = new ArrayList<>();

        int ultimoId = preferences.getInt("ULTIMO_ID", 0);

        for (int id = 1; id <= ultimoId; id++) {

            String nome = preferences.getString(
                    "NOME_" + id,
                    ""
            );

            String descricao = preferences.getString(
                    "DESCRICAO_" + id,
                    ""
            );

            String precoString = preferences.getString(
                    "PRECO_" + id,
                    "0"
            );

            if (!nome.isEmpty()) {

                Double preco = Double.parseDouble(precoString);

                Produto produto = new Produto(
                        preco,
                        descricao,
                        nome,
                        id
                );

                produtos.add(produto);
            }
        }

        return produtos;
    }

    // BUSCA SE EXISTE ALGUM PRODUTO
    public boolean busca() {

        return preferences.getInt("ULTIMO_ID", 0) > 0;
    }
}