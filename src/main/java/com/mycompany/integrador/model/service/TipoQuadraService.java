/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.model.service;

import com.mycompany.integrador.model.TipoQuadra;
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
 * @author ricardo.gonzalez
 */
public class TipoQuadraService {

    Connection conexao = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public TipoQuadra salvarTipoQuadra(TipoQuadra tipoQuadra) {
        String sql = "INSERT INTO tipoquadra (nome_tipoquadra) VALUES (?)";
        try {
            // Obtendo a conexão
            conexao = conexaoBD.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, tipoQuadra.getNome());

            // Executando o comando SQL
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getMessage());
        } finally {
            fecharRecursos();
        }
        return tipoQuadra;
    }

    public TipoQuadra atualizarTipoQuadra(TipoQuadra tipoQuadra) {
        String sql = "UPDATE tipoquadra SET codigo_tipoquadra = ?, nome_tipoquadra = ? "
                + " WHERE codigo_tipoquadra = ?";
        try {
            // Obtendo a conexão
            conexao = conexaoBD.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, tipoQuadra.getCodigo());
            stmt.setString(2, tipoQuadra.getNome());

            //condição where
            stmt.setInt(4, tipoQuadra.getCodigo());

            // Executando o comando SQL
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getMessage());
        } finally {
            fecharRecursos();
        }
        return tipoQuadra;
    }

    public List<TipoQuadra> listarTipoQuadra() {
        String sql = "SELECT * FROM tipoquadra";
        List<TipoQuadra> tipoquadras = new ArrayList<>();

        try {
            // Obtendo a conexão
            conexao = conexaoBD.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);

            // Executando o comando SQL
            rs = stmt.executeQuery();

            // Processando os resultados
            while (rs.next()) {
                int codigo = rs.getInt("codigo_tipoQuadra");
                String nome = rs.getString("nome_tipoQuadra");

                tipoquadras.add(new TipoQuadra(codigo, nome));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        } finally {
            fecharRecursos();
        }

        return tipoquadras;
    }

    public int getMaxCodigoTipoQuadra() {
        BuscarCodigoService service = new BuscarCodigoService();
        return service.getMaxCodigo("tipoquadra", "codigo_tipoquadra");
    }

    public boolean excluirTipoQuadra(int codigo) {
        String sql = "DELETE FROM tipoquadra WHERE codigo_tipoquadra = ?";
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

    public TipoQuadra localizarTipoQuadraPorCodigo(int codigo) {
        TipoQuadra tipoQuadra = null;
        String sql = "SELECT codigo_tipoquadra, nome_tipoquadra "
                + " FROM tipoquadra"
                + " WHERE codigo_tipoquadra = ?";

        try (Connection conexao = conexaoBD.getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, codigo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int codigoTipoQuadra = rs.getInt("codigo_tipoquadra");
                    String nome = rs.getString("nome_tipoquadra");

                    tipoQuadra = new TipoQuadra(codigoTipoQuadra, nome);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        }

        return tipoQuadra;
    }

    public TipoQuadra localizarTipoQuadraPorNome(String nomeTipoQuadra) {
        TipoQuadra tipoQuadra = null;
        String sql = "SELECT codigo_tipoquadra, nome_tipoquadra "
                + " FROM tipoquadra"
                + " WHERE nome_tipoquadra LIKE ? ";

        try (Connection conexao = conexaoBD.getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nomeTipoQuadra);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int codigoTipoQuadra = rs.getInt("codigo_tipoquadra");
                    String nome = rs.getString("nome_tipoquadra");

                    tipoQuadra = new TipoQuadra(codigoTipoQuadra, nome);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        }

        return tipoQuadra;
    }

    public String getNomeTipoQuadra(int codigoTipoQuadra) {
        String nomeTipoQuadra = null;
        String sql = "SELECT nome_tipoquadra FROM tipoquadra WHERE codigo_tipoquadra = ?";
        try {
            conexao = conexaoBD.getConnection();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigoTipoQuadra);
            rs = stmt.executeQuery();
            if (rs.next()) {
                nomeTipoQuadra = rs.getString("nome_tipoquadra");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        } finally {
            fecharRecursos();
        }
        return nomeTipoQuadra;
    }

    public int getCodigoTipoQuadra(String nome) {
        int codigoTipoQuadra = -1; // Valor padrão
        String sql = "SELECT codigo_tipoquadra FROM tipoquadra WHERE nome_tipoquadra LIKE ?";
        try {
            conexao = conexaoBD.getConnection();
            stmt = conexao.prepareStatement(sql);

            stmt.setString(1, nome);

            rs = stmt.executeQuery();
            if (rs.next()) {
                codigoTipoQuadra = rs.getInt("codigo_tipoquadra");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        } finally {
            fecharRecursos();
        }
        return codigoTipoQuadra;
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
