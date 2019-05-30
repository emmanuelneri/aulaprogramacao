package br.com.facec.programacao2.service;

import br.com.facec.programacao2.model.Funcionario;
import br.com.facec.programacao2.repository.CRUDRepository;
import br.com.facec.programacao2.repository.FuncionarioRepository;

public class FuncionarioService extends AbstractService<Funcionario> {

    @Override
    public CRUDRepository getRepository() {
//        return new FuncionarioRepository();
        return null;
    }
}
