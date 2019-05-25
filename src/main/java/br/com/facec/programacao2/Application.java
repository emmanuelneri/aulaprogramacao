package br.com.facec.programacao2;

import br.com.facec.programacao2.model.Atendimento;
import br.com.facec.programacao2.model.Cliente;
import br.com.facec.programacao2.model.Funcionario;
import br.com.facec.programacao2.service.AtendimentoService;
import br.com.facec.programacao2.service.ClienteService;
import br.com.facec.programacao2.service.FuncionarioService;

import java.util.List;

public class Application {

    public static void main(String[] args) {

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("FuncionarioRepository I");

        Cliente cliente = new Cliente();
        cliente.setNome("Cliente I");

        FuncionarioService funcionarioService = new FuncionarioService();
        ClienteService clienteService = new ClienteService();
        AtendimentoService atendimentoService = new AtendimentoService();

        System.out.println("----------Ações--------------");

        funcionarioService.salvar(funcionario);
        clienteService.salvar(cliente);

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
