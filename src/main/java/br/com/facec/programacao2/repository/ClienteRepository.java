package br.com.facec.programacao2.repository;

import br.com.facec.programacao2.db.FabricaDeConexao;
import br.com.facec.programacao2.exceptions.TratamentoCodigosPostgresSQL;
import br.com.facec.programacao2.model.Cliente;
import br.com.facec.programacao2.repository.mapeadores.MapeadorCliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository extends CRUDRepository<Cliente> {

    @Override
    public Cliente criar(Cliente cliente) {
        try(final Connection conexao = FabricaDeConexao.criarConexao()) {
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
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }

        return cliente;
    }

    @Override
    public Cliente atualizar(Cliente cliente) {
        try(Connection conexao = FabricaDeConexao.criarConexao()) {
            String sql = "update cliente set nome = ? where id = ?";

            PreparedStatement declaracaoPreparada =
                    conexao.prepareStatement(sql);

            declaracaoPreparada.setString(1, cliente.getNome());
            declaracaoPreparada.setLong(2, cliente.getId());

            declaracaoPreparada.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar Cliente", e);
        }

        return cliente;
    }

    @Override
    public List<Cliente> buscarTodos() {
        List<Cliente> clientes = new ArrayList<>();

        String sql = "select * from cliente";

        try(Connection conexao = FabricaDeConexao.criarConexao()) {
            PreparedStatement declaracaoPreparada =
                conexao.prepareStatement(sql);

            ResultSet resultado = declaracaoPreparada
                    .executeQuery();

            while (resultado.next()) {
                clientes.add(MapeadorCliente.mapear(resultado));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente", e);
        }

        return clientes;
    }

    @Override
    public Cliente buscarPorId(Long id) {
        try(Connection conexao = FabricaDeConexao.criarConexao()) {
            String sql = "select * from cliente where id = ?";

            PreparedStatement declaracaoPreparada =
                       conexao.prepareStatement(sql);

            declaracaoPreparada.setLong(1, id);

            ResultSet resultado = declaracaoPreparada.executeQuery();

            while (resultado.next()) {
                return MapeadorCliente.mapear(resultado);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por id", e);
        }

        return null;
    }

    @Override
    public void deletar(Long id) {
        try(Connection conexao = FabricaDeConexao.criarConexao()) {
            String sql = "delete from cliente where id = ?";

            PreparedStatement declaracaoPreparada =
                    conexao.prepareStatement(sql);

            declaracaoPreparada.setLong(1, id);
            declaracaoPreparada.execute();
        } catch (SQLException e) {
            TratamentoCodigosPostgresSQL.tratar(e, "Cliente");
        }
    }
}
