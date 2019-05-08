package br.com.facec.programacao2.model;

import br.com.facec.programacao2.exceptions.CampoObrigatorioException;

public class Pessoa {

    private Long id;
    private String nome;

    public void validar() {
        if(this.nome == null
                ||  this.nome.isEmpty()) {
            throw new CampoObrigatorioException("O nome é obrigatório");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
