package br.com.facec.programacao2.exceptions;

import java.sql.SQLException;

public final class TratamentoErrosPostgresSQL {

    private static final String CODIGO_ERRO_CHAVE_ESTRANGEIRA = "23503";
    private static final String CODIGO_ERRO_CHAVE_UNICA = "23505";

    private TratamentoErrosPostgresSQL() {}

    public static void tratarExclusao(SQLException sqlEx, String nomeTabela) {
        if(sqlEx.getSQLState().equals(CODIGO_ERRO_CHAVE_ESTRANGEIRA)) {
            throw new RuntimeException("Registro não pode ser excluído, está relacionado a outro registro. Tabela: " + nomeTabela);
        }

        throw new RuntimeException("Erro ao deletar" + nomeTabela, sqlEx);
    }

    public static void tratarAtualizacao(SQLException sqlEx, String nomeTabela) {
        if(sqlEx.getSQLState().equals(CODIGO_ERRO_CHAVE_UNICA)) {
            throw new RuntimeException("Registro não pode ser inserido ou atualizado, viola a chave única da tabela: " + nomeTabela);
        }

        throw new RuntimeException("Erro ao inserior ou atualizar. Tabela: " + nomeTabela, sqlEx);
    }

}
