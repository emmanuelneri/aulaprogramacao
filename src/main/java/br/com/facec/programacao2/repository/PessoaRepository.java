package br.com.facec.programacao2.repository;

import br.com.facec.programacao2.exceptions.ElementoNaoEncontradoExpcetion;
import br.com.facec.programacao2.model.Pessoa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PessoaRepository implements CRUDRepository<Pessoa> {

    private List<Pessoa> pessoas = new ArrayList<>();

    @Override
    public void criar(Pessoa pessoa) {
        pessoa.setId(new Random().nextLong());
        this.pessoas.add(pessoa);
        System.out.println("Pessoa criada com sucesso! " + pessoa);
    }

    @Override
    public void atualizar(Pessoa pessoa) {
        Integer posicaoPessoa = getPosicaoElemento(pessoa.getId());
        this.pessoas.remove(posicaoPessoa.intValue());
        this.pessoas.add(posicaoPessoa, pessoa);

        System.out.println("Pessoa atualizada com sucesso! " + pessoa);
    }

    private Integer getPosicaoElemento(Long id) {
        Integer posicaoPessoa = null;

        for(int i = 0; i < pessoas.size(); i++) {
            Pessoa pessoaDaLista = pessoas.get(i);
            if(pessoaDaLista.getId().equals(id)) {
                posicaoPessoa = i;
                break;
            }
        }

        if(posicaoPessoa == null) {
            throw new ElementoNaoEncontradoExpcetion("Elemento não encontrado: " + id);
        }
        return posicaoPessoa;
    }

    @Override
    public void deletar(Long id) {
        Integer posicaoPessoa = getPosicaoElemento(id);
        this.pessoas.remove(posicaoPessoa.intValue());
        System.out.println("Pessoa excluído com sucesso! " + id);
    }

    @Override
    public Pessoa buscarPorId(Long id) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getId().equals(id)) {
                return pessoa;
            }
        }
        throw new ElementoNaoEncontradoExpcetion("Elemento não encontrado: " + id);
    }

    @Override
    public List<Pessoa> buscarTodos() {
        return new ArrayList<>(pessoas);
    }
}
