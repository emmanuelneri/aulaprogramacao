package br.com.facec.programacao2.repository;

import br.com.facec.programacao2.model.Atendimento;

import java.util.List;

public class AtendimentoRepository implements CRUDRepository<Atendimento>{

    @Override
    public void criar(Atendimento atendimento) {
        System.out.println("Atendimento criado com sucesso! " + atendimento);
    }

    @Override
    public void atualizar(Atendimento atendimento) {
        System.out.println("Atendimento atualizado com sucesso! " + atendimento);
    }

    @Override
    public void deletar(Long id) {
        System.out.println("Atendimento excluído com sucesso! " + id);
    }

    @Override
    public Atendimento buscarPorId(Long id) {
        return null;
    }

    @Override
    public List<Atendimento> buscarTodos() {
        return null;
    }
}
