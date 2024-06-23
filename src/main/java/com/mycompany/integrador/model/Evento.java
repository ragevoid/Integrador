package com.mycompany.integrador.model;

import java.util.Date;

public class Evento {
    private Date data;
    private String hora;
    private String descricao;

    public Evento(Date data, String hora, String descricao) {
        this.data = data;
        setHora(hora); // Llamada a la validación de la hora
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

    public void setHora(String hora) {
        if (hora.matches("\\d{2}:\\d{2}")) {
            this.hora = hora;
        } else {
            throw new IllegalArgumentException("Hora debe estar en formato HH:MM");
        }
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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