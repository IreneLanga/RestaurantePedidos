package com.example.irenelanga.miriamfproject.model;

public class ItemDoCarrinho {
    private long id;
    private String nome;
    private int quantidadeSelecionado;
    private long idProduto;
    private double precoProduto;
    private  double precoDoItemDaVenda;

    public double getPrecoDoItemDaVenda() {
        return precoDoItemDaVenda;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeSelecionado() {
        return quantidadeSelecionado;
    }

    @Override
    public String toString() {
        return "ItemDoCarrinho{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", quantidadeSelecionado=" + quantidadeSelecionado +
                ", idProduto=" + idProduto +
                ", precoProduto=" + precoProduto +
                ", precoDoItemDaVenda=" + precoDoItemDaVenda +
                '}';
    }

    public void setQuantidadeSelecionado(int quantidadeSelecionado) {
        this.quantidadeSelecionado = quantidadeSelecionado;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }



    public void setPrecoDoItemDaVenda(double  precoDoItemDaVenda) {
        this.precoDoItemDaVenda =  precoDoItemDaVenda;
    }
}
