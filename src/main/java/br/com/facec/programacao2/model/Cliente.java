package br.com.facec.programacao2.model;

public class Cliente extends Pessoa {

    @Override
    public String toString() {
        return "Cliente {" +
                " id: " + getId() +
                " nome: " + getNome() +
                "}";
    }
}
