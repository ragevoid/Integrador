package com.mycompany.integrador.model;

import java.util.Date;

public class Evento {
    private int id;
    private Date data;
    private String horaEntrada;
    private String horaSaida;
    private String descricao;
    private int codigo_quadra;
    private int codigo_cliente;
    private int codigo_modalidade;

    public Evento(int id, Date data, String horaEntrada, String horaSaida, String descricao, int codigo_quadra, int codigo_cliente, int codigo_modalidade) {
        this.id = id;
        this.data = data;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.descricao = descricao;
        this.codigo_quadra = codigo_quadra;
        this.codigo_cliente = codigo_cliente;
        this.codigo_modalidade = codigo_modalidade;
    }

    
    public Evento(Date data, String horaEntrada, String horaSaida, String descricao, int codigo_quadra, int codigo_cliente, int codigo_modalidade) {
        this.data = data;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.descricao = descricao;
        this.codigo_quadra = codigo_quadra;
        this.codigo_cliente = codigo_cliente;
        this.codigo_modalidade = codigo_modalidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(String horaSaida) {
        this.horaSaida = horaSaida;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCodigo_quadra() {
        return codigo_quadra;
    }

    public void setCodigo_quadra(int codigo_quadra) {
        this.codigo_quadra = codigo_quadra;
    }

    public int getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(int codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    public int getCodigo_modalidade() {
        return codigo_modalidade;
    }

    public void setCodigo_modalidade(int codigo_modalidade) {
        this.codigo_modalidade = codigo_modalidade;
    }

 


}