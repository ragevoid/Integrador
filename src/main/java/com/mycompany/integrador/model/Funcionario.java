package com.mycompany.integrador.model;


/**
 *
 * @author jose.zanandrea
 */
public class Funcionario extends Pessoa {

    private String senha;
    private String confirmaSenha;

    // Construtor com senha e código
    public Funcionario(String senha, String confirmaSenha, int codigo, String nome, String CPF, String telefone, String email, String endereco, String numero, String CEP, String bairro, int cidade) {
        super(codigo, nome, CPF, telefone, email, endereco, numero, CEP, bairro, cidade);
        this.senha = senha;
        this.confirmaSenha = confirmaSenha;
    }

    // Construtor com senha sem código
    public Funcionario(String senha, String confirmaSenha, String nome, String CPF, String telefone, String email, String endereco, String numero, String CEP, String bairro, int cidade) {
        super(nome, CPF, telefone, email, endereco, numero, CEP, bairro, cidade);
        this.senha = senha;
        this.confirmaSenha = confirmaSenha;
    }

    // Construtor sem senha e com código
    public Funcionario(int codigo, String nome, String CPF, String telefone, String email, String endereco, String numero, String CEP, String bairro, int cidade) {
        super(codigo, nome, CPF, telefone, email, endereco, numero, CEP, bairro, cidade);
    }

    public String getSenha() {
        return senha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

}
