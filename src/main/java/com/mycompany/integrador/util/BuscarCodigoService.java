/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Top System
 */
public class BuscarCodigoService {

    Connection conexao = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public BuscarCodigoService() {

        conexao = conexaoBD.getConnection();
    }

    public int getMaxCodigo(String tabela, String colunaCodigo) {
        int maiorCodigo = 0;

        String sql = "SELECT MAX(" + colunaCodigo + ") AS max_codigo FROM " + tabela;

        try {

            // Obtendo a conexão
            conexao = conexaoBD.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);

            // Executando o comando SQL
            rs = stmt.executeQuery();

            while (rs.next()) {
                maiorCodigo = rs.getInt("max_codigo");
                maiorCodigo++; // Incrementa o maior código encontrado
            }

        } catch (SQLException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        } finally {
            // Fechando os recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                conexaoBD.fecharConexao(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return maiorCodigo;
    }
}
