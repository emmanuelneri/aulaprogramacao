package br.com.facec.programacao2;

import br.com.facec.programacao2.model.Pessoa;
import br.com.facec.programacao2.service.PessoaService;

public class Application {


    public static void main(String[] args) {

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Pessoa I");

        PessoaService pessoaService = new PessoaService();
        pessoaService.salvar(pessoa);

        pessoa.setId(1L);
        pessoa.setNome("Nome Atualizado");
        pessoaService.salvar(pessoa);
    }

}
