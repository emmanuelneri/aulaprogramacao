package br.com.facec.programacao2.repository;

import br.com.facec.programacao2.db.FabricaDeConexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InicializacaoRepository {

    public void criarTabelaCliente() {
        String sql = "create table if not exists cliente(" +
                "id bigserial primary key," +
                "nome varchar(40) not null" +
                ");";

        criarTabela(sql);
    }

    public void criarTabelaFuncionario() {
        String sql = "create table if not exists funcionario(" +
                "id bigserial primary key," +
                "nome varchar(40) not null" +
                ");";

        criarTabela(sql);
    }

    private void criarTabela(final String sql) {
        Connection conexao = FabricaDeConexao.criarConexao();

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
