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
    private int codigo;
    private String nome;
    private double tamanho;
    private int tipo;


    public Quadra(int codigo, String nome, double tamanho, int tipo) {
        this.codigo = codigo;
        this.nome = nome;
        this.tamanho = tamanho;
        this.tipo = tipo;

    }
//Constructor sem ID
    public Quadra(String nome, double tamanho, int tipo) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.tipo = tipo;

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
      
}

