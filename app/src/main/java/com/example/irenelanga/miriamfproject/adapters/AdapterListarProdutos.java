package com.example.irenelanga.miriamfproject.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.irenelanga.miriamfproject.R;
import com.example.irenelanga.miriamfproject.model.Produto;

import java.util.List;

public class AdapterListarProdutos extends BaseAdapter {
    private Context context;
    private List<Produto> produtoList;



    public AdapterListarProdutos(Context context, List<Produto> produtoList) {
        this.context = context;
        this.produtoList = produtoList;
    }

    @Override
    public int getCount() {
        return this.produtoList.size();
    }
    @Override

    public Object getItem(int posicao) {
        return this.produtoList.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return posicao;
    }

    public  void removerProduto(int posicao){
        this.produtoList.remove(posicao);
        notifyDataSetChanged();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v=View.inflate(this.context,R.layout.layout_produto,null);
        TextView tvNomeProduto=(TextView) v.findViewById(R.id.tvNomeProduto);
        TextView tvPreco=(TextView) v.findViewById(R.id.tvPrecoProduto);
        TextView tvEstoque=(TextView) v.findViewById(R.id.tvEsProdutoProduto);

        tvNomeProduto.setText(this.produtoList.get(position).getNome());
        tvPreco.setText(String.valueOf(this.produtoList.get(position).getPreco()));
        tvEstoque.setText(String.valueOf(this.produtoList.get(position).getQuantidadeEmEstoque()));



        return v;
    }

    public  void atualizar(List<Produto> pProdutos){
        this.produtoList.clear();
        this.produtoList=pProdutos;
        this.notifyDataSetChanged();

    }
}
