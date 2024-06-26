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
    
    
        public List<Evento> listarEventos(String dataSelected) {
        String sql = "SELECT id, data, horaEntrada, horaSaida, descricao, quadra FROM evento WHERE data = ?";
        List<Evento> eventos = new ArrayList<>();

        try {
            conexao = ConectionPostgres.getConnection();
            stmt = conexao.prepareStatement(sql);

            // Converter String para Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date data = dateFormat.parse(dataSelected);

            // Definir o parâmetro da consulta
            stmt.setDate(1, new java.sql.Date(data.getTime()));
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Date dataEvento = rs.getDate("data");
                String horaEntrada = rs.getString("horaEntrada");
                String horaSaida = rs.getString("horaSaida");
                String descricao = rs.getString("descricao");
                String quadra = rs.getString("quadra");
                eventos.add(new Evento(id, dataEvento, horaEntrada, horaSaida, descricao, quadra));
            }

        } catch (SQLException | java.text.ParseException e) {
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
        
                public void apagarEventos(String dataSelected, String descricao) {
        String sql = "DELETE FROM evento WHERE data = ? AND descricao = ?";

        try {
            conexao = ConectionPostgres.getConnection();
            stmt = conexao.prepareStatement(sql);

            // Converter a string de data para o tipo java.sql.Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(dataSelected);
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

            // Definir os parâmetros da consulta
            stmt.setDate(1, sqlDate);
            stmt.setString(2, descricao);

            // Executar a instrução de delete
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Número de registros deletados: " + rowsAffected);

        } catch (SQLException | java.text.ParseException e) {
            System.err.println("Erro ao deletar dados: " + e.getMessage());
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
  



    