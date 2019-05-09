package br.com.facec.programacao2.exceptions;

import br.com.facec.programacao2.model.Entidade;

public class ValidacaoUtil {

    public static void validarCampoObrigatorio(String valor, String mensagemErro) {
        if(valor == null || valor.isEmpty()) {
            throw new CampoObrigatorioException(mensagemErro);
        }
    }

    public static void validarCampoObrigatorio(Object valor, String mensagemErro) {
        if(valor == null) {
            throw new CampoObrigatorioException(mensagemErro);
        }
    }

    public static void validarEntidadeExistente(Entidade entidade, String mensagemErro) {
        if(entidade == null || entidade.getId() == null) {
            throw new RegistroNaoExistenteException(mensagemErro);
        }
    }
}
