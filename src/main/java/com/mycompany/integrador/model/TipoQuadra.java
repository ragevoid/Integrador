/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.model;

import java.util.logging.Logger;

/**
 *
 * @author Top System
 */
public class TipoQuadra {
    
    private int codigo;
    private String nome;

    public TipoQuadra(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public TipoQuadra(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
