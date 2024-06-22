/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.model.service;

import com.mycompany.integrador.model.Funcionario;
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
public class FuncionarioService {
     Connection conexao = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public FuncionarioService() {
        conexao = ConectionPostgres.getConnection();
    }

    public Funcionario salvarPessoa(Funcionario funcionario) {
        String sql = "INSERT INTO pessoa (nome, idade, email, image_path) VALUES (?, ?, ?,?)";
        try {
            // Obtendo a conexão
            conexao = ConectionPostgres.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setInt(2, funcionario.getIdade());
            stmt.setString(3, funcionario.getEmail());

            // Executando o comando SQL
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getMessage());
        } finally {
            // Fechando os recursos
            try {
                if (stmt != null) {
                    stmt.close();
                }
                ConectionPostgres.fecharConexao(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return funcionario;
    }

    public Funcionario atualizarPessoa(Funcionario pessoa) {
        String sql = "UPDATE pessoa SET nome = ?, idade = ?, email = ?, image_path = ? WHERE id = ?";
        try {
            // Obtendo a conexão
            conexao = ConectionPostgres.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setInt(2, pessoa.getIdade());
            stmt.setString(3, pessoa.getEmail());
            stmt.setLong(5, pessoa.getId());

            // Executando o comando SQL
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getMessage());
        } finally {
            // Fechando os recursos
            try {
                if (stmt != null) {
                    stmt.close();
                }
                ConectionPostgres.fecharConexao(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return pessoa;
    }

    public List<Funcionario> listarPessoas() {
        String sql = "SELECT id, nome, idade, email,image_path FROM pessoa";
        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            // Obtendo a conexão
            conexao = ConectionPostgres.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);

            // Executando o comando SQL
            rs = stmt.executeQuery();

            // Processando os resultados
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String image_path = rs.getString("image_path");
                int idade = rs.getInt("idade");
                funcionarios.add(new Funcionario(id, nome, idade, email, image_path));
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
                ConectionPostgres.fecharConexao(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return funcionarios;
    }

    public Funcionario encontrarPessoaPorId(Long id) {
        Funcionario pessoa = new Funcionario();
        String sql = "SELECT id, nome, idade, email,image_path FROM pessoa WHERE id = ?";
        try {
            // Obtendo a conexão
            conexao = ConectionPostgres.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);

            // Executando o comando SQL
            rs = stmt.executeQuery();

            // Processando o resultado
            if (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String image_path = rs.getString("image_path");
                int idade = rs.getInt("idade");
                pessoa = new Funcionario(nome, idade, email, image_path);
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
                ConectionPostgres.fecharConexao(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return pessoa;
    }

    public boolean excluirPessoa(Long id) {
        String sql = "DELETE FROM pessoa WHERE id = ?";
        try {
            // Obtendo a conexão
            conexao = ConectionPostgres.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);

            // Executando o comando SQL
            int linhasAfetadas = stmt.executeUpdate();

            // Retorna verdadeiro se uma linha foi afetada, falso caso contrário
                return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao deletar dados: " + e.getMessage());
            return false;
        } finally {
            // Fechando os recursos
            try {
                if (stmt != null) {
                    stmt.close();
                }
                ConectionPostgres.fecharConexao(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
}
