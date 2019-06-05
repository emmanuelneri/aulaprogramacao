package br.com.facec.programacao2.exceptions;

import java.sql.SQLException;

public final class TratamentoCodigosPostgresSQL {

    private static final String ERRO_FK = "23503";

    private TratamentoCodigosPostgresSQL() {}

    public static void tratar(SQLException sqlEx, String nomeTabela) {
        if(sqlEx.getSQLState().equals(ERRO_FK)) {
            throw new RuntimeException( nomeTabela + " não pode ser excluído, está relacionado a outro registro");
        }

        throw new RuntimeException("Erro ao deletar" + nomeTabela, sqlEx);
    }

}
