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
public class EventoTela extends javax.swing.JFrame {

    private List<Evento> eventos;
    private EventoService eventoService;
    private List<Cliente> clientes;
    private ClienteService clienteService;
    private List<Modalidade> modalidades;
    private ModalidadeService modalidadeService;

    /**
     * Creates new form EventoTela
     */
    public EventoTela() {
        initComponents();
        this.setLocationRelativeTo(null);
        eventos = new ArrayList<>();
        eventoService = new EventoService();
        clientes = new ArrayList<>();
        clienteService = new ClienteService();
        modalidades = new ArrayList<>();
        modalidadeService = new ModalidadeService();
        popularComboboxClientes();
        popularComboboxModalidades();
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
        jButton1 = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Adicionar Evento");
        jButton1.setBackground(new java.awt.Color(0, 153, 204));
        jButton1.setBorderPainted(false);
        jButton1.setForeground(new java.awt.Color(51, 51, 51));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 450, 150, 50));

        descriptionText.setColumns(20);
        descriptionText.setRows(5);
        jScrollPane1.setViewportView(descriptionText);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 380, -1));
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

        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Modalidade");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

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

        if (comprobarHoras(horaEntrada, horaSaida)) {
            JOptionPane.showMessageDialog(null, "Erro no digitamento da hora", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Evento evento = new Evento(data, horaEntrada, horaSaida, descricao, codigoQuadra, codigoCliente, codigoModalidade);

        if (!eventoService.validarHora(evento)) {
            eventoService.salvarEvento(evento);
            JOptionPane.showMessageDialog(null, "Evento salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            // this.dispose(); // Uncomment if you want to close the frame after saving
        } else {
            JOptionPane.showMessageDialog(null, "Erro: já existe uma hora marcada para esse dia", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void modalidadeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modalidadeComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modalidadeComboActionPerformed

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
            java.util.logging.Logger.getLogger(EventoTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EventoTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EventoTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EventoTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EventoTela().setVisible(true);
            }
        });
    }

    ;
    
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

    public void popularComboboxClientes() {
        clientes = clienteService.listarClientes();
        clienteCombo.removeAllItems();
        for (Cliente cliente : clientes) {
            clienteCombo.addItem(cliente.getCodigo() + "-" + cliente.getNome());
        }
    }

    public void popularComboboxModalidades() {
        modalidadeCombo.removeAllItems();
        modalidades = modalidadeService.listarModalidade();
        for (Modalidade modalidade : modalidades) {
            modalidadeCombo.addItem(modalidade.getCodigo_modalidade() + "-" + modalidade.getNome_modalidade());
        }
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
    private javax.swing.JComboBox<String> clienteCombo;
    private com.github.lgooddatepicker.components.DatePicker dataPicker;
    private javax.swing.JTextArea descriptionText;
    private com.github.lgooddatepicker.components.TimePicker horaEntradaPicker;
    private com.github.lgooddatepicker.components.TimePicker horaSaidaPicker;
    private javax.swing.JButton jButton1;
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
