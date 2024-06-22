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

public class FuncionarioService {
    Connection conexao = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public FuncionarioService() {
        conexao = ConectionPostgres.getConnection();
    }
    
      public boolean verificarCredenciais(Long id, String senha) {
        String sql = "SELECT COUNT(*) FROM funcionario WHERE codigo_funcionario = ? AND senha_funcionario = ?";
        try {
            conexao = ConectionPostgres.getConnection();
            stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0; // Devuelve true si se encuentra un registro
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar credenciais: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                ConectionPostgres.fecharConexao(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return false;
      }
    
    
    public Funcionario salvarFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (cpf_funcionario, nome_funcionario, telefone_funcionario, email_funcionario, senha_funcionario, endereco_funcionario) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, funcionario.getCpf());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getTelefone());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getSenha());
            stmt.setLong(6, funcionario.getEnderecoId());

            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getMessage());
        } finally {
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

    public Funcionario atualizarFuncionario(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET cpf_funcionario = ?, nome_funcionario = ?, telefone_funcionario = ?, email_funcionario = ?, senha_funcionario = ?, endereco_funcionario = ? WHERE codigo_funcionario = ?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, funcionario.getCpf());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getTelefone());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getSenha());
            stmt.setLong(6, funcionario.getEnderecoId());
            stmt.setLong(7, funcionario.getId());

            stmt.executeUpdate();
            System.out.println("Dados atualizados com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar dados: " + e.getMessage());
        } finally {
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

    public List<Funcionario> listarFuncionarios() {
        String sql = "SELECT codigo_funcionario, cpf_funcionario, nome_funcionario, telefone_funcionario, email_funcionario, senha_funcionario, endereco_funcionario FROM funcionario";
        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("codigo_funcionario");
                String cpf = rs.getString("cpf_funcionario");
                String nome = rs.getString("nome_funcionario");
                String telefone = rs.getString("telefone_funcionario");
                String email = rs.getString("email_funcionario");
                String senha = rs.getString("senha_funcionario");
                Long enderecoId = rs.getLong("endereco_funcionario");

                Funcionario funcionario = new Funcionario(id, cpf, nome, telefone, email, senha, enderecoId);
                funcionarios.add(funcionario);
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

        return funcionarios;
    }

    public Funcionario encontrarFuncionarioPorId(Long id) {
        Funcionario funcionario = null;
        String sql = "SELECT codigo_funcionario, cpf_funcionario, nome_funcionario, telefone_funcionario, email_funcionario, senha_funcionario, endereco_funcionario FROM funcionario WHERE codigo_funcionario = ?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String cpf = rs.getString("cpf_funcionario");
                String nome = rs.getString("nome_funcionario");
                String telefone = rs.getString("telefone_funcionario");
                String email = rs.getString("email_funcionario");
                String senha = rs.getString("senha_funcionario");
                Long enderecoId = rs.getLong("endereco_funcionario");

                funcionario = new Funcionario(id, cpf, nome, telefone, email, senha, enderecoId);
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

        return funcionario;
    }

    public boolean excluirFuncionario(Long id) {
        String sql = "DELETE FROM funcionario WHERE codigo_funcionario = ?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao deletar dados: " + e.getMessage());
            return false;
        } finally {
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

