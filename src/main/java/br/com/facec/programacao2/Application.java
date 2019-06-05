package br.com.facec.programacao2;

import br.com.facec.programacao2.db.InicializacaoBancoDeDados;
import br.com.facec.programacao2.model.Atendimento;
import br.com.facec.programacao2.model.Cliente;
import br.com.facec.programacao2.model.Funcionario;
import br.com.facec.programacao2.service.AtendimentoService;
import br.com.facec.programacao2.service.ClienteService;
import br.com.facec.programacao2.service.FuncionarioService;

public class Application {

    public static void main(String[] args) {

        InicializacaoBancoDeDados inicializacaoBancoDeDados
                = new InicializacaoBancoDeDados();

        inicializacaoBancoDeDados.criarTabelaCliente();
        inicializacaoBancoDeDados.criarTabelaFuncionario();
        inicializacaoBancoDeDados.criarTabelaAtendimento();

        ClienteService clienteService = new ClienteService();

        Cliente cliente = new Cliente();
        cliente.setNome("Cliente 1");

        clienteService.salvar(cliente);

        System.out.println(cliente);

        clienteService.deletar(1L);

        Cliente cliente3 = new Cliente();
        cliente3.setId(3L);
        cliente3.setNome("Cliente 3");
        clienteService.salvar(cliente3);

        System.out.println("----------- TODOS CLIENTES -------");
        clienteService.buscarTodos()
                .forEach(System.out::println);
        System.out.println("------------------");

        Cliente clienteBuscadoPorId = clienteService.buscarPorId(2L);
        System.out.println(clienteBuscadoPorId);

        FuncionarioService funcionarioService = new FuncionarioService();

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Funcionario");

        funcionarioService.salvar(funcionario);

        funcionario.setNome("Funcionario Alterado");
        funcionarioService.salvar(funcionario);

        System.out.println(funcionario);

        funcionarioService.deletar(1L);


        System.out.println("---------------TODOS funcionários---------");

        funcionarioService.buscarTodos()
                .forEach(System.out::println);

        System.out.println("------------------------------------");


        AtendimentoService atendimentoService = new AtendimentoService();

        Atendimento atendimento = atendimentoService.inicializar(cliente, "Problema", funcionario);
        atendimentoService.encerrar(atendimento);


        System.out.println("---------------TODOS atendimentos---------");

        atendimentoService.buscarTodos()
                .forEach(System.out::println);

        System.out.println("------------------------------------");


        System.out.println(atendimentoService.buscarPorId(atendimento.getId()));
    }

}
