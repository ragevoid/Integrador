/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.model;

/**
 *
 * @author ricardo.gonzalez
 */
public class Modalidade {
    int codigo_modalidade;
    String nome_modalidade;
    float valor_modalidade;

    public Modalidade(int codigo_modalidade, String nome_modalidade, float valor_modalidade) {
        this.codigo_modalidade = codigo_modalidade;
        this.nome_modalidade = nome_modalidade;
        this.valor_modalidade = valor_modalidade;
    }

    public Modalidade(String nome_modalidade, float valor_modalidade) {
        this.nome_modalidade = nome_modalidade;
        this.valor_modalidade = valor_modalidade;
    }

    public int getCodigo_modalidade() {
        return codigo_modalidade;
    }

    public void setCodigo_modalidade(int codigo_modalidade) {
        this.codigo_modalidade = codigo_modalidade;
    }

    public String getNome_modalidade() {
        return nome_modalidade;
    }

    public void setNome_modalidade(String nome_modalidade) {
        this.nome_modalidade = nome_modalidade;
    }

    public float getValor_modalidade() {
        return valor_modalidade;
    }

    public void setValor_modalidade(float valor_modalidade) {
        this.valor_modalidade = valor_modalidade;
    }

   
}
