package br.com.facec.programacao2.repository.genericos;

import br.com.facec.programacao2.model.Cliente;
import br.com.facec.programacao2.repository.mapeadores.Mapeador;
import br.com.facec.programacao2.repository.mapeadores.MapeadorCliente;

import java.sql.SQLType;
import java.util.ArrayList;
import java.util.List;

public class ClienteUsandoGenericoRepository extends AbstractGenericoRepository<Cliente> {

    @Override
    public Cliente criar(Cliente cliente) {
        String sql = "insert into cliente (nome) values (?)";

        List<ParametroConsulta> parametroConsultas = new ArrayList<>();
        parametroConsultas.add(new ParametroConsulta(1, cliente.getNome()));

        return criar(cliente, sql, parametroConsultas);
    }

    @Override
    public Cliente atualizar(Cliente cliente) {
        String sql = "update cliente set nome = ? where id = ?";

        List<ParametroConsulta> parametroConsultas = new ArrayList<>();
        parametroConsultas.add(new ParametroConsulta(1, cliente.getNome()));
        parametroConsultas.add(new ParametroConsulta(2, cliente.getId()));

        return atualizar(cliente, sql, parametroConsultas);
    }

    @Override
    public List<Cliente> buscarTodos() {
        String sql = "select * from cliente";
        return buscarTodos(sql);
    }

    @Override
    public Cliente buscarPorId(Long id) {
        String sql = "select * from cliente where id = ?";
        return buscarPorId(id, sql);
    }

    @Override
    public void deletar(Long id) {
        String sql = "delete from cliente where id = ?";
        deletar(id, sql);
    }

    @Override
    public String nomeTabela() {
        return "Cliente";
    }

    @Override
    public Mapeador<Cliente> getMapeador() {
        return MapeadorCliente.build();
    }
}
