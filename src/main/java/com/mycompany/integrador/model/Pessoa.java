/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.model;

/**
 *
 * @author jose.zanandrea
 */
public class Pessoa {
    
    private int codigo;
    private String nome;
    private String CPF;
    private String telefone;
    private String email;
    private String endereco;
    private String numero;
    private String CEP;
    private String bairro;
    private int cidade;

    public Pessoa(int codigo, String nome, String CPF, String telefone, String email, String endereco, String numero, String CEP, String bairro, int cidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.CPF = CPF;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.numero = numero;
        this.CEP = CEP;
        this.bairro = bairro;
        this.cidade = cidade;
    }
    
        /*construtor sem codigo*/
        public Pessoa(String nome, String CPF, String telefone, String email, String endereco, String numero, String CEP, String bairro, int cidade) {
        this.nome = nome;
        this.CPF = CPF;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.numero = numero;
        this.CEP = CEP;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCPF() {
        return CPF;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNumero() {
        return numero;
    }

    public String getCEP() {
        return CEP;
    }

    public String getBairro() {
        return bairro;
    }

    public int getCidade() {
        return cidade;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(int cidade) {
        this.cidade = cidade;
    }

       
}
