package br.com.facec.programacao2.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDeConexao {

    private final static String URL = "jdbc:postgresql://localhost:5432/aulaprogramacao";
    private final static String USUARIO = "postgres";
    private final static String SENHA = "postgres";

    public static Connection criarConexao() {
        try {
            return DriverManager
                    .getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao conectar ao banco de dados");
        }
    }

}
