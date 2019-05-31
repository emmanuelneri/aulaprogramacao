package br.com.facec.programacao2.model;

public class Funcionario extends Pessoa {

    @Override
    public String toString() {
        return "Funcionario {" +
                " id: " + getId() +
                " nome: " + getNome() +
                "}";
    }
}
