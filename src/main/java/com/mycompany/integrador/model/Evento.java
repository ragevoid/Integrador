package com.mycompany.integrador.model;

import java.util.Date;

public class Evento {
    private int id;
    private Date data;
    private String horaEntrada;
    private String horaSaida;
    private String descricao;
    private String quadra;
    private int codigo_cliente;
    private int codigo_odalidade;

    public Evento(int id, Date data, String horaEntrada, String horaSaida, String descricao, String quadra, int codigo_cliente, int codigo_odalidade) {
        this.id = id;
        this.data = data;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.descricao = descricao;
        this.quadra = quadra;
        this.codigo_cliente = codigo_cliente;
        this.codigo_odalidade = codigo_odalidade;
    }

    //constructor sem id
    public Evento(Date data, String horaEntrada, String horaSaida, String descricao, String quadra, int codigo_cliente, int codigo_odalidade) {
        this.data = data;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.descricao = descricao;
        this.quadra = quadra;
        this.codigo_cliente = codigo_cliente;
        this.codigo_odalidade = codigo_odalidade;
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

    public String getQuadra() {
        return quadra;
    }

    public void setQuadra(String quadra) {
        this.quadra = quadra;
    }

    public int getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(int codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    public int getCodigo_odalidade() {
        return codigo_odalidade;
    }

    public void setCodigo_odalidade(int codigo_odalidade) {
        this.codigo_odalidade = codigo_odalidade;
    }

    
    


}