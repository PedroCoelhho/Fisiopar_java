/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.telas;

import control.Cid10C;
import java.util.ArrayList;
import java.util.List;
import javax.security.auth.login.Configuration;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author David Lucas Kiihl
 */
public class FiltrarCid10 extends javax.swing.JInternalFrame {

    public FiltrarCid10() {
        initComponents();
        setLocation(100, 100);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbFiltro = new javax.swing.JLabel();
        tfFiltro = new javax.swing.JTextField();
        spGrade = new javax.swing.JScrollPane();
        tbGrade = new javax.swing.JTable();
        btnPesquisar = new javax.swing.JButton();
        lbFiltro1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Pesquisa Cid 10");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconCid10.png"))); // NOI18N
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

        lbFiltro.setText("Filtro:");

        tfFiltro.setColumns(30);
        tfFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfFiltroKeyReleased(evt);
            }
        });

        tbGrade.setAutoCreateRowSorter(true);
        tbGrade.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Resultado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbGrade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbGradeMouseClicked(evt);
            }
        });
        spGrade.setViewportView(tbGrade);

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        lbFiltro1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        lbFiltro1.setForeground(new java.awt.Color(153, 153, 153));
        lbFiltro1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFiltro1.setText("Pesquisa por Código ou Situação");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spGrade)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbFiltro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfFiltro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPesquisar)
                .addGap(225, 225, 225))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(154, Short.MAX_VALUE)
                .addComponent(lbFiltro1, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbFiltro1)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbFiltro)
                    .addComponent(tfFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(spGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFiltroKeyReleased
        TableRowSorter rs = (TableRowSorter) tbGrade.getRowSorter();
        rs.setRowFilter(RowFilter.regexFilter("(?i)" + tfFiltro.getText(), 0, 1));
    }//GEN-LAST:event_tfFiltroKeyReleased

    private void tbGradeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGradeMouseClicked
     
    }//GEN-LAST:event_tbGradeMouseClicked

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
    try {
            // Preencher tabela de acordo com o texto digitado
            if("        ".equals(tfFiltro.getText())) {
                
                Cid10C.CONTROL.listTable(tbGrade, false);
            } else {
                // Armazenar texto digitado
                String nomecid = (tfFiltro.getText());
                
                
                Cid10C.CONTROL.listTable(tbGrade, true, nomecid);
                
                // Se encontrar algum valor...
                if(tbGrade.getRowCount() > 0) {
                    // Selecionar a primeira linha
                    tbGrade.setRowSelectionInterval(0, 0);
                    
                   
                   
                }
            }
        } catch (NumberFormatException e) {
            // Ignorar exceções de NumberFormat
            System.out.println("No excpetions today!");
        }       
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JLabel lbFiltro;
    private javax.swing.JLabel lbFiltro1;
    private javax.swing.JScrollPane spGrade;
    private javax.swing.JTable tbGrade;
    private javax.swing.JTextField tfFiltro;
    // End of variables declaration//GEN-END:variables
}
