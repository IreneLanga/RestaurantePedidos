package com.example.irenelanga.miriamfproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irenelanga.miriamfproject.R;
import com.example.irenelanga.miriamfproject.controller.ProdutoCtrl;
import com.example.irenelanga.miriamfproject.dbHelper.ConexaoSQLite;
import com.example.irenelanga.miriamfproject.model.Produto;

public class EditarProdutos extends AppCompatActivity {

    private EditText edtIdProduto;
    private EditText edtNomeProduto;
    private EditText edtPreco;
    private EditText edtEstoqueProduto;
    private Produto produto;

    private Button btnSalvarAlteracoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_produtos);

    this.edtIdProduto=(EditText)findViewById(R.id.idProduto);
    this.edtNomeProduto=(EditText)findViewById(R.id.editNomeProduto);
    this.edtPreco=(EditText)findViewById(R.id.editPrecoProduto);
    this.edtEstoqueProduto=(EditText)findViewById(R.id.editQuantidadeProduto);
    this.btnSalvarAlteracoes=(Button)findViewById(R.id.btnSalvarProduto);


    Bundle bundleDadosE=getIntent().getExtras();
    Produto produto=new Produto();
    produto.setId(bundleDadosE.getLong("id_produto"));
    produto.setNome(bundleDadosE.getString("nome_produto"));
    produto.setPreco(bundleDadosE.getDouble("preco_produto"));
    produto.setQuantidadeEmEstoque(bundleDadosE.getInt("estoque_produto"));
    this.setDadosProduto(produto);
    this.clickNoButaoSalvarListener();


    }


    private void setDadosProduto(Produto produto){
        this.edtIdProduto.setText(String.valueOf(produto.getId()));
        this.edtNomeProduto.setText(produto.getNome());
        this.edtPreco.setText(String.valueOf(produto.getPreco()));
        this.edtEstoqueProduto.setText(String.valueOf(produto.getQuantidadeEmEstoque()));


    }

    private Produto getDadosProdutoDoFormulario(){
        this.produto=new Produto();

        if(this.edtIdProduto.getText().toString().isEmpty()==false){
            this.produto.setId(Long.parseLong(this.edtIdProduto .getText().toString()));
        }else {
            return null;
        }

        if(this.edtNomeProduto.getText().toString().isEmpty()== false){
            this.produto.setNome(this.edtNomeProduto.getText().toString());
        }else {
            return null;}

        if(this.edtEstoqueProduto.getText().toString().isEmpty()==false){
            int quantidadeProduto= Integer.parseInt(this.edtEstoqueProduto.getText().toString());
            this.produto.setQuantidadeEmEstoque(quantidadeProduto);
        }else{
            return null;
        }
        if(this.edtPreco.getText().toString().isEmpty()==false){
            Double precoProduto=Double.parseDouble(this.edtPreco.getText().toString());
            this.produto.setPreco(precoProduto);
        }else

        {
            return null;
        }
        return  produto;
    }

    public void clickNoButaoSalvarListener(){
        this.btnSalvarAlteracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Produto produtoAAtualizar=getDadosProdutoDoFormulario();
                Log.d("PRODUTO RECUP.",produtoAAtualizar.toString());

                if (produtoAAtualizar != null) {
                   ProdutoCtrl produtoCtrl=new ProdutoCtrl(ConexaoSQLite.getInstanciaConexao(EditarProdutos.this));
                    boolean atualizou=produtoCtrl.atualizarProdutoCtrl(produtoAAtualizar);

                    if(atualizou ==true)
                    {
                        Toast.makeText(EditarProdutos.this,"Produtos Atualizado com Sucesso",Toast.LENGTH_LONG).show();

                    }else {
                       Toast.makeText(EditarProdutos.this,"Produto nao Atualizado",Toast.LENGTH_LONG).show();

                    }


             }else {
                   Toast.makeText(EditarProdutos.this,"Todos campos sao Obrigatorios",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
