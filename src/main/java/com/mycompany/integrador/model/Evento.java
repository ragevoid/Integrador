package com.mycompany.integrador.model;

import java.util.Date;
import java.util.Calendar;

public class Evento {

    private int id;
    private Date data;
    private String horaEntrada;
    private String horaSaida;
    private String descricao;
    private int codigo_quadra;
    private int codigo_cliente;
    private int codigo_modalidade;
    private String nomeQuadra;
    private String nomeCliente;
    private String nomeModalidade;

    //construtor com todos os id
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

    //Construtor sem id contrutor sem nomes
    public Evento(Date data, String horaEntrada, String horaSaida, String descricao, int codigo_quadra, int codigo_cliente, int codigo_modalidade) {
        this.data = data;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.descricao = descricao;
        this.codigo_quadra = codigo_quadra;
        this.codigo_cliente = codigo_cliente;
        this.codigo_modalidade = codigo_modalidade;
    }

    //Construtor sem id e com nomes
    public Evento(Date data, String horaEntrada, String horaSaida, String descricao, String nomeQuadra, String nomeCliente, String nomeModalidade) {
        this.data = data;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.descricao = descricao;
        this.nomeQuadra = nomeQuadra;
        this.nomeCliente = nomeCliente;
        this.nomeModalidade = nomeModalidade;
    }

    //Construtor sem id do evento
    public Evento(Date data, String horaEntrada, String horaSaida, String descricao, int codigo_quadra, int codigo_cliente, int codigo_modalidade, String nomeQuadra, String nomeCliente, String nomeModalidade) {
        this.data = data;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.descricao = descricao;
        this.codigo_quadra = codigo_quadra;
        this.codigo_cliente = codigo_cliente;
        this.codigo_modalidade = codigo_modalidade;
        this.nomeQuadra = nomeQuadra;
        this.nomeCliente = nomeCliente;
        this.nomeModalidade = nomeModalidade;
    }

    //Construtor sem id de foreinkey
    public Evento(int id, Date data, String horaEntrada, String horaSaida, String descricao, String nomeQuadra, String nomeCliente, String nomeModalidade) {
        this.id = id;
        this.data = data;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.descricao = descricao;
        this.nomeQuadra = nomeQuadra;
        this.nomeCliente = nomeCliente;
        this.nomeModalidade = nomeModalidade;
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

    public String getNomeQuadra() {
        return nomeQuadra;
    }

    public void setNomeQuadra(String nomeQuadra) {
        this.nomeQuadra = nomeQuadra;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeModalidade() {
        return nomeModalidade;
    }

    public void setNomeModalidade(String nomeModalidade) {
        this.nomeModalidade = nomeModalidade;
    }

    public int getDia() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.data);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public int getMes() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.data);
        return cal.get(Calendar.MONTH) + 1; // Calendar.MONTH is zero-based
    }

    public int getAno() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.data);
        return cal.get(Calendar.YEAR);
    }

}
