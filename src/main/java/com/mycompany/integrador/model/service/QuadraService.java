/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.model.service;

import com.mycompany.integrador.model.Quadra;
import com.mycompany.integrador.util.BuscarCodigoService;
import com.mycompany.integrador.util.conexaoBD;
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
        conexao = conexaoBD.getConnection();
    }

    public Quadra salvarQuadra(Quadra quadra) {
        String sql = "INSERT INTO quadra (nome_quadra, tipoquadra_quadra, tamanho_quadra) VALUES (?, ?, ?)";
        try {
            // Obtendo a conexão
            conexao = conexaoBD.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, quadra.getNome());
            stmt.setInt(2, quadra.getTipo());
            stmt.setDouble(3, quadra.getTamanho());

            // Executando o comando SQL
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getMessage());
        } finally {
            fecharRecursos();
        }
        return quadra;
    }

    public Quadra atualizarQuadra(Quadra quadra) {
        String sql = "UPDATE quadra SET codigo_quadra = ?, nome_quadra = ?, tipoquadra_quadra = ?, tamanho_quadra = ? "
                + " WHERE codigo_quadra = ?";
        try {
            // Obtendo a conexão
            conexao = conexaoBD.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, quadra.getCodigo());
            stmt.setString(2, quadra.getNome());
            stmt.setInt(3, quadra.getTipo());
            stmt.setDouble(4, quadra.getTamanho());

            //condição where
            stmt.setInt(4, quadra.getCodigo());

            // Executando o comando SQL
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getMessage());
        } finally {
            fecharRecursos();
        }
        return quadra;
    }

    public List<Quadra> listarQuadras() {
        String sql = "SELECT codigo_quadra, nome_quadra, tipoquadra_quadra, tamanho_quadra FROM quadra";
        List<Quadra> quadras = new ArrayList<>();

        try {
            conexao = conexaoBD.getConnection();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("codigo_quadra");
                String nome = rs.getString("nome_quadra");
                int tipo = rs.getInt("tipoquadra_quadra");
                double tamanho = rs.getDouble("tamanho_quadra");
                quadras.add(new Quadra(codigo, nome, tamanho, tipo));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        } finally {
            fecharRecursos();
        }

        return quadras;
    }

    public int getMaxCodigoQuadra() {
        BuscarCodigoService service = new BuscarCodigoService();
        return service.getMaxCodigo("quadra", "codigo_quadra");
    }

    public boolean excluirQuadra(int codigo) {
        String sql = "DELETE FROM quadra WHERE codigo_quadra = ?";
        try {
            // Obtendo a conexão
            conexao = conexaoBD.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);

            // Executando o comando SQL
            int linhasAfetadas = stmt.executeUpdate();

            // Retorna verdadeiro se uma linha foi afetada, falso caso contrário
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao deletar dados: " + e.getMessage());
            return false;
        } finally {
            fecharRecursos();
        }
    }

    public Quadra localizarQuadraPorCodigo(int codigo) {
        Quadra quadra = null;
        String sql = "SELECT codigo_quadra, nome_quadra, tipoquadra_quadra, tamanho_quadra "
                + " FROM quadra"
                + " WHERE codigo_quadra = ?";

        try (Connection conexao = conexaoBD.getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, codigo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int codigoQuadra = rs.getInt("codigo_quadra");
                    String nome = rs.getString("nome_quadra");
                    int tipoQuadra = rs.getInt("tipoquadra_quadra");
                    double tamanho = rs.getDouble("tamanho_quadra");

                    quadra = new Quadra(codigoQuadra, nome, tamanho, tipoQuadra);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        }

        return quadra;
    }

    public Quadra localizarQuadraPorNome(String nomeQuadra) {
        Quadra quadra = null;
        String sql = "SELECT codigo_quadra, nome_quadra, tamanho_quadra, tipoquadra_quadra "
                + " FROM quadra"
                + " WHERE nome_quadra LIKE ? ";

        try (Connection conexao = conexaoBD.getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nomeQuadra);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int codigoQuadra = rs.getInt("codigo_quadra");
                    String nome = rs.getString("nome_quadra");
                    int tipo = rs.getInt("tipoquadra_quadra");
                    double tamanho = rs.getDouble("tamanho_quadra");

                    quadra = new Quadra(codigoQuadra, nome, tamanho, tipo);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        }

        return quadra;
    }
    
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