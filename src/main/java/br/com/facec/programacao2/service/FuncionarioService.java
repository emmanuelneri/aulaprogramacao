package br.com.facec.programacao2.service;

import br.com.facec.programacao2.model.Funcionario;
import br.com.facec.programacao2.repository.FuncionarioRepository;

public class FuncionarioService extends AbstractService<Funcionario> {

    @Override
    public FuncionarioRepository getRepository() {
        return new FuncionarioRepository();
    }
}
