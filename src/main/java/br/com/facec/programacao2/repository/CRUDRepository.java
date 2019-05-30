package br.com.facec.programacao2.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class CRUDRepository {

    private final static String URL = "jdbc:postgresql://localhost:5432/aulaprogramacao";
    private final static String USUARIO = "postgres";
    private final static String SENHA = "postgres";

    protected Connection criarConexao() {
        try {
            return DriverManager
                    .getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao conectar ao banco de dados");
        }
    }

    protected PreparedStatement criarDeclaracaoPreparada(Connection conexao, String sql) {
        try {
            return conexao.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erro criar a declaracao preparada ao banco de dados", e);
        }
    }
}
