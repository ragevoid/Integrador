/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.integrador.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import com.mycompany.integrador.util.DataAtual;

/**
 *
 * @author Ricardo
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PrincipalBackGround = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        userCodeLabel = new javax.swing.JLabel();
        principalDataLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        cadatroMenu = new javax.swing.JMenu();
        usersMenu = new javax.swing.JMenuItem();
        clientesMenu = new javax.swing.JMenuItem();
        modalidadesMenu = new javax.swing.JMenuItem();
        quadrasMenu = new javax.swing.JMenuItem();
        agendaMenu = new javax.swing.JMenu();
        iniciarAgenda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(600, 600));
        setMinimumSize(new java.awt.Dimension(600, 600));
        setPreferredSize(new java.awt.Dimension(600, 600));

        PrincipalBackGround.setBackground(new java.awt.Color(51, 51, 51));
        PrincipalBackGround.setMaximumSize(new java.awt.Dimension(600, 600));
        PrincipalBackGround.setMinimumSize(new java.awt.Dimension(600, 600));
        PrincipalBackGround.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo2.png"))); // NOI18N
        jLabel2.setToolTipText("");
        PrincipalBackGround.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 600, 370));

        jPanel1.setBackground(new java.awt.Color(64, 64, 64));
        jPanel1.setFocusable(false);
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        userCodeLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userCodeLabel.setForeground(new java.awt.Color(0, 153, 204));
        userCodeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userCodeLabel.setText("UserCodeLabel");
        jPanel1.add(userCodeLabel);

        principalDataLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        principalDataLabel.setForeground(new java.awt.Color(0, 153, 204));
        principalDataLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        principalDataLabel.setText("PrincipalDataLabel");
        jPanel1.add(principalDataLabel);
        String dataAtual = DataAtual.obtenerDataActual();
        principalDataLabel.setText(dataAtual);

        PrincipalBackGround.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 600, 50));

        jMenuBar1.setBackground(new java.awt.Color(51, 51, 51));
        jMenuBar1.setBorder(null);

        cadatroMenu.setText("Cadastro");
        cadatroMenu.setMargin(new java.awt.Insets(6, 6, 6, 6));

        usersMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_DOWN_MASK));
        usersMenu.setText("Usuarios");
        usersMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersMenuActionPerformed(evt);
            }
        });
        cadatroMenu.add(usersMenu);

        clientesMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK));
        clientesMenu.setText("Clientes");
        clientesMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientesMenuActionPerformed(evt);
            }
        });
        cadatroMenu.add(clientesMenu);

        modalidadesMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_DOWN_MASK));
        modalidadesMenu.setText("Modalidades");
        cadatroMenu.add(modalidadesMenu);

        quadrasMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.ALT_DOWN_MASK));
        quadrasMenu.setText("Quadras");
        cadatroMenu.add(quadrasMenu);

        jMenuBar1.add(cadatroMenu);

        agendaMenu.setText("Agenda");

        iniciarAgenda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_DOWN_MASK));
        iniciarAgenda.setText("Iniciar Agenda");
        iniciarAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarAgendaActionPerformed(evt);
            }
        });
        agendaMenu.add(iniciarAgenda);

        jMenuBar1.add(agendaMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PrincipalBackGround, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PrincipalBackGround, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clientesMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientesMenuActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroCliente().setVisible(true);
            }
        });
    }//GEN-LAST:event_clientesMenuActionPerformed

    private void iniciarAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarAgendaActionPerformed
        // TODO add your handling code here:
        QuadraSelector quadraSelector = new QuadraSelector();
        quadraSelector.setVisible(true);
    }//GEN-LAST:event_iniciarAgendaActionPerformed

    private void usersMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersMenuActionPerformed
                       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroFuncionario().setVisible(true);
            }
        });
    }//GEN-LAST:event_usersMenuActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PrincipalBackGround;
    private javax.swing.JMenu agendaMenu;
    private javax.swing.JMenu cadatroMenu;
    private javax.swing.JMenuItem clientesMenu;
    private javax.swing.JMenuItem iniciarAgenda;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem modalidadesMenu;
    private javax.swing.JLabel principalDataLabel;
    private javax.swing.JMenuItem quadrasMenu;
    public javax.swing.JLabel userCodeLabel;
    private javax.swing.JMenuItem usersMenu;
    // End of variables declaration//GEN-END:variables
}
