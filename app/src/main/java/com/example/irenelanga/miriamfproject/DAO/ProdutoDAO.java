package com.example.irenelanga.miriamfproject.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.irenelanga.miriamfproject.model.Produto;
import com.example.irenelanga.miriamfproject.dbHelper.ConexaoSQLite;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private final ConexaoSQLite conexaoSQLite;

    public ProdutoDAO(ConexaoSQLite conexaoSQLite) {
        this.conexaoSQLite = conexaoSQLite;
    }


    public long salvarProdutoDAO(Produto pProduto) {
        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("id", pProduto.getId());
            values.put("nome",pProduto.getNome());
            values.put("quantidadeEmEstoque", pProduto.getQuantidadeEmEstoque());
            values.put("precoProduto", pProduto.getPreco());
            long idProdutoInserido = db.insert("produto", null, values);

            return idProdutoInserido;


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return 0;

    }

    public List<Produto> getListaDao() {
        List<Produto> listaProdutos = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;
        String query = "SELECT * FROM produto;";

        try {
            db = this.conexaoSQLite.getReadableDatabase();
            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {

                Produto produtoTemporario = null;
                do {

                    produtoTemporario = new Produto();
                    produtoTemporario.setId(cursor.getLong(0));
                    produtoTemporario.setNome(cursor.getString(1));
                    produtoTemporario.setQuantidadeEmEstoque(cursor.getInt(2));
                    produtoTemporario.setPreco(cursor.getInt(3));
                    listaProdutos.add(produtoTemporario);


                } while (cursor.moveToNext());

            }
        } catch (Exception e) {
            Log.d("Erro listar Produtos", "Erro ao retornar os produtos");
            return null;
        } finally {
            if (db != null) {
                db.close();
            }

        }
        return listaProdutos;
    }

    public boolean excluirProdutoDao(long pIdProduto) {
        SQLiteDatabase db = null;
        try {
            db = this.conexaoSQLite.getWritableDatabase();
            db.delete(
                    "produto", "id=?", new String[]{String.valueOf(pIdProduto)}
            );


        } catch (Exception e) {
            Log.d("PRODUTODAO", "Nao foi possivel deletar o produto");
        } finally {
            if (db != null) {
                db.close();
            }

        }
        return true;
    }

    public boolean actualizarProdutoDAO(Produto pProduto) {
        SQLiteDatabase db = null;

        try {
    db=this.conexaoSQLite.getWritableDatabase();
    ContentValues produtoAtributos=new ContentValues();
    produtoAtributos.put("id",pProduto.getId());
    produtoAtributos.put("nome",pProduto.getNome());
    produtoAtributos.put("quandidadeEmEstoque",pProduto.getQuantidadeEmEstoque());
    produtoAtributos.put("precoProduto",pProduto.getPreco());

    int atualizou=db.update("produto",
            produtoAtributos, "id=?",new String[]{String.valueOf(pProduto.getId())}
            );

    if (atualizou>0){



        return true;
    }
        } catch (Exception e) {
            Log.d("PRODUTODAO", "Nao foi possivel Actualizar o Produto");
            return false;
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return  false;
    }
}