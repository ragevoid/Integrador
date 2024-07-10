/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.model.service;

import com.mycompany.integrador.model.Modalidade;
import com.mycompany.integrador.util.conexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ricardo.gonzalez
 */
public class ModalidadeService {
    Connection conexao = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    
    
     public List<Modalidade> listarModalidade() {
        String sql = "SELECT * FROM modalidade";
        List<Modalidade> modalidades = new ArrayList<>();

        try {
            // Obtendo a conexão
            conexao = conexaoBD.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);

            // Executando o comando SQL
            rs = stmt.executeQuery();

            // Processando os resultados
            while (rs.next()) {
                int codigo = rs.getInt("codigo_modalidade");
                String nome = rs.getString("nome_modalidade");
                float valor = rs.getFloat("valor_modalidade");

                modalidades.add(new Modalidade(codigo, nome,valor ));
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

        return modalidades;
    }
}
