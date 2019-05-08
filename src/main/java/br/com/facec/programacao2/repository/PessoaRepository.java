package br.com.facec.programacao2.repository;

import br.com.facec.programacao2.model.Pessoa;

import java.util.List;

public class PessoaRepository {

    public void criar(Pessoa pessoa) {
        System.out.println("Pessoa criada com sucesso! : " + pessoa);
    }

    public void atualizar(Pessoa pessoa) {
        System.out.println("Pessoa atualizada com sucesso! : " + pessoa);
    }

    public Pessoa buscarPorId(Long id) {
        return null;
    }

    public List<Pessoa> buscarTodos() {
        return null;
    }

    public void deletar(Long id) {
        System.out.println("Pessoa excluída com sucesso! : " + id);

    }
}
