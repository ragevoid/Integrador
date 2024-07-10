/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.model.service;

import com.mycompany.integrador.model.Quadra;
import com.mycompany.integrador.util.ConectionPostgres;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public class QuadraService {
    Connection conexao = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public QuadraService() {
        conexao = ConectionPostgres.getConnection();
    }
     public List<Quadra> listarQuadras() {
        String sql = "SELECT codigo_quadra, nome_quadra, tipoquadra_quadra, tamanho_quadra FROM quadra";
        List<Quadra> quadras = new ArrayList<>();

        try {
            conexao = ConectionPostgres.getConnection();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                long id = rs.getInt("codigo_quadra");
                String nome = rs.getString("nome_quadra");
                int tipo = rs.getInt("tipoquadra_quadra");
                double tamanho = rs.getDouble("tamanho_quadra");
                quadras.add(new Quadra(id, nome, tamanho, tipo));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                ConectionPostgres.fecharConexao(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return quadras;
    }
    
}
