package br.com.facec.programacao2.repository.mapeadores;

import br.com.facec.programacao2.model.Entidade;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapeador<T extends Entidade> {

    T mapear(ResultSet resultado) throws SQLException;

}
