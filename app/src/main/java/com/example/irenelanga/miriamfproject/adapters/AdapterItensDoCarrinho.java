package com.example.irenelanga.miriamfproject.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.irenelanga.miriamfproject.R;
import com.example.irenelanga.miriamfproject.model.ItemDoCarrinho;
import com.example.irenelanga.miriamfproject.model.Produto;

import java.util.List;

public class AdapterItensDoCarrinho extends BaseAdapter {
    private Context context;
    private List<ItemDoCarrinho> itemDoCarrinhos;



    public AdapterItensDoCarrinho(Context context, List<ItemDoCarrinho> itemDoCarrinhos) {
        this.context = context;
        this.itemDoCarrinhos = itemDoCarrinhos;
    }

    @Override
    public int getCount() {
        return this.itemDoCarrinhos.size();
    }
    @Override

    public Object getItem(int posicao) {
        return this.itemDoCarrinhos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return posicao;
    }

    public  boolean removerItenDoCarrinho(int posicao){
        this.itemDoCarrinhos.remove(posicao);
        notifyDataSetChanged();
        return  true;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v=View.inflate(this.context,R.layout.layout_carinho_produtos,null);

        TextView tvNomeProduto=(TextView) v.findViewById(R.id.tvNomeProduto);
        TextView tvPreco=(TextView) v.findViewById(R.id.tvPrecoProduto);
        TextView tvQuantidadeSelecionada=(TextView) v.findViewById(R.id.tvQteProduto);
        TextView tvValorIten=(TextView)v.findViewById(R.id.tvValorTotalItem);

        tvNomeProduto.setText(this.itemDoCarrinhos.get(position).getNome());
        tvPreco.setText(String.valueOf(this.itemDoCarrinhos.get(position).getPrecoProduto()));
        tvQuantidadeSelecionada.setText(String.valueOf(this.itemDoCarrinhos.get(position).getQuantidadeSelecionado()));
        tvValorIten.setText(String.valueOf(this.itemDoCarrinhos.get(position).getPrecoDoItemDaVenda()));



        return v;
    }

    public void addItemDoCarrinho(ItemDoCarrinho pItemDoCarrinho){
        this.itemDoCarrinhos.add(pItemDoCarrinho);
        this.notifyDataSetChanged();
    }

    public  void atualizar(List<ItemDoCarrinho> pItenDoCarrinho){
        this.itemDoCarrinhos.clear();

        this.itemDoCarrinhos=pItenDoCarrinho;
        this.notifyDataSetChanged();

    }
}
