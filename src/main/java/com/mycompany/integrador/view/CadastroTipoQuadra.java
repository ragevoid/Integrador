/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.integrador.view;

import com.mycompany.integrador.model.TipoQuadra;
import com.mycompany.integrador.model.service.TipoQuadraService;
import com.mycompany.integrador.util.CombinedFilter;
import com.mycompany.integrador.util.ButtonRenderer;
import com.mycompany.integrador.util.LocalizarService;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

/**
 *
 * @author jose.zanandrea
 */
public class CadastroTipoQuadra extends javax.swing.JFrame {

    private int rowIndex = -1; // Adicionando variável para armazenar o índice da linha selecionada
    private int codigo;
    private final DefaultTableModel tableModel;
    private final TipoQuadraService tipoQuadraService;
    /**
     * Creates new form CadastroFuncionario
     */
    public CadastroTipoQuadra() {
        initComponents();
        setBounds(new java.awt.Rectangle(0, 0, 800, 600));
        setResizable(false);

        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        
        aplicarUpperCaseFilter(jTextFieldNome, 60);

        tableModel = (DefaultTableModel) jTableDadosTipoQuadra.getModel();
        tipoQuadraService = new TipoQuadraService();

        listarTipoQuadras();
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
    
    private void atualizarCodigoTipoQuadra() {
        try {
            int maxCodigo = tipoQuadraService.getMaxCodigoTipoQuadra();
            jTextFieldCodigo.setText(String.valueOf(maxCodigo));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao obter o código do Tipo de Quadra: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configureTable() {
        jTableDadosTipoQuadra.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableDadosTipoQuadra.getSelectionModel().addListSelectionListener(e -> {
            rowIndex = jTableDadosTipoQuadra.getSelectedRow();
        });

        // Renderizador para os botões
        jTableDadosTipoQuadra.getColumn("Edit").setCellRenderer(new ButtonRenderer("Edit"));
        jTableDadosTipoQuadra.getColumn("Delete").setCellRenderer(new ButtonRenderer("Delete"));

        // Editor para os botões
        jTableDadosTipoQuadra.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox(), "Edit", jTableDadosTipoQuadra));
        jTableDadosTipoQuadra.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(), "Delete", jTableDadosTipoQuadra));

    }

    private void CarregarTipoQuadraSelecionada() {
        int codigo = (int) jTableDadosTipoQuadra.getValueAt(rowIndex, 0);
        String codigoStr = String.valueOf(codigo);
        String nome = (String) jTableDadosTipoQuadra.getValueAt(rowIndex, 1);

        // Preencha os campos com os dados do funcionario selecionado para edição 
        jTextFieldCodigo.setText(codigoStr);
        jTextFieldNome.setText(nome);

    }

    public void editarModalidade(int row) {
        jTableDadosTipoQuadra.setRowSelectionInterval(row, row);
        CarregarTipoQuadraSelecionada();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDadosTipoQuadra = new javax.swing.JTable();
        jButtonCadastrar = new javax.swing.JButton();
        jButtonSalvarTela = new javax.swing.JButton();
        jButtonInserir = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonLocalizar = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Modalidade");
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

        jTableDadosTipoQuadra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Edit", "Delete"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableDadosTipoQuadra);
        if (jTableDadosTipoQuadra.getColumnModel().getColumnCount() > 0) {
            jTableDadosTipoQuadra.getColumnModel().getColumn(0).setResizable(false);
            jTableDadosTipoQuadra.getColumnModel().getColumn(0).setPreferredWidth(70);
            jTableDadosTipoQuadra.getColumnModel().getColumn(1).setResizable(false);
            jTableDadosTipoQuadra.getColumnModel().getColumn(1).setPreferredWidth(300);
            jTableDadosTipoQuadra.getColumnModel().getColumn(2).setResizable(false);
            jTableDadosTipoQuadra.getColumnModel().getColumn(3).setResizable(false);
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

        javax.swing.GroupLayout jPanelModalidadeLayout = new javax.swing.GroupLayout(jPanelModalidade);
        jPanelModalidade.setLayout(jPanelModalidadeLayout);
        jPanelModalidadeLayout.setHorizontalGroup(
            jPanelModalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelModalidadeLayout.createSequentialGroup()
                .addGroup(jPanelModalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelModalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelModalidadeLayout.createSequentialGroup()
                            .addComponent(jLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabelNomeModalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextFieldNome))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelModalidadeLayout.createSequentialGroup()
                            .addGap(498, 498, 498)
                            .addComponent(jButtonSalvarTela, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
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
                    .addComponent(jButtonSalvarTela, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanelModalidade, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelModalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
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
        salvarTipoQuadra();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInserirActionPerformed
        novoTipoQuadra();
    }//GEN-LAST:event_jButtonInserirActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        limparCampos();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        String codigo = jTextFieldCodigo.getText();
        int codigoint = Integer.parseInt(codigo);
        JOptionPane.showMessageDialog(null,
                "Deseja realmente excluir os dados selecionados?", "Excluir Tipo de Quadra", JOptionPane.YES_NO_CANCEL_OPTION);        
        tipoQuadraService.excluirTipoQuadra(codigoint);
        listarTipoQuadras();
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jButtonLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLocalizarActionPerformed

        limparCampos();
        abrirDialogoLocalizar();
    }//GEN-LAST:event_jButtonLocalizarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        CarregarTipoQuadraSelecionada();
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonSalvarTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarTelaActionPerformed
        salvarTipoQuadra();
    }//GEN-LAST:event_jButtonSalvarTelaActionPerformed

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed

        String codigo = jTextFieldCodigo.getText();
        int codigoint = Integer.parseInt(codigo);
        String nome = jTextFieldNome.getText();

        if (rowIndex == -1) { // Se rowIndex for -1, é uma nova pessoa

            TipoQuadra tipoQuadra = new TipoQuadra(nome);
            tipoQuadraService.salvarTipoQuadra(tipoQuadra);
        } else { // Caso contrário, é uma edição de modalidade existente

            TipoQuadra tipoQuadraEditada = new TipoQuadra(nome);
            tipoQuadraEditada.setCodigo((int) jTableDadosTipoQuadra.getValueAt(rowIndex, 0)); // Define o Código da pessoa
            tipoQuadraService.atualizarTipoQuadra(tipoQuadraEditada);
            rowIndex = -1; // Reseta o índice da linha após a edição
        }

        //novoFuncionario();
        limparCampos();
        listarTipoQuadras();
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroTipoQuadra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroTipoQuadra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroTipoQuadra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroTipoQuadra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
    }

    private void salvarTipoQuadra() {
        String codigo = jTextFieldCodigo.getText();
        int codigoint = Integer.parseInt(codigo);
        String nome = jTextFieldNome.getText();

        if (rowIndex == -1) { // Se rowIndex for -1, é uma nova pessoa

            TipoQuadra tipoQuadra = new TipoQuadra(nome);
            tipoQuadraService.salvarTipoQuadra(tipoQuadra);
        } else { // Caso contrário, é uma edição de modalidade existente

            TipoQuadra tipoQuadraEditada = new TipoQuadra(nome);
            tipoQuadraEditada.setCodigo((int) jTableDadosTipoQuadra.getValueAt(rowIndex, 0)); // Define o Código da pessoa
            tipoQuadraService.atualizarTipoQuadra(tipoQuadraEditada);
            rowIndex = -1; // Reseta o índice da linha após a edição
        }

        JOptionPane.showMessageDialog(null,
                "Dados inseridos com sucesso!", "Sucesso", JOptionPane.OK_OPTION);
        limparCampos();
        listarTipoQuadras();
    }

    private void listarTipoQuadras() {
        tableModel.setRowCount(0);

        tipoQuadraService.listarTipoQuadra().forEach(tipoQuadra -> {
            tableModel.addRow(new Object[]{tipoQuadra.getCodigo(), tipoQuadra.getNome()});
        });
    }

    private void novoTipoQuadra() {
        limparCampos();
        atualizarCodigoTipoQuadra();
        jTextFieldNome.requestFocus();
    }

    private void limparCampos() {
        jTextFieldCodigo.setText("");
        jTextFieldNome.setText("");
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
                            CarregarTipoQuadraSelecionada();
                            break;
                        }
                    case "Delete":
                        int excluir = JOptionPane.showConfirmDialog(editorComponent, "Tem certeza?");
                        if (excluir == 0) {
                            int selectedRow = jTableDadosTipoQuadra.getSelectedRow();
                            if (selectedRow != -1) {
                                int localCodigo = (int) jTableDadosTipoQuadra.getValueAt(selectedRow, 0);
                                tipoQuadraService.excluirTipoQuadra(localCodigo);
                                listarTipoQuadras();
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

            localizarTipoQuadra(criterio, pesquisa);
        }
    }

    private void localizarTipoQuadra(String criterio, String valor) {
        TipoQuadra tipoQuadra = null;

        try {
            switch (criterio) {
                case "Código":
                    int codigo = Integer.parseInt(valor);
                    tipoQuadra = tipoQuadraService.localizarTipoQuadraPorCodigo(codigo);
                    CarregarTipoQuadraSelecionada();
                    break;
                case "Nome":
                    tipoQuadra = tipoQuadraService.localizarTipoQuadraPorNome(valor);
                    CarregarTipoQuadraSelecionada();
                    break;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O valor do código deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        if (tipoQuadra != null) {
            // Exibir os detalhes do Tipo de Quadra localizada
            JOptionPane.showMessageDialog(this, "Tipo de Quadra localizada:\n" + tipoQuadra.getNome(), "Tipo de Quadra Encontrada", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Tipo de Quadra não encontrada.", "Tipo de Quadra Não Encontrada", JOptionPane.WARNING_MESSAGE);
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
    private javax.swing.JLabel jLabelCodigo;
    private javax.swing.JLabel jLabelNomeModalidade;
    private javax.swing.JPanel jPanelModalidade;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDadosTipoQuadra;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldNome;
    // End of variables declaration//GEN-END:variables
}
