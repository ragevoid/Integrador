/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.integrador.view;

import com.mycompany.integrador.model.Cidade;
import com.mycompany.integrador.model.Funcionario;
import com.mycompany.integrador.model.service.CidadeService;
import com.mycompany.integrador.util.CombinedFilter;
import com.mycompany.integrador.model.service.FuncionarioService;
import com.mycompany.integrador.util.ValidarCPFCNPJService;
import com.mycompany.integrador.util.ButtonRenderer;
import com.mycompany.integrador.util.LocalizarService;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.Arrays;
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
//import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author jose.zanandrea
 */
public class CadastroFuncionario extends javax.swing.JFrame {

    private int rowIndex = -1; // Adicionando variável para armazenar o índice da linha selecionada
    private int codigo;
    private final DefaultTableModel tableModel;
    private final FuncionarioService funcionarioService;
    private final CidadeService cidadeService;
    private final ValidarCPFCNPJService validarCPFCNPJService;

    /**
     * Creates new form CadastroFuncionario
     */
    public CadastroFuncionario() {
        initComponents();
        setBounds(new java.awt.Rectangle(0, 0, 800, 600));
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        
        
        aplicarUpperCaseFilter(jTextFieldNome, 60);
        aplicarUpperCaseFilter(jTextFieldTelefone, 20);
        aplicarLowerCaseFilter(jTextFieldEmail, 40);
        aplicarUpperCaseFilter(jTextFieldEndereco, 60);
        aplicarUpperCaseFilter(jTextFieldBairro, 40);
        aplicarFormatoCEP(jFormattedTextFieldCEP);


        tableModel = (DefaultTableModel) jTableDadosFuncionario.getModel();
        funcionarioService = new FuncionarioService();
        cidadeService = new CidadeService();
        validarCPFCNPJService = new ValidarCPFCNPJService();

        aplicarMascaraCPF();
        listarFuncionarios();
        configureTable();

    }
    
    
    private void aplicarMascaraCPF() {
        try {
            MaskFormatter formatterCPF = new MaskFormatter("###.###.###-##");
            formatterCPF.setPlaceholderCharacter('_');
            formatterCPF.install(jFormattedTextFieldCPF); // Instala o formatter no campo existente

            // Adiciona um ouvinte de foco para formatar e validar enquanto o usuário digita
            jFormattedTextFieldCPF.addFocusListener(new FocusAdapter() {
               @Override
                public void focusGained(FocusEvent e) {
                    if (jFormattedTextFieldCPF.getText().trim().isEmpty()) {
                        jFormattedTextFieldCPF.setText("___.___.___-__");
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    formatarEValidarCPF();
                }
            });

            // Adiciona um ouvinte de teclado para formatar e validar enquanto o usuário digita
            jFormattedTextFieldCPF.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    formatarEValidarCPF();
                }
            });

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void formatarEValidarCPF() {
        String texto = jFormattedTextFieldCPF.getText();
        if (texto == null || texto.trim().isEmpty()) {
            return;
        }

        String textoFormatado = validarCPFCNPJService.formatarCpfCnpj(texto);
        jFormattedTextFieldCPF.setText(textoFormatado);

        if (textoFormatado.length() == 14) {
            boolean valido = validarCPFCNPJService.validarCpf(textoFormatado);
            if (!valido) {
                JOptionPane.showMessageDialog(this,
                        "CPF inválido!", "Erro de validação", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
        private void aplicarFormatoCEP(JFormattedTextField campoCEP) {
        try {
            MaskFormatter maskFormatter = new MaskFormatter("#####-###");
            maskFormatter.install(campoCEP);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    
    public static void aplicarUpperCaseFilter(JTextField textField, int limite) {
        AbstractDocument doc = (AbstractDocument) textField.getDocument();
        doc.setDocumentFilter(new CombinedFilter(limite, true)); // Filtro de maiúsculas
    }

    public static void aplicarLowerCaseFilter(JTextField textField, int limite) {
        AbstractDocument doc = (AbstractDocument) textField.getDocument();
        doc.setDocumentFilter(new CombinedFilter(limite, false)); // Filtro de minúsculas
    }
    
    private void atualizarCodigoFuncionario() {
        try {
            int maxCodigo = funcionarioService.getMaxCodigoFuncionario();
            jTextFieldCodigo.setText(String.valueOf(maxCodigo));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao obter o código da cidade: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configureTable() {
        jTableDadosFuncionario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableDadosFuncionario.getSelectionModel().addListSelectionListener(e -> {
            rowIndex = jTableDadosFuncionario.getSelectedRow();
        });

        // Renderizador para os botões
        jTableDadosFuncionario.getColumn("Edit").setCellRenderer(new ButtonRenderer("Edit"));
        jTableDadosFuncionario.getColumn("Delete").setCellRenderer(new ButtonRenderer("Delete"));

        // Editor para os botões
        jTableDadosFuncionario.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox(), "Edit", jTableDadosFuncionario));
        jTableDadosFuncionario.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(), "Delete", jTableDadosFuncionario));

    }

    private void CarregarFuncionarioSelecionado() {
        int codigo = (int) jTableDadosFuncionario.getValueAt(rowIndex, 0);
        String codigoStr = String.valueOf(codigo);
        String nome = (String) jTableDadosFuncionario.getValueAt(rowIndex, 1);
        String CPF = (String) jTableDadosFuncionario.getValueAt(rowIndex, 2);
        String email = (String) jTableDadosFuncionario.getValueAt(rowIndex, 3);
        String telefone = (String) jTableDadosFuncionario.getValueAt(rowIndex, 4);
        String endereco = (String) jTableDadosFuncionario.getValueAt(rowIndex, 5);
        String numero = (String) jTableDadosFuncionario.getValueAt(rowIndex, 6);
        String CEP = (String) jTableDadosFuncionario.getValueAt(rowIndex, 7);
        String bairro = (String) jTableDadosFuncionario.getValueAt(rowIndex, 8);
        int codigoCidade = (int) jTableDadosFuncionario.getValueAt(rowIndex, 9);
        //String senha = (String) jTableDadosFuncionario.getValueAt(rowIndex, 10);
        //String confirmarSenha = (String) jTableDadosFuncionario.getValueAt(rowIndex, 11);

        // Preencha os campos com os dados do funcionario selecionado para edição 
        jTextFieldCodigo.setText(codigoStr);
        jTextFieldNome.setText(nome);
        jFormattedTextFieldCPF.setText(CPF);
        jTextFieldTelefone.setText(telefone);
        jTextFieldEmail.setText(email);
        jTextFieldEndereco.setText(endereco);
        jTextFieldNumero.setText(numero);
        jFormattedTextFieldCEP.setText(CEP);
        jTextFieldBairro.setText(bairro);
        //jPasswordFieldConfirmaSenha.setText(confirmarSenha);
        //jPasswordFieldSenha.setText(senha);

        String nomeCidade = cidadeService.getNomeCidade(codigoCidade);

        // Limpar e adicionar apenas o nome da cidade do funcionário selecionado ao combobox
        jComboBoxCidade.removeAllItems();
        jComboBoxCidade.addItem(nomeCidade); // Adiciona o nome da cidade ao combobox
        jComboBoxCidade.setSelectedItem(nomeCidade); // Seleciona o nome da cidade

        jPasswordFieldSenha.setEditable(false);
        jPasswordFieldConfirmaSenha.setEditable(false);
    }

    public void editarFuncionario(int row) {
        jTableDadosFuncionario.setRowSelectionInterval(row, row);
        CarregarFuncionarioSelecionado();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelFuncionario = new javax.swing.JPanel();
        jLabelCodigo = new javax.swing.JLabel();
        jTextFieldCodigo = new javax.swing.JTextField();
        jLabelNome = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabelCPF = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabelTelefone = new javax.swing.JLabel();
        jTextFieldTelefone = new javax.swing.JTextField();
        jLabelSenha = new javax.swing.JLabel();
        jLabelConfirmaSenha = new javax.swing.JLabel();
        jPasswordFieldSenha = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDadosFuncionario = new javax.swing.JTable();
        jPasswordFieldConfirmaSenha = new javax.swing.JPasswordField();
        jButtonCadastrar = new javax.swing.JButton();
        jLabelCEP = new javax.swing.JLabel();
        jTextFieldEndereco = new javax.swing.JTextField();
        jLabelEndereco = new javax.swing.JLabel();
        jTextFieldNumero = new javax.swing.JTextField();
        jLabelNumero = new javax.swing.JLabel();
        jLabelCidade = new javax.swing.JLabel();
        jTextFieldBairro = new javax.swing.JTextField();
        jLabelBairro = new javax.swing.JLabel();
        jComboBoxCidade = new javax.swing.JComboBox<>();
        jFormattedTextFieldCPF = new javax.swing.JFormattedTextField();
        jFormattedTextFieldCEP = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        jButtonInserir = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonLocalizar = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Funcionário");
        setBackground(new java.awt.Color(204, 255, 255));

        jPanelFuncionario.setBackground(new java.awt.Color(153, 153, 153));
        jPanelFuncionario.setPreferredSize(new java.awt.Dimension(1250, 700));

        jLabelCodigo.setText("Código:");

        jTextFieldCodigo.setEditable(false);
        jTextFieldCodigo.setEnabled(false);
        jTextFieldCodigo.setMinimumSize(new java.awt.Dimension(70, 30));
        jTextFieldCodigo.setNextFocusableComponent(jTextFieldNome);

        jLabelNome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelNome.setText("Nome:");

        jTextFieldNome.setNextFocusableComponent(jFormattedTextFieldCPF);

        jLabelCPF.setText("CPF:");

        jLabelEmail.setText("Email:");

        jLabelTelefone.setText("Telefone:");

        jTextFieldTelefone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldTelefoneKeyTyped(evt);
            }
        });

        jLabelSenha.setText("Senha:");

        jLabelConfirmaSenha.setText("Confirma Senha:");

        jTableDadosFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "CPF", "Email", "Telefone", "Endereco", "Numero", "CEP", "Bairro", "Cidade", "Edit", "Delete"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableDadosFuncionario);
        if (jTableDadosFuncionario.getColumnModel().getColumnCount() > 0) {
            jTableDadosFuncionario.getColumnModel().getColumn(0).setResizable(false);
            jTableDadosFuncionario.getColumnModel().getColumn(0).setPreferredWidth(70);
            jTableDadosFuncionario.getColumnModel().getColumn(1).setResizable(false);
            jTableDadosFuncionario.getColumnModel().getColumn(1).setPreferredWidth(300);
            jTableDadosFuncionario.getColumnModel().getColumn(2).setResizable(false);
            jTableDadosFuncionario.getColumnModel().getColumn(2).setPreferredWidth(120);
            jTableDadosFuncionario.getColumnModel().getColumn(3).setResizable(false);
            jTableDadosFuncionario.getColumnModel().getColumn(3).setPreferredWidth(200);
            jTableDadosFuncionario.getColumnModel().getColumn(4).setResizable(false);
            jTableDadosFuncionario.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTableDadosFuncionario.getColumnModel().getColumn(5).setResizable(false);
            jTableDadosFuncionario.getColumnModel().getColumn(5).setPreferredWidth(200);
            jTableDadosFuncionario.getColumnModel().getColumn(6).setResizable(false);
            jTableDadosFuncionario.getColumnModel().getColumn(6).setPreferredWidth(50);
            jTableDadosFuncionario.getColumnModel().getColumn(7).setResizable(false);
            jTableDadosFuncionario.getColumnModel().getColumn(7).setPreferredWidth(120);
            jTableDadosFuncionario.getColumnModel().getColumn(8).setResizable(false);
            jTableDadosFuncionario.getColumnModel().getColumn(8).setPreferredWidth(100);
            jTableDadosFuncionario.getColumnModel().getColumn(9).setResizable(false);
            jTableDadosFuncionario.getColumnModel().getColumn(9).setPreferredWidth(120);
            jTableDadosFuncionario.getColumnModel().getColumn(10).setResizable(false);
            jTableDadosFuncionario.getColumnModel().getColumn(11).setResizable(false);
        }

        jButtonCadastrar.setBackground(new java.awt.Color(51, 153, 255));
        jButtonCadastrar.setText("Cadastrar");
        jButtonCadastrar.setIconTextGap(5);
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });

        jLabelCEP.setText("CEP:");

        jLabelEndereco.setText("Endereço:");

        jLabelNumero.setText("Número:");

        jLabelCidade.setText("Cidade:");

        jLabelBairro.setText("Bairro:");

        jComboBoxCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCidadeActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFuncionarioLayout = new javax.swing.GroupLayout(jPanelFuncionario);
        jPanelFuncionario.setLayout(jPanelFuncionarioLayout);
        jPanelFuncionarioLayout.setHorizontalGroup(
            jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFuncionarioLayout.createSequentialGroup()
                .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelFuncionarioLayout.createSequentialGroup()
                            .addComponent(jLabelBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(jLabelCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jComboBoxCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelFuncionarioLayout.createSequentialGroup()
                        .addComponent(jLabelSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPasswordFieldConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(132, 132, 132)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelFuncionarioLayout.createSequentialGroup()
                        .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCodigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelFuncionarioLayout.createSequentialGroup()
                                .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelFuncionarioLayout.createSequentialGroup()
                                .addComponent(jTextFieldEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelCEP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextFieldCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelFuncionarioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelFuncionarioLayout.createSequentialGroup()
                                .addComponent(jLabelCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextFieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 44, Short.MAX_VALUE))
        );
        jPanelFuncionarioLayout.setVerticalGroup(
            jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFuncionarioLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFuncionarioLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFuncionarioLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordFieldConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(34, Short.MAX_VALUE))
            .addComponent(jPanelFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
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
                .addComponent(jPanelFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
        
        String codigo = jTextFieldCodigo.getText();
        int codigoint = Integer.parseInt(codigo); 
        String nome = jTextFieldNome.getText();
        String CPF = jFormattedTextFieldCPF.getText();
        String telefone = jTextFieldTelefone.getText();
        String email = jTextFieldEmail.getText();
        char[] senhaChars = jPasswordFieldSenha.getPassword();
        String senha = new String(senhaChars); // Obtém a senha como String
        char[] confirmarSenhaChars = jPasswordFieldConfirmaSenha.getPassword();
        String confirmarSenha = new String(confirmarSenhaChars); // Obtém a confirmação da senha como String
        String endereco = jTextFieldEndereco.getText();
        String numero = jTextFieldNumero.getText();
        String CEP = jFormattedTextFieldCEP.getText();
        String bairro = jTextFieldBairro.getText();

        int codigoCidade = cidadeService.getCodigoCidade(jComboBoxCidade.getSelectedItem().toString());

        if (!senha.equals(confirmarSenha)) {
            JOptionPane.showMessageDialog(null, "As senhas não correspondem. Por favor, tente novamente.");
            return;
        }
        

        // Limpando o array de caracteres da senha após o uso
        Arrays.fill(senhaChars, ' ');
        Arrays.fill(confirmarSenhaChars, ' ');

        if (rowIndex == -1) { // Se rowIndex for -1, é uma nova pessoa

            Funcionario funcionario = new Funcionario(senha, confirmarSenha, nome, CPF, telefone, email, endereco, numero, CEP, bairro, codigoCidade);
            funcionarioService.salvarFuncionario(funcionario);
        } else { // Caso contrário, é uma edição de pessoa existente

            Funcionario funcionarioEditado = new Funcionario(senha, confirmarSenha, nome, CPF, telefone, email, endereco, numero, CEP, bairro, codigoCidade);
            funcionarioEditado.setCodigo((int) jTableDadosFuncionario.getValueAt(rowIndex, 0)); // Define o Código da pessoa
            funcionarioService.atualizarFuncionario(funcionarioEditado);
            rowIndex = -1; // Reseta o índice da linha após a edição
        }

        //novoFuncionario();
        limparCampos();
        listarFuncionarios();
        
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void carregarCidades() {
        List<Cidade> cidades = cidadeService.listarCidades();

        for (Cidade cidade : cidades) {
            jComboBoxCidade.addItem(cidade.getNome());
            
        }
    }

    private void jComboBoxCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCidadeActionPerformed

        jComboBoxCidade.getSelectedItem();

    }//GEN-LAST:event_jComboBoxCidadeActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        salvarFuncionario();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInserirActionPerformed
        novoFuncionario();
    }//GEN-LAST:event_jButtonInserirActionPerformed

    private void jTextFieldTelefoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTelefoneKeyTyped
        String caracteres = "0987654321-";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldTelefoneKeyTyped

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        limparCampos();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        String codigo = jTextFieldCodigo.getText();
        int codigoint = Integer.parseInt(codigo);
        JOptionPane.showMessageDialog(null,
                "Deseja realmente excluir os dados selecionados?", "Excluir Funciónario", JOptionPane.YES_NO_CANCEL_OPTION);        
        funcionarioService.excluirFuncionario(codigoint);
        listarFuncionarios();
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jButtonLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLocalizarActionPerformed

        limparCampos();
        abrirDialogoLocalizar();
    }//GEN-LAST:event_jButtonLocalizarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        CarregarFuncionarioSelecionado();
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        salvarFuncionario();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroFuncionario().setVisible(true);
            }
        //</editor-fold>

        /* Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroFuncionario().setVisible(true);
            }
        });*/
    }

    private void salvarFuncionario() {
        String codigo = jTextFieldCodigo.getText();
        int codigoint = Integer.parseInt(codigo);
        String nome = jTextFieldNome.getText();
        String CPF = jFormattedTextFieldCPF.getText();
        String telefone = jTextFieldTelefone.getText();
        String email = jTextFieldEmail.getText();
        char[] senhaChars = jPasswordFieldSenha.getPassword();
        String senha = new String(senhaChars); // Obtém a senha como String
        char[] confirmarSenhaChars = jPasswordFieldConfirmaSenha.getPassword();
        String confirmarSenha = new String(confirmarSenhaChars); // Obtém a confirmação da senha como String
        String endereco = jTextFieldEndereco.getText();
        String numero = jTextFieldNumero.getText();
        String CEP = jFormattedTextFieldCEP.getText();
        String bairro = jTextFieldBairro.getText();

        int codigoCidade = cidadeService.getCodigoCidade(jComboBoxCidade.getSelectedItem().toString());

        if (!senha.equals(confirmarSenha)) {
            // Lógica para lidar com senhas não correspondentes
            JOptionPane.showMessageDialog(null, "As senhas não correspondem. Por favor, tente novamente.");
            return;
        }

        // Limpando o array de caracteres da senha após o uso
        Arrays.fill(senhaChars, ' ');
        Arrays.fill(confirmarSenhaChars, ' ');

        if (rowIndex == -1) { // Se rowIndex for -1, é uma nova pessoa

            Funcionario funcionario = new Funcionario(senha, confirmarSenha, nome, CPF, telefone, email, endereco, numero, CEP, bairro, codigoCidade);
            funcionarioService.salvarFuncionario(funcionario);
        } else { // Caso contrário, é uma edição de pessoa existente

            Funcionario funcionarioEditado = new Funcionario(codigoint, nome, CPF, telefone, email, endereco, numero, CEP, bairro, codigoCidade);
            funcionarioEditado.setCodigo((int) jTableDadosFuncionario.getValueAt(rowIndex, 0)); // Define o Código da pessoa
            funcionarioService.atualizarFuncionario(funcionarioEditado);
            rowIndex = -1; // Reseta o índice da linha após a edição
        }

        JOptionPane.showMessageDialog(null,
                "Dados inseridos com sucesso!", "Sucesso", JOptionPane.OK_OPTION);
        limparCampos();
        listarFuncionarios();
    }

    private void listarFuncionarios() {
        tableModel.setRowCount(0);

        funcionarioService.listarFuncionarios().forEach(funcionario -> {
            tableModel.addRow(new Object[]{funcionario.getCodigo(), funcionario.getNome(), funcionario.getCPF(), funcionario.getEmail(),
                funcionario.getTelefone(), funcionario.getEndereco(), funcionario.getNumero(), funcionario.getCEP(), funcionario.getBairro(), funcionario.getCidade(),
                funcionario.getSenha(), funcionario.getConfirmaSenha()});
        });
    }

    private void novoFuncionario() {
        limparCampos();
        atualizarCodigoFuncionario();
        jTextFieldNome.requestFocus();
        aplicarMascaraCPF();
        carregarCidades();
    }

    private void limparCampos() {
        jTextFieldCodigo.setText("");
        jTextFieldNome.setText("");
        jFormattedTextFieldCPF.setText("");
        jTextFieldEmail.setText("");
        jTextFieldTelefone.setText("");
        jTextFieldEndereco.setText("");
        jTextFieldNumero.setText("");
        jFormattedTextFieldCEP.setText("");
        jTextFieldBairro.setText("");
        jPasswordFieldConfirmaSenha.setText("");
        jPasswordFieldSenha.setText("");
        jComboBoxCidade.removeAllItems();
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
                            CarregarFuncionarioSelecionado();
                            break;
                        }
                    case "Delete":
                        int excluir = JOptionPane.showConfirmDialog(editorComponent, "Tem certeza?");
                        if (excluir == 0) {
                            int selectedRow = jTableDadosFuncionario.getSelectedRow();
                            if (selectedRow != -1) {
                                int localCodigo = (int) jTableDadosFuncionario.getValueAt(selectedRow, 0);
                                funcionarioService.excluirFuncionario(localCodigo);
                                listarFuncionarios();
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

            localizarFuncionario(criterio, pesquisa);
        }
    }

    private void localizarFuncionario(String criterio, String valor) {
        Funcionario funcionario = null;

        try {
            switch (criterio) {
                case "Código":
                    int codigo = Integer.parseInt(valor);
                    funcionario = funcionarioService.localizarFuncionarioPorCodigo(codigo);
                    CarregarFuncionarioSelecionado();
                    break;
                case "Nome":
                    funcionario = funcionarioService.localizarFuncionarioPorNome(valor);
                    CarregarFuncionarioSelecionado();
                    break;
                case "CPF":
                    funcionario = funcionarioService.localizarFuncionarioPorCPF(valor);
                    CarregarFuncionarioSelecionado();
                    break;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O valor do código deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        if (funcionario != null) {
            // Exibir os detalhes do funcionário localizado
            JOptionPane.showMessageDialog(this, "Funcionário localizado:\n" + funcionario.getNome(), "Funcionário Encontrado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Funcionário não encontrado.", "Funcionário Não Encontrado", JOptionPane.WARNING_MESSAGE);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonInserir;
    private javax.swing.JButton jButtonLocalizar;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox<String> jComboBoxCidade;
    private javax.swing.JFormattedTextField jFormattedTextFieldCEP;
    private javax.swing.JFormattedTextField jFormattedTextFieldCPF;
    private javax.swing.JLabel jLabelBairro;
    private javax.swing.JLabel jLabelCEP;
    private javax.swing.JLabel jLabelCPF;
    private javax.swing.JLabel jLabelCidade;
    private javax.swing.JLabel jLabelCodigo;
    private javax.swing.JLabel jLabelConfirmaSenha;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelEndereco;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelNumero;
    private javax.swing.JLabel jLabelSenha;
    private javax.swing.JLabel jLabelTelefone;
    private javax.swing.JPanel jPanelFuncionario;
    private javax.swing.JPasswordField jPasswordFieldConfirmaSenha;
    private javax.swing.JPasswordField jPasswordFieldSenha;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDadosFuncionario;
    private javax.swing.JTextField jTextFieldBairro;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldEndereco;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldNumero;
    private javax.swing.JTextField jTextFieldTelefone;
    // End of variables declaration//GEN-END:variables
}
