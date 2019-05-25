package br.com.facec.programacao2.service;

import br.com.facec.programacao2.model.Cliente;
import br.com.facec.programacao2.repository.ClienteRepository;

public class ClienteService extends AbstractService<Cliente> {

    @Override
    public ClienteRepository getRepository() {
        return new ClienteRepository();
    }
}
