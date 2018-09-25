package view.telas;

import control.PacienteC;
import dao.PacienteDao;
import java.awt.Color;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Paciente;
import util.Yagami;
import view.popups.FindEndereco;

/**
 * Tela para vizualizar detalhes do Paciente e efetuar alterações
 * @author Juan Galvão
 */
public class DetailPaciente extends javax.swing.JInternalFrame {
    // Varíaveis para controle interno da view
    
    // Array para armazenar todos os TextFields
    protected JTextField[] textFields; 
    
    // Array para armazenar todos os ComboBoxes 
    protected JComboBox[] comboBoxes;
    
    // Instância da classe Dao do Paciente
    protected PacienteDao pc = new PacienteDao(); 
    
    // Boolean para testar se está no modo de edição 
    protected boolean editMode = false; 
    
    // Popup da janela de buscar Endereços
    protected FindEndereco fe = new FindEndereco(null, true);
    
    //
    boolean cpfReturn;
    
    /**
     * Cria a InternalForm DetailPaciente
     * @throws java.text.ParseException
     */
    public DetailPaciente() throws ParseException {
        initComponents();
        
        // Preenche todos os campos de acordo com a ID passada para o Yagami
        preencherCampos(Yagami.YG.getPublicId());
        
        // Joga toodos os componentes para as arrays de armazenamento
        loadTextFields();
        loadComboBoxes();
        
        // Disabilita a edição de todos os campos
        disableAllTextFields();
        disableAllComboBoxes();
        
    }
    
    /** 
     * Método para preenchimento dos campos 
     * @param int id - id para buscar resultado no banco e preencher campos
     * @throws ParseException
    */
    private void preencherCampos(int id) throws ParseException {
        // Cria lista de pacientes de acordo com a ID
        List<Paciente> listP = pc.listar(true, id);
        
        // Pega o único valor da lista e joga na varíavel pcn (Paciente)
        Paciente pcn = listP.get(0);
        
        // Preenchimento dos Text Fields
        txtNome.setText(pcn.getNome());
        txtDatanas.setText(Yagami.convertDate(pcn.getData_nasc(), "dd/MM/yyyy"));
        txtCpf.setText(pcn.getCpf().replaceAll("[^a-zA-Z0-9]", ""));
        txtRg.setText(pcn.getRg());
        txtNomeMae.setText(pcn.getNome_mae());
        txtNomeResp.setText(pcn.getNome_resp());
        txtTel1.setText(pcn.getTel_prim());
        txtTel2.setText(pcn.getTel_sec());
        txtEmail.setText(pcn.getEmail());
        txtEndereco.setText(String.valueOf(pcn.getFk_endereco()));
        txtConvenio.setText(pcn.getConvenio());
        txtCns.setText(pcn.getCns());
        txtValidCart.setText(Yagami.convertDate(pcn.getValid_cart(), "dd/MM/yyyy"));
        txtObservacao.setText(pcn.getObservacoes());
        
        // Preenchimento dos Combo Boxes
        cmbEstCivil.setSelectedItem(pcn.getEst_civ());
        cmbEtnia.setSelectedItem(pcn.getEtnia());
        cmbGenero.setSelectedItem(pcn.getSexo());
        
        // Passa a ID do paciente atual para a label lblId
        lblId.setText(String.valueOf(pcn.getId()));
    }
    
    /** Método para desabilitar edição de todos os Text Fields da View */
    private void disableAllTextFields() {
        for(JTextField jtf : textFields) {
            jtf.setEditable(false);
        }
    }
    
    /** Método para habilitar edição de todos os Text Fields da View */
    private void enableAllTextFields() {
        for(JTextField jtf : textFields) {
            jtf.setEditable(true);
        }
    }
    
    /** Método para desabilitar todos os Combo Boxes da View */
    private void disableAllComboBoxes() {
        for(JComboBox jcb : comboBoxes) {
            jcb.setEnabled(false);
        }
    }
    
    /** Método para habilitar todos os Combo Boxes da View */
    private void enableAllComboBoxes() {
        for(JComboBox jcb : comboBoxes) {
            jcb.setEnabled(true);
        }
    }
    
    /** Método para carregar Array com os Text Fields da View */
    private void loadTextFields() {
        textFields = new JTextField[]{
            txtCns,
            txtConvenio,
            txtCpf,
            txtDatanas,
            txtEmail,
            txtEndereco,
            txtNome,
            txtNomeMae,
            txtNomeResp,
            txtRg,
            txtTel1,
            txtTel2,
            txtValidCart
        };
    }
    
    /** Método para carregar Array com os Combo Boxes da View */
    private void loadComboBoxes() {
        comboBoxes = new JComboBox[] {
            cmbEstCivil, cmbEtnia, cmbGenero
        };
    }
    
    /**
     * Método para definir se está no modo de edição ou não
     * @param boolean mode - true ou false para ativar ou desativar edição
     * @throws ParseException 
     */
    private void setEditMode(boolean mode) throws ParseException {
        if(mode) {
            // Ativa a edição, habilitando todos os campos para alteração
            editMode = true;
            enableAllComboBoxes();
            enableAllTextFields();
            txtObservacao.setEditable(true);
            btnUpdate.setEnabled(true);
        } else {
            // Desativa a edição, desabilitando todos os campos novamente
            editMode = false;
            preencherCampos(Integer.parseInt(lblId.getText()));
            disableAllComboBoxes();
            disableAllTextFields();
            txtObservacao.setEditable(false);
            btnUpdate.setEnabled(false);
        }
    }
    
    /**
     * Método para criar e passar objeto do Paciente
     * @return Paciente
     * @throws ParseException 
     */
    private Paciente createObjectPaciente() throws ParseException {
        // Instância da classe Paciente
        Paciente paciente = new Paciente();

        // Preenchimento total do objeto
        paciente.setId(Integer.parseInt(lblId.getText()));
        paciente.setNome(txtNome.getText());
        paciente.setData_nasc(Yagami.stringToDate(txtDatanas.getText()));
        paciente.setSexo(cmbGenero.getSelectedItem().toString());
        paciente.setCpf(txtCpf.getText());
        paciente.setRg(txtRg.getText());
        paciente.setEst_civ(cmbEstCivil.getSelectedItem().toString());
        paciente.setEtnia(cmbEtnia.getSelectedItem().toString());
        paciente.setNome_resp(txtNomeResp.getText());
        paciente.setNome_mae(txtNomeMae.getText());
        paciente.setTel_prim(txtTel1.getText());
        paciente.setTel_sec(txtTel2.getText());
        paciente.setEmail(txtEmail.getText());
        paciente.setFk_endereco(Integer.parseInt(txtEndereco.getText())); //---
        paciente.setConvenio(txtConvenio.getText());
        paciente.setCns(txtCns.getText());
        paciente.setValid_cart(Yagami.stringToDate(txtValidCart.getText()));
        paciente.setData_hr(LocalDateTime.now().toString());
        paciente.setObservacoes(txtObservacao.getText());
        
        // Retorna o objeto criado e preenchido
        return paciente;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDetail = new javax.swing.JPanel();
        txtNome = new javax.swing.JTextField();
        txtDatanas = new javax.swing.JFormattedTextField();
        lblDatanas = new javax.swing.JLabel();
        lblCpf = new javax.swing.JLabel();
        lblGenero = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        lblNome = new javax.swing.JLabel();
        txtRg = new javax.swing.JFormattedTextField();
        lblRg = new javax.swing.JLabel();
        cmbEstCivil = new javax.swing.JComboBox<String>();
        lblEstCivil = new javax.swing.JLabel();
        cmbGenero = new javax.swing.JComboBox<String>();
        lblEtnia = new javax.swing.JLabel();
        cmbEtnia = new javax.swing.JComboBox<String>();
        lblNomeMae = new javax.swing.JLabel();
        lblNomeResp = new javax.swing.JLabel();
        txtTel1 = new javax.swing.JFormattedTextField();
        lblTel1 = new javax.swing.JLabel();
        txtTel2 = new javax.swing.JFormattedTextField();
        lblTel2 = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblEndereco = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        lblConvenio = new javax.swing.JLabel();
        txtConvenio = new javax.swing.JTextField();
        lblCns = new javax.swing.JLabel();
        txtValidCart = new javax.swing.JFormattedTextField();
        lblValidCart = new javax.swing.JLabel();
        txtCns = new javax.swing.JFormattedTextField();
        btnUpdate = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        txtObservacao = new javax.swing.JTextArea();
        lblObservacao = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        lblIdNv = new javax.swing.JLabel();
        txtNomeResp = new javax.swing.JTextField();
        txtNomeMae = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        panelOperacoes = new javax.swing.JPanel();
        lblModificar = new javax.swing.JLabel();
        lblExcluir = new javax.swing.JLabel();
        lblProximo = new javax.swing.JLabel();
        lblAnterior = new javax.swing.JLabel();
        lblTOperacoes = new javax.swing.JLabel();
        lblAltM = new javax.swing.JLabel();
        lblAltE = new javax.swing.JLabel();
        lblPgUp = new javax.swing.JLabel();
        lblPgDw = new javax.swing.JLabel();
        lblSair = new javax.swing.JLabel();
        lblEsc = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuOperacoes = new javax.swing.JMenu();
        menuModificar = new javax.swing.JMenuItem();
        menuExcluir = new javax.swing.JMenuItem();
        menuProximo = new javax.swing.JMenuItem();
        menuAnterior = new javax.swing.JMenuItem();
        menuSair = new javax.swing.JMenuItem();

        setClosable(true);
        setMaximizable(true);
        setTitle("Detalhes de Paciente");
        setName("PAC000"); // NOI18N

        panelDetail.setBackground(new java.awt.Color(237, 241, 244));
        panelDetail.setBorder(javax.swing.BorderFactory.createBevelBorder(0));

        try {
            txtDatanas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDatanas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDatanasFocusLost(evt);
            }
        });

        lblDatanas.setText("Nascimento:");

        lblCpf.setText("CPF:");

        lblGenero.setText("Gênero:");

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCpfFocusLost(evt);
            }
        });

        lblNome.setText("Nome:");

        try {
            txtRg.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblRg.setText("RG:");

        cmbEstCivil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Solteiro(a)", "Casado(a)", "Viuvo(a)", "Divorciado(a)" }));

        lblEstCivil.setText("Estado Civil:");

        cmbGenero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Feminino" }));

        lblEtnia.setText("Etnia:");

        cmbEtnia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Branca", "Preta", "Parda", "Amarela", "Indigena" }));

        lblNomeMae.setText("Nome Mãe:");

        lblNomeResp.setText("Nome Responsável:");

        try {
            txtTel1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblTel1.setText("Telefone 1:");

        try {
            txtTel2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblTel2.setText("Telefone 2:");

        lblEmail.setText("E-mail:");

        lblEndereco.setText("Endereço:");

        txtEndereco.setBackground(new java.awt.Color(204, 204, 255));
        txtEndereco.setName(""); // NOI18N
        txtEndereco.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEnderecoFocusGained(evt);
            }
        });

        lblConvenio.setText("Convênio:");

        lblCns.setText("CNS:");

        try {
            txtValidCart.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblValidCart.setText("Validade Cartão:");

        try {
            txtCns.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.####.####.####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCns.setVerifyInputWhenFocusTarget(false);

        btnUpdate.setText("Gravar Alterações");
        btnUpdate.setEnabled(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        txtObservacao.setEditable(false);
        txtObservacao.setColumns(20);
        txtObservacao.setRows(5);
        scrollPane.setViewportView(txtObservacao);

        lblObservacao.setText("Observações:");

        lblId.setBackground(new java.awt.Color(255, 255, 255));
        lblId.setText("10");

        lblIdNv.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblIdNv.setText("ID:");

        javax.swing.GroupLayout panelDetailLayout = new javax.swing.GroupLayout(panelDetail);
        panelDetail.setLayout(panelDetailLayout);
        panelDetailLayout.setHorizontalGroup(
            panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetailLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblValidCart)
                    .addComponent(lblNome)
                    .addComponent(lblCpf)
                    .addComponent(lblEtnia)
                    .addComponent(lblEndereco)
                    .addComponent(lblTel1)
                    .addComponent(txtCpf)
                    .addComponent(cmbEtnia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTel1)
                    .addComponent(txtEndereco)
                    .addComponent(txtValidCart)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDetailLayout.createSequentialGroup()
                        .addComponent(lblObservacao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblIdNv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDetailLayout.createSequentialGroup()
                        .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblConvenio)
                                .addComponent(lblNomeMae)
                                .addComponent(lblTel2)
                                .addComponent(lblRg)
                                .addComponent(txtRg)
                                .addComponent(lblDatanas)
                                .addComponent(txtDatanas, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(txtTel2, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(txtConvenio, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                            .addComponent(txtNomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCns, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCns)
                            .addComponent(lblNomeResp)
                            .addComponent(lblEstCivil)
                            .addComponent(lblEmail)
                            .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cmbEstCivil, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbGenero, javax.swing.GroupLayout.Alignment.LEADING, 0, 200, Short.MAX_VALUE))
                            .addComponent(lblGenero)
                            .addComponent(txtNomeResp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(scrollPane))
                .addGap(40, 40, 40))
        );
        panelDetailLayout.setVerticalGroup(
            panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetailLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(lblDatanas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDatanas, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDetailLayout.createSequentialGroup()
                        .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEstCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbEstCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelDetailLayout.createSequentialGroup()
                        .addComponent(lblRg, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(lblEtnia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomeResp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbEtnia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeResp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(lblEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCns, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCns, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValidCart, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObservacao)
                    .addComponent(lblId)
                    .addComponent(lblIdNv))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelDetailLayout.createSequentialGroup()
                        .addComponent(txtValidCart, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        panelOperacoes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblModificar.setText("Modificar");

        lblExcluir.setText("Excluir");

        lblProximo.setText("Próximo");

        lblAnterior.setText("Anterior");

        lblTOperacoes.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTOperacoes.setText("Operações");

        lblAltM.setText("Alt +M");

        lblAltE.setText("Alt + E");

        lblPgUp.setText("Pg Up");

        lblPgDw.setText("Pg Down");

        lblSair.setText("Sair");

        lblEsc.setText("ESC");

        javax.swing.GroupLayout panelOperacoesLayout = new javax.swing.GroupLayout(panelOperacoes);
        panelOperacoes.setLayout(panelOperacoesLayout);
        panelOperacoesLayout.setHorizontalGroup(
            panelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOperacoesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelOperacoesLayout.createSequentialGroup()
                        .addComponent(lblSair)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblEsc))
                    .addGroup(panelOperacoesLayout.createSequentialGroup()
                        .addComponent(lblTOperacoes)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelOperacoesLayout.createSequentialGroup()
                        .addComponent(lblModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAltM))
                    .addGroup(panelOperacoesLayout.createSequentialGroup()
                        .addComponent(lblExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(lblAltE))
                    .addGroup(panelOperacoesLayout.createSequentialGroup()
                        .addComponent(lblProximo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPgUp))
                    .addGroup(panelOperacoesLayout.createSequentialGroup()
                        .addComponent(lblAnterior)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPgDw)))
                .addGap(20, 20, 20))
        );
        panelOperacoesLayout.setVerticalGroup(
            panelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOperacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTOperacoes)
                .addGap(18, 18, 18)
                .addGroup(panelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModificar)
                    .addComponent(lblAltM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblExcluir)
                    .addComponent(lblAltE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProximo)
                    .addComponent(lblPgUp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAnterior)
                    .addComponent(lblPgDw))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelOperacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSair)
                    .addComponent(lblEsc))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuOperacoes.setText("Operações");

        menuModificar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK));
        menuModificar.setText("Modificar");
        menuModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuModificarActionPerformed(evt);
            }
        });
        menuOperacoes.add(menuModificar);

        menuExcluir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        menuExcluir.setText("Excluir");
        menuExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExcluirActionPerformed(evt);
            }
        });
        menuOperacoes.add(menuExcluir);

        menuProximo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_PAGE_UP, 0));
        menuProximo.setText("Próximo");
        menuProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProximoActionPerformed(evt);
            }
        });
        menuOperacoes.add(menuProximo);

        menuAnterior.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_PAGE_DOWN, 0));
        menuAnterior.setText("Anterior");
        menuAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAnteriorActionPerformed(evt);
            }
        });
        menuOperacoes.add(menuAnterior);

        menuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        menuSair.setText("Sair");
        menuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSairActionPerformed(evt);
            }
        });
        menuOperacoes.add(menuSair);

        menuBar.add(menuOperacoes);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(panelDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelOperacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelOperacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** Método chamado ao apertar ALT + M ou clicar no item Modificar */
    private void menuModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuModificarActionPerformed
        try {
            // Testa se está no edit mode, se não estiver, ativar
            if(editMode) {
                JOptionPane.showMessageDialog(null, "Você já está modificando o paciente.");
            } else {
                setEditMode(true);
            }
            
        } catch (ParseException ex) {
            // Mostra mensagem de erro caso caia na Exception
            Yagami.mensagemErro(ex);
        }
        
    }//GEN-LAST:event_menuModificarActionPerformed

    /** Método chamado ao apertar ALT + E ou clicar no item Excluir */
    private void menuExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExcluirActionPerformed
        // Cria diálogo de confirmação da ação
        int reply = JOptionPane.showConfirmDialog(
                null,
                "Tem certeza que deseja excluir este paciente?\n"
                + "A operação não pode ser desfeita!",
                "Excluir Paciente",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE
        );
        
        // Testa a confirmação pelo diálogo
        if (reply == JOptionPane.YES_OPTION) {
            // Pega a id do paciente que será excluído
            int idExcluir = Integer.parseInt(lblId.getText());
            
            // Lê os paciente "ao lado" do paciente que será excluído
            Paciente nPaciente = PacienteC.CONTROL.loadNextPaciente(idExcluir);
            Paciente pPaciente = PacienteC.CONTROL.loadPreviosPaciente(idExcluir);
            
            // Testa se o paciente excluído não é o último da tabela
            if(nPaciente != null) {
                try {
                    // Se não for o último, ela preenche a view com os dados
                    // do próximo paciente
                    preencherCampos(nPaciente.getId());
                    
                } catch (ParseException ex) {
                    // Mostra mensagem de erro caso caia na Exception
                    Yagami.mensagemErro(ex);
                }
            } else {
                // Se for o último, ele testa se existe algum paciente "atrás"
                // do que será excluído
                if(pPaciente != null) {
                    try {
                        // Se tiver, ele preenche os dados com este
                        preencherCampos(pPaciente.getId());
                    } catch (ParseException ex) {
                        // Mostra mensagem de erro caso caia na Exception
                        Yagami.mensagemErro(ex);
                    }
                } else {
                    // Se o paciente for o último paciente da tabela, a View
                    // é fechada após a exclusão do paciente
                    PacienteC.CONTROL.delete(idExcluir);
                    this.dispose();
                    
                    // Retorno para garantir fim de execução do método
                    return;
                }
            }
            // Excluir paciente
            PacienteC.CONTROL.delete(idExcluir);
        }
    }//GEN-LAST:event_menuExcluirActionPerformed

    /** Método chamado ao apertar Page Up ou clicar no item Próximo */
    private void menuProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProximoActionPerformed
        // Testa se está no modo de edição
        if(!editMode) {
            try {
                // Chama o método para carregar o próximo paciente da tabela
                Paciente nPaciente = PacienteC.CONTROL.loadNextPaciente(Integer.parseInt(lblId.getText()));
                
                // Testa se o próximo paciente existe
                if(nPaciente != null) {
                    // Chama método para preencher os campos
                    preencherCampos(nPaciente.getId());
                } else {
                    // Exibe mensagem falando que não existe próximo paciente
                    JOptionPane.showMessageDialog(null, "Não existem mais resultados");
                }
            } catch (ParseException ex) {
                // Mostra mensagem de erro caso caia na Exception
                Yagami.mensagemErro(ex);
            }
        }
    }//GEN-LAST:event_menuProximoActionPerformed

    /** Método chamado ao apertar Page Down ou clicar no item Anterior */
    private void menuAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAnteriorActionPerformed
        if(!editMode) {
            try {
                // Chama o método para carregar o paciente anterior da tabela
                Paciente pPaciente = PacienteC.CONTROL.loadPreviosPaciente(Integer.parseInt(lblId.getText()));
                
                // Testa se existe um paciente anterior ao atual
                if(pPaciente != null) {
                    // Preenche os campos com o paciente anterior
                    preencherCampos(pPaciente.getId());
                } else {
                    // Exibe mensagem caso não exista um paciente anterior
                    JOptionPane.showMessageDialog(null, "Não existem mais resultados");
                }
            } catch (ParseException ex) {
                // Mostra mensagem de erro caso caia na Exception
                Yagami.mensagemErro(ex);
            }
        }
    }//GEN-LAST:event_menuAnteriorActionPerformed

    /** Método chamado ao clicar no menu sair ou tentar fechar a janela */
    private void menuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSairActionPerformed
        if(editMode) {
            try {
                // Se estiver no modo de edição, confirmar saída
                int reply = JOptionPane.showConfirmDialog(
                        null, 
                        "Tem certeza que deseja cancelar a edição do paciente?", 
                        "Cancelar Modificação", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                
                // Desativar modo de edição caso confirme
                if (reply == JOptionPane.YES_OPTION) {
                    setEditMode(false);
                }
                
            } catch (ParseException ex) {
                // Mostra mensagem de erro caso caia na Exception
                Yagami.mensagemErro(ex);
            }
        } else {
            // Fechar janela
            this.dispose();
        }
    }//GEN-LAST:event_menuSairActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            // Cria objeto do paciente a partir do método createObjectPaciente()
            Paciente uPaciente = createObjectPaciente();
            
            // Método para alterar paciente a partir do objeto criado
            PacienteC.CONTROL.update(uPaciente);
            
            // Desabilita o modo de edição
            setEditMode(false);
        } catch (ParseException ex) {
            // Mostra mensagem de erro caso caia na Exception
            Yagami.mensagemErro(ex);
        }
        
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtEnderecoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEnderecoFocusGained
        if(editMode) {
            // Se estiver no modo de edição...
            
            // Foca no campo txtConvenio
            txtConvenio.requestFocus();
            
            // Mostra o Popup de Endereços
            fe.setVisible(true);
            
            // Pega a id escolhida no Popup e joga para o campo txtEndereco
            txtEndereco.setText(String.valueOf(fe.idEndereco));
            
            // Requisita foco no campo txtConvenio
            txtConvenio.requestFocus();
        }
    }//GEN-LAST:event_txtEnderecoFocusGained

    private void txtCpfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCpfFocusLost
        // TODO add your handling code here:
        String Cpf = txtCpf.getText().replaceAll("[^a-zA-Z0-9]", "");
        //Verificando se o campo esta preenchido        
        if (Cpf.length() > 0) {
            // usando o metodo isCPF() 
            if (Yagami.isCPF(Cpf) == true) {
                txtCpf.setBackground(Color.WHITE);
                cpfReturn = false;
            } else {
                cpfReturn = true;
                txtCpf.setBackground(Color.red);
            }
            //Verificando se ja existe Cpf cadastrado            
            try {
                if (PacienteC.CONTROL.search(Cpf) == true) {
                    txtCpf.setText(null);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CadPaciente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtCpfFocusLost

    private void txtDatanasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDatanasFocusLost
        // TODO add your handling code here:
        String DataNas = txtDatanas.getText();
        
        if (Yagami.dataMenorQueHoje(DataNas) == true) {
            txtDatanas.setBackground(Color.WHITE);

        } else {
            JOptionPane.showMessageDialog(null, "Data de Nascimento Inválida", "Mensage Error", JOptionPane.ERROR_MESSAGE);
            txtDatanas.setBackground(Color.red);

        }
    }//GEN-LAST:event_txtDatanasFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbEstCivil;
    private javax.swing.JComboBox<String> cmbEtnia;
    private javax.swing.JComboBox<String> cmbGenero;
    private javax.swing.JLabel lblAltE;
    private javax.swing.JLabel lblAltM;
    private javax.swing.JLabel lblAnterior;
    private javax.swing.JLabel lblCns;
    private javax.swing.JLabel lblConvenio;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblDatanas;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblEsc;
    private javax.swing.JLabel lblEstCivil;
    private javax.swing.JLabel lblEtnia;
    private javax.swing.JLabel lblExcluir;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblIdNv;
    private javax.swing.JLabel lblModificar;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNomeMae;
    private javax.swing.JLabel lblNomeResp;
    private javax.swing.JLabel lblObservacao;
    private javax.swing.JLabel lblPgDw;
    private javax.swing.JLabel lblPgUp;
    private javax.swing.JLabel lblProximo;
    private javax.swing.JLabel lblRg;
    private javax.swing.JLabel lblSair;
    private javax.swing.JLabel lblTOperacoes;
    private javax.swing.JLabel lblTel1;
    private javax.swing.JLabel lblTel2;
    private javax.swing.JLabel lblValidCart;
    private javax.swing.JMenuItem menuAnterior;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuExcluir;
    private javax.swing.JMenuItem menuModificar;
    private javax.swing.JMenu menuOperacoes;
    private javax.swing.JMenuItem menuProximo;
    private javax.swing.JMenuItem menuSair;
    private javax.swing.JPanel panelDetail;
    private javax.swing.JPanel panelOperacoes;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JFormattedTextField txtCns;
    private javax.swing.JTextField txtConvenio;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JFormattedTextField txtDatanas;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNomeMae;
    private javax.swing.JTextField txtNomeResp;
    private javax.swing.JTextArea txtObservacao;
    private javax.swing.JFormattedTextField txtRg;
    private javax.swing.JFormattedTextField txtTel1;
    private javax.swing.JFormattedTextField txtTel2;
    private javax.swing.JFormattedTextField txtValidCart;
    // End of variables declaration//GEN-END:variables
}
