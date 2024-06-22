/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ricardo
 */
public class ConectionPostgres {
    private static final String URL = "quadraManager:postgresql://localhost:5432/";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "28579994";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }

    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", e);
            }
        }
    }

    public static void main(String[] args) {
        // Exemplo de como usar a classe de conexão
        Connection conexao = null;
        try {
            conexao = ConectionPostgres.getConnection();
            System.out.println("Conexão estabelecida com sucesso!");
        } finally {
            ConectionPostgres.fecharConexao(conexao);
        }
    }
}
