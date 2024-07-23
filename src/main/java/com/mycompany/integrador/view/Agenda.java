package com.mycompany.integrador.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.mycompany.integrador.model.Evento;
import com.mycompany.integrador.model.service.EventoService;
import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ricardo
 */
public class Agenda extends javax.swing.JFrame {

    private List<Evento> eventos;
    private List<Evento> eventosMes;
    private EventoService eventoService;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Creates new form Agenda
     */
    public Agenda() {
        initComponents();
        eventoService = new EventoService();
        eventosMes = new ArrayList<>();
        mostrarDiasEventos();
        eventos = new ArrayList<>();
        this.setLocationRelativeTo(null);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        agendaBackGroundPanel = new javax.swing.JPanel();
        EventosPanelBackGround = new javax.swing.JPanel();
        scrollTableEventos = new javax.swing.JScrollPane();
        eventosTable = new javax.swing.JTable();
        quadraLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jSeparator1 = new javax.swing.JSeparator();
        eventosButtonPanel = new javax.swing.JPanel();
        adicionarEventoButton = new javax.swing.JButton();
        editarButton = new javax.swing.JButton();
        apagarEventoButton = new javax.swing.JButton();
        buttonVoltar = new javax.swing.JButton();
        atualizarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 420));

        agendaBackGroundPanel.setBackground(new java.awt.Color(51, 51, 51));
        agendaBackGroundPanel.setMaximumSize(new java.awt.Dimension(800, 420));
        agendaBackGroundPanel.setMinimumSize(new java.awt.Dimension(800, 420));
        agendaBackGroundPanel.setPreferredSize(new java.awt.Dimension(800, 420));
        agendaBackGroundPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        EventosPanelBackGround.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        eventosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Data", "Entrada", "Saida", "Descripção", "Modalidad", "Cliente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        eventosTable.setColumnSelectionAllowed(true);
        scrollTableEventos.setViewportView(eventosTable);
        eventosTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        EventosPanelBackGround.add(scrollTableEventos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 330));

        agendaBackGroundPanel.add(EventosPanelBackGround, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 490, 330));

        quadraLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        quadraLabel.setForeground(new java.awt.Color(204, 204, 204));
        quadraLabel.setText("1-Quadra A");
        quadraLabel.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                quadraLabelPropertyChange(evt);
            }
        });
        agendaBackGroundPanel.add(quadraLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 230, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Agenda para:");
        agendaBackGroundPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jCalendar1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jCalendar1PropertyChange(evt);
            }
        });
        zerarCalendar();
        agendaBackGroundPanel.add(jCalendar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 380, 330));
        zerarCalendar();
        agendaBackGroundPanel.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 930, 10));

        eventosButtonPanel.setLayout(new java.awt.GridLayout(1, 0));

        adicionarEventoButton.setText("Adicionar Evento");
        adicionarEventoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adicionarEventoButtonMouseClicked(evt);
            }
        });
        adicionarEventoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarEventoButtonActionPerformed(evt);
            }
        });
        eventosButtonPanel.add(adicionarEventoButton);

        editarButton.setText("Editar Evento");
        editarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarButtonActionPerformed(evt);
            }
        });
        eventosButtonPanel.add(editarButton);

        apagarEventoButton.setText("Apagar Evento");
        apagarEventoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apagarEventoButtonActionPerformed(evt);
            }
        });
        eventosButtonPanel.add(apagarEventoButton);

        agendaBackGroundPanel.add(eventosButtonPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 430, 490, 50));

        buttonVoltar.setBackground(new java.awt.Color(102, 102, 102));
        buttonVoltar.setForeground(new java.awt.Color(204, 204, 204));
        buttonVoltar.setText("Voltar");
        buttonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVoltarActionPerformed(evt);
            }
        });
        agendaBackGroundPanel.add(buttonVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 23, 190, 30));

        atualizarButton.setText("Atualizar");
        atualizarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarButtonActionPerformed(evt);
            }
        });
        agendaBackGroundPanel.add(atualizarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 380, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(agendaBackGroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 986, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(agendaBackGroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adicionarEventoButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adicionarEventoButtonMouseClicked
        // TODO add your handling code here

    }//GEN-LAST:event_adicionarEventoButtonMouseClicked

    private void apagarEventoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apagarEventoButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = eventosTable.getSelectedRow();
        if (selectedRow >= 0) {

            int codigo_evento = Integer.parseInt(eventosTable.getValueAt(selectedRow, 0).toString());
            String dia = eventosTable.getValueAt(selectedRow, 1).toString();
            String horaEntrada = eventosTable.getValueAt(selectedRow, 2).toString();
            String nomeCliente = eventosTable.getValueAt(selectedRow, 6).toString(); // Ajustar se o índice da coluna do cliente for diferente

            int confirm = JOptionPane.showConfirmDialog(this, "Realmente quer apagar o evento do dia " + dia + " na hora " + horaEntrada + " do cliente " + nomeCliente + "?", "Confirmar Apagar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {

                eventoService.apagarEventos(codigo_evento);

                DefaultTableModel model = (DefaultTableModel) eventosTable.getModel();
                model.removeRow(selectedRow);

                JOptionPane.showMessageDialog(this, "Registro apagado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                mostrarDiasEventos();
            }
        } else {

            JOptionPane.showMessageDialog(this, "Por favor, selecione um registro para apagar.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        mostrarDiasEventos();
    }//GEN-LAST:event_apagarEventoButtonActionPerformed

    private void adicionarEventoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarEventoButtonActionPerformed
        // TODO add your handling code here:
        EventoTela eventoTela = new EventoTela();
        eventoTela.quadraLabel.setText(quadraLabel.getText());

        eventoTela.setTitle("Eventos - " + pegarNomeQuadra(quadraLabel.getText()));
        eventoTela.setVisible(true);
    }//GEN-LAST:event_adicionarEventoButtonActionPerformed

    private void jCalendar1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jCalendar1PropertyChange
        // TODO add your handling code here:
        if ("calendar".equals(evt.getPropertyName())) {
            int codigoQuadra = pegarId(quadraLabel.getText());
            Object newValue = evt.getNewValue();
            if (newValue instanceof Calendar) {
                Calendar calendar = (Calendar) newValue;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String selectedDate = dateFormat.format(calendar.getTime());
                List<Evento> eventos = eventoService.listarEventos(selectedDate, codigoQuadra);
                addEventoTabela(eventos);
                mostrarDiasEventos();

            }
        }

    }//GEN-LAST:event_jCalendar1PropertyChange

    private void buttonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVoltarActionPerformed
        // TODO add your handling code here:
        this.dispose();
        QuadraSelector quadraSelector = new QuadraSelector();
        quadraSelector.setVisible(true);
    }//GEN-LAST:event_buttonVoltarActionPerformed

    private void editarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarButtonActionPerformed
        // TODO add your handling code here:

        int selectedRow = eventosTable.getSelectedRow();
        if (selectedRow >= 0) {
            String codigo_evento = eventosTable.getValueAt(selectedRow, 0).toString();
            EventoTelaEditar eventoTelaEditar = new EventoTelaEditar();
            System.out.println(codigo_evento);
            eventoTelaEditar.codigoEventoLabel.setText(codigo_evento);
            eventoTelaEditar.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um registro para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_editarButtonActionPerformed

    private void quadraLabelPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_quadraLabelPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_quadraLabelPropertyChange

    private void atualizarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarButtonActionPerformed
        // TODO add your handling code here:
        mostrarDiasEventos();
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
            java.util.logging.Logger.getLogger(Agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Agenda agenda = new Agenda();
                agenda.setVisible(true);
            }
        });
    }

    public void addEventoTabela(List<Evento> eventos) {
        DefaultTableModel model = (DefaultTableModel) eventosTable.getModel();
        model.setRowCount(0);

        for (Evento evento : eventos) {
            Object[] row = {evento.getId(), evento.getData(), evento.getHoraEntrada(), evento.getHoraSaida(), evento.getDescricao(), evento.getNomeModalidade(), evento.getNomeCliente()};
            model.addRow(row);
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

    public static String pegarNomeQuadra(String input) {
        int dashIndex = input.indexOf("-");
        return input.substring(dashIndex + 1);
    }

    public void mostrarDiasEventos() {
        int codigoQuadra = pegarId(quadraLabel.getText());
        int month = jCalendar1.getMonthChooser().getMonth() + 1;
        int year = jCalendar1.getYearChooser().getYear();
        JPanel jpanel = jCalendar1.getDayChooser().getDayPanel();
        Component[] components = jpanel.getComponents();
        eventosMes = eventoService.listarEventosMes(codigoQuadra, month);

        zerarCalendar();

        for (Component comp : components) {
            if (comp instanceof JButton) {
                JButton dayButton = (JButton) comp;
                String dayText = dayButton.getText();

                if (dayText.matches("\\d+")) {
                    int day = Integer.parseInt(dayText);
                    Calendar compCalendar = Calendar.getInstance();
                    compCalendar.set(year, month - 1, day);

                    for (Evento eventoMes : eventosMes) {
                        Date eventDate = eventoMes.getData();
                        Calendar eventCalendar = Calendar.getInstance();
                        eventCalendar.setTime(eventDate);

                        if (eventCalendar.get(Calendar.YEAR) == compCalendar.get(Calendar.YEAR)
                                && eventCalendar.get(Calendar.MONTH) == compCalendar.get(Calendar.MONTH)
                                && eventCalendar.get(Calendar.DAY_OF_MONTH) == compCalendar.get(Calendar.DAY_OF_MONTH)) {
                            dayButton.setBackground(Color.RED);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void zerarCalendar() {
        JPanel jpanel = jCalendar1.getDayChooser().getDayPanel();
        Component[] components = jpanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JButton) {
                JButton dayButton = (JButton) comp;
                dayButton.setBackground(null);
            }
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel EventosPanelBackGround;
    private javax.swing.JButton adicionarEventoButton;
    private javax.swing.JPanel agendaBackGroundPanel;
    private javax.swing.JButton apagarEventoButton;
    private javax.swing.JButton atualizarButton;
    private javax.swing.JButton buttonVoltar;
    private javax.swing.JButton editarButton;
    private javax.swing.JPanel eventosButtonPanel;
    private javax.swing.JTable eventosTable;
    public com.toedter.calendar.JCalendar jCalendar1;
    public javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JLabel quadraLabel;
    private javax.swing.JScrollPane scrollTableEventos;
    // End of variables declaration//GEN-END:variables
}
