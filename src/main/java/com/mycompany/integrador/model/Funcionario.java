/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.model;

/**
 *
 * @author Ricardo
 */
public class Funcionario {
    private Long id;
    private String nome;
    private int idade;
    private String email;
    public Funcionario() {
    }
    
    // Construtor sem o campo 'id'
    public Funcionario(String nome, int idade, String email, String imagePath) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
    }
 
    public Funcionario(Long id, String nome, int idade, String email, String imagePath) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
   
    
    
}


