package br.com.facec.programacao2.repository.mapeadores;

import br.com.facec.programacao2.model.Atendimento;
import br.com.facec.programacao2.model.Cliente;
import br.com.facec.programacao2.model.Funcionario;
import br.com.facec.programacao2.model.StatusAtendimento;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class MapeadorAtendimento {

    private static final String COLUNA_ID = "id";
    private static final String COLUNA_DESCRICAO_PROBLEMA = "descricao_problema";
    private static final String COLUNA_DATA_HORA = "data_hora";
    private static final String COLUNA_STATUS = "status";

    private MapeadorAtendimento() {}

    public static Atendimento mapear(ResultSet resultado) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(resultado.getLong("id_cliente"));
        cliente.setNome(resultado.getString("nome_cliente"));

        Funcionario funcionario = new Funcionario();
        funcionario.setId(resultado.getLong("id_funcionario"));
        funcionario.setNome(resultado.getString("nome_funcionario"));

        Atendimento atendimento = new Atendimento();
        atendimento.setId(resultado.getLong("id_atendimento"));
        atendimento.setCliente(cliente);
        atendimento.setFuncionario(funcionario);
        atendimento.setDescricaoProblema(resultado.getString(COLUNA_DESCRICAO_PROBLEMA));
        atendimento.setDataHora(resultado.getTimestamp(COLUNA_DATA_HORA).toLocalDateTime());
        atendimento.setStatus(StatusAtendimento.valueOf(resultado.getString(COLUNA_STATUS)));
        return atendimento;
    }

    public static Atendimento mapear(ResultSet resultado, Cliente cliente, Funcionario funcionario) throws SQLException {
        Atendimento atendimento = new Atendimento();
        atendimento.setId(resultado.getLong(COLUNA_ID));
        atendimento.setCliente(cliente);
        atendimento.setFuncionario(funcionario);
        atendimento.setDescricaoProblema(resultado.getString(COLUNA_DESCRICAO_PROBLEMA));
        atendimento.setDataHora(resultado.getTimestamp(COLUNA_DATA_HORA).toLocalDateTime());
        atendimento.setStatus(StatusAtendimento.valueOf(resultado.getString(COLUNA_STATUS)));
        return atendimento;
    }

}
