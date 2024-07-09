/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.model.service;

import com.mycompany.integrador.model.Cliente;
import com.mycompany.integrador.model.Funcionario;
import com.mycompany.integrador.util.BuscarCodigoService;
import com.mycompany.integrador.util.ConectionPostgres;
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
public class ClienteService {
    
    Connection conexao = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public ClienteService() {
        conexao = conexaoBD.getConnection();
    }

    public Cliente salvarCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (cpf_cliente, nome_cliente, telefone_cliente, email_cliente, datanascimento_cliente,"
                + " endereco_cliente, numero_cliente, CEP_cliente, bairro_cliente, cidade_cliente) VALUES (?, ?, ?,?, ?, ?, ?, ?, ?, ?)";
        try {
            // Obtendo a conexão
            conexao = conexaoBD.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cliente.getCPF());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            java.util.Date dataUtil = cliente.getDataNascimento();
            java.sql.Date dataSQL = new java.sql.Date(dataUtil.getTime());
            stmt.setDate(5, dataSQL);
            stmt.setString(6, cliente.getEndereco());
            stmt.setString(7, cliente.getNumero());
            stmt.setString(8, cliente.getCEP());
            stmt.setString(9, cliente.getBairro());
            stmt.setInt(10, cliente.getCidade());


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
                conexaoBD.fecharConexao(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return cliente;
    }
    
        public Cliente atualizarCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET cpf_cliente = ?, nome_cliente = ?, telefone_cliente = ?, email_cliente = ?, datanascimento_cliente, "
                + "endereco_funcionario = ?, numero_funcionario = ?, CEP_funcionario = ?, bairro_funcionario = ?, cidade_funcionario = ? "
                + " WHERE codigo_cliente = ?";
        try {
            // Obtendo a conexão
            conexao = conexaoBD.getConnection();

            // Preparando a instrução SQL
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cliente.getCPF());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            java.util.Date dataUtil = cliente.getDataNascimento();
            java.sql.Date dataSQL = new java.sql.Date(dataUtil.getTime());
            stmt.setDate(5, dataSQL);
            stmt.setString(6, cliente.getEndereco());
            stmt.setString(7, cliente.getNumero());
            stmt.setString(8, cliente.getCEP());
            stmt.setString(9, cliente.getBairro());
            stmt.setInt(10, cliente.getCidade());

            
            //condição where
            stmt.setInt(11, cliente.getCodigo());

            // Executando o comando SQL
            stmt.executeUpdate();
            System.out.println("Dados atualizados com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getMessage());
        } finally {
            // Fechando os recursos
            try {
                if (stmt != null) {
                    stmt.close();
                }
                conexaoBD.fecharConexao(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return cliente;
    }

    public List<Cliente> listarClientes() {
        String sql = "SELECT codigo_funcionario, nome_funcionario, CPF_funcionario, email_funcionario, telefone_funcionario, "
                + " endereco_funcionario, numero_funcionario, cep_funcionario, bairro_funcionario, cidade_funcionario "
                + " FROM funcionario";
        List<Cliente> clientes = new ArrayList<>();

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

                String endereco = rs.getString("endereco_funcionario");
                String numero = rs.getString("numero_funcionario");
                String CEP = rs.getString("CEP_funcionario");
                String bairro = rs.getString("bairro_funcionario");
                int cidade = rs.getInt("cidade_funcionario");

                clientes.add(new Cliente(codigo, nome, CPF, telefone, email, endereco, numero, CEP, bairro, cidade, ));
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
                + " senha_funcionario, endereco_funcionario, numero_funcionario, cep_funcionario, bairro_funcionario, cidade_funcionario, "
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
            // Fechando os recursos
            try {
                if (stmt != null) {
                    stmt.close();
                }
                conexaoBD.fecharConexao(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
    
    public int getMaxCodigoFuncionario() {
        BuscarCodigoService service = new BuscarCodigoService();
        return service.getMaxCodigo("funcionario", "codigo_funcionario");
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
                conexaoBD.fecharConexao(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return false;
      }

}

