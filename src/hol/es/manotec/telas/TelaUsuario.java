/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hol.es.manotec.telas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import hol.es.manotec.dal.ModuloConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author mateu
 */
public class TelaUsuario extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaUsuario
     */
    public TelaUsuario() {
        initComponents();
        conexao = ModuloConexao.conector();
        buscar();
    }
    
         Connection conexao = null;
         PreparedStatement pst = null;
         PreparedStatement verific = null;
         ResultSet rs = null;
         
    private void adicionar() {

        String sql = "insert into tbusuarios (usuario, fone, loguin, senha, perfil) values ( ?, ?, ?, ?, ?)";
        String sqlVerification = "select * from tbusuarios where loguin = ?";
        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtTelefone.getText());
            pst.setString(3, txtLoguin.getText());
            pst.setString(4, txtSenha.getText());
            pst.setString(5, combPerfil.getSelectedItem().toString());
            verific = conexao.prepareStatement(sqlVerification);
            verific.setString(1, txtLoguin.getText());
            rs = verific.executeQuery();

            if (txtSenha.getText().isEmpty() || txtNome.getText().isEmpty() || txtLoguin.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Ops, algo deu errado, você preencheu todos os campos  ?");

            } else if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Acho que já temos alguém com esse usuário, tente outro.");
            } else {
                int adicionado = adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso ! ");
                    txtId.setText(null);
                 txtNome.setText(null);
                 txtLoguin.setText(null);
                 txtSenha.setText(null);
                 txtTelefone.setText(null);
                 buscar();
                }
            }
                
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Opa, algo deu errado, verifique novamente se todos os campos estão devidamente preenchidos");
        }
    }


     private void editar() {
        String sql = "update tbusuarios set iduser= ?, usuario = ?, fone =?, loguin =?, senha= ?, perfil = ? where iduser = ?";
        String verificadora = "select * from tbusuarios where loguin = ?";
        
         try {
             
              int verificador = 0;
             verific = conexao.prepareStatement(verificadora);
             verific.setString(1, txtLoguin.getText());
             rs =  verific.executeQuery();
             pst = conexao.prepareStatement(sql);
             pst.setString(1, txtId.getText());
             pst.setString(2, txtNome.getText());
             pst.setString(3, txtTelefone.getText());
             pst.setString(4, txtLoguin.getText());
             pst.setString(5, txtSenha.getText());
             pst.setString(6, combPerfil.getSelectedItem().toString());
             pst.setString(7, txtId.getText());
             
             
             if(rs.next()) {
                 
             } 
             
                 if (txtSenha.getText().isEmpty() || txtNome.getText().isEmpty() || txtLoguin.getText().isEmpty()) {

                         JOptionPane.showMessageDialog(null, "Ops, algo deu errado, você preencheu todos os campos  ?");

                        }else { 
             
                         verificador = pst.executeUpdate();
                         }
             if( verificador > 0) {
                 JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso ! ");
                 buscar();
             }
         } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, e);
         }
     }
     
    
    private void consultar() {
        String sql = "select * from tbusuarios where iduser = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtId.getText());
            rs = pst.executeQuery();
            
            if (rs.next()) {
                txtNome.setText(rs.getString(2));
                txtTelefone.setText(rs.getString(3));
                txtLoguin.setText(rs.getString(4));
                txtSenha.setText(rs.getString(5));
                combPerfil.setSelectedItem(rs.getString(6));
            } else {
                txtId.setText("");
                txtNome.setText("");
                txtTelefone.setText("");  
                txtLoguin.setText("");
                txtSenha.setText("");
                JOptionPane.showMessageDialog(null, "Usuário não encontrado");
            }
        } catch (Exception e) {
            
                 
            
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    
    
    public void deletar() {
        String sql = "delete from tbusuarios where iduser = ?";
        
        
        int confirm = JOptionPane.showConfirmDialog(null, "Tem certaza que deseja deletar este usuário ?", "Atenção", JOptionPane.YES_NO_OPTION);
        
        if(confirm == JOptionPane.YES_OPTION) {
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtId.getText());
            int verificador = pst.executeUpdate();
            
                if (verificador > 0) {
                
                 JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso ! ");
                 txtId.setText(null);
                 txtNome.setText(null);
                 txtLoguin.setText(null);
                 txtSenha.setText(null);
                 txtTelefone.setText(null);
            }
            
        } catch (Exception e) {
        }
        }else {
            
        }
        
    }
    
    public void buscar() {
        
        String sql = "select iduser as ID, usuario as Nome, fone as Telefone, loguin as Loguin, senha as Senha, perfil as Perfil from tbusuarios where usuario like ?";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtBusca.getText() + "%");
            rs  = pst.executeQuery();
            
            tblUsuario.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
        }
    }
    
    public void setar_campos() {
        
        int linha = tblUsuario.getSelectedRow();
        
        txtNome.setText(tblUsuario.getModel().getValueAt(linha, 1).toString());
        txtTelefone.setText(tblUsuario.getModel().getValueAt(linha, 2).toString());
        txtLoguin.setText(tblUsuario.getModel().getValueAt(linha, 3).toString());
        txtSenha.setText(tblUsuario.getModel().getValueAt(linha, 4).toString());
        combPerfil.setSelectedItem(tblUsuario.getModel().getValueAt(linha, 5));
        txtId.setText(tblUsuario.getModel().getValueAt(linha, 0).toString());
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
        jLabel5 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtLoguin = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        txtTelefone = new javax.swing.JFormattedTextField();
        btnDeletar = new javax.swing.JButton();
        combPerfil = new javax.swing.JComboBox<>();
        btnCadastrar1 = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtBusca = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuario = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuarios");
        setMinimumSize(new java.awt.Dimension(1053, 687));
        setPreferredSize(new java.awt.Dimension(1053, 687));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Nome*");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Telefone");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Perfil*");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Loguin*");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Senha*");

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hol/es/manotec/icones/1467648398_remove_user.png"))); // NOI18N
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        combPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "User" }));

        btnCadastrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hol/es/manotec/icones/1467648387_edit_user.png"))); // NOI18N
        btnCadastrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrar1ActionPerformed(evt);
            }
        });

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hol/es/manotec/icones/1467648392_add_user.png"))); // NOI18N
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("ID");

        txtId.setEditable(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("*Campos obrigatórios");

        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscaKeyReleased(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hol/es/manotec/icones/1467648571_search.png"))); // NOI18N

        tblUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 109, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnAdicionar)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel8))))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnCadastrar1)
                                            .addGap(38, 38, 38)
                                            .addComponent(btnDeletar))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(combPerfil, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtTelefone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(54, 54, 54)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel5))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtSenha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtLoguin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(21, 21, 21))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7))
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(153, 153, 153))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAdicionar, btnCadastrar1, btnDeletar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtLoguin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(combPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdicionar)
                    .addComponent(btnDeletar)
                    .addComponent(btnCadastrar1))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAdicionar, btnCadastrar1, btnDeletar});

        setBounds(0, 0, 914, 720);
    }// </editor-fold>//GEN-END:initComponents
         
    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        // TODO add your handling code here:
          deletar();
          
         
    }//GEN-LAST:event_btnDeletarActionPerformed

    private void btnCadastrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrar1ActionPerformed
        // TODO add your handling code here:
        
        editar();
    }//GEN-LAST:event_btnCadastrar1ActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void txtBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyReleased
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_txtBuscaKeyReleased

    private void tblUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuarioMouseClicked
        // TODO add your handling code here:
        setar_campos();
    }//GEN-LAST:event_tblUsuarioMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnCadastrar1;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JComboBox<String> combPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUsuario;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLoguin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
