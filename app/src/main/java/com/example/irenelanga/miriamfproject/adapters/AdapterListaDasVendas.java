package com.example.irenelanga.miriamfproject.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.irenelanga.miriamfproject.R;
import com.example.irenelanga.miriamfproject.model.Produto;
import com.example.irenelanga.miriamfproject.model.Vendas;

import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterListaDasVendas extends BaseAdapter {
    private Context context;
    private List<Vendas> vendasList;

        private SimpleDateFormat simpleDateFormat;

    public AdapterListaDasVendas(Context context, List<Vendas> vendasList) {
        this.context = context;
        this.vendasList =vendasList;
        this.simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public int getCount() {
        return this.vendasList.size();
    }
    @Override

    public Object getItem(int posicao) {
        return this.vendasList.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return posicao;
    }

    public  void removerProduto(int posicao){
        this.vendasList.remove(posicao);
        notifyDataSetChanged();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v=View.inflate(this.context,R.layout.layout_minhas_vendas,null);
        TextView tvDataVenda=(TextView) v.findViewById(R.id.tvDataVenda);
        TextView tvPrecoTotal=(TextView) v.findViewById(R.id.tvTotalVenda);
        TextView tvQteItens=(TextView) v.findViewById(R.id.tvQteItens);

        tvDataVenda.setText(this.simpleDateFormat.format(this.vendasList.get(position).getDataDaVenda()));

        tvPrecoTotal.setText(String.valueOf(this.vendasList.get(position).getPrecoTotalVenda()));
        tvQteItens.setText(String.valueOf(this.vendasList.get(position).getQteVendas()));



        return v;
    }

    public  void atualizar(List<Vendas> pVendas){
        this.vendasList.clear();
        this.vendasList=pVendas;
        this.notifyDataSetChanged();

    }
}
