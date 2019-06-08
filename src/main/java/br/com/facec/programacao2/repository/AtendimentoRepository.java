package br.com.facec.programacao2.repository;

import br.com.facec.programacao2.db.FabricaDeConexao;
import br.com.facec.programacao2.exceptions.TratamentoErrosPostgresSQL;
import br.com.facec.programacao2.model.Atendimento;
import br.com.facec.programacao2.model.Cliente;
import br.com.facec.programacao2.model.Funcionario;
import br.com.facec.programacao2.repository.mapeadores.MapeadorAtendimento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoRepository extends CRUDRepository<Atendimento> {

    private ClienteRepository clienteRepository = new ClienteRepository();
    private FuncionarioRepository funcionarioRepository = new FuncionarioRepository();

    @Override
    public Atendimento criar(Atendimento atendimento) {
        try(Connection conexao = FabricaDeConexao.criarConexao()) {

            String[] colunasParaRetornar = {"id"};
            String sql = "insert into atendimento (id_cliente, id_funcionario, data_hora, descricao_problema, status) values (?, ?, ?, ?, ?);";

            PreparedStatement declaracaoPreparada
                    = conexao.prepareStatement(sql, colunasParaRetornar);

            declaracaoPreparada.setLong(1, atendimento.getCliente().getId());
            declaracaoPreparada.setLong(2, atendimento.getFuncionario().getId());
            declaracaoPreparada.setTimestamp(3, Timestamp.valueOf(atendimento.getDataHora()));
            declaracaoPreparada.setString(4, atendimento.getDescricaoProblema());
            declaracaoPreparada.setString(5, atendimento.getStatus().name());
            declaracaoPreparada.execute();

            ResultSet colunasRetornadas = declaracaoPreparada.getGeneratedKeys();

            if(colunasRetornadas.next()) {
                atendimento.setId(colunasRetornadas.getLong(1));
            }
        } catch (SQLException ex) {
            TratamentoErrosPostgresSQL.tratarAtualizacao(ex, "Atendimento");
        }

        return atendimento;
    }

    @Override
    public Atendimento atualizar(Atendimento atendimento) {
        try(Connection conexao = FabricaDeConexao.criarConexao()) {

            String sql = "update atendimento set id_cliente = ?, id_funcionario = ?, data_hora = ?, descricao_problema = ?, status =? where id = ?";

            PreparedStatement declaracaoPreparada
                    = conexao.prepareStatement(sql);

            declaracaoPreparada.setLong(1, atendimento.getCliente().getId());
            declaracaoPreparada.setLong(2, atendimento.getFuncionario().getId());
            declaracaoPreparada.setTimestamp(3, Timestamp.valueOf(atendimento.getDataHora()));
            declaracaoPreparada.setString(4, atendimento.getDescricaoProblema());
            declaracaoPreparada.setString(5, atendimento.getStatus().name());
            declaracaoPreparada.setLong(6, atendimento.getId());
            declaracaoPreparada.execute();

        } catch (SQLException ex) {
            TratamentoErrosPostgresSQL.tratarAtualizacao(ex, "Atendimento");
        }

        return atendimento;
    }

    @Override
    public List<Atendimento> buscarTodos() {
        List<Atendimento> atendimentos = new ArrayList<>();

        try(Connection conexao = FabricaDeConexao.criarConexao()) {
            String sql = "select a.id as id_atendimento, a.data_hora, a.descricao_problema, a.status, " +
                    " c.id as id_cliente, c.nome nome_cliente, " +
                    " f.id as id_funcionario, f.nome nome_funcionario " +
                    " from atendimento a " +
                    " inner join cliente c on c.id = a.id_cliente" +
                    " inner join funcionario f on f.id = a.id_funcionario";

            PreparedStatement declaracaoPreparada =
                    conexao.prepareStatement(sql);

            ResultSet resultado = declaracaoPreparada
                    .executeQuery();

            while (resultado.next()) {
                atendimentos.add(MapeadorAtendimento.build().mapear(resultado));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar atendimento", e);
        }

        return atendimentos;
    }

    @Override
    public Atendimento buscarPorId(Long id) {
        try(Connection conexao = FabricaDeConexao.criarConexao()) {
            String sql = "select * from atendimento where id = ?";

            PreparedStatement declaracaoPreparada =
                    conexao.prepareStatement(sql);

            declaracaoPreparada.setLong(1, id);

            ResultSet resultado = declaracaoPreparada.executeQuery();

            while (resultado.next()) {
                Cliente cliente = clienteRepository.buscarPorId(resultado.getLong("id_cliente"));
                Funcionario funcionario = funcionarioRepository.buscarPorId(resultado.getLong("id_funcionario"));
                return MapeadorAtendimento.mapear(resultado, cliente, funcionario);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por id", e);
        }

        return null;
    }

    @Override
    public void deletar(Long id) {
        try(Connection conexao = FabricaDeConexao.criarConexao()) {
            String sql = "delete from atendimento where id = ?";

            PreparedStatement declaracaoPreparada =
                    conexao.prepareStatement(sql);

            declaracaoPreparada.setLong(1, id);
            declaracaoPreparada.execute();
        } catch (SQLException e) {
            TratamentoErrosPostgresSQL.tratarExclusao(e, "Atendimento");
        }
    }
}
