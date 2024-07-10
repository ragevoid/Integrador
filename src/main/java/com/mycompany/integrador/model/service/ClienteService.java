/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.model.service;

import com.mycompany.integrador.model.Cliente;
import com.mycompany.integrador.util.BuscarCodigoService;
import com.mycompany.integrador.util.conexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
                + "endereco_cliente = ?, numero_cliente = ?, CEP_cliente = ?, bairro_cliente = ?, cidade_cliente = ? "
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
        String sql = "SELECT codigo_cliente, nome_cliente, cpf_cliente, email_cliente, telefone_cliente, datanascimento_cliente, "
                + " endereco_cliente, numero_cliente, cep_cliente, bairro_cliente, cidade_cliente "
                + " FROM cliente";
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
                int codigo = rs.getInt("codigo_cliente");
                String nome = rs.getString("nome_cliente");
                String email = rs.getString("email_cliente");
                String CPF = rs.getString("cpf_cliente");
                String telefone = rs.getString("telefone_cliente");
                Date dataNascimento = rs.getDate("datanascimento_cliente");
                String endereco = rs.getString("endereco_cliente");
                String numero = rs.getString("numero_cliente");
                String CEP = rs.getString("cep_cliente");
                String bairro = rs.getString("bairro_cliente");
                int cidade = rs.getInt("cidade_cliente");

                clientes.add(new Cliente(codigo, nome, CPF, telefone, email, endereco, numero, CEP, bairro, cidade,dataNascimento ));
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

        return clientes;
    }
    
    public Cliente localizarClientePorCodigo(int codigo) {
        Cliente cliente = null;
        String sql = "SELECT codigo_cliente, nome_cliente, CPF_cliente, email_cliente, telefone_cliente, datanascimento_cliente,  "
                + " senha_cliente, endereco_cliente, numero_cliente, cep_cliente, bairro_cliente, cidade_cliente, "
                + " FROM cliente"
                + " WHERE codigo_cliente = ?";

        try (Connection conexao = conexaoBD.getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, codigo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int codigoFuncionario = rs.getInt("codigo_funcionario");
                    String nome = rs.getString("nome_funcionario");
                    String CPF = rs.getString("CPF_funcionario");
                    String telefone = rs.getString("telefone_funcionario");
                    String email = rs.getString("email_funcionario");
                    Date dataNascimento = rs.getDate("datanascimento_cliente");
                    String endereco = rs.getString("endereco_funcionario");
                    String numero = rs.getString("numero_funcionario");
                    String CEP = rs.getString("CEP_funcionario");
                    String bairro = rs.getString("bairro_funcionario");
                    int cidade = rs.getInt("cidade_funcionario");
                    cliente = new Cliente(codigoFuncionario, nome, CPF, telefone, email, endereco, numero, CEP, bairro, cidade,dataNascimento);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        }

        return cliente;
    }
    
    public Cliente localizarClientePorNome(String nomeCliente) {
        Cliente cliente = null;
        String sql = "SELECT codigo_cliente, nome_cliente, CPF_cliente, email_cliente, telefone_cliente, datanascimento_cliente,  "
                + " senha_cliente, endereco_cliente, numero_cliente, cep_cliente, bairro_cliente, cidade_cliente, "
                + " FROM cliente"
                + " WHERE nome_cliente LIKE ? ";

        try (Connection conexao = conexaoBD.getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nomeCliente);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int codigoFuncionario = rs.getInt("codigo_funcionario");
                    String nome = rs.getString("nome_funcionario");
                    String CPF = rs.getString("CPF_funcionario");
                    String telefone = rs.getString("telefone_funcionario");
                    String email = rs.getString("email_funcionario");
                    Date dataNascimento = rs.getDate("datanascimento_cliente");
                    String endereco = rs.getString("endereco_funcionario");
                    String numero = rs.getString("numero_funcionario");
                    String CEP = rs.getString("CEP_funcionario");
                    String bairro = rs.getString("bairro_funcionario");
                    int cidade = rs.getInt("cidade_funcionario");
                    cliente = new Cliente(codigoFuncionario, nome, CPF, telefone, email, endereco, numero, CEP, bairro, cidade, dataNascimento);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        }

        return cliente;
    }
    
    public Cliente localizarClientePorCPF(String CPFCliente) {
        Cliente cliente = null;
        String sql = "SELECT codigo_cliente, nome_cliente, CPF_cliente, email_cliente, telefone_cliente, datanascimento_cliente,  "
                + " senha_cliente, endereco_cliente, numero_cliente, cep_cliente, bairro_cliente, cidade_cliente, "
                + " FROM cliente"
                + " WHERE CPF_cliente LIKE ? ";

        try (Connection conexao = conexaoBD.getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, CPFCliente);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int codigoFuncionario = rs.getInt("codigo_funcionario");
                    String nome = rs.getString("nome_funcionario");
                    String CPF = rs.getString("CPF_funcionario");
                    String telefone = rs.getString("telefone_funcionario");
                    String email = rs.getString("email_funcionario");
                    Date dataNascimento = rs.getDate("datanascimento_cliente");
                    String endereco = rs.getString("endereco_funcionario");
                    String numero = rs.getString("numero_funcionario");
                    String CEP = rs.getString("CEP_funcionario");
                    String bairro = rs.getString("bairro_funcionario");
                    int cidade = rs.getInt("cidade_funcionario");
                    cliente = new Cliente(codigoFuncionario, nome, CPF, telefone, email, endereco, numero, CEP, bairro, cidade, dataNascimento);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        }

        return cliente;
    }
    
    public boolean excluirCliente(int codigo) {
        String sql = "DELETE FROM cliente WHERE codigo_cliente = ?";
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
    
    public int getMaxCodigoCliente() {
        BuscarCodigoService service = new BuscarCodigoService();
        return service.getMaxCodigo("cliente", "codigo_cliente");
    }

}

