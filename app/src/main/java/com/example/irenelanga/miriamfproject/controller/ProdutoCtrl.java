package com.example.irenelanga.miriamfproject.controller;

import com.example.irenelanga.miriamfproject.DAO.ProdutoDAO;
import com.example.irenelanga.miriamfproject.dbHelper.ConexaoSQLite;
import com.example.irenelanga.miriamfproject.model.Produto;

import java.util.List;

public class ProdutoCtrl {
    private final ProdutoDAO produtoDAO;

    public ProdutoCtrl(ConexaoSQLite pConexaoSQLite) {
        this.produtoDAO = new ProdutoDAO(pConexaoSQLite);
    }
    public long salvarProdutoCtrl(Produto pProduto){
       return this.produtoDAO.salvarProdutoDAO(pProduto);

    }
    public List<Produto> getListaProdutoCtrl(){
return this.produtoDAO.getListaDao();
    }
    public boolean excluirProdutoCtrl(long pIdProduto){
        return this.produtoDAO.excluirProdutoDao(pIdProduto);

        }

    public boolean atualizarProdutoCtrl(Produto pProduto){
        return this.produtoDAO.actualizarProdutoDAO(pProduto);
    }
}
