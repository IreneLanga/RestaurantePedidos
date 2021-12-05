package com.example.irenelanga.miriamfproject.DAO;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.irenelanga.miriamfproject.dbHelper.ConexaoSQLite;
import com.example.irenelanga.miriamfproject.model.ItemDoCarrinho;
import com.example.irenelanga.miriamfproject.model.Vendas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.LongToIntFunction;

public class VendaDAO {

    private final ConexaoSQLite conexaoSQLite;

    public VendaDAO(ConexaoSQLite conexaoSQLite) {


        this.conexaoSQLite = conexaoSQLite;
    }


    public long salvarVendaDAO(Vendas pVendas) {
        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();
        try {

            ContentValues values = new ContentValues();
            values.put("data", pVendas.getDataDaVenda().getTime());
            Long idVendaInserido = db.insert("venda", null, values);
            return idVendaInserido;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return 0;
    }

    public boolean salvarItensDaVendaDAO(Vendas pVendas) {
        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();
        try {

            ContentValues values = null;
            for (ItemDoCarrinho itemdaVenda : pVendas.getItensDaVenda()) {
                values = new ContentValues();
                values.put("quantidade_vendida", itemdaVenda.getQuantidadeSelecionado());
                values.put("id_produto", itemdaVenda.getIdProduto());
                values.put("id_venda", pVendas.getId());
                values.put("id_preco", itemdaVenda.getPrecoDoItemDaVenda());
                db.insert("item_da_venda", null, values);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return false;
    }


    public List<Vendas> listarVendaDAO() {
        List<Vendas> listVendas = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;

        String query =
                "SELECT " +
                "venda.id," +
                "venda.data," +
                "SUM(produto.precoProduto)," +
                "COUNT(produto.id) " +
                "FROM venda " +
                "INNER JOIN item_da_venda ON (venda.id=item_da_venda.id_produto) " +
                        "INNER JOIN produto ON (item_da_venda.id_produto=produto.id) "+
                        "GROUP BY venda.id;";




        try {

            db = this.conexaoSQLite.getReadableDatabase();
            cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {

                Vendas temVendas=null;
                do {
                    temVendas = new Vendas();
                    temVendas.setId(cursor.getLong(0));
                    temVendas.setDataDaVenda(new Date(cursor.getLong(1)));
                    temVendas.setPrecoTotalVenda(cursor.getDouble(2));
                    temVendas.setQteVendas(cursor.getInt(3));

                    listVendas.add(temVendas);

                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            Log.d("ERRO VENDAS", "Erro ao retornar vendas");
            return null;
        } finally {
            if (db != null) {
                db.close();
            }


            return listVendas;
        }

    }
}
