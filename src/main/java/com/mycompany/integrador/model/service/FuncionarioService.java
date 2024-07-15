/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.model.service;

import com.mycompany.integrador.model.Funcionario;
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
 * @author jose.zanandrea
 */
public class FuncionarioService {

    Connection conexao = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public FuncionarioService() {
        conexao = conexaoBD.getConnection();
    }

    public Funcionario salvarFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (cpf_funcionario, nome_funcionario, telefone_funcionario, email_funcionario, senha_funcionario,"
                + " endereco_funcionario, numero_funcionario, CEP_funcionario, bairro_funcionario, cidade_funcionario, confirmaSenha_funcionario) VALUES (?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            // Obtendo a conexão
            conexao = conexaoBD.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, funcionario.getCPF());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getTelefone());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getSenha());
            stmt.setString(6, funcionario.getEndereco());
            stmt.setString(7, funcionario.getNumero());
            stmt.setString(8, funcionario.getCEP());
            stmt.setString(9, funcionario.getBairro());
            stmt.setInt(10, funcionario.getCidade());
            stmt.setString(11, funcionario.getConfirmaSenha());

            // Executando o comando SQL
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getMessage());
        } finally {
            fecharRecursos();
        }
        return funcionario;
    }

    public Funcionario atualizarFuncionario(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET codigo_funcionario = ?, cpf_funcionario = ?, nome_funcionario = ?, telefone_funcionario = ?, email_funcionario = ?, "
                + " senha_funcionario =?, endereco_funcionario = ?, numero_funcionario = ?, CEP_funcionario = ?, bairro_funcionario = ?, cidade_funcionario = ?, confirmasenha_funcionario = ? "
                + " WHERE codigo_funcionario = ?";
        try {
            // Obtendo a conexão
            conexao = conexaoBD.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, funcionario.getCodigo());
            stmt.setString(2, funcionario.getCPF());
            stmt.setString(3, funcionario.getNome());
            stmt.setString(4, funcionario.getTelefone());
            stmt.setString(5, funcionario.getEmail());
            stmt.setString(6, funcionario.getSenha());
            stmt.setString(7, funcionario.getEndereco());
            stmt.setString(8, funcionario.getNumero());
            stmt.setString(9, funcionario.getCEP());
            stmt.setString(10, funcionario.getBairro());
            stmt.setInt(11, funcionario.getCidade());
            stmt.setString(12, funcionario.getConfirmaSenha());

            //condição where
            stmt.setInt(13, funcionario.getCodigo());

            // Executando o comando SQL
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getMessage());
        } finally {
            fecharRecursos();
        }
        return funcionario;
    }

    public List<Funcionario> listarFuncionarios() {
        String sql = "SELECT codigo_funcionario, nome_funcionario, CPF_funcionario, email_funcionario, telefone_funcionario, confirmaSenha_funcionario, "
                + " endereco_funcionario, numero_funcionario, cep_funcionario, bairro_funcionario, cidade_funcionario, senha_funcionario "
                + " FROM funcionario";
        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            // Obtendo a conexão
            conexao = conexaoBD.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);

            // Executando o comando SQL
            rs = stmt.executeQuery();

            // Processando os resultados
            while (rs.next()) {
                int codigo = rs.getInt("codigo_funcionario");
                String nome = rs.getString("nome_funcionario");
                String email = rs.getString("email_funcionario");
                String CPF = rs.getString("CPF_funcionario");
                String telefone = rs.getString("telefone_funcionario");
                String senha = rs.getString("senha_funcionario");
                String confirmaSenha = rs.getString("confirmaSenha_funcionario");
                String endereco = rs.getString("endereco_funcionario");
                String numero = rs.getString("numero_funcionario");
                String CEP = rs.getString("CEP_funcionario");
                String bairro = rs.getString("bairro_funcionario");
                int cidade = rs.getInt("cidade_funcionario");

                funcionarios.add(new Funcionario(senha, confirmaSenha, codigo, nome, CPF, telefone, email, endereco, numero, CEP, bairro, cidade));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        } finally {
            fecharRecursos();
        }

        return funcionarios;
    }

    public Funcionario localizarFuncionarioPorCodigo(int codigo) {
        Funcionario funcionario = null;
        String sql = "SELECT codigo_funcionario, nome_funcionario, CPF_funcionario, email_funcionario, telefone_funcionario, "
                + " senha_funcionario, endereco_funcionario, numero_funcionario, cep_funcionario, bairro_funcionario, cidade_funcionario, "
                + " confirmaSenha_funcionario FROM funcionario"
                + " WHERE codigo_funcionario = ?";

        try (Connection conexao = conexaoBD.getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, codigo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int codigoFuncionario = rs.getInt("codigo_funcionario");
                    String senha = rs.getString("senha_funcionario");
                    String confirmaSenha = rs.getString("confirmaSenha_funcionario");
                    String nome = rs.getString("nome_funcionario");
                    String CPF = rs.getString("CPF_funcionario");
                    String telefone = rs.getString("telefone_funcionario");
                    String email = rs.getString("email_funcionario");
                    String endereco = rs.getString("endereco_funcionario");
                    String numero = rs.getString("numero_funcionario");
                    String CEP = rs.getString("CEP_funcionario");
                    String bairro = rs.getString("bairro_funcionario");
                    int cidade = rs.getInt("cidade_funcionario");
                    funcionario = new Funcionario(senha, confirmaSenha, codigoFuncionario, nome, CPF, telefone, email, endereco, numero, CEP, bairro, cidade);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        }

        return funcionario;
    }

    public Funcionario localizarFuncionarioPorNome(String nomeFuncionario) {
        Funcionario funcionario = null;
        String sql = "SELECT codigo_funcionario, nome_funcionario, CPF_funcionario, email_funcionario, telefone_funcionario, "
                + " senha_funcionario, endereco_funcionario, numero_funcionario, cep_funcionario, bairro_funcionario, cidade_funcionario, "
                + " confirmaSenha_funcionario FROM funcionario"
                + " WHERE nome_funcionario LIKE ? ";

        try (Connection conexao = conexaoBD.getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nomeFuncionario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int codigoFuncionario = rs.getInt("codigo_funcionario");
                    String senha = rs.getString("senha_funcionario");
                    String confirmaSenha = rs.getString("confirmaSenha_funcionario");
                    String nome = rs.getString("nome_funcionario");
                    String CPF = rs.getString("CPF_funcionario");
                    String telefone = rs.getString("telefone_funcionario");
                    String email = rs.getString("email_funcionario");
                    String endereco = rs.getString("endereco_funcionario");
                    String numero = rs.getString("numero_funcionario");
                    String CEP = rs.getString("CEP_funcionario");
                    String bairro = rs.getString("bairro_funcionario");
                    int cidade = rs.getInt("cidade_funcionario");
                    funcionario = new Funcionario(senha, confirmaSenha, codigoFuncionario, nome, CPF, telefone, email, endereco, numero, CEP, bairro, cidade);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        }

        return funcionario;
    }

    public Funcionario localizarFuncionarioPorCPF(String CPFFuncionario) {
        Funcionario funcionario = null;
        String sql = "SELECT codigo_funcionario, nome_funcionario, CPF_funcionario, email_funcionario, telefone_funcionario, "
                + " senha_funcionario, endereco_funcionario, numero_funcionario, cep_funcionario, bairro_funcionario, cidade_funcionario "
                + " confirmaSenha_funcionario FROM funcionario"
                + " WHERE CPF_funcionario LIKE ? ";

        try (Connection conexao = conexaoBD.getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, CPFFuncionario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int codigoFuncionario = rs.getInt("codigo_funcionario");
                    String senha = rs.getString("senha_funcionario");
                    String confirmaSenha = rs.getString("confirmaSenha_funcionario");
                    String nome = rs.getString("nome_funcionario");
                    String CPF = rs.getString("CPF_funcionario");
                    String telefone = rs.getString("telefone_funcionario");
                    String email = rs.getString("email_funcionario");
                    String endereco = rs.getString("endereco_funcionario");
                    String numero = rs.getString("numero_funcionario");
                    String CEP = rs.getString("CEP_funcionario");
                    String bairro = rs.getString("bairro_funcionario");
                    int cidade = rs.getInt("cidade_funcionario");
                    funcionario = new Funcionario(senha, confirmaSenha, codigoFuncionario, nome, CPF, telefone, email, endereco, numero, CEP, bairro, cidade);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        }

        return funcionario;
    }

    public boolean excluirFuncionario(int codigo) {
        String sql = "DELETE FROM funcionario WHERE codigo_funcionario = ?";
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

    public int getMaxCodigoFuncionario() {
        BuscarCodigoService service = new BuscarCodigoService();
        return service.getMaxCodigo("funcionario", "codigo_funcionario");
    }

    public boolean verificarCredenciais(Long id, String senha) {
        String sql = "SELECT COUNT(*) FROM funcionario WHERE codigo_funcionario = ? AND senha_funcionario = ?";
        try {
            conexao = conexaoBD.getConnection();
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
        return false;
    }

    public String getNome(int codigoFuncionario) {
        String sql = "SELECT nome_funcionario FROM funcionario WHERE codigo_funcionario = ?";
        String nomeFuncionario = null;

        try {
            // Obtendo a conexão
            conexao = conexaoBD.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigoFuncionario);

            // Executando o comando SQL
            rs = stmt.executeQuery();

            // Processando os resultados
            if (rs.next()) {
                nomeFuncionario = rs.getString("nome_funcionario");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        } finally {
            fecharRecursos();
        }

        return nomeFuncionario;
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
