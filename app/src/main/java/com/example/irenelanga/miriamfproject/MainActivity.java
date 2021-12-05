package com.example.irenelanga.miriamfproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.irenelanga.miriamfproject.activities.ActivityProduto;
import com.example.irenelanga.miriamfproject.activities.ActivityVenda;
import com.example.irenelanga.miriamfproject.activities.ListarProdutosActivity;
import com.example.irenelanga.miriamfproject.activities.VendasConsolidadas;
import com.example.irenelanga.miriamfproject.controller.ProdutoCtrl;
import com.example.irenelanga.miriamfproject.dbHelper.ConexaoSQLite;
import com.example.irenelanga.miriamfproject.model.Produto;

import java.util.List;

public class MainActivity extends AppCompatActivity {

        private Button btnCadastro;
        private  Button btnListarProduto;
        private  Button btnVender;
        private Button btnMinhasVendas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexaoSQLite conexaoSQLite= ConexaoSQLite.getInstanciaConexao(this);



        this.btnCadastro=(Button) findViewById(R.id.btnCadastroProduto);
        this.btnListarProduto=(Button)findViewById(R.id.btnListarProduto);
        this.btnVender=(Button)findViewById(R.id.btnVender);
        this.btnMinhasVendas=(Button)findViewById(R.id.btnMinhasVendas);

        this.btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               Intent intent=new Intent(MainActivity.this,ActivityProduto.class);
                startActivity(intent);
            }
        });
        this.btnListarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(MainActivity.this,ListarProdutosActivity.class);
                startActivity(intent);
            }
        });



        this.btnVender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ActivityVenda.class);
                startActivity(intent);

            }
        });

        this.btnMinhasVendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,VendasConsolidadas.class);
                startActivity(intent);

            }
        });
    }


}
