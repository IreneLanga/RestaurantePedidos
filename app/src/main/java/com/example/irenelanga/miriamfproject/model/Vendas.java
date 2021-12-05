package com.example.irenelanga.miriamfproject.model;

import java.util.Date;
import java.util.List;

public class Vendas {
    private long id;
    private List<ItemDoCarrinho> itensDaVenda;
    private Date DataDaVenda;
    private double precoTotalVenda;

    public int getQteVendas() {
        return QteVendas;
    }

    public void setQteVendas(int qteVendas) {
        QteVendas = qteVendas;
    }

    private int QteVendas;

    public double getPrecoTotalVenda() {
        return precoTotalVenda;
    }

    public void setPrecoTotalVenda(double precoTotalVenda) {
        this.precoTotalVenda = precoTotalVenda;
    }



    public List<ItemDoCarrinho> getItensDaVenda() {
        return itensDaVenda;
    }

    public void setItensDaVenda(List<ItemDoCarrinho> itensDaVenda) {
        this.itensDaVenda = itensDaVenda;
    }

    public Vendas() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Date getDataDaVenda() {
        return DataDaVenda;
    }

    public void setDataDaVenda(Date dataDaVenda) {
        DataDaVenda = dataDaVenda;
    }

    @Override
    public String toString() {
        return "Vendas{" +
                "id=" + id +
                ", nomeDoCliente='" + '\'' +
                ", DataDaVenda=" + DataDaVenda +
                '}';
    }
}
