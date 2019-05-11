package br.com.facec.programacao2.service;

import br.com.facec.programacao2.model.Atendimento;
import br.com.facec.programacao2.model.Pessoa;
import br.com.facec.programacao2.repository.AtendimentoRepository;

import java.util.List;

public class AtendimentoService {

    private AtendimentoRepository atendimentoRepository = new AtendimentoRepository();

    public Atendimento inicializar(Pessoa cliente, String descricaoProblema, Pessoa funcionario) {
        Atendimento atendimento = new Atendimento();
        atendimento.setCliente(cliente);
        atendimento.setFuncionario(funcionario);
        atendimento.setDescricaoProblema(descricaoProblema);
        atendimento.validar();
        atendimentoRepository.criar(atendimento);
        return atendimento;
    }

    public Atendimento atualizar(Atendimento atendimento, String descricaoProblema, Pessoa funcionario) {
        if(descricaoProblema != null) {
            atendimento.setDescricaoProblema(descricaoProblema);
        }

        if(funcionario != null) {
            atendimento.setFuncionario(funcionario);
        }

        atendimentoRepository.atualizar(atendimento);

        return atendimento;
    }

    public void encerrar(Atendimento atendimento) {
        atendimento.encerrar();
        atendimentoRepository.atualizar(atendimento);
    }

    public List<Atendimento> buscarTodos() {
        return atendimentoRepository.buscarTodos();
    }

}
