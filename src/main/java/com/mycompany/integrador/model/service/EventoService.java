/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.model.service;
import com.mycompany.integrador.model.Evento;
import com.mycompany.integrador.util.ConectionPostgres;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        conexao = ConectionPostgres.getConnection();
    }
    
    public Evento salvarEvento(Evento evento) {
    String sql = "INSERT INTO evento (data, horaEntrada, horaSaida, descricao, quadra) VALUES (?, ?, ?, ?, ?)";
    try {
        stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setDate(1, new java.sql.Date(evento.getData().getTime()));
        stmt.setString(2, evento.getHoraEntrada());
        stmt.setString(3, evento.getHoraSaida());
        stmt.setString(4, evento.getDescricao());
        stmt.setString(5, evento.getQuadra());

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
            ConectionPostgres.fecharConexao(conexao);
        } catch (SQLException e) {
            System.err.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }
    return evento;
}
    
    
        public List<Evento> listarEventos() {
        String sql = "SELECT id, data, hora_entrada, hora_saida, descricao, quadra FROM evento";
        List<Evento> eventos = new ArrayList<>();

        try {
            conexao = ConectionPostgres.getConnection();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Date data = rs.getDate("data");
                String horaEntrada = rs.getString("hora_entrada");
                String horaSaida = rs.getString("hora_saida");
                String descricao = rs.getString("descricao");
                String quadra = rs.getString("quadra");
                eventos.add(new Evento(id, data, horaEntrada, horaSaida, descricao, quadra));
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

        return eventos;
    }
}
    
    