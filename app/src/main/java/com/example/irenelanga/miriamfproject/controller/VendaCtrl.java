package com.example.irenelanga.miriamfproject.controller;

import com.example.irenelanga.miriamfproject.DAO.VendaDAO;
import com.example.irenelanga.miriamfproject.dbHelper.ConexaoSQLite;
import com.example.irenelanga.miriamfproject.model.Vendas;

import java.util.List;

public class VendaCtrl {
    private final VendaDAO vendaDAO;
    public VendaCtrl(ConexaoSQLite pConexaoSQLite){
        vendaDAO=new VendaDAO(pConexaoSQLite);

    }

    public long salvarVendaCtrl(Vendas pVendas
    ){
        return vendaDAO.salvarVendaDAO(pVendas);
    }
    public boolean salvarItensVendasCtrl(Vendas pVendas){
        return vendaDAO.salvarItensDaVendaDAO(pVendas);
    }

    public List<Vendas> listarVendaCtrl(){

        return this.vendaDAO.listarVendaDAO();
    }
}
