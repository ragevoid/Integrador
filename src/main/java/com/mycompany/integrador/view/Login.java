/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.integrador.view;

import com.mycompany.integrador.model.service.FuncionarioService;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

/**
 *
 * @author Ricardo
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        setLocationCenter();
        setVisible(true);
        
    }
        public Login(int moveWidth, int moveHeight) {    
        initComponents();
        setLocationMove(moveWidth, moveHeight);
        setVisible(true);
    }
 public void setLocationCenter(){
        setLocationMove(0, 0);
    }
  public void setLocationMove(int moveWidth, int moveHeight) {
        // Obtenemos el tamaño de la pantalla.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Obtenemos el tamaño de nuestro frame.
        Dimension frameSize = this.getSize();
        frameSize.width = frameSize.width > screenSize.width?screenSize.width:frameSize.width;
        frameSize.height = frameSize.height > screenSize.height?screenSize.height:frameSize.height;   
        // We define the location. Definimos la localización.
        setLocation((screenSize.width - frameSize.width) / 2 + moveWidth, (screenSize.height - frameSize.height) / 2 + moveHeight);        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backGround = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        userField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        passwrodLabel = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        userSeparator = new javax.swing.JSeparator();
        passwordSeparator = new javax.swing.JSeparator();
        cadastrarButton = new javax.swing.JButton();
        loginButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quadra Manager");
        setPreferredSize(new java.awt.Dimension(720, 480));
        setResizable(false);

        backGround.setBackground(new java.awt.Color(51, 51, 51));
        backGround.setForeground(new java.awt.Color(51, 51, 51));
        backGround.setPreferredSize(new java.awt.Dimension(720, 480));
        backGround.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        backGround.setLocation(0, 0);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Login.jpeg"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(240, 480));
        jLabel1.setMinimumSize(new java.awt.Dimension(240, 480));
        jLabel1.setPreferredSize(new java.awt.Dimension(240, 240));
        backGround.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 254, 492));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png"))); // NOI18N
        backGround.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(254, 0, 430, 122));

        userField.setBackground(new java.awt.Color(51, 51, 51));
        userField.setForeground(new java.awt.Color(153, 153, 153));
        userField.setText("Número de Usuario");
        userField.setBorder(null);
        userField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                userFieldMousePressed(evt);
            }
        });
        userField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userFieldActionPerformed(evt);
            }
        });
        userField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userFieldKeyPressed(evt);
            }
        });
        backGround.add(userField, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, 210, 40));

        passwordField.setBackground(new java.awt.Color(51, 51, 51));
        passwordField.setForeground(new java.awt.Color(153, 153, 153));
        passwordField.setText("jPasswordField1");
        passwordField.setBorder(null);
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFieldFocusGained(evt);
            }
        });
        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordFieldKeyPressed(evt);
            }
        });
        backGround.add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 270, 180, 40));

        passwrodLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        passwrodLabel.setForeground(new java.awt.Color(204, 204, 204));
        passwrodLabel.setText("SENHA");
        backGround.add(passwrodLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, -1, -1));

        userLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        userLabel.setForeground(new java.awt.Color(204, 204, 204));
        userLabel.setText("USUARIO");
        backGround.add(userLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, -1, -1));

        userSeparator.setBackground(new java.awt.Color(0, 153, 204));
        userSeparator.setForeground(new java.awt.Color(0, 153, 204));
        backGround.add(userSeparator, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 310, 230, 10));

        passwordSeparator.setBackground(new java.awt.Color(0, 153, 204));
        passwordSeparator.setForeground(new java.awt.Color(0, 153, 204));
        backGround.add(passwordSeparator, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 230, 10));

        cadastrarButton.setBackground(new java.awt.Color(153, 153, 153));
        cadastrarButton.setForeground(new java.awt.Color(51, 51, 51));
        cadastrarButton.setText("Cadastrar");
        cadastrarButton.setBorder(null);
        backGround.add(cadastrarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, 70, 30));

        loginButton.setBackground(new java.awt.Color(0, 153, 204));
        loginButton.setForeground(new java.awt.Color(51, 51, 51));
        loginButton.setText("Login");
        loginButton.setBorder(null);
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        backGround.add(loginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 370, 80, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backGround, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backGround, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userFieldActionPerformed

    private void userFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userFieldMousePressed
        // TODO add your handling code here:
        userField.setText("");
        userField.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_userFieldMousePressed

    private void userFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userFieldKeyPressed

        if(evt.getKeyCode() == 10){
        passwordField.requestFocusInWindow();
        } 
    }//GEN-LAST:event_userFieldKeyPressed

    private void passwordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFieldFocusGained
        // TODO add your handling code here:
        passwordField.setText("");
        passwordField.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_passwordFieldFocusGained

    private void passwordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyPressed
        // TODO add your handling code here:
                if(evt.getKeyCode() == 10){
                    loginButton.doClick();
        } 
    }//GEN-LAST:event_passwordFieldKeyPressed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
           String idText = userField.getText(); // Suponiendo que tienes un JTextField para el ID
    String senha = new String(passwordField.getPassword()); // Suponiendo que tienes un JPasswordField para la contraseña
    
    try {
        Long id = Long.parseLong(idText);
        FuncionarioService funcionarioService = new FuncionarioService();
        
        if (funcionarioService.verificarCredenciais(id, senha)) {
            System.out.println("Login bem-sucedido!");
            this.dispose();
            Principal principal = new Principal();
            principal.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null,"ID ou Senha Incorreto","Error",
JOptionPane.ERROR_MESSAGE);;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null,"Acceso Denegado","Error",
JOptionPane.ERROR_MESSAGE);;
    }
        
    }//GEN-LAST:event_loginButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backGround;
    private javax.swing.JButton cadastrarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JSeparator passwordSeparator;
    private javax.swing.JLabel passwrodLabel;
    private javax.swing.JTextField userField;
    private javax.swing.JLabel userLabel;
    private javax.swing.JSeparator userSeparator;
    // End of variables declaration//GEN-END:variables
}