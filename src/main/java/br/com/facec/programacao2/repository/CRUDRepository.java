package br.com.facec.programacao2.repository;

import br.com.facec.programacao2.model.Entidade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class CRUDRepository<T extends Entidade> {

    protected PreparedStatement criarDeclaracaoPreparada(Connection conexao, String sql) {
        try {
            return conexao.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erro criar a declaracao preparada ao banco de dados", e);
        }
    }

    public abstract T criar(T entidade);
    public abstract T atualizar(T entidade);
    public abstract List<T> buscarTodos();
    public abstract T buscarPorId(Long id);
    public abstract void deletar(Long id);
}
