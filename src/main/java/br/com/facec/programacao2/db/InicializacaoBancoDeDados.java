package br.com.facec.programacao2.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InicializacaoBancoDeDados {

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

    public void criarTabelaAtendimento() {
        String sql = "create table if not exists atendimento(" +
                "id bigserial primary key," +
                "id_cliente bigint not null," +
                "id_funcionario bigint not null," +
                "data_hora timestamp not null," +
                "descricao_problema varchar(100)," +
                "status varchar(50) not null," +
                " CONSTRAINT atendimento_id_cliente_fk FOREIGN KEY (id_cliente) REFERENCES cliente(id)," +
                " CONSTRAINT atendimento_id_funcionario_fk FOREIGN KEY (id_funcionario) REFERENCES funcionario(id)" +
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
