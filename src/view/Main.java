package view;

import engine.DComponent;
import engine.DataSource;
import java.awt.Color;
import java.awt.Container;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import util.Yagami;
import view.popups.FindTela;
import view.telas.Menu;

/**
 * Tela principal - Container que exibirá todas as janelas da view.telas e
 * simulará a "Área de Trabalho" do Sistema
 *
 * @author Juan Galvão
 */
public class Main extends javax.swing.JFrame {
    public static final Main FORM = new Main();
    /**
     * Cria nova JFrame Main
     */
    public Main() {
        initComponents();
        setLocationRelativeTo(null);
        desktop.setBackground(new Color(0,90,108));
        // Teste
        /*
        Dimension d = Main.imageDesktop(img, this.Desktop, 0, 0);
        int width = (int) d.getWidth();
        int height = (int) d.getHeight();
        */
        
        // Faz a janela abrir maximizada
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        // Chama método que adiciona os componentes (Ícones) na tela
        adicionarComponentes();
        // Pega o nome do usuário logado e exibe (Testes)
        lblWelcome.setText("Bem-vindo " + Yagami.usuario.getNome());

    }

    
    /**
     * Método para verificar o nivel de acesso do usuário cadastrado;
     * @author Pedro Zampiroli
     */
    private void adicionarComponentes() {
        switch (Yagami.usuario.getNivelAcesso()) {
            //Admin
            case 1:
                // Pega cada atalho da DataSource do array admin e o adiciona no Container Desktop
                for (DComponent d : DataSource.DS.admin) {
                    desktop.add(d);
                }
                break;
            //Fisioterapeuta
            case 2:
                // Pega cada atalho da DataSource do array fesioterapeuta e o adiciona no Container Desktop
                for (DComponent d : DataSource.DS.fisioterapeuta) {
                    desktop.add(d);
                }
                break;
            //Funcionario
            case 3:
                // Pega cada atalho da DataSource do array funcionario e o adiciona no Container Desktop
                for (DComponent d : DataSource.DS.funcionario) {
                    desktop.add(d);
                }
                break;
            //Aluno
            case 4:
                // Pega cada atalho da DataSource do array aluno e o adiciona no Container Desktop
                for (DComponent d : DataSource.DS.aluno) {
                    desktop.add(d);
                }
                break;
            default:
                // Pega cada atalho da DataSource do array fesioterapeuta e o adiciona no Container Desktop
                for (DComponent d : DataSource.DS.fisioterapeuta) {
                    desktop.add(d);
                }
                break;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        panelAgenda = new javax.swing.JPanel();
        lblWelcome = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panelBar = new javax.swing.JPanel();
        btnMenu = new javax.swing.JButton();
        lblSair = new javax.swing.JLabel();
        lblSair1 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuOp = new javax.swing.JMenu();
        menuAbrirTela = new javax.swing.JMenuItem();
        menuMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Yagami - Principal");
        setPreferredSize(new java.awt.Dimension(906, 490));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        desktop.setName("Desktopao"); // NOI18N
        desktop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                desktopMouseClicked(evt);
            }
        });

        lblWelcome.setText("Bem-Vindo");
        lblWelcome.setToolTipText("Teste");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Data", "Exame", "Paciente", "Médico"
            }
        ));
        scrollPane.setViewportView(jTable1);

        javax.swing.GroupLayout panelAgendaLayout = new javax.swing.GroupLayout(panelAgenda);
        panelAgenda.setLayout(panelAgendaLayout);
        panelAgendaLayout.setHorizontalGroup(
            panelAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgendaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblWelcome)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAgendaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelAgendaLayout.setVerticalGroup(
            panelAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAgendaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblWelcome)
                .addGap(18, 18, 18)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelBar.setBackground(new java.awt.Color(230, 230, 230));
        panelBar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 51, 102)));

        btnMenu.setBackground(new java.awt.Color(153, 255, 153));
        btnMenu.setText("I");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        lblSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconLogout.png"))); // NOI18N
        lblSair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblSairMouseReleased(evt);
            }
        });

        lblSair1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconPesquisa.png"))); // NOI18N
        lblSair1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSair1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblSair1MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panelBarLayout = new javax.swing.GroupLayout(panelBar);
        panelBar.setLayout(panelBarLayout);
        panelBarLayout.setHorizontalGroup(
            panelBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSair1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSair)
                .addContainerGap())
        );
        panelBarLayout.setVerticalGroup(
            panelBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSair1)
                    .addComponent(lblSair)
                    .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, desktopLayout.createSequentialGroup()
                .addContainerGap(507, Short.MAX_VALUE)
                .addComponent(panelAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(panelBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(panelBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        desktop.setLayer(panelAgenda, javax.swing.JLayeredPane.DEFAULT_LAYER);
        desktop.setLayer(panelBar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        menuOp.setText("Operações");

        menuAbrirTela.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        menuAbrirTela.setText("Abrir tela");
        menuAbrirTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAbrirTelaActionPerformed(evt);
            }
        });
        menuOp.add(menuAbrirTela);

        menuMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_WINDOWS, java.awt.event.InputEvent.CTRL_MASK));
        menuMenu.setText("Abrir menu");
        menuMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMenuActionPerformed(evt);
            }
        });
        menuOp.add(menuMenu);

        menuBar.add(menuOp);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // Exibir dialogo e confirmar saída do sistema
        int reply = JOptionPane.showConfirmDialog(
                null,
                "Tem certeza que deseja sair do sistema?",
                "Sair do Yagami",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        // Caso confirme, tentar sair
        if (reply == JOptionPane.YES_OPTION) {
            try {
                Yagami.exit(0);
            } catch (SQLException ex) {
                // Mostrar mensagem de erro caso caia na Exception
                Yagami.mensagemErro(ex);
            }
        } else {
            // Impedir que a janela seja fechada
            this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        // TODO add your handling code here:
        Menu pTeste = new Menu();
        /*
        pTeste.setBounds(0, 100, 439, 491);
        //pTeste.setLocation(50, 50);
        pTeste.setVisible(true);
        pTeste.requestFocus();
        desktop.add(pTeste);
        */
        Yagami.exibirTela(pTeste, desktop, false);
        
    }//GEN-LAST:event_btnMenuActionPerformed

    private void desktopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desktopMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_desktopMouseClicked

    private void menuAbrirTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAbrirTelaActionPerformed
        // TODO add your handling code here:
        new FindTela(this, true).setVisible(true);
    }//GEN-LAST:event_menuAbrirTelaActionPerformed

    private void menuMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMenuActionPerformed
        // TODO add your handling code here:
        btnMenuActionPerformed(null);
    }//GEN-LAST:event_menuMenuActionPerformed

    private void lblSairMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSairMouseReleased
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            Yagami.logout(this);
        } catch (SQLException ex) {
            Yagami.mensagemErro(ex);
        }
    }//GEN-LAST:event_lblSairMouseReleased

    private void lblSair1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSair1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lblSair1MouseReleased

    
    /**
     * Método main da classe Main
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Look and Feel */
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Cria e exibe a Janela */
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMenu;
    public static javax.swing.JDesktopPane desktop;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblSair;
    private javax.swing.JLabel lblSair1;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JMenuItem menuAbrirTela;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuMenu;
    private javax.swing.JMenu menuOp;
    private javax.swing.JPanel panelAgenda;
    private javax.swing.JPanel panelBar;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
