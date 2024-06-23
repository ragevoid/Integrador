package com.mycompany.integrador.model;

import java.util.Date;

public class Evento {
    private Date data;
    private String hora;
    private String descricao;

    public Evento(Date data, String hora, String descricao) {
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "data=" + data +
                ", hora='" + hora + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}