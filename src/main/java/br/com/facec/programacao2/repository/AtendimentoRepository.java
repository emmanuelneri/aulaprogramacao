package br.com.facec.programacao2.repository;

import br.com.facec.programacao2.exceptions.ElementoNaoEncontradoExpcetion;
import br.com.facec.programacao2.model.Atendimento;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AtendimentoRepository implements CRUDRepository<Atendimento>{

    private List<Atendimento> atendimentos = new ArrayList<>();

    @Override
    public void criar(Atendimento atendimento) {
        atendimento.setId(new Random().nextLong());
        this.atendimentos.add(atendimento);
        System.out.println("Atendimento criado com sucesso! " + atendimento);
    }

    @Override
    public void atualizar(Atendimento atendimento) {
        Integer posicaoAtendimento = getPosicaoElemento(atendimento.getId());
        this.atendimentos.remove(posicaoAtendimento.intValue());
        this.atendimentos.add(posicaoAtendimento, atendimento);

        System.out.println("Atendimento atualizado com sucesso! " + atendimento);
    }

    private Integer getPosicaoElemento(Long id) {
        Integer posicaoAtendimento = null;

        for(int i = 0; i < atendimentos.size(); i++) {
            Atendimento atendimentoLista = atendimentos.get(i);
            if(atendimentoLista.getId().equals(id)) {
                posicaoAtendimento = i;
                break;
            }
        }

        if(posicaoAtendimento == null) {
            throw new ElementoNaoEncontradoExpcetion("Elemento não encontrado: " + id);
        }
        return posicaoAtendimento;
    }

    @Override
    public void deletar(Long id) {
        Integer posicaoAtendimento = getPosicaoElemento(id);
        this.atendimentos.remove(posicaoAtendimento.intValue());
        System.out.println("Atendimento excluído com sucesso! " + id);
    }

    @Override
    public Atendimento buscarPorId(Long id) {
        for (Atendimento atendimento : atendimentos) {
            if (atendimento.getId().equals(id)) {
                return atendimento;
            }
        }
        throw new ElementoNaoEncontradoExpcetion("Elemento não encontrado: " + id);
    }

    @Override
    public List<Atendimento> buscarTodos() {
        return new ArrayList<>(atendimentos);
    }
}
