package br.com.facec.programacao2.repository;

import br.com.facec.programacao2.model.Entidade;

import java.util.List;

public abstract class CRUDRepository<T extends Entidade> {


    public abstract T criar(T entidade);
    public abstract T atualizar(T entidade);
    public abstract List<T> buscarTodos();
    public abstract T buscarPorId(Long id);
    public abstract void deletar(Long id);
}
