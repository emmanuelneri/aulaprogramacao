package br.com.facec.programacao2.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InicializacaoRepository extends CRUDRepository {

    public void criarTabelaCliente() {
        String tabelaClienteSql = "create table if not exists cliente(" +
                "id bigserial primary key," +
                "nome varchar(40) not null" +
                ");";

        criarTabela(tabelaClienteSql);
    }

    private void criarTabela(final String sql) {
        Connection conexao = criarConexao();

        try {
            PreparedStatement declaracaoPreparada
                    = conexao.prepareStatement(sql);

            declaracaoPreparada.execute();
            declaracaoPreparada.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela", e);
        }
    }

}
