package br.com.facec.programacao2;

import br.com.facec.programacao2.model.Cliente;
import br.com.facec.programacao2.model.Funcionario;
import br.com.facec.programacao2.repository.ClienteRepository;
import br.com.facec.programacao2.repository.FuncionarioRepository;
import br.com.facec.programacao2.repository.InicializacaoRepository;

import java.util.List;

public class Application {

    public static void main(String[] args) {

        InicializacaoRepository inicializacaoRepository
                = new InicializacaoRepository();

        inicializacaoRepository.criarTabelaCliente();
        inicializacaoRepository.criarTabelaFuncionario();

        ClienteRepository clienteRepository
                = new ClienteRepository();

        Cliente cliente = new Cliente();
        cliente.setNome("Cliente 1");

        clienteRepository.criar(cliente);

        System.out.println(cliente);



        clienteRepository.deletar(1L);

        Cliente cliente3 = new Cliente();
        cliente3.setId(3L);
        cliente3.setNome("Cliente 3");
        clienteRepository.atualizar(cliente3);

        System.out.println("----------- TODOS CLIENTES -------");
        List<Cliente> clientes = clienteRepository.buscarTodos();
        System.out.println(clientes);
        System.out.println("------------------");

        Cliente clienteBuscadoPorId = clienteRepository.buscarPorId(2L);
        System.out.println(clienteBuscadoPorId);

        FuncionarioRepository funcionarioRepository = new FuncionarioRepository();

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Funcionario");

        funcionarioRepository.criar(funcionario);

        funcionario.setNome("Funcionario Alterado");
        funcionarioRepository.atualizar(funcionario);

        System.out.println(funcionario);

        funcionarioRepository.deletar(1L);


        System.out.println("---------------TODOS funcionários---------");

        funcionarioRepository.buscarTodos()
                .forEach(System.out::println);

        System.out.println("------------------------------------");
    }

}
