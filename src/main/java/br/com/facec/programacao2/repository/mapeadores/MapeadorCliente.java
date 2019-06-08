package br.com.facec.programacao2.repository.mapeadores;

import br.com.facec.programacao2.model.Cliente;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class MapeadorCliente implements Mapeador<Cliente> {

    private static final String COLUNA_ID = "id";
    private static final String COLUNA_NOME = "nome";

    private MapeadorCliente() {}

    public static MapeadorCliente build() {
        return new MapeadorCliente();
    }

    @Override
    public Cliente mapear(ResultSet resultSet) throws SQLException {
        final Cliente cliente = new Cliente();
        cliente.setId(resultSet.getLong(COLUNA_ID));
        cliente.setNome(resultSet.getString(COLUNA_NOME));
        return cliente;
    }

}
