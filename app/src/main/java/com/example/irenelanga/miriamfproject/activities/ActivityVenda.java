package com.example.irenelanga.miriamfproject.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.irenelanga.miriamfproject.R;
import com.example.irenelanga.miriamfproject.adapters.AdapterItensDoCarrinho;
import com.example.irenelanga.miriamfproject.controller.ProdutoCtrl;
import com.example.irenelanga.miriamfproject.controller.VendaCtrl;
import com.example.irenelanga.miriamfproject.dbHelper.ConexaoSQLite;
import com.example.irenelanga.miriamfproject.model.ItemDoCarrinho;
import com.example.irenelanga.miriamfproject.model.Produto;
import com.example.irenelanga.miriamfproject.model.Vendas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityVenda extends AppCompatActivity {
    private Spinner spnProdutos;
    private List<Produto> listaProduto;
    private ProdutoCtrl produtoCtrl;
    private EditText quantidadeIten;
    private TextView tvTotalVenda;
  //  private EditText nomeCliente;
    private ListView lsvCarrinhoCompras;
    private List<ItemDoCarrinho> listaDeitensDoCarrinho;
    private AdapterItensDoCarrinho adpItenDoCarrinho;

    private VendaCtrl vendaCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda);

        this.produtoCtrl=new ProdutoCtrl(ConexaoSQLite.getInstanciaConexao(this));
        this.listaProduto=this.produtoCtrl.getListaProdutoCtrl();

        this.vendaCtrl=new VendaCtrl(ConexaoSQLite.getInstanciaConexao(this));



        this.spnProdutos=(Spinner)findViewById(R.id.spnProduto);
        ArrayAdapter<Produto>spnProdutoAdapter=new ArrayAdapter<Produto>
                (this, android.R.layout.simple_spinner_item,this.listaProduto);
        this.spnProdutos.setAdapter(spnProdutoAdapter);
       // this.nomeCliente=(EditText)this.findViewById(R.id.edtNomeCliente);
        this.quantidadeIten=(EditText)this.findViewById(R.id.editQuantidadeProduto);
        this.tvTotalVenda=(TextView) this.findViewById(R.id.tvTotalVenda);


        this.lsvCarrinhoCompras=(ListView)this.findViewById(R.id.lsvProduto);
        this.listaDeitensDoCarrinho=new ArrayList<>();
        this.adpItenDoCarrinho=new AdapterItensDoCarrinho(ActivityVenda.this,this.listaDeitensDoCarrinho);
        this.lsvCarrinhoCompras.setAdapter(this.adpItenDoCarrinho);


        this.lsvCarrinhoCompras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final ItemDoCarrinho itemDoCarrinho=(ItemDoCarrinho)adpItenDoCarrinho.getItem(position);
                AlertDialog.Builder janelaDeEscolha=new AlertDialog.Builder(ActivityVenda.this);
                janelaDeEscolha.setTitle("Escolha:");
                janelaDeEscolha.setMessage("Deseja remover o item "+ itemDoCarrinho.getNome()+"?");

                janelaDeEscolha.setNegativeButton("Não",null);
                janelaDeEscolha.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        boolean excluiu=false;
                        excluiu=adpItenDoCarrinho.removerItenDoCarrinho(position);
                        double totalVenda=calcularTotalVenda(listaDeitensDoCarrinho);
                        atualizarValorTotalVenda(totalVenda);

                        if(!excluiu){
                            Toast.makeText(ActivityVenda.this,"Erro ao excluir item do carrinho",Toast.LENGTH_LONG).show();

                        }


                    }
                });
                janelaDeEscolha.create().show();

            }
        });

    }
    public void eventFecharVenda(View view) {
        Vendas vendaFezchada=this.criarVenda();


        if(  this.salvarVenda(vendaFezchada)==true){
            Toast.makeText(this, "venda fechada com sucesso", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Erro ao fechar a venda", Toast.LENGTH_LONG).show();
        }
    }
    public  void eventAddProduto(View view){

        ItemDoCarrinho itemDoCarrinho=new ItemDoCarrinho();
        Produto produtoSelecionado=(Produto)this.spnProdutos.getSelectedItem();
      int quantidadeProduto=0;
      if(this.quantidadeIten.getText().toString().equals("")){
          quantidadeProduto=1;
      }else {

          quantidadeProduto = Integer.parseInt(this.quantidadeIten.getText().toString());
      }

      itemDoCarrinho.setNome(produtoSelecionado.getNome());
      itemDoCarrinho.setQuantidadeSelecionado(quantidadeProduto);
      itemDoCarrinho.setPrecoProduto(produtoSelecionado.getPreco());
      itemDoCarrinho.setPrecoDoItemDaVenda(itemDoCarrinho.getPrecoDoItemDaVenda()*itemDoCarrinho.getQuantidadeSelecionado());
      itemDoCarrinho.setIdProduto(produtoSelecionado.getId());

      this.adpItenDoCarrinho.addItemDoCarrinho(itemDoCarrinho);
      double totalVenda=this.calcularTotalVenda(this.listaDeitensDoCarrinho);
      this.atualizarValorTotalVenda(totalVenda);


    }



    private  double calcularTotalVenda(List<ItemDoCarrinho> pListaItensDoCarrinho){

        double totalVenda=0.0d;
        for(ItemDoCarrinho itemDoCarrinho:pListaItensDoCarrinho){
            totalVenda+=itemDoCarrinho.getPrecoDoItemDaVenda();
            Log.d("PRODUTOS",itemDoCarrinho.toString());

        }
        return  totalVenda;

    }
    private void atualizarValorTotalVenda(double pValorTotal){
        this.tvTotalVenda.setText(String.valueOf(pValorTotal));

    }
    private Vendas criarVenda(){
        Vendas vendas=new Vendas();
        vendas.setDataDaVenda(new Date());

        vendas.setItensDaVenda(this.listaDeitensDoCarrinho);

        return vendas;

    }

    private boolean salvarVenda(Vendas pVendas){
       Long idVenda=vendaCtrl.salvarVendaCtrl(pVendas);

        if(idVenda>0){
            pVendas.setId(idVenda);
            if(vendaCtrl.salvarItensVendasCtrl(pVendas)){
                Toast.makeText(this, "Venda realizada", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Venda nao foi realizada,tente novamente", Toast.LENGTH_SHORT).show();
            }

            return true;
        }
        return false;
    }


}
