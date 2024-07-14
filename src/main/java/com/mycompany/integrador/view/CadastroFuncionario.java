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
import com.mycompany.integrador.util.ValidacoesService;
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
import javax.xml.validation.Validator;
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
    private final ValidacoesService validacoesService;

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
        validacoesService = new ValidacoesService();

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

        String textoFormatado = validacoesService.formatarCpfCnpj(texto);
        jFormattedTextFieldCPF.setText(textoFormatado);

        if (textoFormatado.length() == 14) {
            boolean valido = validacoesService.validarCpf(textoFormatado);
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
        
    public boolean validarEmailField() {
        if (!validacoesService.validarEmail(jTextFieldEmail)) {
            JOptionPane.showMessageDialog(null, "Email inválido. Verifique!");
            jTextFieldEmail.requestFocus();
            return false;
        }
        return true;
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

        backgroundPanel = new javax.swing.JPanel();
        buttonsPanel = new javax.swing.JPanel();
        jButtonInserir = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonLocalizar = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Funcionário");
        setBackground(new java.awt.Color(51, 51, 51));
        setMaximumSize(new java.awt.Dimension(1095, 800));
        setMinimumSize(new java.awt.Dimension(1095, 800));

        backgroundPanel.setBackground(new java.awt.Color(51, 51, 51));
        backgroundPanel.setMaximumSize(new java.awt.Dimension(1080, 800));
        backgroundPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonsPanel.setBackground(new java.awt.Color(51, 51, 51));
        buttonsPanel.setMaximumSize(new java.awt.Dimension(1080, 100));
        buttonsPanel.setMinimumSize(new java.awt.Dimension(1000, 100));
        buttonsPanel.setPreferredSize(new java.awt.Dimension(1000, 100));
        buttonsPanel.setLayout(new java.awt.GridLayout(1, 0));

        jButtonInserir.setBackground(new java.awt.Color(51, 51, 51));
        jButtonInserir.setForeground(new java.awt.Color(204, 204, 204));
        jButtonInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/novo.png"))); // NOI18N
        jButtonInserir.setText("Inserir");
        jButtonInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInserirActionPerformed(evt);
            }
        });
        buttonsPanel.add(jButtonInserir);

        jButtonExcluir.setBackground(new java.awt.Color(51, 51, 51));
        jButtonExcluir.setForeground(new java.awt.Color(204, 204, 204));
        jButtonExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/apagar.png"))); // NOI18N
        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });
        buttonsPanel.add(jButtonExcluir);

        jButtonSalvar.setBackground(new java.awt.Color(51, 51, 51));
        jButtonSalvar.setForeground(new java.awt.Color(204, 204, 204));
        jButtonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/salvar.png"))); // NOI18N
        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });
        buttonsPanel.add(jButtonSalvar);

        jButtonCancelar.setBackground(new java.awt.Color(51, 51, 51));
        jButtonCancelar.setForeground(new java.awt.Color(204, 204, 204));
        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancelar.png"))); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        buttonsPanel.add(jButtonCancelar);

        jButtonLocalizar.setBackground(new java.awt.Color(51, 51, 51));
        jButtonLocalizar.setForeground(new java.awt.Color(204, 204, 204));
        jButtonLocalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/localizar.png"))); // NOI18N
        jButtonLocalizar.setText("Localizar");
        jButtonLocalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLocalizarActionPerformed(evt);
            }
        });
        buttonsPanel.add(jButtonLocalizar);

        jButtonEditar.setBackground(new java.awt.Color(51, 51, 51));
        jButtonEditar.setForeground(new java.awt.Color(204, 204, 204));
        jButtonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edit.png"))); // NOI18N
        jButtonEditar.setText("Editar");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });
        buttonsPanel.add(jButtonEditar);

        jButtonSair.setBackground(new java.awt.Color(51, 51, 51));
        jButtonSair.setForeground(new java.awt.Color(204, 204, 204));
        jButtonSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sair.png"))); // NOI18N
        jButtonSair.setText("Sair");
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });
        buttonsPanel.add(jButtonSair);

        backgroundPanel.add(buttonsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 120));

        jPanelFuncionario.setBackground(new java.awt.Color(51, 51, 51));
        jPanelFuncionario.setForeground(new java.awt.Color(204, 204, 204));
        jPanelFuncionario.setPreferredSize(new java.awt.Dimension(1250, 700));

        jLabelCodigo.setForeground(new java.awt.Color(204, 204, 204));
        jLabelCodigo.setText("Código:");

        jTextFieldCodigo.setEditable(false);
        jTextFieldCodigo.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldCodigo.setForeground(new java.awt.Color(51, 51, 51));
        jTextFieldCodigo.setBorder(null);
        jTextFieldCodigo.setEnabled(false);
        jTextFieldCodigo.setMinimumSize(new java.awt.Dimension(70, 30));
        jTextFieldCodigo.setNextFocusableComponent(jTextFieldNome);

        jLabelNome.setForeground(new java.awt.Color(204, 204, 204));
        jLabelNome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelNome.setText("Nome:");

        jTextFieldNome.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldNome.setForeground(new java.awt.Color(51, 51, 51));
        jTextFieldNome.setBorder(null);
        jTextFieldNome.setNextFocusableComponent(jFormattedTextFieldCPF);

        jLabelCPF.setForeground(new java.awt.Color(204, 204, 204));
        jLabelCPF.setText("CPF:");

        jLabelEmail.setForeground(new java.awt.Color(204, 204, 204));
        jLabelEmail.setText("Email:");

        jTextFieldEmail.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldEmail.setForeground(new java.awt.Color(51, 51, 51));
        jTextFieldEmail.setBorder(null);

        jLabelTelefone.setForeground(new java.awt.Color(204, 204, 204));
        jLabelTelefone.setText("Telefone:");

        jTextFieldTelefone.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldTelefone.setForeground(new java.awt.Color(51, 51, 51));
        jTextFieldTelefone.setBorder(null);
        jTextFieldTelefone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldTelefoneKeyTyped(evt);
            }
        });

        jLabelSenha.setForeground(new java.awt.Color(204, 204, 204));
        jLabelSenha.setText("Senha:");

        jLabelConfirmaSenha.setForeground(new java.awt.Color(204, 204, 204));
        jLabelConfirmaSenha.setText("Confirma Senha:");

        jPasswordFieldSenha.setBackground(new java.awt.Color(153, 153, 153));
        jPasswordFieldSenha.setForeground(new java.awt.Color(51, 51, 51));
        jPasswordFieldSenha.setBorder(null);

        jTableDadosFuncionario.setBackground(new java.awt.Color(51, 51, 51));
        jTableDadosFuncionario.setForeground(new java.awt.Color(204, 204, 204));
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
        jTableDadosFuncionario.setGridColor(new java.awt.Color(51, 51, 51));
        jTableDadosFuncionario.setMaximumSize(new java.awt.Dimension(939, 400));
        jTableDadosFuncionario.setMinimumSize(new java.awt.Dimension(939, 400));
        jTableDadosFuncionario.setPreferredSize(new java.awt.Dimension(939, 400));
        jTableDadosFuncionario.setSelectionBackground(new java.awt.Color(0, 153, 204));
        jTableDadosFuncionario.setSelectionForeground(new java.awt.Color(51, 51, 51));
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

        jPasswordFieldConfirmaSenha.setBackground(new java.awt.Color(153, 153, 153));
        jPasswordFieldConfirmaSenha.setForeground(new java.awt.Color(51, 51, 51));
        jPasswordFieldConfirmaSenha.setBorder(null);

        jButtonCadastrar.setBackground(new java.awt.Color(51, 153, 255));
        jButtonCadastrar.setForeground(new java.awt.Color(51, 51, 51));
        jButtonCadastrar.setText("Cadastrar");
        jButtonCadastrar.setIconTextGap(5);
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });

        jLabelCEP.setForeground(new java.awt.Color(204, 204, 204));
        jLabelCEP.setText("CEP:");

        jTextFieldEndereco.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldEndereco.setForeground(new java.awt.Color(51, 51, 51));
        jTextFieldEndereco.setBorder(null);

        jLabelEndereco.setForeground(new java.awt.Color(204, 204, 204));
        jLabelEndereco.setText("Endereço:");

        jTextFieldNumero.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldNumero.setForeground(new java.awt.Color(51, 51, 51));
        jTextFieldNumero.setBorder(null);

        jLabelNumero.setForeground(new java.awt.Color(204, 204, 204));
        jLabelNumero.setText("Número:");

        jLabelCidade.setForeground(new java.awt.Color(204, 204, 204));
        jLabelCidade.setText("Cidade:");

        jTextFieldBairro.setBackground(new java.awt.Color(153, 153, 153));
        jTextFieldBairro.setForeground(new java.awt.Color(51, 51, 51));
        jTextFieldBairro.setBorder(null);

        jLabelBairro.setForeground(new java.awt.Color(204, 204, 204));
        jLabelBairro.setText("Bairro:");

        jComboBoxCidade.setBackground(new java.awt.Color(153, 153, 153));
        jComboBoxCidade.setForeground(new java.awt.Color(51, 51, 51));
        jComboBoxCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCidadeActionPerformed(evt);
            }
        });

        jFormattedTextFieldCPF.setBackground(new java.awt.Color(51, 51, 51));
        jFormattedTextFieldCPF.setForeground(new java.awt.Color(204, 204, 204));

        jFormattedTextFieldCEP.setBackground(new java.awt.Color(153, 153, 153));
        jFormattedTextFieldCEP.setForeground(new java.awt.Color(51, 51, 51));

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setForeground(new java.awt.Color(51, 51, 51));
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
                .addContainerGap()
                .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFuncionarioLayout.createSequentialGroup()
                        .addComponent(jLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jLabelTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTelefone))
                    .addGroup(jPanelFuncionarioLayout.createSequentialGroup()
                        .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelFuncionarioLayout.createSequentialGroup()
                                .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelFuncionarioLayout.createSequentialGroup()
                                        .addComponent(jTextFieldEndereco)
                                        .addGap(68, 68, 68))
                                    .addGroup(jPanelFuncionarioLayout.createSequentialGroup()
                                        .addComponent(jFormattedTextFieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                                        .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)))
                                .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelFuncionarioLayout.createSequentialGroup()
                                        .addComponent(jLabelNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabelCEP)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFormattedTextFieldCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFuncionarioLayout.createSequentialGroup()
                                .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelFuncionarioLayout.createSequentialGroup()
                                        .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39)
                                        .addComponent(jLabelConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPasswordFieldConfirmaSenha))
                                    .addGroup(jPanelFuncionarioLayout.createSequentialGroup()
                                        .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBoxCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFuncionarioLayout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        jPanelFuncionarioLayout.setVerticalGroup(
            jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFuncionarioLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordFieldConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jFormattedTextFieldCPF.setBorder(null);
        jFormattedTextFieldCEP.setBorder(null);

        backgroundPanel.add(jPanelFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 1080, 680));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1095, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        
        if (!validarEmailField()) {
            return;
        }
        
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
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JPanel buttonsPanel;
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
