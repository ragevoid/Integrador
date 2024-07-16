/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.model;

/**
 *
 * @author Top System
 */
public class Estado {
    private int codigo;
    private String nome;
    private String uf;
    private int pais;

    public Estado(int codigo, String nome, String uf, int pais) {
        this.codigo = codigo;
        this.nome = nome;
        this.uf = uf;
        this.pais = pais;
    }

    /*contrutor sem codigo*/
    public Estado(String nome, String uf, int pais) {
        this.nome = nome;
        this.uf = uf;
        this.pais = pais;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getUf() {
        return uf;
    }

    public int getPais() {
        return pais;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setPais(int pais) {
        this.pais = pais;
    }
    
         @Override
    public String toString() {
        return nome;
    }
    
}
