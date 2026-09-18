package com.example.sharedpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sharedpreferences.database.ProdutoDAO;
import com.example.sharedpreferences.model.Produto;

import java.util.ArrayList;
import java.util.Locale;

public class ChecarProdutosActivity extends AppCompatActivity {

    private TableLayout tabelaProdutos;
    private ProdutoDAO produtoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checar_produtos);

        tabelaProdutos = findViewById(R.id.tabelaProdutos);
        Button cadastrar = findViewById(R.id.button3);

        produtoDAO = new ProdutoDAO(this);

        carregarProdutos();

        cadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        ChecarProdutosActivity.this,
                        com.example.sharedpreferences.MainActivity.class
                );

                startActivity(intent);
            }
        });
    }

    private void carregarProdutos() {

        ArrayList<Produto> produtos = produtoDAO.listar();

        for (Produto produto : produtos) {

            TableRow linha = new TableRow(this);

            TextView id = criarCelula(
                    String.valueOf(produto.getId())
            );

            TextView nome = criarCelula(
                    produto.getNome()
            );

            TextView descricao = criarCelula(
                    produto.getDescricao()
            );

            TextView preco = criarCelula(
                    String.format(
                            Locale.getDefault(),
                            "R$ %.2f",
                            produto.getPreco()
                    )
            );

            linha.addView(id);
            linha.addView(nome);
            linha.addView(descricao);
            linha.addView(preco);

            tabelaProdutos.addView(linha);
        }
    }

    private TextView criarCelula(String texto) {

        TextView textView = new TextView(this);

        textView.setText(texto);
        textView.setTextSize(16);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(10, 15, 10, 15);

        return textView;
    }
}
