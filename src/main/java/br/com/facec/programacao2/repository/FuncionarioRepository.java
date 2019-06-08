package br.com.facec.programacao2.repository;

import br.com.facec.programacao2.db.FabricaDeConexao;
import br.com.facec.programacao2.exceptions.TratamentoErrosPostgresSQL;
import br.com.facec.programacao2.model.Funcionario;
import br.com.facec.programacao2.repository.mapeadores.MapeadorFuncionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository extends CRUDRepository<Funcionario> {

    @Override
    public Funcionario criar(Funcionario funcionario) {
       try(Connection conexao = FabricaDeConexao.criarConexao()) {

           String[] colunasParaRetornar = {"id"};
           String sql = "insert into funcionario (nome) values (?);";

           PreparedStatement declaracaoPreparada
                   = conexao.prepareStatement(sql, colunasParaRetornar);

           declaracaoPreparada.setString(1, funcionario.getNome());
           declaracaoPreparada.execute();

           ResultSet colunasRetornadas = declaracaoPreparada.getGeneratedKeys();

           if(colunasRetornadas.next()) {
               funcionario.setId(colunasRetornadas.getLong(1));
           }
       } catch (SQLException ex) {
           TratamentoErrosPostgresSQL.tratarAtualizacao(ex, "Funcionario");
       }

        return funcionario;
    }

    @Override
    public Funcionario atualizar(Funcionario funcionario) {
        try(Connection conexao = FabricaDeConexao.criarConexao()) {

            String sql = "update funcionario set nome = ? where id = ?";

            PreparedStatement declaracaoPreparada = conexao.prepareStatement(sql);

            declaracaoPreparada.setString(1, funcionario.getNome());
            declaracaoPreparada.setLong(2, funcionario.getId());
            declaracaoPreparada.execute();
        } catch (SQLException ex) {
            TratamentoErrosPostgresSQL.tratarAtualizacao(ex, "Funcionario");
        }

        return funcionario;
    }

    @Override
    public List<Funcionario> buscarTodos() {
        List<Funcionario> funcionarios = new ArrayList<>();

        try(Connection conexao = FabricaDeConexao.criarConexao()){

            String sql = "select * from funcionario";

            PreparedStatement declaracaoPreparada = conexao.prepareStatement(sql);

            ResultSet resultado = declaracaoPreparada.executeQuery();

            while (resultado.next()) {
                funcionarios.add(MapeadorFuncionario.build().mapear(resultado));
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar funcionários");
        }

        return funcionarios;
    }

    @Override
    public Funcionario buscarPorId(Long id) {
        try(Connection conexao = FabricaDeConexao.criarConexao()) {

            String sql = "select * from funcionario where id = ?";

            PreparedStatement declaracaoPreparada = conexao.prepareStatement(sql);
            declaracaoPreparada.setLong(1, id);

            ResultSet resultado = declaracaoPreparada.executeQuery();

            while (resultado.next()) {
                return MapeadorFuncionario.build().mapear(resultado);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar funcionário por id: " + id);
        }

        return null;
    }

    @Override
    public void deletar(Long id) {
        try(Connection conexao = FabricaDeConexao.criarConexao()) {

            String sql = "delete from funcionario where id = ?";
            PreparedStatement declaracaoPreparada = conexao.prepareStatement(sql);

            declaracaoPreparada.setLong(1, id);
            declaracaoPreparada.execute();

        } catch (SQLException ex) {
            TratamentoErrosPostgresSQL.tratarExclusao(ex, "Funcionario");

        }
    }
}
