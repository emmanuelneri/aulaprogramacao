package br.com.facec.programacao2.repository.mapeadores;

import br.com.facec.programacao2.model.Cliente;
import br.com.facec.programacao2.model.Funcionario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeadorFuncionario {

    private static final String COLUNA_ID = "id";
    private static final String COLUNA_NOME = "nome";

    private MapeadorFuncionario() {}

    public static Funcionario mapear(ResultSet resultado) throws SQLException {
        final Funcionario funcionario = new Funcionario();
        funcionario.setId(resultado.getLong(COLUNA_ID));
        funcionario.setNome(resultado.getString(COLUNA_NOME));
        return funcionario;
    }

}
