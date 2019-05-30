package br.com.facec.programacao2.repository;

import br.com.facec.programacao2.model.Cliente;
import br.com.facec.programacao2.repository.mapeadores.MapeadorCliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository extends CRUDRepository {

    public Cliente criar(Cliente cliente) {
        Connection conexao = criarConexao();
        try {
            final String sql = "insert into cliente (nome) values (?)";

            String colunasParaRetornar[]= {"id"};
            PreparedStatement declaracaoPreparada =
                    conexao.prepareStatement(sql, colunasParaRetornar);

            declaracaoPreparada.setString(1, cliente.getNome());
            declaracaoPreparada.execute();

            ResultSet colunasRetornadas = declaracaoPreparada
                    .getGeneratedKeys();

            if(colunasRetornadas.next()) {
                cliente.setId(colunasRetornadas.getLong(1));
            }

            declaracaoPreparada.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao conectar ao banco de dados");
        }

        return cliente;
    }

    public void atualizar(Cliente cliente) {
        String sql = "update cliente set nome = ? where id = ?";

        Connection conexao = criarConexao();
        PreparedStatement declaracaoPreparada =
                criarDeclaracaoPreparada(conexao, sql);

        try {
            declaracaoPreparada.setString(1, cliente.getNome());
            declaracaoPreparada.setLong(2, cliente.getId());

            declaracaoPreparada.execute();

            declaracaoPreparada.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar Cliente", e);
        }
    }

    public List<Cliente> buscarTodos() {
        List<Cliente> clientes = new ArrayList<>();

        String sql = "select * from cliente";


        Connection conexao = criarConexao();
        PreparedStatement declaracaoPreparada =
                criarDeclaracaoPreparada(conexao, sql);

        try {
            ResultSet resultSet = declaracaoPreparada
                    .executeQuery();

            while (resultSet.next()) {
                clientes.add(MapeadorCliente.mapear(resultSet));
            }

            declaracaoPreparada.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente", e);
        }

        return clientes;
    }

    public Cliente buscarPorId(Long id) {
        String sql = "select * from cliente where id = ?";

        Connection conexao = criarConexao();
        PreparedStatement declaracaoPreparada =
                criarDeclaracaoPreparada(conexao, sql);

        try {
            declaracaoPreparada.setLong(1, id);

            ResultSet resultSet = declaracaoPreparada.executeQuery();

            while (resultSet.next()) {
                return MapeadorCliente.mapear(resultSet);
            }

            declaracaoPreparada.close();
            conexao.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por id", e);
        }

        return null;
    }

    public void deletar(Long id) {
        String sql = "delete from cliente where id = ?";

        Connection conexao = criarConexao();
        PreparedStatement declaracaoPreparada =
                criarDeclaracaoPreparada(conexao, sql);

        try {
            declaracaoPreparada.setLong(1, id);
            declaracaoPreparada.execute();

            declaracaoPreparada.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar cliente", e);
        }
    }
}
