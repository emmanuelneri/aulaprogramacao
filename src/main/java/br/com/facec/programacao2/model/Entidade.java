package br.com.facec.programacao2.model;

import br.com.facec.programacao2.others.Validavel;

public abstract class Entidade implements Validavel {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
