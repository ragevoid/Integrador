/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.model;

/**
 *
 * @author Top System
 */
public class Cidade {
    
    private int codigo;
    private String nome;
    private int codigoFiscal;
    private int populacao;
    private int estado;

    public Cidade(int codigo, String nome, int codigoFiscal, int populacao, int estado) {
        this.codigo = codigo;
        this.nome = nome;
        this.codigoFiscal = codigoFiscal;
        this.populacao = populacao;
        this.estado = estado;
    }
    
        public Cidade(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }
    
    /*Contrutor sem codigo*/

    public Cidade(String nome, int codigoFiscal, int populacao, int estado) {
        this.nome = nome;
        this.codigoFiscal = codigoFiscal;
        this.populacao = populacao;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getCodigoFiscal() {
        return codigoFiscal;
    }

    public int getPopulacao() {
        return populacao;
    }

    public int getEstado() {
        return estado;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodigoFiscal(int codigoFiscal) {
        this.codigoFiscal = codigoFiscal;
    }

    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
     @Override
    public String toString() {
        return nome; // Isso faz com que o nome do funcionário seja exibido no JComboBox
    }
    
}
