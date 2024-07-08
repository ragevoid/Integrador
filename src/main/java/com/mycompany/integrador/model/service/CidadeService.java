/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.model.service;

import com.mycompany.integrador.model.Cidade;
import com.mycompany.integrador.util.conexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Top System
 */
public class CidadeService {
    
    Connection conexao = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public CidadeService() {
        
        conexao = conexaoBD.getConnection();
    }
     public List<Cidade> listarCidades() {
        String sql = "SELECT codigo_cidade, nome_cidade FROM cidade";
        List<Cidade> cidades = new ArrayList<>();

        try {
            // Obtendo a conexão
            conexao = conexaoBD.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);

            // Executando o comando SQL
            rs = stmt.executeQuery();

            // Processando os resultados
            while (rs.next()) {
                int codigo = rs.getInt("codigo_cidade");
                String nome = rs.getString("nome_cidade");
                
                cidades.add(new Cidade(codigo, nome));
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

        return cidades;
    }   
     
     public Cidade getCidades(String nome) {
        String sql = "SELECT codigo_cidade, nome_cidade FROM cidade where nome_cidade like ?";        
        Cidade cidade = null;
        try {
            // Obtendo a conexão
            conexao = conexaoBD.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);

            // Executando o comando SQL
            rs = stmt.executeQuery();

            // Processando os resultados
            while (rs.next()) {
                int codigo = rs.getInt("codigo_cidade");
                String nomeCidade = rs.getString("nome_cidade");
                
                cidade = new Cidade(codigo,nomeCidade);
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

        return cidade;
    }
      
    public int getCodigoCidade(String nome) {
        int codigoCidade = -1; // Valor padrão
        String sql = "SELECT codigo_cidade FROM cidade WHERE nome_cidade LIKE ?";
        try {
            conexao = conexaoBD.getConnection();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            if (rs.next()) {
                codigoCidade = rs.getInt("codigo_cidade");
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
                conexaoBD.fecharConexao(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return codigoCidade;
    }
    
    public String getNomeCidade(int codigoCidade) {
        String nomeCidade = null;
        String sql = "SELECT nome_cidade FROM cidade WHERE codigo_cidade = ?";
        try {
            conexao = conexaoBD.getConnection();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigoCidade);
            rs = stmt.executeQuery();
            if (rs.next()) {
                nomeCidade = rs.getString("nome_cidade");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        } finally {
            fecharRecursos();
        }
        return nomeCidade;
    }

    // Método auxiliar para fechar recursos
    private void fecharRecursos() {
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

}
