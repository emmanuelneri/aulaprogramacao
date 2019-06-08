package br.com.facec.programacao2.repository.genericos;

import br.com.facec.programacao2.db.FabricaDeConexao;
import br.com.facec.programacao2.exceptions.TratamentoErrosPostgresSQL;
import br.com.facec.programacao2.model.Entidade;
import br.com.facec.programacao2.repository.mapeadores.Mapeador;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGenericoRepository<T extends Entidade> {

    protected T criar(T entidade, String sql, List<ParametroConsulta> parametros) {
        try(Connection conexao = FabricaDeConexao.criarConexao()) {

            String[] colunasParaRetornar = {"id"};

            PreparedStatement declaracaoPreparada
                    = conexao.prepareStatement(sql, colunasParaRetornar);

            for(ParametroConsulta parametro : parametros) {
                parametro.adicionarParametro(declaracaoPreparada);
            }

            declaracaoPreparada.execute();

            ResultSet colunasRetornadas = declaracaoPreparada.getGeneratedKeys();

            if(colunasRetornadas.next()) {
                entidade.setId(colunasRetornadas.getLong(1));
            }

        } catch (SQLException ex) {
            TratamentoErrosPostgresSQL.tratarAtualizacao(ex, nomeTabela());
        }

        return entidade;
    }

    protected T atualizar(T entidade, String sql, List<ParametroConsulta> parametros) {
        try(Connection conexao = FabricaDeConexao.criarConexao()) {

            PreparedStatement declaracaoPreparada
                    = conexao.prepareStatement(sql);

            for(ParametroConsulta parametro : parametros) {
                parametro.adicionarParametro(declaracaoPreparada);
            }

            declaracaoPreparada.execute();

        } catch (SQLException ex) {
            TratamentoErrosPostgresSQL.tratarAtualizacao(ex, nomeTabela());
        }

        return entidade;
    }

    protected List<T> buscarTodos(String sql) {
        List<T> entidades = new ArrayList<>();

        try(Connection conexao = FabricaDeConexao.criarConexao()) {
            PreparedStatement declaracaoPreparada =
                    conexao.prepareStatement(sql);

            ResultSet resultado = declaracaoPreparada
                    .executeQuery();

            while (resultado.next()) {
                entidades.add(getMapeador().mapear(resultado));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todos", e);
        }

        return entidades;
    }

    protected T buscarPorId(Long id, String sql) {
        try(Connection conexao = FabricaDeConexao.criarConexao()) {
            PreparedStatement declaracaoPreparada =
                    conexao.prepareStatement(sql);

            declaracaoPreparada.setLong(1, id);

            ResultSet resultado = declaracaoPreparada.executeQuery();

            while (resultado.next()) {
                return getMapeador().mapear(resultado);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar por id", e);
        }

        return null;
    }

    protected void deletar(Long id, String sql) {
        try(Connection conexao = FabricaDeConexao.criarConexao()) {
            PreparedStatement declaracaoPreparada =
                    conexao.prepareStatement(sql);

            declaracaoPreparada.setLong(1, id);
            declaracaoPreparada.execute();
        } catch (SQLException e) {
            TratamentoErrosPostgresSQL.tratarExclusao(e, nomeTabela());
        }
    }

    final class ParametroConsulta {

        private final int index;
        private final Object valor;

        ParametroConsulta(int index, Object valor) {
            this.index = index;
            this.valor = valor;
        }

        private void adicionarParametro(PreparedStatement declaracaoPreparada) throws SQLException {
            if(valor instanceof String) {
                declaracaoPreparada.setString(index, String.valueOf(valor));
                return;
            }

            if(valor instanceof Long) {
                declaracaoPreparada.setLong(index, (Long) valor);
                return;
            }

            if(valor instanceof LocalDateTime) {
                LocalDateTime valorLocalDateTime = (LocalDateTime) valor;
                declaracaoPreparada.setTimestamp(index, Timestamp.valueOf(valorLocalDateTime));
                return;
            }

            if(valor instanceof LocalDate) {
                LocalDate valorLocalDate = (LocalDate) valor;
                declaracaoPreparada.setDate(index, Date.valueOf(valorLocalDate));
                return;
            }

            if(valor == null) {
                ParameterMetaData parametro = declaracaoPreparada.getParameterMetaData();
                int tipoDoParametro = parametro.getParameterType(index);
                declaracaoPreparada.setNull(index, tipoDoParametro);
                return;
            }

            throw new RuntimeException("Tipo de parâmetro não suportado: " + valor.getClass());
        }
    }

    public abstract T criar(T entidade);
    public abstract T atualizar(T entidade);
    public abstract List<T> buscarTodos();
    public abstract T buscarPorId(Long id);
    public abstract void deletar(Long id);

    public abstract String nomeTabela();
    public abstract Mapeador<T> getMapeador();

}
