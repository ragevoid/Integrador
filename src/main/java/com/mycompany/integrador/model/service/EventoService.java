/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.model.service;

import com.mycompany.integrador.model.Evento;
import com.mycompany.integrador.util.conexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public class EventoService {

    Connection conexao = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public EventoService() {
        conexao = conexaoBD.getConnection();
    }

    public Time formatTime(String timeString) {
        // Adicionar segundos se necessário para garantir o formato correto
        if (timeString.length() == 5) {
            timeString = timeString + ":00";
        }
        return Time.valueOf(timeString);
    }

    public Evento salvarEvento(Evento evento) {

        String sql = "INSERT INTO evento (data_evento, horaEntrada_evento, horaSaida_evento, descripcao_evento, codigo_quadra, codigo_cliente, codigo_modalidade) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, new java.sql.Date(evento.getData().getTime()));
            stmt.setTime(2, formatTime(evento.getHoraEntrada()));
            stmt.setTime(3, formatTime(evento.getHoraSaida()));
            stmt.setString(4, evento.getDescricao());
            stmt.setInt(5, evento.getCodigo_quadra());
            stmt.setInt(6, evento.getCodigo_cliente());
            stmt.setInt(7, evento.getCodigo_modalidade());

            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");

            // Obter o ID gerado
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                evento.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                conexaoBD.fecharConexao(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return evento;
    }

    public List<Evento> listarEventos(String dataSelected, int quadra) {
        String sql = "SELECT e.codigo_evento, e.data_evento, e.horaEntrada_evento, e.horaSaida_evento, e.descripcao_evento, "
                + "q.nome_quadra, c.nome_cliente, m.nome_modalidade "
                + "FROM evento e "
                + "JOIN quadra q ON e.codigo_quadra = q.codigo_quadra "
                + "JOIN cliente c ON e.codigo_cliente = c.codigo_cliente "
                + "JOIN modalidade m ON e.codigo_modalidade = m.codigo_modalidade "
                + "WHERE e.data_evento = ? AND e.codigo_quadra = ?";
        List<Evento> eventos = new ArrayList<>();

        try {
            conexao = conexaoBD.getConnection();
            stmt = conexao.prepareStatement(sql);

            // Converter String para Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(dataSelected);
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

            // Definir os parâmetros da consulta
            stmt.setDate(1, sqlDate);
            stmt.setInt(2, quadra);

            rs = stmt.executeQuery();

            while (rs.next()) {
                int codigo_evento = rs.getInt("codigo_evento");
                Date dataEvento = rs.getDate("data_evento");
                String horaEntrada = rs.getString("horaEntrada_evento");
                String horaSaida = rs.getString("horaSaida_evento");
                String descricao = rs.getString("descripcao_evento");
                String nomeQuadra = rs.getString("nome_quadra");
                String nomeCliente = rs.getString("nome_cliente");
                String nomeModalidade = rs.getString("nome_modalidade");

                eventos.add(new Evento(codigo_evento, dataEvento, horaEntrada, horaSaida, descricao, nomeQuadra, nomeCliente, nomeModalidade));
            }

        } catch (SQLException | ParseException e) {
            System.err.println("Erro ao selecionar dados: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conexao != null) {
                    conexaoBD.fecharConexao(conexao);
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return eventos;
    }

    public void apagarEventos(int codigo_evento) {
        String sql = "DELETE FROM evento WHERE codigo_evento = ?";

        try {
            conexao = conexaoBD.getConnection();
            stmt = conexao.prepareStatement(sql);

            // Definir o parâmetro da consulta
            stmt.setInt(1, codigo_evento);

            // Executar a instrução de delete
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Número de registros deletados: " + rowsAffected);

        } catch (SQLException e) {
            System.err.println("Erro ao deletar dados: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conexao != null) {
                    conexaoBD.fecharConexao(conexao);
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    public boolean validarHora(Evento evento) {
        String sql = "SELECT COUNT(*) FROM evento WHERE data_evento = ? AND "
                + "((horaEntrada_evento BETWEEN ? AND ?) OR "
                + "(horaSaida_evento BETWEEN ? AND ?) OR "
                + "(? BETWEEN horaEntrada_evento AND horaSaida_evento) OR "
                + "(? BETWEEN horaEntrada_evento AND horaSaida_evento));";

        boolean isHoraDisponivel = true;
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Time horaEntrada = formatTime(evento.getHoraEntrada());
            Time horaSaida = formatTime(evento.getHoraSaida());

            conexao = conexaoBD.getConnection();
            stmt = conexao.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(evento.getData().getTime()));
            stmt.setTime(2, horaEntrada);
            stmt.setTime(3, horaSaida);
            stmt.setTime(4, horaEntrada);
            stmt.setTime(5, horaSaida);
            stmt.setTime(6, horaEntrada);
            stmt.setTime(7, horaSaida);

            rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                isHoraDisponivel = false;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao validar hora: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conexao != null) {
                    conexaoBD.fecharConexao(conexao);
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return isHoraDisponivel;
    }

}
