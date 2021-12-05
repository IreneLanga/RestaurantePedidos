package com.example.irenelanga.miriamfproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.irenelanga.miriamfproject.R;
import com.example.irenelanga.miriamfproject.controller.ProdutoCtrl;
import com.example.irenelanga.miriamfproject.dbHelper.ConexaoSQLite;
import com.example.irenelanga.miriamfproject.model.Produto;

public class ActivityProduto extends AppCompatActivity {
    private EditText idProduto;
    private EditText editNomeProduto;
    private EditText editQuantidadeProduto;
    private EditText editPrecoProduto;
    private Button btnSalvarProduto;
    private Produto produto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        idProduto=(EditText)findViewById(R.id.idProduto);
        editNomeProduto=(EditText) findViewById(R.id.editNomeProduto);
        editPrecoProduto=(EditText) findViewById(R.id.editPrecoProduto);
        editQuantidadeProduto=(EditText) findViewById(R.id.editQuantidadeProduto);
        btnSalvarProduto=(Button)findViewById(R.id.btnSalvarProduto);
        this.clickNoButaoListener();

    }


  public void clickNoButaoListener(){
        this.btnSalvarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Produto produtoACdastrar=getDadosProdutoDoFormulario();

                if (produtoACdastrar != null) {

                    ProdutoCtrl produtoCtrl=new ProdutoCtrl(ConexaoSQLite.getInstanciaConexao(ActivityProduto.this));
                    long idProduto=produtoCtrl.salvarProdutoCtrl(produtoACdastrar);
                    if(idProduto > 0)
                    {
                        Toast.makeText(ActivityProduto.this,"Produtos Cadastrados com Sucesso",Toast.LENGTH_LONG).show();

                    }else {
                        Toast.makeText(ActivityProduto.this,"Produto nao Cadastrado",Toast.LENGTH_LONG).show();

                    }

                }else {
                    Toast.makeText(ActivityProduto.this,"Todos campos sao Obrigatorios",Toast.LENGTH_LONG).show();
                }
            }
        });
  }

    private Produto getDadosProdutoDoFormulario(){
        this.produto=new Produto();

        if(this.idProduto.getText().toString().isEmpty()==false){
            this.produto.setId(Long.parseLong(this.idProduto.getText().toString()));
        }else {
            return null;
        }

        if(this.editNomeProduto.getText().toString().isEmpty()== false){
            this.produto.setNome(this.editNomeProduto.getText().toString());
        }else {
            return null;}

        if(this.editQuantidadeProduto.getText().toString().isEmpty()==false){
           int quantidadeProduto= Integer.parseInt(this.editQuantidadeProduto.getText().toString());
            this.produto.setQuantidadeEmEstoque(quantidadeProduto);
        }else{
                return null;
    }
        if(this.editPrecoProduto.getText().toString().isEmpty()==false){
            Double precoProduto=Double.parseDouble(this.editPrecoProduto.getText().toString());
            this.produto.setPreco(precoProduto);
        }else

    {
        return null;
    }
    return  produto;
    }


}
