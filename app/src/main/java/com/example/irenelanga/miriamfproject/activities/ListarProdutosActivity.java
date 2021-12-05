package com.example.irenelanga.miriamfproject.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.irenelanga.miriamfproject.R;
import com.example.irenelanga.miriamfproject.adapters.AdapterListarProdutos;
import com.example.irenelanga.miriamfproject.controller.ProdutoCtrl;
import com.example.irenelanga.miriamfproject.dbHelper.ConexaoSQLite;
import com.example.irenelanga.miriamfproject.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ListarProdutosActivity extends AppCompatActivity {
    private ListView lsvProdutos;
    private List<Produto> produtoList;
    private AdapterListarProdutos adapterListarProdutos;
    private  ProdutoCtrl produtoCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produtos);


      this.produtoCtrl=new ProdutoCtrl(ConexaoSQLite.getInstanciaConexao(ListarProdutosActivity.this));
      produtoList=  produtoCtrl.getListaProdutoCtrl();

        this.produtoList=new ArrayList<>();
        this.lsvProdutos=(ListView) findViewById(R.id.listViewProdutos);
        this.adapterListarProdutos=new AdapterListarProdutos(ListarProdutosActivity.this,this.produtoList);
        this.lsvProdutos.setAdapter(this.adapterListarProdutos);

        this.lsvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final Produto produtoSelecionadao=(Produto)adapterListarProdutos.getItem(position);

                AlertDialog.Builder janelaDialogo=new AlertDialog.Builder(ListarProdutosActivity.this);
                janelaDialogo.setTitle("Escola:").setMessage("O que  deseja fazer com o item selecionado");
                janelaDialogo.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
                janelaDialogo.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                           boolean excluiu= produtoCtrl.excluirProdutoCtrl(produtoSelecionadao.getId());

                        dialog.cancel();


                           if(excluiu==true){
                               adapterListarProdutos.removerProduto(position);
                               Toast.makeText(ListarProdutosActivity.this,"Produto excluido com Sucesso",Toast.LENGTH_LONG).show();
                           }else {
                               Toast.makeText(ListarProdutosActivity.this,"Nao foi posssivel excluir o produto",Toast.LENGTH_LONG).show();

                           }


                    }
                });
                janelaDialogo.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle bundleDadosProduto=new Bundle();
                        bundleDadosProduto.putLong("id_produto",produtoSelecionadao.getId());
                        bundleDadosProduto.putString("nome_produto", produtoSelecionadao.getNome());
                        bundleDadosProduto.putDouble("preco_produto",produtoSelecionadao.getPreco());
                        bundleDadosProduto.putInt("quantidade_produto",produtoSelecionadao.getQuantidadeEmEstoque());

                        Intent intentEditarProdutos=new Intent(ListarProdutosActivity.this, EditarProdutos.class);
                        intentEditarProdutos.putExtras(bundleDadosProduto);
                        startActivity(intentEditarProdutos);


                    }
                });
                janelaDialogo.create().show();

            }
        });


    }


    public  void eventAtualizarProdutos(View view){

        this.adapterListarProdutos.atualizar(this.produtoCtrl.getListaProdutoCtrl());
    }
}
