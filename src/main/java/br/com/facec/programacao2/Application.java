package br.com.facec.programacao2;

import br.com.facec.programacao2.model.Atendimento;
import br.com.facec.programacao2.model.Pessoa;
import br.com.facec.programacao2.service.AtendimentoService;
import br.com.facec.programacao2.service.PessoaService;

public class Application {


    public static void main(String[] args) {

        Pessoa funcionario = new Pessoa();
        funcionario.setId(1L);
        funcionario.setNome("Funcionario I");

        Pessoa cliente = new Pessoa();
        cliente.setId(1L);
        cliente.setNome("Cliente I");

        System.out.println("----------");

        AtendimentoService atendimentoService = new AtendimentoService();

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

        System.out.println(atendimentoService.buscarTodos());


    }

}
