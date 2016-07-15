/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hol.es.manotec.telas;
import  hol.es.manotec.telas.TelaPrincipal;
import java.sql.*;
import hol.es.manotec.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author mateu
 */
public class TelaCliente extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaCliente
     * 
     */
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public TelaCliente() {
        initComponents();
        
        conexao = ModuloConexao.conector();
      /*  try {
            String sql = "select * from tbclientes where nomecli like ?";
           pst = conexao.prepareStatement(sql);
           pst.setString(1, txtBuscar.getText() + "%");
           rs = pst.executeQuery();
           // Usando a biblioteca importada 
           tblCliente.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        } */
      pesquisar();
           
    }

    
    public void cadastrarCliente() {
        
        String sql = "insert into tbclientes( nomecli, endcli, fonecli, emailcli ) values (?, ?, ?, ?)";
     
        
        try {
            
            
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtEndereco.getText());
            pst.setString(3, txtTelefone.getText());
            pst.setString(4, txtEmail.getText());
            
            if(txtNome.getText().isEmpty() || txtTelefone.getText().isEmpty() ){
                JOptionPane.showMessageDialog(null, "Ops, algo deu errado, Você preencheu todos os campos ? ");
            }else {
                int contador = pst.executeUpdate();
                
                    if(contador  > 0) {
                        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso ! ");
                        pesquisar();
                    }
            }
            
        } catch (Exception e) {
        }
    }
    
    
    
    public void cadastroClienteOrdem() {
        
         String sql = "select * from tbclientes where  fonecli = ? AND nomecli = ? ";
         
        try {
                   pst = conexao.prepareStatement(sql);
                   String telefone = txtTelefone.getText();
                   pst.setString(1, telefone);
                   pst.setString(2, txtNome.getText());
                   rs = pst.executeQuery();
                   if(rs.next()) {
                   String nomecli = rs.getString(2);
                   String idcli = rs.getString(1);
                   TelaOrdem ordem = new TelaOrdem();
                   ordem.setVisible(true);
                   TelaPrincipal.desktop.remove(this);
                   TelaPrincipal.desktop.add(ordem);
                   ordem.show();
                   TelaOrdem.txtId.setText(idcli);
                   TelaOrdem.txtNomeCliente.setText(nomecli);
                   }else {
                       System.out.println("erro");
                   }
                   
         }catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
        }
        
        
        
       
        
    }
    
    
    public void pesquisar() {
        String sql = "select idcli as ID, nomecli as Nome, endcli as Endereço, fonecli as Telefone, emailcli as Emanil from tbclientes where nomecli like ?";
        
        try {
           pst = conexao.prepareStatement(sql);
           pst.setString(1, txtBuscar.getText() + "%");
           rs = pst.executeQuery();
           // Usando a biblioteca importada 
           tblCliente.setModel(DbUtils.resultSetToTableModel(rs));
           
        } catch (Exception e) {
        }
    }
    
    
    public void setar_campos() {
        
          int setar =   tblCliente.getSelectedRow();
          txtId.setText(tblCliente.getModel().getValueAt(setar, 0).toString());
          txtNome.setText(tblCliente.getModel().getValueAt(setar, 1).toString());
          txtEndereco.setText(tblCliente.getModel().getValueAt(setar, 2).toString());
          txtEmail.setText(tblCliente.getModel().getValueAt(setar, 4).toString());
          txtTelefone.setText(tblCliente.getModel().getValueAt(setar, 3).toString());
          
    }
    
    
    public void editarCliente() {
         int setar =   tblCliente.getSelectedRow();
         String sql = "update tbclientes set nomecli = ?, endcli = ?, emailcli = ?, fonecli = ? where idcli =  ?";
        try {
        String linha = tblCliente.getModel().getValueAt(setar, 0).toString();
        pst = conexao.prepareStatement(sql);
        pst.setString(1, txtNome.getText());
        pst.setString(2, txtEndereco.getText());
        pst.setString(3, txtEmail.getText());
        pst.setString(4, txtTelefone.getText());
        pst.setString(5, linha);
        
        
        if(txtNome.getText().isEmpty() || txtTelefone.getText().isEmpty()) {
             JOptionPane.showMessageDialog(null, "Ops, algo deu errado, Você preencheu todos os campos ? ");
        }else {
        pst.executeUpdate();
        pesquisar();
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
        public void deletar() {
            int linha = tblCliente.getSelectedRow();
            String sql = " delete from tbclientes where idcli =  ? ";
            try {
                 String id = tblCliente.getModel().getValueAt(linha, 0).toString();
                 pst = conexao.prepareStatement(sql);
                 System.out.println(id);
                 pst.setString(1, id);
                 
                 int verificacao = JOptionPane.showConfirmDialog(null, "Deseja mesmo deletar esse cliente ? ", "Atenção" , JOptionPane.YES_NO_OPTION);
                 
                    if(verificacao == JOptionPane.YES_OPTION) {
                        
                         pst.execute();
                         pesquisar();
                    }
                 
                 
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtEndereco = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JFormattedTextField();
        btnCadCli = new javax.swing.JButton();
        btnCadCliOrd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de clientes");
        setPreferredSize(new java.awt.Dimension(898, 720));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Nome");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Endereço");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Telefone");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Email");

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNome.setPreferredSize(new java.awt.Dimension(6, 29));

        txtEndereco.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnCadCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hol/es/manotec/icones/1467648392_add_user.png"))); // NOI18N
        btnCadCli.setToolTipText("Adicionar cliente");
        btnCadCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadCliActionPerformed(evt);
            }
        });

        btnCadCliOrd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hol/es/manotec/icones/1467649221_shoping_cart_filled.png"))); // NOI18N
        btnCadCliOrd.setToolTipText("Adicionar ordem para esse cliente");
        btnCadCliOrd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadCliOrdActionPerformed(evt);
            }
        });

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Endereço", "Telefone", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCliente);

        txtBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hol/es/manotec/icones/1467648571_search.png"))); // NOI18N

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hol/es/manotec/icones/1467648387_edit_user.png"))); // NOI18N
        btnEditar.setToolTipText("Editar");
        btnEditar.setAlignmentX(2.0F);
        btnEditar.setAlignmentY(2.0F);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hol/es/manotec/icones/1467648398_remove_user.png"))); // NOI18N
        btnRemover.setToolTipText("Remover");
        btnRemover.setAlignmentX(2.0F);
        btnRemover.setAlignmentY(2.0F);
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("ID");

        txtId.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel4)
                                            .addGap(27, 27, 27)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtId))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(338, 338, 338)
                        .addComponent(btnCadCli)
                        .addGap(43, 43, 43)
                        .addComponent(btnCadCliOrd)))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnRemover)
                .addGap(42, 42, 42)
                .addComponent(btnEditar)
                .addGap(306, 306, 306))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCadCliOrd, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(btnCadCli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );

        setBounds(0, 0, 898, 720);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadCliActionPerformed
        // TODO add your handling code here:
        cadastrarCliente();
        
    }//GEN-LAST:event_btnCadCliActionPerformed

    private void btnCadCliOrdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadCliOrdActionPerformed
        // TODO add your handling code here:
        cadastroClienteOrdem();
    }//GEN-LAST:event_btnCadCliOrdActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tblClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClienteMouseClicked
        // TODO add your handling code here:
        setar_campos();
    }//GEN-LAST:event_tblClienteMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        editarCliente();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // TODO add your handling code here:
        deletar();
    }//GEN-LAST:event_btnRemoverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadCli;
    private javax.swing.JButton btnCadCliOrd;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
