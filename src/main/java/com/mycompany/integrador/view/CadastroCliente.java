/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.integrador.view;

import com.mycompany.integrador.model.Cidade;
import com.mycompany.integrador.model.Cliente;
import com.mycompany.integrador.model.service.CidadeService;
import com.mycompany.integrador.model.service.ClienteService;
import com.mycompany.integrador.util.CombinedFilter;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class CadastroCliente extends javax.swing.JFrame {

    private int rowIndex = -1; // Adicionando variável para armazenar o índice da linha selecionada
    private int codigo;
    private final DefaultTableModel tableModel;
    private final ClienteService clienteService;
    private final CidadeService cidadeService;
    private final ValidacoesService validacoesService;
    /**
     * Creates new form CadastroFuncionario
     */
    public CadastroCliente() {
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
        configurarMascaraDataNascimento(jFormattedTextFielddataNascimento);


        tableModel = (DefaultTableModel) jTableDadosCliente.getModel();
        clienteService = new ClienteService();
        cidadeService = new CidadeService();
        validacoesService = new ValidacoesService();

        aplicarMascaraCPF();
        listarClientes();
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
    
    public void configurarMascaraDataNascimento(JFormattedTextField jFormattedTextFieldDataNascimento) {
        try {
            MaskFormatter maskFormatter = new MaskFormatter("##/##/####");
            maskFormatter.setPlaceholderCharacter('_');
            maskFormatter.install(jFormattedTextFieldDataNascimento);
        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao configurar a máscara de data de nascimento.");
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
    
    private void atualizarCodigoCliente() {
        try {
            int maxCodigo = clienteService.getMaxCodigoCliente();
            jTextFieldCodigo.setText(String.valueOf(maxCodigo));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao obter o código da cliente: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configureTable() {
        jTableDadosCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableDadosCliente.getSelectionModel().addListSelectionListener(e -> {
            rowIndex = jTableDadosCliente.getSelectedRow();
        });

        // Renderizador para os botões
        jTableDadosCliente.getColumn("Edit").setCellRenderer(new ButtonRenderer("Edit"));
        jTableDadosCliente.getColumn("Delete").setCellRenderer(new ButtonRenderer("Delete"));

        // Editor para os botões
        jTableDadosCliente.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox(), "Edit", jTableDadosCliente));
        jTableDadosCliente.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(), "Delete", jTableDadosCliente));

    }

    private void CarregarClienteSelecionado() {
        int codigo = (int) jTableDadosCliente.getValueAt(rowIndex, 0);
        String codigoStr = String.valueOf(codigo);
        String nome = (String) jTableDadosCliente.getValueAt(rowIndex, 1);
        String CPF = (String) jTableDadosCliente.getValueAt(rowIndex, 2);
        String email = (String) jTableDadosCliente.getValueAt(rowIndex, 3);
        String telefone = (String) jTableDadosCliente.getValueAt(rowIndex, 4);
        String endereco = (String) jTableDadosCliente.getValueAt(rowIndex, 5);
        String numero = (String) jTableDadosCliente.getValueAt(rowIndex, 6);
        String CEP = (String) jTableDadosCliente.getValueAt(rowIndex, 7);
        String bairro = (String) jTableDadosCliente.getValueAt(rowIndex, 8);
        int codigoCidade = (int) jTableDadosCliente.getValueAt(rowIndex, 9);
        Date dataNascimento = (Date) jTableDadosCliente.getValueAt(rowIndex, 10);

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
        
        /*String dataNascimento1 = jFormattedTextFielddataNascimento.getText();

        Date dataNascimentoDate = null;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); // Defina o formato da sua data de nascimento

        try {
            dataNascimentoDate = df.parse(dataNascimento1);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de data inválido. Use o formato dd/MM/yyyy.");
            return;
        }
        jFormattedTextFielddataNascimento.setText(dataNascimento1);*/

        String nomeCidade = cidadeService.getNomeCidade(codigoCidade);

        // Limpar e adicionar apenas o nome da cidade do funcionário selecionado ao combobox
        jComboBoxCidade.removeAllItems();
        jComboBoxCidade.addItem(nomeCidade); // Adiciona o nome da cidade ao combobox
        jComboBoxCidade.setSelectedItem(nomeCidade); // Seleciona o nome da cidade

    }

    public void editarCliente(int row) {
        jTableDadosCliente.setRowSelectionInterval(row, row);
        CarregarClienteSelecionado();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BackGround = new javax.swing.JPanel();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDadosCliente = new javax.swing.JTable();
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
        jFormattedTextFielddataNascimento = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanelButtons = new javax.swing.JPanel();
        jButtonLocalizar = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonInserir = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Cliente");
        setBackground(new java.awt.Color(51, 51, 51));
        setMaximumSize(new java.awt.Dimension(940, 730));
        setMinimumSize(new java.awt.Dimension(940, 730));
        setPreferredSize(new java.awt.Dimension(940, 730));

        BackGround.setBackground(new java.awt.Color(51, 51, 51));
        BackGround.setMaximumSize(new java.awt.Dimension(940, 730));
        BackGround.setPreferredSize(new java.awt.Dimension(940, 730));
        BackGround.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelFuncionario.setBackground(new java.awt.Color(51, 51, 51));
        jPanelFuncionario.setMaximumSize(new java.awt.Dimension(939, 645));
        jPanelFuncionario.setMinimumSize(new java.awt.Dimension(939, 645));
        jPanelFuncionario.setPreferredSize(new java.awt.Dimension(939, 645));
        jPanelFuncionario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelCodigo.setForeground(new java.awt.Color(204, 204, 204));
        jLabelCodigo.setText("Código:");
        jPanelFuncionario.add(jLabelCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 60, 25));

        jTextFieldCodigo.setEditable(false);
        jTextFieldCodigo.setBackground(new java.awt.Color(102, 102, 102));
        jTextFieldCodigo.setForeground(new java.awt.Color(204, 204, 204));
        jTextFieldCodigo.setEnabled(false);
        jTextFieldCodigo.setMinimumSize(new java.awt.Dimension(70, 30));
        jTextFieldCodigo.setNextFocusableComponent(jTextFieldNome);
        jPanelFuncionario.add(jTextFieldCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 15, 100, 25));

        jLabelNome.setForeground(new java.awt.Color(204, 204, 204));
        jLabelNome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelNome.setText("Nome:");
        jPanelFuncionario.add(jLabelNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 15, 50, 25));

        jTextFieldNome.setBackground(new java.awt.Color(102, 102, 102));
        jTextFieldNome.setForeground(new java.awt.Color(204, 204, 204));
        jTextFieldNome.setNextFocusableComponent(jFormattedTextFieldCPF);
        jPanelFuncionario.add(jTextFieldNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 650, 25));

        jLabelCPF.setForeground(new java.awt.Color(204, 204, 204));
        jLabelCPF.setText("CPF:");
        jPanelFuncionario.add(jLabelCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 46, 60, 25));

        jLabelEmail.setForeground(new java.awt.Color(204, 204, 204));
        jLabelEmail.setText("Email:");
        jPanelFuncionario.add(jLabelEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 81, 60, 25));

        jTextFieldEmail.setBackground(new java.awt.Color(102, 102, 102));
        jTextFieldEmail.setForeground(new java.awt.Color(204, 204, 204));
        jPanelFuncionario.add(jTextFieldEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 81, 850, 25));

        jLabelTelefone.setForeground(new java.awt.Color(204, 204, 204));
        jLabelTelefone.setText("Telefone:");
        jPanelFuncionario.add(jLabelTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 46, 60, 25));

        jTextFieldTelefone.setBackground(new java.awt.Color(102, 102, 102));
        jTextFieldTelefone.setForeground(new java.awt.Color(204, 204, 204));
        jTextFieldTelefone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldTelefoneKeyTyped(evt);
            }
        });
        jPanelFuncionario.add(jTextFieldTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 46, 430, 25));

        jTableDadosCliente.setBackground(new java.awt.Color(51, 51, 51));
        jTableDadosCliente.setForeground(new java.awt.Color(204, 204, 204));
        jTableDadosCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "CPF", "Email", "Telefone", "Endereco", "Numero", "CEP", "Bairro", "Cidade", "Data Nascimento", "Edit", "Delete"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDadosCliente.setGridColor(new java.awt.Color(51, 51, 51));
        jTableDadosCliente.setMaximumSize(new java.awt.Dimension(939, 400));
        jTableDadosCliente.setMinimumSize(new java.awt.Dimension(939, 400));
        jTableDadosCliente.setName(""); // NOI18N
        jTableDadosCliente.setPreferredSize(new java.awt.Dimension(939, 400));
        jScrollPane1.setViewportView(jTableDadosCliente);
        if (jTableDadosCliente.getColumnModel().getColumnCount() > 0) {
            jTableDadosCliente.getColumnModel().getColumn(0).setResizable(false);
            jTableDadosCliente.getColumnModel().getColumn(0).setPreferredWidth(70);
            jTableDadosCliente.getColumnModel().getColumn(1).setResizable(false);
            jTableDadosCliente.getColumnModel().getColumn(1).setPreferredWidth(300);
            jTableDadosCliente.getColumnModel().getColumn(2).setResizable(false);
            jTableDadosCliente.getColumnModel().getColumn(2).setPreferredWidth(120);
            jTableDadosCliente.getColumnModel().getColumn(3).setResizable(false);
            jTableDadosCliente.getColumnModel().getColumn(3).setPreferredWidth(200);
            jTableDadosCliente.getColumnModel().getColumn(4).setResizable(false);
            jTableDadosCliente.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTableDadosCliente.getColumnModel().getColumn(5).setResizable(false);
            jTableDadosCliente.getColumnModel().getColumn(5).setPreferredWidth(200);
            jTableDadosCliente.getColumnModel().getColumn(6).setResizable(false);
            jTableDadosCliente.getColumnModel().getColumn(6).setPreferredWidth(50);
            jTableDadosCliente.getColumnModel().getColumn(7).setResizable(false);
            jTableDadosCliente.getColumnModel().getColumn(7).setPreferredWidth(120);
            jTableDadosCliente.getColumnModel().getColumn(8).setResizable(false);
            jTableDadosCliente.getColumnModel().getColumn(8).setPreferredWidth(100);
            jTableDadosCliente.getColumnModel().getColumn(9).setResizable(false);
            jTableDadosCliente.getColumnModel().getColumn(9).setPreferredWidth(120);
            jTableDadosCliente.getColumnModel().getColumn(10).setResizable(false);
            jTableDadosCliente.getColumnModel().getColumn(11).setResizable(false);
            jTableDadosCliente.getColumnModel().getColumn(12).setResizable(false);
        }

        jPanelFuncionario.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 920, 370));

        jButtonCadastrar.setBackground(new java.awt.Color(51, 153, 255));
        jButtonCadastrar.setText("Cadastrar");
        jButtonCadastrar.setIconTextGap(5);
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });
        jPanelFuncionario.add(jButtonCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 190, 190, 25));

        jLabelCEP.setForeground(new java.awt.Color(204, 204, 204));
        jLabelCEP.setText("CEP:");
        jPanelFuncionario.add(jLabelCEP, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, 25));

        jTextFieldEndereco.setBackground(new java.awt.Color(102, 102, 102));
        jTextFieldEndereco.setForeground(new java.awt.Color(204, 204, 204));
        jPanelFuncionario.add(jTextFieldEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 118, 350, 25));

        jLabelEndereco.setForeground(new java.awt.Color(204, 204, 204));
        jLabelEndereco.setText("Endereço:");
        jPanelFuncionario.add(jLabelEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 118, 60, 25));

        jTextFieldNumero.setBackground(new java.awt.Color(102, 102, 102));
        jTextFieldNumero.setForeground(new java.awt.Color(204, 204, 204));
        jPanelFuncionario.add(jTextFieldNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, 220, 25));

        jLabelNumero.setForeground(new java.awt.Color(204, 204, 204));
        jLabelNumero.setText("Número:");
        jPanelFuncionario.add(jLabelNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 118, 50, 25));

        jLabelCidade.setForeground(new java.awt.Color(204, 204, 204));
        jLabelCidade.setText("Cidade:");
        jPanelFuncionario.add(jLabelCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 50, 25));

        jTextFieldBairro.setBackground(new java.awt.Color(102, 102, 102));
        jTextFieldBairro.setForeground(new java.awt.Color(204, 204, 204));
        jPanelFuncionario.add(jTextFieldBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 149, 340, 25));

        jLabelBairro.setForeground(new java.awt.Color(204, 204, 204));
        jLabelBairro.setText("Bairro:");
        jPanelFuncionario.add(jLabelBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 149, 60, 25));

        jComboBoxCidade.setBackground(new java.awt.Color(102, 102, 102));
        jComboBoxCidade.setForeground(new java.awt.Color(204, 204, 204));
        jComboBoxCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCidadeActionPerformed(evt);
            }
        });
        jPanelFuncionario.add(jComboBoxCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 430, 25));

        jFormattedTextFieldCPF.setBackground(new java.awt.Color(102, 102, 102));
        jFormattedTextFieldCPF.setForeground(new java.awt.Color(204, 204, 204));
        jPanelFuncionario.add(jFormattedTextFieldCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 46, 350, 25));

        jFormattedTextFieldCEP.setBackground(new java.awt.Color(102, 102, 102));
        jFormattedTextFieldCEP.setForeground(new java.awt.Color(204, 204, 204));
        jPanelFuncionario.add(jFormattedTextFieldCEP, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 120, 171, 25));

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanelFuncionario.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, 200, 25));

        jFormattedTextFielddataNascimento.setBackground(new java.awt.Color(102, 102, 102));
        jFormattedTextFielddataNascimento.setForeground(new java.awt.Color(204, 204, 204));
        jPanelFuncionario.add(jFormattedTextFielddataNascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 300, 25));

        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Data Nascimento:");
        jPanelFuncionario.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 192, -1, 25));

        BackGround.add(jPanelFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 940, 600));

        jPanelButtons.setBackground(new java.awt.Color(51, 51, 51));
        jPanelButtons.setMaximumSize(new java.awt.Dimension(939, 71));
        jPanelButtons.setMinimumSize(new java.awt.Dimension(939, 71));
        jPanelButtons.setPreferredSize(new java.awt.Dimension(939, 71));
        jPanelButtons.setLayout(new java.awt.GridLayout());

        jButtonLocalizar.setBackground(new java.awt.Color(102, 102, 102));
        jButtonLocalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/localizar.png"))); // NOI18N
        jButtonLocalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLocalizarActionPerformed(evt);
            }
        });
        jPanelButtons.add(jButtonLocalizar);

        jButtonSair.setBackground(new java.awt.Color(102, 102, 102));
        jButtonSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sair.png"))); // NOI18N
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });
        jPanelButtons.add(jButtonSair);

        jButtonEditar.setBackground(new java.awt.Color(102, 102, 102));
        jButtonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edit.png"))); // NOI18N
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });
        jPanelButtons.add(jButtonEditar);

        jButtonInserir.setBackground(new java.awt.Color(102, 102, 102));
        jButtonInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/localizar.png"))); // NOI18N
        jButtonInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInserirActionPerformed(evt);
            }
        });
        jPanelButtons.add(jButtonInserir);

        jButtonSalvar.setBackground(new java.awt.Color(102, 102, 102));
        jButtonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/salvar.png"))); // NOI18N
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });
        jPanelButtons.add(jButtonSalvar);

        jButtonExcluir.setBackground(new java.awt.Color(102, 102, 102));
        jButtonExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/apagar.png"))); // NOI18N
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });
        jPanelButtons.add(jButtonExcluir);

        jButtonCancelar.setBackground(new java.awt.Color(102, 102, 102));
        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancelar.png"))); // NOI18N
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        jPanelButtons.add(jButtonCancelar);

        BackGround.add(jPanelButtons, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 130));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackGround, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BackGround, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void carregarCidades() {
        List<Cidade> cidades = cidadeService.listarCidades();

        for (Cidade cidade : cidades) {
            jComboBoxCidade.addItem(cidade.getNome());
            
        }
    }

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        
        salvarCliente();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInserirActionPerformed
        novoCliente();
    }//GEN-LAST:event_jButtonInserirActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        limparCampos();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        String codigo = jTextFieldCodigo.getText();
        int codigoint = Integer.parseInt(codigo);
        JOptionPane.showMessageDialog(null,
                "Deseja realmente excluir os dados selecionados?", "Excluir Cliente", JOptionPane.YES_NO_CANCEL_OPTION);        
        clienteService.excluirCliente(codigoint);
        listarClientes();
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jButtonLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLocalizarActionPerformed

        limparCampos();
        abrirDialogoLocalizar();
    }//GEN-LAST:event_jButtonLocalizarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        CarregarClienteSelecionado();
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        salvarCliente();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBoxCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCidadeActionPerformed

        jComboBoxCidade.getSelectedItem();
    }//GEN-LAST:event_jComboBoxCidadeActionPerformed

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed

        String codigo = jTextFieldCodigo.getText();
        int codigoint = Integer.parseInt(codigo);
        String nome = jTextFieldNome.getText();
        String CPF = jFormattedTextFieldCPF.getText();
        String telefone = jTextFieldTelefone.getText();
        String email = jTextFieldEmail.getText();
        String endereco = jTextFieldEndereco.getText();
        String numero = jTextFieldNumero.getText();
        String CEP = jFormattedTextFieldCEP.getText();
        String bairro = jTextFieldBairro.getText();
        String dataNascimento = jFormattedTextFielddataNascimento.getText();

        Date dataNascimentoDate = null;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); // Defina o formato da sua data de nascimento

        try {
            dataNascimentoDate = df.parse(dataNascimento);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de data inválido. Use o formato dd/MM/yyyy.");
            return;
        }

        int codigoCidade = cidadeService.getCodigoCidade(jComboBoxCidade.getSelectedItem().toString());

        if (rowIndex == -1) { // Se rowIndex for -1, é uma nova pessoa

            Cliente cliente = new Cliente(nome, CPF, telefone, email, endereco, numero, CEP, bairro, codigoCidade, dataNascimentoDate);
            clienteService.salvarCliente(cliente);
        } else { // Caso contrário, é uma edição de pessoa existente

            Cliente clienteEditado = new Cliente(nome, CPF, telefone, email, endereco, numero, CEP, bairro, codigoCidade, dataNascimentoDate);
            clienteEditado.setCodigo((int) jTableDadosCliente.getValueAt(rowIndex, 0)); // Define o Código da pessoa
            clienteService.atualizarCliente(clienteEditado);
            rowIndex = -1; // Reseta o índice da linha após a edição
        }

        //novoFuncionario();
        limparCampos();
        listarClientes();
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jTextFieldTelefoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTelefoneKeyTyped
        String caracteres = "0987654321-";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldTelefoneKeyTyped

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
            java.util.logging.Logger.getLogger(CadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

    private void salvarCliente() {
        
                if (!validarEmailField()) {
            return;
        }
                
        String codigo = jTextFieldCodigo.getText();
        int codigoint = Integer.parseInt(codigo);
        String nome = jTextFieldNome.getText();
        String CPF = jFormattedTextFieldCPF.getText();
        String telefone = jTextFieldTelefone.getText();
        String email = jTextFieldEmail.getText();
        String endereco = jTextFieldEndereco.getText();
        String numero = jTextFieldNumero.getText();
        String CEP = jFormattedTextFieldCEP.getText();
        String bairro = jTextFieldBairro.getText();
        String dataNascimento = jFormattedTextFielddataNascimento.getText();

        Date dataNascimentoDate = null;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); // Defina o formato da sua data de nascimento

        try {
            dataNascimentoDate = df.parse(dataNascimento);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de data inválido. Use o formato dd/MM/yyyy.");
            return;
        }

        int codigoCidade = cidadeService.getCodigoCidade(jComboBoxCidade.getSelectedItem().toString());

        if (rowIndex == -1) { // Se rowIndex for -1, é um novo cliente.

            Cliente cliente = new Cliente(nome, CPF, telefone, email, endereco, numero, CEP, bairro, codigoCidade, dataNascimentoDate);
            clienteService.salvarCliente(cliente);
        } else { // Caso contrário, é uma edição de cliente existente

            Cliente clienteEditado = new Cliente(codigoint, nome, CPF, telefone, email, endereco, numero, CEP, bairro, codigoCidade, dataNascimentoDate);
            clienteEditado.setCodigo((int) jTableDadosCliente.getValueAt(rowIndex, 0)); // Define o Código da pessoa
            clienteService.atualizarCliente(clienteEditado);
            rowIndex = -1; // Reseta o índice da linha após a edição
        }

        JOptionPane.showMessageDialog(null,
                "Dados inseridos com sucesso!", "Sucesso", JOptionPane.OK_OPTION);
        limparCampos();
        listarClientes();
    }

    private void listarClientes() {
        tableModel.setRowCount(0);

        clienteService.listarClientes().forEach(cliente -> {
            tableModel.addRow(new Object[]{cliente.getCodigo(), cliente.getNome(), cliente.getCPF(), cliente.getEmail(), cliente.getTelefone(), 
                cliente.getEndereco(), cliente.getNumero(), cliente.getCEP(), cliente.getBairro(), cliente.getCidade(), cliente.getDataNascimento()});
        });
    }

    private void novoCliente() {
        limparCampos();
        atualizarCodigoCliente();
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
        jFormattedTextFielddataNascimento.setText("");
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
                            CarregarClienteSelecionado();
                            break;
                        }
                    case "Delete":
                        int excluir = JOptionPane.showConfirmDialog(editorComponent, "Tem certeza?");
                        if (excluir == 0) {
                            int selectedRow = jTableDadosCliente.getSelectedRow();
                            if (selectedRow != -1) {
                                int localCodigo = (int) jTableDadosCliente.getValueAt(selectedRow, 0);
                                clienteService.excluirCliente(localCodigo);
                                listarClientes();
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

            localizarCliente(criterio, pesquisa);
        }
    }

    private void localizarCliente(String criterio, String valor) {
        Cliente cliente = null;

        try {
            switch (criterio) {
                case "Código":
                    int codigo = Integer.parseInt(valor);
                    cliente = clienteService.localizarClientePorCodigo(codigo);
                    CarregarClienteSelecionado();
                    break;
                case "Nome":
                    cliente = clienteService.localizarClientePorNome(valor);
                    CarregarClienteSelecionado();
                    break;
                case "CPF":
                    cliente = clienteService.localizarClientePorCPF(valor);
                    CarregarClienteSelecionado();
                    break;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O valor do código deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        if (cliente != null) {
            // Exibir os detalhes do funcionário localizado
            JOptionPane.showMessageDialog(this, "Funcionário localizado:\n" + cliente.getNome(), "Funcionário Encontrado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Funcionário não encontrado.", "Funcionário Não Encontrado", JOptionPane.WARNING_MESSAGE);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BackGround;
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
    private javax.swing.JFormattedTextField jFormattedTextFielddataNascimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelBairro;
    private javax.swing.JLabel jLabelCEP;
    private javax.swing.JLabel jLabelCPF;
    private javax.swing.JLabel jLabelCidade;
    private javax.swing.JLabel jLabelCodigo;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelEndereco;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelNumero;
    private javax.swing.JLabel jLabelTelefone;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JPanel jPanelFuncionario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDadosCliente;
    private javax.swing.JTextField jTextFieldBairro;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldEndereco;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldNumero;
    private javax.swing.JTextField jTextFieldTelefone;
    // End of variables declaration//GEN-END:variables
}
