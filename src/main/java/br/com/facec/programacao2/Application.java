package br.com.facec.programacao2;

import br.com.facec.programacao2.model.Cliente;
import br.com.facec.programacao2.repository.ClienteRepository;
import br.com.facec.programacao2.repository.InicializacaoRepository;

public class Application {

    public static void main(String[] args) {

        InicializacaoRepository inicializacaoRepository
                = new InicializacaoRepository();

        inicializacaoRepository.criarTabelaCliente();

        ClienteRepository clienteRepository
                = new ClienteRepository();

        Cliente cliente = new Cliente();
        cliente.setNome("Cliente 1");

        clienteRepository.criar(cliente);

        System.out.println(cliente);

//        List<Cliente> clientes = clienteRepository.buscarTodos();
//
//        clienteRepository.deletar(1L);
//
//        Cliente cliente3 = new Cliente();
//        cliente3.setId(3L);
//        cliente3.setNome("Cliente 3");
//        clienteRepository.atualizar(cliente3);
//
//        System.out.println(clientes);
//
//        Cliente clienteBuscadoPorId = clienteRepository.buscarPorId(2L);
//        System.out.println(clienteBuscadoPorId);
    }

}
