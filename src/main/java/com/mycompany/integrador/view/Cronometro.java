/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.integrador.view;

import com.mycompany.integrador.model.service.CronometroService;

/**
 *
 * @author Top System
 */
public class Cronometro extends javax.swing.JFrame implements CronometroService.CronometroListener {
    
    private CronometroService cronometroService;

    /**
     * Creates new form Cronometro
     */
    public Cronometro() {
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);

        cronometroService = new CronometroService(this);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCronometro = new java.awt.Panel();
        labelCronometro = new java.awt.Label();
        buttonIniciar = new java.awt.Button();
        buttonParar = new java.awt.Button();
        buttonReiniciar = new java.awt.Button();
        labelCronometroPara = new java.awt.Label();
        labelNomeQuadra = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelCronometro.setBackground(new java.awt.Color(204, 204, 204));

        labelCronometro.setAlignment(java.awt.Label.CENTER);
        labelCronometro.setBackground(new java.awt.Color(153, 153, 153));
        labelCronometro.setFont(new java.awt.Font("Arial", 0, 72)); // NOI18N
        labelCronometro.setText("00:00:00");

        buttonIniciar.setBackground(new java.awt.Color(102, 102, 102));
        buttonIniciar.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        buttonIniciar.setLabel("Iniciar");
        buttonIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIniciarActionPerformed(evt);
            }
        });

        buttonParar.setBackground(new java.awt.Color(102, 102, 102));
        buttonParar.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        buttonParar.setLabel("Parar");
        buttonParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPararActionPerformed(evt);
            }
        });

        buttonReiniciar.setBackground(new java.awt.Color(102, 102, 102));
        buttonReiniciar.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        buttonReiniciar.setLabel("Reiniciar");
        buttonReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReiniciarActionPerformed(evt);
            }
        });

        labelCronometroPara.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        labelCronometroPara.setText("Cronômetro Quadra:");

        labelNomeQuadra.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        labelNomeQuadra.setText("Quadra 1");

        javax.swing.GroupLayout panelCronometroLayout = new javax.swing.GroupLayout(panelCronometro);
        panelCronometro.setLayout(panelCronometroLayout);
        panelCronometroLayout.setHorizontalGroup(
            panelCronometroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCronometroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCronometroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCronometro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCronometroLayout.createSequentialGroup()
                        .addComponent(buttonIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonParar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(buttonReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCronometroLayout.createSequentialGroup()
                        .addComponent(labelCronometroPara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(labelNomeQuadra, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelCronometroLayout.setVerticalGroup(
            panelCronometroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCronometroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCronometroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCronometroPara, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(labelNomeQuadra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCronometro, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCronometroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCronometroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(buttonIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                        .addComponent(buttonParar, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                    .addComponent(buttonReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        labelCronometro.getAccessibleContext().setAccessibleName("labelCronometro");
        labelCronometro.getAccessibleContext().setAccessibleDescription("");
        labelNomeQuadra.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCronometro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCronometro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIniciarActionPerformed
        cronometroService.iniciar();
    }//GEN-LAST:event_buttonIniciarActionPerformed

    private void buttonPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPararActionPerformed

        cronometroService.parar();
    }//GEN-LAST:event_buttonPararActionPerformed

    private void buttonReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReiniciarActionPerformed

        cronometroService.resetar();
    }//GEN-LAST:event_buttonReiniciarActionPerformed

        @Override
    public void onTempoAtualizado(String tempoFormatado) {
        // Atualiza o label com o tempo formatado
        labelCronometro.setText(tempoFormatado);
    }
    
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
            java.util.logging.Logger.getLogger(Cronometro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cronometro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cronometro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cronometro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cronometro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button buttonIniciar;
    private java.awt.Button buttonParar;
    private java.awt.Button buttonReiniciar;
    private java.awt.Label labelCronometro;
    private java.awt.Label labelCronometroPara;
    public java.awt.Label labelNomeQuadra;
    private java.awt.Panel panelCronometro;
    // End of variables declaration//GEN-END:variables
}
