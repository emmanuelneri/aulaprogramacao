package br.com.facec.programacao2.model;

import br.com.facec.programacao2.others.Validavel;

import static br.com.facec.programacao2.exceptions.ValidacaoUtil.validarCampoObrigatorio;

public class Pessoa extends Entidade {

    private String nome;

    @Override
    public void validar() {
        validarCampoObrigatorio(nome,
                "Nome é obrigatório.");

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + getId() +
                ", nome='" + nome + '\'' +
                '}';
    }
}
