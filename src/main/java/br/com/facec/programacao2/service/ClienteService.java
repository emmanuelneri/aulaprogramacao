package br.com.facec.programacao2.service;

import br.com.facec.programacao2.model.Cliente;
import br.com.facec.programacao2.repository.ClienteRepository;

public class ClienteService extends AbstractCRUDService<Cliente> {

    @Override
    public ClienteRepository getRepository() {
        return new ClienteRepository();
    }
}
