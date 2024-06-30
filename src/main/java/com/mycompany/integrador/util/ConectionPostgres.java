package com.mycompany.integrador.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionPostgres {
    private static final String URL = "jdbc:postgresql://localhost:5432/quadraManager"; // URL corregida
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
        try (Connection conexao = ConectionPostgres.getConnection()) { // Usando try-with-resources
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao estabelecer a conexão: " + e.getMessage());
        }
    }
}
