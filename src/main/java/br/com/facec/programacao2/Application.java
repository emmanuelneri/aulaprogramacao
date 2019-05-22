package br.com.facec.programacao2;

import br.com.facec.programacao2.model.Atendimento;
import br.com.facec.programacao2.model.Pessoa;
import br.com.facec.programacao2.service.AtendimentoService;
import br.com.facec.programacao2.service.PessoaService;

import java.util.List;

public class Application {


    public static void main(String[] args) {

        Pessoa funcionario = new Pessoa();
        funcionario.setNome("Funcionario I");

        Pessoa cliente = new Pessoa();
        cliente.setNome("Cliente I");

        PessoaService pessoaService = new PessoaService();
        AtendimentoService atendimentoService = new AtendimentoService();

        System.out.println("----------Ações--------------");

        pessoaService.salvar(funcionario);
        pessoaService.salvar(cliente);

        Atendimento atendimento = atendimentoService.inicializar(
                cliente,
                "Problema X",
                funcionario);

        atendimentoService.atualizar(atendimento,
                "Nova descrição",
                null);

        Atendimento atendimento2 = atendimentoService.inicializar(
                cliente,
                "Problema y",
                funcionario);

        Atendimento atendimento3 = atendimentoService.inicializar(
                cliente,
                "Problema z",
                funcionario);

        atendimentoService.encerrar(atendimento3);

        System.out.println("----------Todos atendimentos-------------");

        List<Atendimento> atendimentos = atendimentoService.buscarTodos();

        for (Atendimento atendimentoLista: atendimentos) {
            System.out.println(atendimentoLista);
        }


    }

}
