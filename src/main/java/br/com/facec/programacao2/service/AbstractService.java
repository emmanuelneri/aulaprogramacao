package br.com.facec.programacao2.service;

import br.com.facec.programacao2.model.Entidade;
import br.com.facec.programacao2.repository.CRUDRepository;

import java.util.List;

public abstract class AbstractService<T extends Entidade> {

    private CRUDRepository<T> repository = getRepository();

    public void salvar(T entidade) {
        entidade.validar();

        if(entidade.getId() == null) {
            repository.criar(entidade);
        } else {
            repository.atualizar(entidade);
        }
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }

    public List<T> buscarTodos() {
        return repository.buscarTodos();
    }

    public T buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public abstract CRUDRepository<T> getRepository();

}
