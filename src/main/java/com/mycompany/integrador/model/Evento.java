package com.mycompany.integrador.model;

import java.util.Date;

public class Evento {
    private int id;
    private Date data;
    private String hora;
    private String descricao;
    private String quadra;

    public Evento(int id, Date data, String hora, String descricao, String quadra) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
        this.quadra = quadra;
    }
    
    //Constructor Sem ID
     public Evento(Date data, String hora, String descricao, String quadra) {
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
        this.quadra = quadra;
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
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

}