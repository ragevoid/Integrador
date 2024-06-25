package com.mycompany.integrador.view;

import com.mycompany.integrador.model.Evento;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.*;
import com.mycompany.integrador.model.Evento;
import java.awt.Color;
import java.awt.Component;
import java.awt.PopupMenu;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

        
/**
 *
 * @author Ricardo
 */
public class Agenda extends javax.swing.JFrame {
private Calendar calendar;
private List<Evento> eventos;

    /**
     * Creates new form Agenda
     */
    public Agenda() {
        initComponents();
        initializeCalendar();
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
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jDayChooser1 = new com.toedter.calendar.JDayChooser();
        EventosPanelBackGround = new javax.swing.JPanel();
        scrollTableEventos = new javax.swing.JScrollPane();
        eventosTable = new javax.swing.JTable();
        eventosButtonPanel = new javax.swing.JPanel();
        adicionarEventoButton = new javax.swing.JButton();
        apagarEventoButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 420));
        setMinimumSize(new java.awt.Dimension(800, 420));

        agendaBackGroundPanel.setBackground(new java.awt.Color(51, 51, 51));
        agendaBackGroundPanel.setMaximumSize(new java.awt.Dimension(800, 420));
        agendaBackGroundPanel.setMinimumSize(new java.awt.Dimension(800, 420));
        agendaBackGroundPanel.setPreferredSize(new java.awt.Dimension(800, 420));
        agendaBackGroundPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        agendaBackGroundPanel.add(jMonthChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 140, 30));
        jMonthChooser1.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("month".equals(evt.getPropertyName())) {
                    updateDayChooser();
                }
            }
        });
        agendaBackGroundPanel.add(jYearChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 110, 30));
        jYearChooser1.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("year".equals(evt.getPropertyName())) {
                    updateDayChooser();
                }
            }
        });

        jDayChooser1.setDecorationBackgroundColor(new java.awt.Color(0, 153, 255));
        jDayChooser1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDayChooser1FocusGained(evt);
            }
        });
        agendaBackGroundPanel.add(jDayChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 330, 290));
        jDayChooser1.addPropertyChangeListener("day", new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                apagarEventosTabela();
                if (evt.getPropertyName().equals("day")) {
                    int day = (int) evt.getNewValue();
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(jYearChooser1.getYear(), jMonthChooser1.getMonth(), day);
                    diaEnEvento(selectedDate.get(Calendar.DAY_OF_MONTH));

                    // Imprime el día seleccionado en la consola
                    System.out.println("Día seleccionado: " + selectedDate.getTime());
                }
            }
        });

        EventosPanelBackGround.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        eventosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Cita Medica", "12:00"}
            },
            new String [] {
                "Descripçao", "Hora"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        eventosTable.setColumnSelectionAllowed(true);
        scrollTableEventos.setViewportView(eventosTable);
        eventosTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        EventosPanelBackGround.add(scrollTableEventos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 280));

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

        apagarEventoButton.setText("Apagar Evento");
        apagarEventoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apagarEventoButtonActionPerformed(evt);
            }
        });
        eventosButtonPanel.add(apagarEventoButton);

        EventosPanelBackGround.add(eventosButtonPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 300, 50));

        agendaBackGroundPanel.add(EventosPanelBackGround, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 300, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(agendaBackGroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(agendaBackGroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 414, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adicionarEventoButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adicionarEventoButtonMouseClicked
        // TODO add your handling code here

    }//GEN-LAST:event_adicionarEventoButtonMouseClicked

    private void jDayChooser1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDayChooser1FocusGained
        // TODO add your handling code here:
    
    }//GEN-LAST:event_jDayChooser1FocusGained

    private void apagarEventoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apagarEventoButtonActionPerformed
        // TODO add your handling code here:
        apagarEventosTabela();
    }//GEN-LAST:event_apagarEventoButtonActionPerformed

    private void adicionarEventoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarEventoButtonActionPerformed
        // TODO add your handling code here:
         EventoTela eventoTela = new EventoTela();
            eventoTela.setVisible(true);
    }//GEN-LAST:event_adicionarEventoButtonActionPerformed

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
    
    
      private void initializeCalendar() {
        calendar = Calendar.getInstance();
        jMonthChooser1.setMonth(calendar.get(Calendar.MONTH));
        jYearChooser1.setYear(calendar.get(Calendar.YEAR));
        jDayChooser1.setDay(calendar.get(Calendar.DAY_OF_MONTH));
    }

    private void updateDayChooser() {
        int selectedYear = jYearChooser1.getYear();
        int selectedMonth = jMonthChooser1.getMonth();
        calendar.set(selectedYear, selectedMonth, 1);
        jDayChooser1.setMonth(selectedMonth);
        jDayChooser1.setYear(selectedYear);
        System.out.println(eventos);
    }
        public void eventosTeste() {
        // Crear los eventos y agregarlos a la lista
        Evento evento1 = new Evento(new Date(), "10:00", "Reunión de equipo");
        Evento evento2 = new Evento(new Date(), "15:30", "Presentación de proyecto");

        eventos.add(evento1);
        eventos.add(evento2);
    }
    
     public void imprimirEventos() {
        System.out.println("Lista de eventos:");
        for (Evento evento : eventos) {
            System.out.println("Fecha: " + evento.getData() +
                               ", Hora: " + evento.getHora() +
                               ", Descripción: " + evento.getDescricao());
        }
    }
     
 public void addEventoTabela() {
     DefaultTableModel model = (DefaultTableModel) eventosTable.getModel();
        
        for (Evento evento : eventos) {
            Object[] row = { evento.getDescricao(), evento.getHora()};
            model.addRow(row);
        }
}
 
 public void apagarEventosTabela(){
     DefaultTableModel model = (DefaultTableModel) eventosTable.getModel();
      model.setRowCount(0);
 }
 
 public void diaEnEvento(int diaDelMes) {
    boolean eventosEncontrados = false; // Bandera para verificar si se encontraron eventos

    // Supongamos que tienes una lista de eventos llamada "eventos"
    for (Evento evento : eventos) {
        Calendar fechaEvento = Calendar.getInstance();
        fechaEvento.setTime(evento.getData());
        if (fechaEvento.get(Calendar.DAY_OF_MONTH) == diaDelMes) {
            System.out.println("Evento para el día " + diaDelMes + ": " + evento.getDescricao());
            eventosEncontrados = true;

         
        }
    }
    if (!eventosEncontrados) {
        System.out.println("No existen eventos para el día: " + diaDelMes);
    }else{
                addEventoTabela();
    }
}

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel EventosPanelBackGround;
    private javax.swing.JButton adicionarEventoButton;
    private javax.swing.JPanel agendaBackGroundPanel;
    private javax.swing.JButton apagarEventoButton;
    private javax.swing.JPanel eventosButtonPanel;
    private javax.swing.JTable eventosTable;
    private com.toedter.calendar.JDayChooser jDayChooser1;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JScrollPane scrollTableEventos;
    // End of variables declaration//GEN-END:variables
}
