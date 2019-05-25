package br.com.facec.programacao2.repository;

import br.com.facec.programacao2.exceptions.ElementoNaoEncontradoExpcetion;
import br.com.facec.programacao2.model.Entidade;
import br.com.facec.programacao2.others.Validavel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class CRUDRepository<T extends Entidade> {

    private List<T> entidades = new ArrayList<>();

    public void criar(T entidade) {
        entidade.setId(new Random().nextLong());
        this.entidades.add(entidade);
        System.out.println("Entidade criada com sucesso! " + entidade);
    }

    public void atualizar(T entidade) {
        Integer posicaoEntidade = getPosicaoElemento(entidade.getId());
        this.entidades.remove(posicaoEntidade.intValue());
        this.entidades.add(posicaoEntidade, entidade);

        System.out.println("Pessoa atualizada com sucesso! " + entidade);
    }

    private Integer getPosicaoElemento(Long id) {
        Integer posicaoEntidade = null;

        for(int i = 0; i < entidades.size(); i++) {
            T entidadeDaLista = entidades.get(i);
            if(entidadeDaLista.getId().equals(id)) {
                posicaoEntidade = i;
                break;
            }
        }

        if(posicaoEntidade == null) {
            throw new ElementoNaoEncontradoExpcetion("Elemento não encontrado: " + id);
        }
        return posicaoEntidade;
    }

    public void deletar(Long id) {
        Integer posicaoEntidade = getPosicaoElemento(id);
        this.entidades.remove(posicaoEntidade.intValue());
        System.out.println("Entidade excluído com sucesso! " + id);
    }

    public T buscarPorId(Long id) {
        for (T entidade : entidades) {
            if (entidade.getId().equals(id)) {
                return entidade;
            }
        }
        throw new ElementoNaoEncontradoExpcetion("Elemento não encontrado: " + id);
    }

    public List<T> buscarTodos() {
        return new ArrayList<>(entidades);
    }

}
