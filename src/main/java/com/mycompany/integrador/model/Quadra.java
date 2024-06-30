/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.model;

/**
 *
 * @author Ricardo
 */
public class Quadra {
    private Long id;
    private String nome;
    private double tamanho;
    private int tipo;
    private int modalidade;

    public Quadra(Long id, String nome, double tamanho, int tipo, int modalidade) {
        this.id = id;
        this.nome = nome;
        this.tamanho = tamanho;
        this.tipo = tipo;
        this.modalidade = modalidade;
    }
//Constructor sem ID
    public Quadra(String nome, double tamanho, int tipo, int modalidade) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.tipo = tipo;
        this.modalidade = modalidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(float tamanho) {
        this.tamanho = tamanho;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getModalidade() {
        return modalidade;
    }

    public void setModalidade(int modalidade) {
        this.modalidade = modalidade;
    }
    
    
    
    
    
}
