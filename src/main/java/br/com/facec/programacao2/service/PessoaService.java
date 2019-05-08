package br.com.facec.programacao2.service;

import br.com.facec.programacao2.exceptions.CampoObrigatorioException;
import br.com.facec.programacao2.model.Pessoa;
import br.com.facec.programacao2.repository.PessoaRepository;

import java.util.List;

public class PessoaService {

    private PessoaRepository pessoaRepository = new PessoaRepository();

    public void salvar(Pessoa pessoa) {
        pessoa.validar();

        if(pessoa.getId() == null) {
            pessoaRepository.criar(pessoa);
        } else {
            pessoaRepository.atualizar(pessoa);
        }
    }

    public void deletar(Long id) {
        pessoaRepository.deletar(id);
    }

    public List<Pessoa> buscarTodos() {
        return pessoaRepository.buscarTodos();
    }

    public Pessoa buscarPorId(Long id) {
        return pessoaRepository.buscarPorId(id);
    }

}
