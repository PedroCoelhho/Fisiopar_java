package view.telas;

import control.PacienteC;
import java.awt.Color;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.InternalFrameEvent;
import model.Paciente;
import util.Yagami;
import view.popups.FindEndereco;

/**
 * Tela de cadastro de paciente
 * @author Juan Galvão
 */
public class CadPaciente extends javax.swing.JInternalFrame {
    // Variável para o contador
    protected Timer timer = new Timer();
    
    // Array para armazenar o Text Fields
    protected JTextField[] textFields;
    
    // Instância do popup da janela de endereços
    protected FindEndereco fe = new FindEndereco(null, true);
    
    //
    boolean cpfReturn;
    
    /**
     * Cria o InternalFrame CadPaciente
     */
    public CadPaciente() {
        initComponents();
        setLocation(100, 100);
                
        // Requisita focos no txtNome
        txtNome.requestFocus();
        
        // Chama método para preencher array dos Text Fields
        loadTextFields();
        
        // Loop que atualzia a cada 50 milisegundos
        timer.schedule( new TimerTask() {
            @Override
            public void run() {
               // Método para verificar preenchimento dos Text Fields
               verify();
               
               // Garantir que o texto do campo endereço esteja correto
               txtEndereco.setText(String.valueOf(fe.idEndereco));
            }
         }, 0, 50);
    }
    
    /** Método para verificar preenchimento dos Text Fields */
    private void verify() {
        // N° de campos preenchidos
        int completes = 0;
        
        // Para cada Text Field que não esteja vazio, contar +1 complete
        for(JTextField tf : textFields) {
            if(!"".equals(tf.getText())) {
                completes++;
            }
        }
        
        // Se todos os campos estiverem preenchidos, liberar botão
        if(completes == textFields.length) {
            btnCadastrar.setEnabled(true);
        } else {
            btnCadastrar.setEnabled(false);
        }  
    }
    

    /** Método para preencher array com os Text Fields */
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCadastro = new javax.swing.JPanel();
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
        txtNomeMae = new javax.swing.JTextField();
        lblNomeMae = new javax.swing.JLabel();
        lblNomeResp = new javax.swing.JLabel();
        txtNomeResp = new javax.swing.JTextField();
        txtTel1 = new javax.swing.JFormattedTextField();
        lblTel1 = new javax.swing.JLabel();
        txtTel2 = new javax.swing.JFormattedTextField();
        lblTel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        lblEndereco = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        lblConvenio = new javax.swing.JLabel();
        txtConvenio = new javax.swing.JTextField();
        lblCns = new javax.swing.JLabel();
        txtValidCart = new javax.swing.JFormattedTextField();
        lblValidCart = new javax.swing.JLabel();
        btnCadastrar = new javax.swing.JButton();
        txtCns = new javax.swing.JFormattedTextField();
        lblTitulo = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuOperacoes = new javax.swing.JMenu();
        menuSair = new javax.swing.JMenuItem();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("+Paciente");
        setToolTipText("Tela para cadastro de paciente");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconTestePurple.png"))); // NOI18N
        setName("PAC001"); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        panelCadastro.setBackground(new java.awt.Color(237, 241, 244));
        panelCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(0));

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
        txtEndereco.setNextFocusableComponent(txtConvenio);
        txtEndereco.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEnderecoFocusGained(evt);
            }
        });
        txtEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnderecoActionPerformed(evt);
            }
        });

        lblConvenio.setText("Convênio:");

        txtConvenio.setText("SUS");
        txtConvenio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtConvenioFocusGained(evt);
            }
        });

        lblCns.setText("CNS:");

        try {
            txtValidCart.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtValidCart.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtValidCartFocusLost(evt);
            }
        });

        lblValidCart.setText("Validade Cartão:");

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        try {
            txtCns.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.####.####.####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCns.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout panelCadastroLayout = new javax.swing.GroupLayout(panelCadastro);
        panelCadastro.setLayout(panelCadastroLayout);
        panelCadastroLayout.setHorizontalGroup(
            panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCadastroLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblConvenio)
                    .addComponent(lblNomeMae)
                    .addComponent(lblTel2)
                    .addComponent(lblRg)
                    .addComponent(txtRg)
                    .addComponent(lblDatanas)
                    .addComponent(txtDatanas)
                    .addComponent(txtTel2, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(txtNomeMae)
                    .addComponent(txtConvenio, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCns, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(lblCns)
                    .addComponent(lblNomeResp)
                    .addComponent(lblEstCivil)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail)
                    .addComponent(txtNomeResp)
                    .addComponent(cmbEstCivil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbGenero, 0, 151, Short.MAX_VALUE)
                    .addComponent(lblGenero)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
        );
        panelCadastroLayout.setVerticalGroup(
            panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCadastroLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(lblDatanas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDatanas, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCadastroLayout.createSequentialGroup()
                        .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEstCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbEstCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelCadastroLayout.createSequentialGroup()
                        .addComponent(lblRg, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(lblEtnia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomeResp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbEtnia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeResp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(lblEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCns, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCns, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(lblValidCart, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValidCart, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTitulo.setText("Cadastro de Paciente");

        menuOperacoes.setText("Operações");

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
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addComponent(panelCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(panelCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        try {
            // Instância da classe Paciente
            Paciente paciente = new Paciente();
            
            // Preenchimento de atributos
            paciente.setNome(txtNome.getText());
            paciente.setData_nasc(Yagami.stringToDate(txtDatanas.getText()));
            paciente.setSexo(cmbGenero.getSelectedItem().toString());
            paciente.setCpf(txtCpf.getText().replaceAll("[^a-zA-Z0-9]", ""));
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
            paciente.setObservacoes("TEST PHASE");
            
            // Caso consiga cadastrar, limpa todos os campos e volta o foco para
            // o primeiro Text Field
            if(PacienteC.CONTROL.create(paciente)) {
                for(JTextField tf : textFields) {
                    if(!"".equals(tf.getText())) {
                        tf.setText("");
                    }
                }
                txtNome.requestFocus();
            }          
        } catch (ParseException ex) {
            // Mostra mensagem de erro caso caia na Exception
            Yagami.mensagemErro(ex);
        }
        
    }//GEN-LAST:event_btnCadastrarActionPerformed
    
    /** Método chamado ao entrar no txtEndereco */
    private void txtEnderecoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEnderecoFocusGained
        // Requisita o foco no campo do Convenio
        txtConvenio.requestFocus();
        
        // Mostra popup de endereços
        fe.setVisible(true);
        
        // Pega a ID do endereço selecionado no popup
        txtEndereco.setText(String.valueOf(fe.idEndereco));
        
        // Requisita o foco no campo do Convenio novamente (Garantia)
        txtConvenio.requestFocus();
    }//GEN-LAST:event_txtEnderecoFocusGained

    private void txtEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnderecoActionPerformed

    private void txtConvenioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtConvenioFocusGained
        // TODO add your handling code here:
        System.out.println("LUL");
    }//GEN-LAST:event_txtConvenioFocusGained

    /** Método chamado ao tentar fechar a janela */
    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // Dialogo de confirmação
        int reply = JOptionPane.showConfirmDialog(
            null,
            "Tem certeza que deseja sair do cadastro de paciente?",
            "Cancelar Cadastro",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        // Se confirmar o dialogo, fecha
        if (reply == JOptionPane.YES_OPTION) {
            this.dispose();
        } else {
            this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_formInternalFrameClosing

    private void menuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSairActionPerformed
        // Ao clicar em sair chama o método que acontece quando você tenta
        // fechar a janela
        InternalFrameEvent evt2 = null;
        formInternalFrameClosing(evt2);
    }//GEN-LAST:event_menuSairActionPerformed

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

    private void txtValidCartFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValidCartFocusLost
        // TODO add your handling code here:
        String Valid = txtValidCart.getText();
        
        if (Yagami.data(Valid) == true) {
            txtValidCart.setBackground(Color.WHITE);

        } else {
            txtValidCart.setBackground(Color.red);
        }
    }//GEN-LAST:event_txtValidCartFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JComboBox<String> cmbEstCivil;
    private javax.swing.JComboBox<String> cmbEtnia;
    private javax.swing.JComboBox<String> cmbGenero;
    private javax.swing.JLabel lblCns;
    private javax.swing.JLabel lblConvenio;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblDatanas;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblEstCivil;
    private javax.swing.JLabel lblEtnia;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNomeMae;
    private javax.swing.JLabel lblNomeResp;
    private javax.swing.JLabel lblRg;
    private javax.swing.JLabel lblTel1;
    private javax.swing.JLabel lblTel2;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblValidCart;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuOperacoes;
    private javax.swing.JMenuItem menuSair;
    private javax.swing.JPanel panelCadastro;
    private javax.swing.JFormattedTextField txtCns;
    private javax.swing.JTextField txtConvenio;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JFormattedTextField txtDatanas;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNomeMae;
    private javax.swing.JTextField txtNomeResp;
    private javax.swing.JFormattedTextField txtRg;
    private javax.swing.JFormattedTextField txtTel1;
    private javax.swing.JFormattedTextField txtTel2;
    private javax.swing.JFormattedTextField txtValidCart;
    // End of variables declaration//GEN-END:variables
}
