/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.model.service;

import com.mycompany.integrador.model.Modalidade;
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
public class ModalidadeService {

    Connection conexao = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public Modalidade salvarModalidade(Modalidade modalidade) {
        String sql = "INSERT INTO modalidade (nome_modalidade, valor_modalidade) VALUES (?, ?)";
        try {
            // Obtendo a conexão
            conexao = conexaoBD.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, modalidade.getNome_modalidade());
            stmt.setFloat(2, modalidade.getValor_modalidade());

            // Executando o comando SQL
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getMessage());
        } finally {
            fecharRecursos();
        }
        return modalidade;
    }

    public Modalidade atualizarModalidade(Modalidade modalidade) {
        String sql = "UPDATE modalidade SET codigo_modalidade = ?, nome_modalidade = ?, valor_modalidade = ? "
                + " WHERE codigo_modalidade = ?";
        try {
            // Obtendo a conexão
            conexao = conexaoBD.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, modalidade.getCodigo_modalidade());
            stmt.setString(2, modalidade.getNome_modalidade());
            stmt.setFloat(3, modalidade.getValor_modalidade());

            //condição where
            stmt.setInt(4, modalidade.getCodigo_modalidade());

            // Executando o comando SQL
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getMessage());
        } finally {
            fecharRecursos();
        }
        return modalidade;
    }

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

                modalidades.add(new Modalidade(codigo, nome, valor));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        } finally {
            fecharRecursos();
        }

        return modalidades;
    }

    public int getMaxCodigoModalidade() {
        BuscarCodigoService service = new BuscarCodigoService();
        return service.getMaxCodigo("modalidade", "codigo_modalidade");
    }

    public boolean excluirModalidade(int codigo) {
        String sql = "DELETE FROM modalidade WHERE codigo_modalidade = ?";
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

    public Modalidade localizarModalidadePorCodigo(int codigo) {
        Modalidade modalidade = null;
        String sql = "SELECT codigo_modalidade, nome_modalidade, valor_modalidade "
                + " FROM modalidade"
                + " WHERE codigo_modalidade = ?";

        try (Connection conexao = conexaoBD.getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, codigo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int codigoModalidade = rs.getInt("codigo_modalidade");
                    String nome = rs.getString("nome_modalidade");
                    float valor = rs.getFloat("valor_modalidade");

                    modalidade = new Modalidade(codigoModalidade, nome, valor);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        }

        return modalidade;
    }

    public Modalidade localizarModalidadePorNome(String nomeModalidade) {
        Modalidade modalidade = null;
        String sql = "SELECT codigo_modalidade, nome_modalidade, valor_modalidade "
                + " FROM modalidade"
                + " WHERE nome_modalidade LIKE ? ";

        try (Connection conexao = conexaoBD.getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nomeModalidade);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int codigoModalidade = rs.getInt("codigo_modalidade");
                    String nome = rs.getString("nome_modalidade");
                    float valor = rs.getFloat("valor_modalidade");

                    modalidade = new Modalidade(codigoModalidade, nome, valor);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        }

        return modalidade;
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

    
    
    
        public Modalidade listarModalidadeEspecifica(int codigoModalidade) {
        String sql = "SELECT * FROM modalidade WHERE codigo_modalidade = ?";
        Modalidade modalidade = null;
        try {
            // Obtendo a conexão
            conexao = conexaoBD.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigoModalidade);
            // Executando o comando SQL
            rs = stmt.executeQuery();

            // Processando os resultados
            while (rs.next()) {
                int codigo = rs.getInt("codigo_modalidade");
                String nome = rs.getString("nome_modalidade");
                float valor = rs.getFloat("valor_modalidade");

                modalidade = new Modalidade(codigo, nome,valor );
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

        return modalidade;
    }
                 
}
