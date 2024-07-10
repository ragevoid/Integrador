/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.model;

import java.util.Date;

/**
 *
 * @author jose.zanandrea
 */
public class Cliente extends Pessoa {

    private Date dataNascimento;

    public Cliente(int codigo, String nome, String CPF, String telefone, String email, String endereco, String numero, String CEP, String bairro, int cidade, Date dataNascimento) {
        super(codigo, nome, CPF, telefone, email, endereco, numero, CEP, bairro, cidade);
        this.dataNascimento = dataNascimento;
    }

    public Cliente(String nome, String CPF, String telefone, String email, String endereco, String numero, String CEP, String bairro, int cidade, Date dataNascimento) {
        super(nome, CPF, telefone, email, endereco, numero, CEP, bairro, cidade);
        this.dataNascimento = dataNascimento;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }   
}

