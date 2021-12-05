package com.example.irenelanga.miriamfproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.irenelanga.miriamfproject.R;
import com.example.irenelanga.miriamfproject.adapters.AdapterListaDasVendas;
import com.example.irenelanga.miriamfproject.controller.VendaCtrl;
import com.example.irenelanga.miriamfproject.dbHelper.ConexaoSQLite;
import com.example.irenelanga.miriamfproject.model.Vendas;

import java.util.List;

public class VendasConsolidadas extends AppCompatActivity {
    private ListView listView;
    private List<Vendas>vendasFeitasList;
    private AdapterListaDasVendas adapterListaDasVendas;
    private VendaCtrl  vendaCtrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendas_consolidadas);


        this.vendaCtrl=new VendaCtrl(ConexaoSQLite.getInstanciaConexao(VendasConsolidadas.this));
        vendasFeitasList=this.vendaCtrl.listarVendaCtrl();
        listView=(ListView)findViewById(R.id.lsvMinhasVendas);
        this.adapterListaDasVendas=new AdapterListaDasVendas(VendasConsolidadas.this,vendasFeitasList);
        this.listView.setAdapter(adapterListaDasVendas);
    }
}
