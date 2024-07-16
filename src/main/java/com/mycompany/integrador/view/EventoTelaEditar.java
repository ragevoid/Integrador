/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.integrador.view;

import com.mycompany.integrador.model.Cliente;
import com.mycompany.integrador.model.Evento;
import com.mycompany.integrador.model.Modalidade;
import com.mycompany.integrador.model.service.ClienteService;
import com.mycompany.integrador.model.service.EventoService;
import com.mycompany.integrador.model.service.ModalidadeService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ricardo.gonzalez
 */
public class EventoTelaEditar extends javax.swing.JFrame {

    private List<Evento> eventos;
    private EventoService eventoService;
    private List<Cliente> clientes;
    private ClienteService clienteService;
    private List<Modalidade> modalidades;
    private ModalidadeService modalidadeService;


    /**
     * Creates new form EventoTela
     */
    public EventoTelaEditar() {
        initComponents();
        this.setLocationRelativeTo(null);
        eventoService = new EventoService();
        clientes = new ArrayList<>();
        clienteService = new ClienteService();
        modalidades = new ArrayList<>();
        modalidadeService = new ModalidadeService();
     

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        editarButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionText = new javax.swing.JTextArea();
        dataPicker = new com.github.lgooddatepicker.components.DatePicker();
        horaEntradaPicker = new com.github.lgooddatepicker.components.TimePicker();
        horaSaidaPicker = new com.github.lgooddatepicker.components.TimePicker();
        quadraLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        modalidadeCombo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        clienteCombo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        codigoEventoLabel = new javax.swing.JLabel();
        atualizarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editarButton.setText("Editar Evento");
        editarButton.setBackground(new java.awt.Color(0, 153, 204));
        editarButton.setBorderPainted(false);
        editarButton.setForeground(new java.awt.Color(51, 51, 51));
        editarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarButtonActionPerformed(evt);
            }
        });
        jPanel1.add(editarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 470, 150, 50));

        descriptionText.setColumns(20);
        descriptionText.setRows(5);
        jScrollPane1.setViewportView(descriptionText);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 380, -1));
        jPanel1.add(dataPicker, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, -1, -1));
        jPanel1.add(horaEntradaPicker, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 200, -1));
        jPanel1.add(horaSaidaPicker, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 200, -1));

        quadraLabel.setText("1 - Quadra 1");
        quadraLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        quadraLabel.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.add(quadraLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 200, 40));

        jLabel2.setText("Eventos para:");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 160, 40));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 410, -1));

        modalidadeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        modalidadeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modalidadeComboActionPerformed(evt);
            }
        });
        jPanel1.add(modalidadeCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 200, -1));

        jLabel1.setText("Cliente");
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, -1, -1));

        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Data");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, -1));

        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Hora Entrada");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, -1));

        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Hora Saida");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        clienteCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(clienteCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 200, -1));

        jLabel6.setText("Modalidade");
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, -1, -1));

        codigoEventoLabel.setText("5");
        codigoEventoLabel.setEnabled(false);
        jPanel1.add(codigoEventoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 280, 70, 30));

        atualizarButton.setText("Carregar dados");
        atualizarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarButtonActionPerformed(evt);
            }
        });
        jPanel1.add(atualizarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 120, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarButtonActionPerformed
        // TODO add your handling code here:

       try {
        LocalDate localDate = dataPicker.getDate();
        Date data = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        String horaEntrada = horaEntradaPicker.getTimeStringOrEmptyString();
        String horaSaida = horaSaidaPicker.getTimeStringOrEmptyString();
        String descricao = descriptionText.getText();
        String quadra = quadraLabel.getText();
        int codigoQuadra = pegarId(quadra);
        String cliente = clienteCombo.getSelectedItem().toString();
        int codigoCliente = pegarId(cliente);
        String modalidade = modalidadeCombo.getSelectedItem().toString();
        int codigoModalidade = pegarId(modalidade);
        int codigo_evento = Integer.parseInt(codigoEventoLabel.getText());


        Evento evento = new Evento(codigo_evento, data, horaEntrada, horaSaida, descricao, codigoQuadra, codigoCliente, codigoModalidade);

        boolean sucesso = eventoService.atualizarEvento(evento);
        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Evento atualizado com sucesso!");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar evento.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao processar atualização: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_editarButtonActionPerformed

    private void modalidadeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modalidadeComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modalidadeComboActionPerformed

    private void atualizarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarButtonActionPerformed
        // TODO add your handling code here:
        popularEvento(Integer.parseInt(codigoEventoLabel.getText()));
    }//GEN-LAST:event_atualizarButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EventoTelaEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EventoTelaEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EventoTelaEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EventoTelaEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EventoTelaEditar().setVisible(true);
            }
        });
    }

    public static boolean comprobarHoras(String hora1, String hora2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        try {
            LocalTime time1 = LocalTime.parse(hora1, formatter);
            LocalTime time2 = LocalTime.parse(hora2, formatter);

            // Retorna true si hora1 está después de hora2
            return time1.isAfter(time2);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de hora inválido");
            return false;  // O puedes lanzar una excepción, según tu preferencia
        }
    }

    public void popularComboboxModalidades() {
        modalidadeCombo.removeAllItems();
        modalidades = modalidadeService.listarModalidade();
        for (Modalidade modalidade : modalidades) {
            modalidadeCombo.addItem(modalidade.getCodigo_modalidade() + "-" + modalidade.getNome_modalidade());
        }
    }

    public void popularComboboxClientes() {
        clientes = clienteService.listarClientes();
        clienteCombo.removeAllItems();
        for (Cliente cliente : clientes) {
            clienteCombo.addItem(cliente.getCodigo() + "-" + cliente.getNome());
        }
    }

    public void popularEvento(int codigo_evento) {
        Evento evento = eventoService.getEvento(codigo_evento);

        // Converter java.sql.Date para LocalDate
        String dateString = evento.getData().toString();
        LocalDate localDate = LocalDate.parse(dateString);
        dataPicker.setDate(localDate);
        System.out.println(localDate);

        // Setar hora de entrada
        String horaEntrada = evento.getHoraEntrada();
        LocalTime horaEntrada_parsed = LocalTime.parse(horaEntrada);
        horaEntradaPicker.setTime(horaEntrada_parsed);

        // Setar hora de saída
        String horaSaida = evento.getHoraSaida();
        LocalTime horaSaida_parsed = LocalTime.parse(horaSaida);
        horaSaidaPicker.setTime(horaSaida_parsed);

        // Popular combobox de modalidades
        popularComboboxModalidades();

        // Setar modalidade selecionada
        int codigoModalidade = evento.getCodigo_modalidade();
        for (int i = 0; i < modalidadeCombo.getItemCount(); i++) {
            String item = (String) modalidadeCombo.getItemAt(i);
            if (item.startsWith(String.valueOf(codigoModalidade) + "-")) {
                modalidadeCombo.setSelectedIndex(i);
                break;
            }
        }

        // Popular combobox de clientes
        popularComboboxClientes();
        int codigoCliente = evento.getCodigo_cliente();
        for (int i = 0; i < clienteCombo.getItemCount(); i++) {
            String item = (String) clienteCombo.getItemAt(i);
            if (item.startsWith(String.valueOf(codigoCliente) + "-")) {
                clienteCombo.setSelectedIndex(i);
                break;
            }
        }

        // Setar descrição
        descriptionText.setText(evento.getDescricao());
    }

    public int pegarId(String string) {
        if (string != null && !string.isEmpty()) {
            char idChar = string.charAt(0);
            return Integer.parseInt(String.valueOf(idChar));
        } else {
            throw new IllegalArgumentException("A string fornecida está vazia ou é nula.");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atualizarButton;
    private javax.swing.JComboBox<String> clienteCombo;
    public javax.swing.JLabel codigoEventoLabel;
    private com.github.lgooddatepicker.components.DatePicker dataPicker;
    private javax.swing.JTextArea descriptionText;
    private javax.swing.JButton editarButton;
    private com.github.lgooddatepicker.components.TimePicker horaEntradaPicker;
    private com.github.lgooddatepicker.components.TimePicker horaSaidaPicker;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox<String> modalidadeCombo;
    public javax.swing.JLabel quadraLabel;
    // End of variables declaration//GEN-END:variables
}
