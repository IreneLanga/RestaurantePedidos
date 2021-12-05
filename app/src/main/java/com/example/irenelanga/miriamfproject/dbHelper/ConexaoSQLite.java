package com.example.irenelanga.miriamfproject.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoSQLite extends SQLiteOpenHelper {
    private static ConexaoSQLite INSTANCIA_CONEXAO;
    private static final int VERSAO_DB=1;
    private static final String NOME_DB="mf_produtos";

    public ConexaoSQLite( Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    public static ConexaoSQLite getInstanciaConexao(Context context){
        if(INSTANCIA_CONEXAO==null){
            INSTANCIA_CONEXAO= new ConexaoSQLite(context);
        }
            return INSTANCIA_CONEXAO;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

            String sqlTabelaProduto="CREATE TABLE IF NOT EXISTS produto " +
                    "(" +
                "id INTEGER PRIMARY KEY," +"nome TEXT,"+ "quantidadeEmEstoque INTEGER,"+ "precoProduto REAL"+
                    ")";

            db.execSQL(sqlTabelaProduto);



            String sqlTabelaVenda="CREATE TABLE IF NOT EXISTS venda"+
                    "("+
                    "id INTEGER PRIMARY KEY,"+"data INTEGER"+
                            ")";



            db.execSQL(sqlTabelaVenda);

            String sqlTabelaItemDaVenda="CREATE TABLE IF NOT EXISTS item_da_venda"+
                    "("+
                    "id INTEGER PRIMARY KEY,"+"quantidade_vendida INTEGER,"+
                    "id_produto INTEGER, " +"id_venda INTEGER"+ "id_preco"+
                    ")";
            db.execSQL(sqlTabelaItemDaVenda);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
