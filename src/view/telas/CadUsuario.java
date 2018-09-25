package view.telas;

import control.UsuarioC;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;
import model.Usuario;
import util.Yagami;

/**
 * Tela para cadastro de usuários - W.I.P
 * @author Juan Galvão
 */
public class CadUsuario extends javax.swing.JInternalFrame {

    /**
     * Cria a InternalForm CadUsuario
     */
    public CadUsuario() {
        initComponents();
        setLocation(130, 100);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked", "Convert2Lambda"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupAdmin = new javax.swing.ButtonGroup();
        txtNome = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        txtUsuario = new javax.swing.JTextField();
        btnCadastrar = new javax.swing.JButton();
        lblNome = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        lblAdmin = new javax.swing.JLabel();
        jComboNivelAcesso = new javax.swing.JComboBox();

        setClosable(true);
        setTitle("Cadastro de Usuário");
        setToolTipText("Tela para cadastro de usuário");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconTesteBlue.png"))); // NOI18N
        setName("USU001"); // NOI18N

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        lblNome.setText("Nome:");

        lblUsuario.setText("Usuário:");

        lblSenha.setText("Senha:");

        lblAdmin.setText("Tipo de usuário:");

        jComboNivelAcesso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--------------", "Administrador", "Fisioterapeuta", "Funcionário", "Aluno" }));
        jComboNivelAcesso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboNivelAcessoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAdmin)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboNivelAcesso, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCadastrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblUsuario)
                                    .addComponent(lblSenha)
                                    .addComponent(lblNome)
                                    .addComponent(txtSenha)
                                    .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                    .addComponent(txtNome))))
                        .addGap(80, 80, 80))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblUsuario)
                .addGap(2, 2, 2)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblAdmin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboNivelAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        boolean teste1, teste2; // Duas variáveis para testes lógicos
        Usuario usuario;        // Variável para armazenar usuário
        int  teste3 = jComboNivelAcesso.getSelectedIndex();
        
        // Testar se escolheu tipo de usuário (Teste 3)
        if(teste3 > 0) {
            // Testar se todos os campos estão preenchidos (Teste 1)
            if(!"".equals(txtNome.getText()) && !"".equals(txtSenha.getText()) && 
                !"".equals(txtUsuario.getText())) {
                teste1 = true;

            } else {
                teste1 = false;
                JOptionPane.showMessageDialog(
                        null,
                        "Preencha todos os campos!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }

            // Preenche usuário com método de busca e verificação na tabela
            usuario = UsuarioC.CONTROL.readVerify(txtUsuario.getText());

            // Testa se o usuário existe na tabela
            if (usuario == null) {
                teste2 = true;
            } else {
                teste2 = false;
                JOptionPane.showMessageDialog(
                        null,
                        "Usuário já cadastrado!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }

            // Caso os dois testes forem positivos, cadastrar
            if (teste1 == true && teste2 == true) {
                try {
                    // Instância da classe Usuário e preenchimento dos atributos
                    usuario = new Usuario();
                    usuario.setNome(txtNome.getText());
                    usuario.setUsuario(txtUsuario.getText());
                    usuario.setSenha(Yagami.md5(txtSenha.getText()));
                    usuario.setNivelAcesso(teste3);

                    // Se a criação de usuário der certo, fechar a janela
                    if (UsuarioC.CONTROL.create(usuario)) {
                        this.dispose();
                    }
                } catch (NoSuchAlgorithmException ex) {
                    // Mostra mensagem de erro caso caia na exceção
                    Yagami.mensagemErro(ex);
                }
            }
        } else{
            JOptionPane.showMessageDialog(
                    null,
                    "Selecione o tipo de usuário!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        
        
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void jComboNivelAcessoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboNivelAcessoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboNivelAcessoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.ButtonGroup groupAdmin;
    private javax.swing.JComboBox jComboNivelAcesso;
    private javax.swing.JLabel lblAdmin;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}