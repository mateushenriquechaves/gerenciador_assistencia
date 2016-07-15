/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hol.es.manotec.telas;
import hol.es.manotec.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author mateu
 */
public class TelaOrdem extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaOrdem
     */
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection conexao = null;
    public TelaOrdem() {
        initComponents();
        conexao = ModuloConexao.conector();
        pesquisar();
        panBtn.setVisible(false);
        rdborc.setSelected(true);
        
    }
    
    
    
       public void editar() {
           String sql = "update tbclientes set nomeCliente = ?, equipamento = ?, defeito = ?, servico = ?, tecnico = ?, valor = ?, situacao = ?, tipo = ? where os = ? ";
           String tipo;
           if(rdborc.isSelected()) {
                tipo = "Orçamento";
           }else {
                tipo = "Ordem de serviço";
           }
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomeCliente.getText());
            pst.setString(2, txtEquipamento.getText());
            pst.setString(3, txtDefeito.getText());
            pst.setString(4, txtServico.getText());
            pst.setString(5, txtTecnico.getText());
            pst.setString(6, txtValor.getText());
            pst.setString(7, combSit.getSelectedItem().toString());
            pst.setString(8, tipo);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TelaOrdem.class.getName()).log(Level.SEVERE, null, ex);
        }
       }

       public void pesquisar() {
           String sql = "select idcli as ID,  nomecli as Nome, fonecli as Telefone from tbclientes where nomecli like ?";
           try {
               pst = conexao.prepareStatement(sql);
               pst.setString(1, txtBuscar.getText() + "%");
               rs = pst.executeQuery();
               tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
               

           } catch (Exception e) {
           }
       }
       
       public void preencher_campos() {
           
           int linha = tblClientes.getSelectedRow();
           
           txtNomeCliente.setText(tblClientes.getModel().getValueAt(linha, 1).toString());
           txtId.setText(tblClientes.getModel().getValueAt(linha, 0).toString());
       }
       
       public void mostra_painel() {
           if(txtDefeito.getText().isEmpty() || txtEquipamento.getText().isEmpty() || txtNomeCliente.getText().isEmpty() || txtServico.getText().isEmpty() || txtId.getText().isEmpty()) {
               panBtn.setVisible(false);
           }else {
                panBtn.setVisible(true);
           }
       }
       
       
       public void adicionar_os() {
           
           Date data = new Date();
           Calendar mes = new GregorianCalendar();
           Calendar ano = new GregorianCalendar();
           int m = mes.get((Calendar.MONTH));
           int a = ano.get((Calendar.YEAR));
           DateFormat formatador =  DateFormat.getDateInstance(DateFormat.FULL);
           System.out.println(formatador.format(data));
           
           String sql = "insert into tbos (data_os, equipamento, defeito, servico, tecnico, valor, idcli, nomeCliente, situacao, tipo, mes, ano) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
           String tipo;
           if(rdborc.isSelected()) {
                tipo = "Orçamento";
           }else {
                tipo = "Ordem de serviço";
           }
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1, formatador.format(data));
            pst.setString(2, txtEquipamento.getText());
            pst.setString(3, txtDefeito.getText());
            pst.setString(4, txtServico.getText());
            pst.setString(5, txtTecnico.getText());
            pst.setString(6, txtValor.getText());
            pst.setString(7, txtId.getText());
            pst.setString(8, txtNomeCliente.getText());
            pst.setString(9, combSit.getSelectedItem().toString());
            pst.setString(10, tipo);
            pst.setInt(11, m);
            pst.setInt(12, a);
            int verificacao = pst.executeUpdate();
                if(verificacao > 0) {
                    JOptionPane.showMessageDialog(null, tipo + " adicionado com sucesso ! ");
                    txtDefeito.setText(null);
                    txtEquipamento.setText(null);
                    txtTecnico.setText(null);
                    txtServico.setText(null);
                    txtTecnico.setText(null);
                    txtNomeCliente.setText(null);
                    txtId.setText(null);
                    txtValor.setText(null);
                    
                }
        } catch (SQLException ex) {
            Logger.getLogger(TelaOrdem.class.getName()).log(Level.SEVERE, null, ex);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        txtNomeCliente = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        txtTecnico = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        txtDefeito = new javax.swing.JTextField();
        txtServico = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        combSit = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        txtEquipamento = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        rdborc = new javax.swing.JRadioButton();
        rdbOrd = new javax.swing.JRadioButton();
        panBtn = new javax.swing.JPanel();
        btAdicionar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastra ordem de serviço");
        setPreferredSize(new java.awt.Dimension(898, 720));
        setVisible(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNomeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeClienteActionPerformed(evt);
            }
        });
        getContentPane().add(txtNomeCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 473, 356, -1));

        txtId.setEditable(false);
        getContentPane().add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 442, 63, -1));
        getContentPane().add(txtTecnico, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 499, 147, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hol/es/manotec/icones/1467648571_search.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(220, 220, 220))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel9)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 877, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nome");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 473, 41, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("ID");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 442, 24, -1));
        getContentPane().add(txtValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 499, 132, -1));

        txtDefeito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDefeitoKeyReleased(evt);
            }
        });
        getContentPane().add(txtDefeito, new org.netbeans.lib.awtextra.AbsoluteConstraints(512, 503, 358, -1));

        txtServico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtServicoKeyReleased(evt);
            }
        });
        getContentPane().add(txtServico, new org.netbeans.lib.awtextra.AbsoluteConstraints(512, 471, 358, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Serviço");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 471, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Defeito");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 497, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Valor");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 496, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Técnico");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 499, -1, -1));

        combSit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Na bancada", "Orçamento aprovado", "Orçamento reprovado", "Pronto" }));
        getContentPane().add(combSit, new org.netbeans.lib.awtextra.AbsoluteConstraints(511, 437, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Situação");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 437, -1, -1));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblClientesMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblClientes);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 152, 877, 226));

        txtEquipamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEquipamentoKeyReleased(evt);
            }
        });
        getContentPane().add(txtEquipamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 442, 198, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Equipamento");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 442, -1, -1));

        buttonGroup2.add(rdborc);
        rdborc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdborc.setText("Orçamento");
        getContentPane().add(rdborc, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 440, -1, -1));

        buttonGroup2.add(rdbOrd);
        rdbOrd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdbOrd.setText("Ordem de serviço");
        getContentPane().add(rdbOrd, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 440, -1, -1));

        panBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hol/es/manotec/icones/1467844314_icon-40-clipboard-list.png"))); // NOI18N
        btAdicionar.setToolTipText("Adicionar ordem de serviço");
        btAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hol/es/manotec/icones/1467844310_icon-124-printer-text.png"))); // NOI18N
        jButton2.setToolTipText("Imprimir ordem de serviço");

        javax.swing.GroupLayout panBtnLayout = new javax.swing.GroupLayout(panBtn);
        panBtn.setLayout(panBtnLayout);
        panBtnLayout.setHorizontalGroup(
            panBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBtnLayout.createSequentialGroup()
                .addContainerGap(290, Short.MAX_VALUE)
                .addComponent(btAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(280, 280, 280))
        );
        panBtnLayout.setVerticalGroup(
            panBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBtnLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btAdicionar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        getContentPane().add(panBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 534, -1, -1));

        setBounds(0, 0, 923, 706);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeClienteActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // TODO add your handling code here:
        preencher_campos();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
         
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarActionPerformed
        // TODO add your handling code here:
        adicionar_os();
    }//GEN-LAST:event_btAdicionarActionPerformed

    private void txtEquipamentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEquipamentoKeyReleased
        // TODO add your handling code here:
        mostra_painel();
    }//GEN-LAST:event_txtEquipamentoKeyReleased

    private void txtServicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtServicoKeyReleased
        // TODO add your handling code here:
        mostra_painel();
    }//GEN-LAST:event_txtServicoKeyReleased

    private void txtDefeitoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDefeitoKeyReleased
        // TODO add your handling code here:
        mostra_painel();
    }//GEN-LAST:event_txtDefeitoKeyReleased

    private void tblClientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMousePressed
        // TODO add your handling code here:
         tblClientes.editingCanceled(null);
           tblClientes.editingStopped(null);
    }//GEN-LAST:event_tblClientesMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdicionar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> combSit;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panBtn;
    private javax.swing.JRadioButton rdbOrd;
    private javax.swing.JRadioButton rdborc;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDefeito;
    private javax.swing.JTextField txtEquipamento;
    public static javax.swing.JTextField txtId;
    public static javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtServico;
    private javax.swing.JTextField txtTecnico;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
