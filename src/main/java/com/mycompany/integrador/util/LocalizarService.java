/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.util;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Top System
 */
public class LocalizarService {
    
    public static class PesquisaResult {
        private String criterio;
        private String valor;

        public PesquisaResult(String criterio, String valor) {
            this.criterio = criterio;
            this.valor = valor;
        }

        public String getCriterio() {
            return criterio;
        }

        public String getValor() {
            return valor;
        }
    }

    public static PesquisaResult abrirDialogoPesquisa(Frame owner) {
        LocalizarFuncionarioDialog dialog = new LocalizarFuncionarioDialog(owner);
        dialog.setVisible(true);

        if (dialog.isConfirmado()) {
            return new PesquisaResult(dialog.getCriterioSelecionado(), dialog.getValorPesquisa());
        } else {
            return null;
        }
    }

    private static class LocalizarFuncionarioDialog extends JDialog {

        private JComboBox<String> criteriosComboBox;
        private JTextField pesquisaField;
        private JButton localizarButton;
        private JButton cancelarButton;
        private String criterioSelecionado;
        private String valorPesquisa;
        private boolean confirmado;

        public LocalizarFuncionarioDialog(Frame owner) {
            super(owner, "Localizar Funcionário", true);
            setLayout(new GridBagLayout());
            getContentPane().setBackground(Color.LIGHT_GRAY);

            criteriosComboBox = new JComboBox<>(new String[]{"Código", "Nome", "CPF"});
            pesquisaField = new JTextField(20);
            localizarButton = new JButton("Localizar");
            cancelarButton = new JButton("Cancelar");

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            gbc.gridx = 0;
            gbc.gridy = 0;
            add(new JLabel("Critério:"), gbc);

            gbc.gridx = 1;
            add(criteriosComboBox, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            add(new JLabel("Pesquisa:"), gbc);

            gbc.gridx = 1;
            add(pesquisaField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            add(localizarButton, gbc);

            gbc.gridx = 1;
            gbc.gridwidth = 1;
            add(cancelarButton, gbc);

            setSize(400, 200);
            setLocationRelativeTo(owner);

            // Ação do botão localizar
            localizarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    criterioSelecionado = (String) criteriosComboBox.getSelectedItem();
                    valorPesquisa = pesquisaField.getText();
                    confirmado = true;
                    setVisible(false);
                }
            });

            // Ação do botão cancelar
            cancelarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    confirmado = false;
                    setVisible(false);
                }
            });
        }

        public boolean isConfirmado() {
            return confirmado;
        }

        public String getCriterioSelecionado() {
            return criterioSelecionado;
        }

        public String getValorPesquisa() {
            return valorPesquisa;
        }
    }  
}
