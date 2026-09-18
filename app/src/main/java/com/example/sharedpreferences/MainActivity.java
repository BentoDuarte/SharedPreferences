package com.example.sharedpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sharedpreferences.database.ProdutoDAO;
import com.example.sharedpreferences.model.Produto;

public class MainActivity extends AppCompatActivity {

    private EditText nome;
    private EditText preco;
    private EditText descricao;
    private Button cadastrar;

    private ProdutoDAO produtoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.editTextText);
        preco = findViewById(R.id.editTextText2);
        descricao = findViewById(R.id.editTextText3);
        cadastrar = findViewById(R.id.button);
        Button consultar = findViewById(R.id.button2);

        produtoDAO = new ProdutoDAO(this);

        cadastrar.setOnClickListener(v -> cadastrarProduto());

        consultar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        MainActivity.this,
                        com.example.sharedpreferences.ChecarProdutosActivity.class
                );

                startActivity(intent);
            }
        });
    }

    private void cadastrarProduto() {

        String nomeProduto = nome.getText().toString().trim();
        String precoProduto = preco.getText().toString().trim();
        String descricaoProduto = descricao.getText().toString().trim();

        // Verifica se os campos estão preenchidos
        if (nomeProduto.isEmpty() ||
                precoProduto.isEmpty() ||
                descricaoProduto.isEmpty()) {

            Toast.makeText(
                    this,
                    "Preencha todos os campos!",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        try {

            // Converte o preço para Double
            Double valor = Double.parseDouble(
                    precoProduto.replace(",", ".")
            );

            // Cria o produto
            Produto produto = new Produto(
                    nomeProduto,
                    descricaoProduto,
                    valor
            );

            // Salva no banco
            boolean sucesso = produtoDAO.inserir(produto);

            if (sucesso) {

                Toast.makeText(
                        this,
                        "Produto cadastrado com sucesso!",
                        Toast.LENGTH_SHORT
                ).show();

                // Limpa os campos
                nome.setText("");
                preco.setText("");
                descricao.setText("");
            }

        } catch (NumberFormatException e) {

            Toast.makeText(
                    this,
                    "Digite um preço válido!",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}