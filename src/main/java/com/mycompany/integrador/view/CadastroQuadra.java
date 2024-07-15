/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.integrador.view;

import com.mycompany.integrador.model.Quadra;
import com.mycompany.integrador.model.TipoQuadra;
import com.mycompany.integrador.model.service.QuadraService;
import com.mycompany.integrador.model.service.TipoQuadraService;
import com.mycompany.integrador.util.CombinedFilter;
import com.mycompany.integrador.util.ButtonRenderer;
import com.mycompany.integrador.util.LocalizarService;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author jose.zanandrea
 */
public class CadastroQuadra extends javax.swing.JFrame {

    private int rowIndex = -1; // Adicionando variável para armazenar o índice da linha selecionada
    private int codigo;
    private final DefaultTableModel tableModel;
    private final QuadraService quadraService;
    private final TipoQuadraService tipoQuadraService;
    
    /**
     * Creates new form CadastroFuncionario
     */
    public CadastroQuadra() {
        initComponents();
        setBounds(new java.awt.Rectangle(0, 0, 800, 600));
        setResizable(false);
        setLocationRelativeTo(null);

        getContentPane().setLayout(null);
        
        aplicarUpperCaseFilter(jTextFieldNome, 60);
        aplicarFormatoTamanhoQuadra(jFormattedTextFieldTamanho);

        tableModel = (DefaultTableModel) jTableDadosQuadra.getModel();
        quadraService = new QuadraService();
        tipoQuadraService = new TipoQuadraService();

        listarQuadras();
        configureTable();
    }
       
    public static void aplicarUpperCaseFilter(JTextField textField, int limite) {
        AbstractDocument doc = (AbstractDocument) textField.getDocument();
        doc.setDocumentFilter(new CombinedFilter(limite, true)); // Filtro de maiúsculas
    }

    public static void aplicarLowerCaseFilter(JTextField textField, int limite) {
        AbstractDocument doc = (AbstractDocument) textField.getDocument();
        doc.setDocumentFilter(new CombinedFilter(limite, false)); // Filtro de minúsculas
    }
    
    private void aplicarFormatoTamanhoQuadra(JFormattedTextField campoTamanho) {
        try {
            MaskFormatter maskFormatter = new MaskFormatter(".##");
            maskFormatter.install(campoTamanho);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void atualizarCodigoQuadra() {
        try {
            int maxCodigo = quadraService.getMaxCodigoQuadra();
            jTextFieldCodigo.setText(String.valueOf(maxCodigo));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao obter o código da Quadra: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configureTable() {
        jTableDadosQuadra.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableDadosQuadra.getSelectionModel().addListSelectionListener(e -> {
            rowIndex = jTableDadosQuadra.getSelectedRow();
        });

        // Renderizador para os botões
        jTableDadosQuadra.getColumn("Edit").setCellRenderer(new ButtonRenderer("Edit"));
        jTableDadosQuadra.getColumn("Delete").setCellRenderer(new ButtonRenderer("Delete"));

        // Editor para os botões
        jTableDadosQuadra.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox(), "Edit", jTableDadosQuadra));
        jTableDadosQuadra.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(), "Delete", jTableDadosQuadra));

    }

    private void CarregarQuadraSelecionada() {
        
        int codigo = (int) jTableDadosQuadra.getValueAt(rowIndex, 0);
        String codigoStr = String.valueOf(codigo);
        String nome = (String) jTableDadosQuadra.getValueAt(rowIndex, 1);
        double tamanho = (double) jTableDadosQuadra.getValueAt(rowIndex, 2);
        int codigoTipoQuadra = (int) jTableDadosQuadra.getValueAt(rowIndex, 3);

        // Preencha os campos com os dados da quadra selecionada para edição 
        jTextFieldCodigo.setText(codigoStr);
        jTextFieldNome.setText(nome);
        String tamanhoConvertido = String.valueOf(tamanho);
        jFormattedTextFieldTamanho.setText(tamanhoConvertido);
        
        String nomeTipoQuadra = tipoQuadraService.getNomeTipoQuadra(codigoTipoQuadra);

        // Limpar e adicionar apenas o nome do tipo de quadra selecionada ao combobox
        jComboBoxTipoQuadra.removeAllItems();
        jComboBoxTipoQuadra.addItem(nomeTipoQuadra); // Adiciona o nome do tipo de quadra ao combobox
        jComboBoxTipoQuadra.setSelectedItem(nomeTipoQuadra); // Seleciona o nome do tipo de quadra

    }

    public void editarQuadra(int row) {
        jTableDadosQuadra.setRowSelectionInterval(row, row);
        CarregarQuadraSelecionada();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelModalidade = new javax.swing.JPanel();
        jLabelCodigo = new javax.swing.JLabel();
        jTextFieldCodigo = new javax.swing.JTextField();
        jLabelNomeModalidade = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabelTamanho = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDadosQuadra = new javax.swing.JTable();
        jButtonCadastrar = new javax.swing.JButton();
        jButtonSalvarTela = new javax.swing.JButton();
        jFormattedTextFieldTamanho = new javax.swing.JFormattedTextField();
        jComboBoxTipoQuadra = new javax.swing.JComboBox<>();
        jLabelTamanho1 = new javax.swing.JLabel();
        jButtonInserir = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonLocalizar = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Quadra");
        setBackground(new java.awt.Color(204, 255, 255));

        jPanelModalidade.setBackground(new java.awt.Color(153, 153, 153));
        jPanelModalidade.setPreferredSize(new java.awt.Dimension(1250, 700));

        jLabelCodigo.setText("Código:");

        jTextFieldCodigo.setEditable(false);
        jTextFieldCodigo.setEnabled(false);
        jTextFieldCodigo.setMinimumSize(new java.awt.Dimension(70, 30));
        jTextFieldCodigo.setNextFocusableComponent(jTextFieldNome);

        jLabelNomeModalidade.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelNomeModalidade.setText("Nome Modalidade:");

        jLabelTamanho.setText("Tamanho:");

        jTableDadosQuadra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Tamanho", "Tipo Quadra", "Edit", "Delete"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableDadosQuadra);
        if (jTableDadosQuadra.getColumnModel().getColumnCount() > 0) {
            jTableDadosQuadra.getColumnModel().getColumn(0).setResizable(false);
            jTableDadosQuadra.getColumnModel().getColumn(0).setPreferredWidth(70);
            jTableDadosQuadra.getColumnModel().getColumn(1).setResizable(false);
            jTableDadosQuadra.getColumnModel().getColumn(1).setPreferredWidth(300);
            jTableDadosQuadra.getColumnModel().getColumn(2).setResizable(false);
            jTableDadosQuadra.getColumnModel().getColumn(3).setResizable(false);
            jTableDadosQuadra.getColumnModel().getColumn(4).setResizable(false);
            jTableDadosQuadra.getColumnModel().getColumn(5).setResizable(false);
        }

        jButtonCadastrar.setBackground(new java.awt.Color(51, 153, 255));
        jButtonCadastrar.setText("Cadastrar");
        jButtonCadastrar.setIconTextGap(5);
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });

        jButtonSalvarTela.setBackground(new java.awt.Color(204, 204, 255));
        jButtonSalvarTela.setText("Salvar");
        jButtonSalvarTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarTelaActionPerformed(evt);
            }
        });

        jFormattedTextFieldTamanho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldTamanhoKeyTyped(evt);
            }
        });

        jComboBoxTipoQuadra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoQuadraActionPerformed(evt);
            }
        });

        jLabelTamanho1.setText("Tipo de Quadra:");

        javax.swing.GroupLayout jPanelModalidadeLayout = new javax.swing.GroupLayout(jPanelModalidade);
        jPanelModalidade.setLayout(jPanelModalidadeLayout);
        jPanelModalidadeLayout.setHorizontalGroup(
            jPanelModalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModalidadeLayout.createSequentialGroup()
                .addGroup(jPanelModalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelModalidadeLayout.createSequentialGroup()
                        .addGroup(jPanelModalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelModalidadeLayout.createSequentialGroup()
                                .addComponent(jLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelNomeModalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelModalidadeLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabelTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextFieldTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelTamanho1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanelModalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelModalidadeLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxTipoQuadra, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonSalvarTela, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelModalidadeLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldNome)))))
                .addGap(0, 0, 0))
        );
        jPanelModalidadeLayout.setVerticalGroup(
            jPanelModalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModalidadeLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelModalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNomeModalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelModalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSalvarTela, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxTipoQuadra, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTamanho1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE))
        );

        jButtonInserir.setText("Inserir");
        jButtonInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInserirActionPerformed(evt);
            }
        });

        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonLocalizar.setText("Localizar");
        jButtonLocalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLocalizarActionPerformed(evt);
            }
        });

        jButtonEditar.setText("Editar");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        jButtonSair.setText("Sair");
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 28, Short.MAX_VALUE))
                    .addComponent(jPanelModalidade, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelModalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   /* private void carregarCidades() {
        List<Cidade> cidades = cidadeService.listarCidades();

        for (Cidade cidade : cidades) {
            jComboBoxCidade.addItem(cidade.getNome());
            
        }
    }*/

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        salvarQuadra();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInserirActionPerformed
        novaQuadra();
    }//GEN-LAST:event_jButtonInserirActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        limparCampos();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        String codigo = jTextFieldCodigo.getText();
        int codigoint = Integer.parseInt(codigo);
        JOptionPane.showMessageDialog(null,
                "Deseja realmente excluir os dados selecionados?", "Excluir Quadra", JOptionPane.YES_NO_CANCEL_OPTION);        
        quadraService.excluirQuadra(codigoint);
        listarQuadras();
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jButtonLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLocalizarActionPerformed

        limparCampos();
        abrirDialogoLocalizar();
    }//GEN-LAST:event_jButtonLocalizarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        CarregarQuadraSelecionada();
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonSalvarTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarTelaActionPerformed
        salvarQuadra();
    }//GEN-LAST:event_jButtonSalvarTelaActionPerformed

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed

        String codigo = jTextFieldCodigo.getText();
        int codigoint = Integer.parseInt(codigo);
        String nome = jTextFieldNome.getText();
        String tamanho = jFormattedTextFieldTamanho.getText();
        double tamanhodouble = Double.parseDouble(tamanho);
        
         int codigoTipoQuadra = tipoQuadraService.getCodigoTipoQuadra(jComboBoxTipoQuadra.getSelectedItem().toString());

        if (rowIndex == -1) { // Se rowIndex for -1, é uma nova pessoa

            Quadra quadra = new Quadra(nome, tamanhodouble, codigoTipoQuadra);
            quadraService.salvarQuadra(quadra);
        } else { // Caso contrário, é uma edição de quadra existente

            Quadra quadraEditada = new Quadra(nome, tamanhodouble, codigoTipoQuadra);
            quadraEditada.setCodigo((int) jTableDadosQuadra.getValueAt(rowIndex, 0)); // Define o Código da pessoa
            quadraService.atualizarQuadra(quadraEditada);
            rowIndex = -1; // Reseta o índice da linha após a edição
        }

        JOptionPane.showMessageDialog(null,
                "Dados inseridos com sucesso!", "Sucesso", JOptionPane.OK_OPTION);
        limparCampos();
        listarQuadras();
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jComboBoxTipoQuadraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoQuadraActionPerformed

        jComboBoxTipoQuadra.getSelectedItem();
    }//GEN-LAST:event_jComboBoxTipoQuadraActionPerformed

    private void jFormattedTextFieldTamanhoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldTamanhoKeyTyped
        String caracteres = "0987654321.";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_jFormattedTextFieldTamanhoKeyTyped

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
            java.util.logging.Logger.getLogger(CadastroQuadra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroQuadra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroQuadra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroQuadra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

    }
    
        private void carregarCidades() {
        List<TipoQuadra> tipoQuadras = tipoQuadraService.listarTipoQuadra();

        for (TipoQuadra tipoQuadra : tipoQuadras) {
            jComboBoxTipoQuadra.addItem(tipoQuadra.getNome());
            
        }
    }

    private void salvarQuadra() {
        String codigo = jTextFieldCodigo.getText();
        int codigoint = Integer.parseInt(codigo);
        String nome = jTextFieldNome.getText();
        String tamanho = jFormattedTextFieldTamanho.getText();
        double tamanhodouble = Double.parseDouble(tamanho);
        
         int codigoTipoQuadra = tipoQuadraService.getCodigoTipoQuadra(jComboBoxTipoQuadra.getSelectedItem().toString());

        if (rowIndex == -1) { // Se rowIndex for -1, é uma nova pessoa

            Quadra quadra = new Quadra(nome, tamanhodouble, codigoTipoQuadra);
            quadraService.salvarQuadra(quadra);
        } else { // Caso contrário, é uma edição de quadra existente

            Quadra quadraEditada = new Quadra(nome, tamanhodouble, codigoTipoQuadra);
            quadraEditada.setCodigo((int) jTableDadosQuadra.getValueAt(rowIndex, 0)); // Define o Código da pessoa
            quadraService.atualizarQuadra(quadraEditada);
            rowIndex = -1; // Reseta o índice da linha após a edição
        }

        JOptionPane.showMessageDialog(null,
                "Dados inseridos com sucesso!", "Sucesso", JOptionPane.OK_OPTION);
        limparCampos();
        listarQuadras();
    }

    private void listarQuadras() {
        tableModel.setRowCount(0);

        quadraService.listarQuadras().forEach(quadra -> {
            tableModel.addRow(new Object[]{quadra.getCodigo(), quadra.getNome(), quadra.getTamanho(), quadra.getTipo()});
        });
    }

    private void novaQuadra() {
        limparCampos();
        atualizarCodigoQuadra();
        jTextFieldNome.requestFocus();
        carregarCidades();
    }

    private void limparCampos() {
        jTextFieldCodigo.setText("");
        jTextFieldNome.setText("");
        jFormattedTextFieldTamanho.setText("");
        jComboBoxTipoQuadra.removeAllItems();
    }

    class ButtonEditor extends DefaultCellEditor {

        private String label;
        private JButton button;
        private boolean isPushed;
        private JTable table;

        public ButtonEditor(JCheckBox checkBox, String text, JTable table) {
            super(checkBox);
            this.table = table;
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
            label = text;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                switch (label) {
                    case "Edit":
                        if (rowIndex != -1) {
                            CarregarQuadraSelecionada();
                            break;
                        }
                    case "Delete":
                        int excluir = JOptionPane.showConfirmDialog(editorComponent, "Tem certeza?");
                        if (excluir == 0) {
                            int selectedRow = jTableDadosQuadra.getSelectedRow();
                            if (selectedRow != -1) {
                                int localCodigo = (int) jTableDadosQuadra.getValueAt(selectedRow, 0);
                                quadraService.excluirQuadra(localCodigo);
                                listarQuadras();
                            }
                            JOptionPane.showMessageDialog(editorComponent, "Registro removido com sucesso!!");
                            break;
                        } else {
                            break;
                        }
                }

            }
            isPushed = false;
            return new String(label);
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
    
    private void abrirDialogoLocalizar() {
        LocalizarService.PesquisaResult result = LocalizarService.abrirDialogoPesquisa(this);
        if (result != null) {
            String criterio = result.getCriterio();
            String pesquisa = result.getValor();

            localizarQuadra(criterio, pesquisa);
        }
    }

    private void localizarQuadra(String criterio, String valor) {
        Quadra quadra = null;

        try {
            switch (criterio) {
                case "Código":
                    int codigo = Integer.parseInt(valor);
                    quadra = quadraService.localizarQuadraPorCodigo(codigo);
                    CarregarQuadraSelecionada();
                    break;
                case "Nome":
                    quadra = quadraService.localizarQuadraPorNome(valor);
                    CarregarQuadraSelecionada();
                    break;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O valor do código deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        if (quadra != null) {
            // Exibir os detalhes da quadra localizada
            JOptionPane.showMessageDialog(this, "Quadra localizado:\n" + quadra.getNome(), "Quadra Encontrada", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Quadra não encontrada.", "Quadra Não Encontrada", JOptionPane.WARNING_MESSAGE);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonInserir;
    private javax.swing.JButton jButtonLocalizar;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonSalvarTela;
    private javax.swing.JComboBox<String> jComboBoxTipoQuadra;
    private javax.swing.JFormattedTextField jFormattedTextFieldTamanho;
    private javax.swing.JLabel jLabelCodigo;
    private javax.swing.JLabel jLabelNomeModalidade;
    private javax.swing.JLabel jLabelTamanho;
    private javax.swing.JLabel jLabelTamanho1;
    private javax.swing.JPanel jPanelModalidade;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDadosQuadra;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldNome;
    // End of variables declaration//GEN-END:variables
}
