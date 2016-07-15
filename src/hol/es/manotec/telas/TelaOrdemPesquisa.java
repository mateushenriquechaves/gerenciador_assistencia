/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hol.es.manotec.telas;
import hol.es.manotec.dal.ModuloConexao;
import java.awt.Color;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author mateu
 */
public class TelaOrdemPesquisa extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaOrdemPesquisa
     */
    
    Connection conexao = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    public TelaOrdemPesquisa() {
        initComponents();
        conexao = ModuloConexao.conector();
        pesquisar_on_load();
       rdbOrcamento.setSelected(true);
       rdbOrdem.setSelected(true);
       panBtn.setVisible(false);
       rdborc.setSelected(true);
    }
    
    
    
   
    public void mostrar_btn_editar() {
        
        if((txtDefeito.getText().isEmpty() || txtNomeCliente.getText().isEmpty() || txtServico.getText().isEmpty() || txtEquipamento.getText().isEmpty()) ) {
             panBtn.setVisible(false);
        }else {
            
            panBtn.setVisible(true);
        
        }
    }
    public void pesquisar_nothing(){
        
         String sql2 = "select os as Os, data_os as Data, equipamento as Equipamento, defeito as Defeito, servico as Serviço, tecnico as Técnico, valor as Valor, idcli as IDCliente, nomeCliente as Nome, situacao as Situação, tipo as Tipo from tbos where defeito = 'sexo'";
        try {
            pst = conexao.prepareStatement(sql2);
            rs = pst.executeQuery();
            tblOrdem.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }
    }
    
    public void pesquisar_on_load() {
        String sql2 = "select os as Os, data_os as Data, equipamento as Equipamento, defeito as Defeito, servico as Serviço, tecnico as Técnico, valor as Valor,  nomeCliente as Nome, situacao as Situação, tipo as Tipo from tbos ";
        
        try {
            
           
                 pst = conexao.prepareStatement(sql2);
                 rs = pst.executeQuery();
                 tblOrdem.setModel(DbUtils.resultSetToTableModel(rs));
          
            
        } catch (Exception e) {
        }
    }
    
    public void pesquisar() {
        String sqlCriterioNome = "select os as Os, data_os as Data, equipamento as Equipamento, defeito as Defeito, servico as Serviço, tecnico as Técnico, valor as Valor, nomeCliente as Nome, situacao as Situação, tipo as Tipo from tbos where nomeCliente like ? AND tipo = ? ";
        
        String sqlNome = "select os as Os, data_os as Data, equipamento as Equipamento, defeito as Defeito, servico as Serviço, tecnico as Técnico, valor as Valor, nomeCliente as Nome, situacao as Situação, tipo as Tipo from tbos where nomeCliente like ?  ";

        String sqlOs = "select os as Os, data_os as Data, equipamento as Equipamento, defeito as Defeito, servico as Serviço, tecnico as Técnico, valor as Valor,  nomeCliente as Nome, situacao as Situação, tipo as Tipo from tbos where os like ?";
        
              String sqlOsCriterio = "select os as Os, data_os as Data, equipamento as Equipamento, defeito as Defeito, servico as Serviço, tecnico as Técnico, valor as Valor,  nomeCliente as Nome, situacao as Situação, tipo as Tipo from tbos where os like ? AND tipo = ?";
                     

        try {
            if (comPesquisa.getSelectedItem().toString().equals("Nome")) {
                if (rdbOrcamento.isSelected() && rdbOrdem.isSelected()) {
                    pst = conexao.prepareStatement(sqlNome);
                    pst.setString(1, txtBuscar.getText() + "%");
                    rs = pst.executeQuery();
                    tblOrdem.setModel(DbUtils.resultSetToTableModel(rs));
                } else if (rdbOrdem.isSelected()) {
                    pst = conexao.prepareStatement(sqlCriterioNome);
                    pst.setString(1, txtBuscar.getText() + "%");
                    pst.setString(2, "Ordem de serviço");
                    rs = pst.executeQuery();
                    tblOrdem.setModel(DbUtils.resultSetToTableModel(rs));
                } else if (rdbOrcamento.isSelected()) {
                    pst = conexao.prepareStatement(sqlCriterioNome);
                    pst.setString(1, txtBuscar.getText() + "%");
                    pst.setString(2, "Orçamento");
                    rs = pst.executeQuery();
                    tblOrdem.setModel(DbUtils.resultSetToTableModel(rs));

                } else {
                    pesquisar_nothing();
                }

            } else   {
                    if((rdbOrcamento.isSelected() && rdbOrdem.isSelected())) {
                pst = conexao.prepareStatement(sqlOs);
                pst.setString(1, txtBuscar.getText() + "%");
                rs = pst.executeQuery();
                tblOrdem.setModel(DbUtils.resultSetToTableModel(rs));
                    }
            else if (rdbOrdem.isSelected()) {
                pst = conexao.prepareStatement(sqlCriterioNome);
                pst.setString(1, txtBuscar.getText() + "%");
                pst.setString(2, "Ordem de serviço");
                rs = pst.executeQuery();
                tblOrdem.setModel(DbUtils.resultSetToTableModel(rs));
            } else if (rdbOrcamento.isSelected()) {
                pst = conexao.prepareStatement(sqlCriterioNome);
                pst.setString(1, txtBuscar.getText() + "%");
                pst.setString(2, "Orçamento");
                rs = pst.executeQuery();
                tblOrdem.setModel(DbUtils.resultSetToTableModel(rs));

            } else {
                pesquisar_nothing();
            }
            }
            
            System.out.println(pst.executeQuery().toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void mostra_painel() {
           if(txtDefeito.getText().isEmpty() || txtEquipamento.getText().isEmpty() || txtNomeCliente.getText().isEmpty() || txtServico.getText().isEmpty() || txtOs.getText().isEmpty()) {
               panBtn.setVisible(false);
           }else {
                panBtn.setVisible(true);
           }
       }
    
    
    public void editar() {
            int linha = tblOrdem.getSelectedRow();
           String sql = "update tbos set nomeCliente = ?, equipamento = ?, defeito = ?, servico = ?, tecnico = ?, valor = ?, situacao = ?, tipo = ? where os = ? ";
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
            pst.setString(9, tblOrdem.getModel().getValueAt(linha, 0).toString());
            int verificar = pst.executeUpdate();
            if(verificar > 0) {
                JOptionPane.showMessageDialog(null, "Registro modificado com sucesso ! ");
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrdem = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        rdbOrcamento = new javax.swing.JRadioButton();
        rdbOrdem = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        comPesquisa = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        panBtn = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        btnGrafico = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNomeCliente = new javax.swing.JTextField();
        txtOs = new javax.swing.JTextField();
        combSit = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtTecnico = new javax.swing.JTextField();
        txtEquipamento = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rdborc = new javax.swing.JRadioButton();
        txtValor = new javax.swing.JTextField();
        rdbOrd = new javax.swing.JRadioButton();
        txtDefeito = new javax.swing.JTextField();
        txtServico = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Pesquisa de Ordens");

        tblOrdem.setModel(new javax.swing.table.DefaultTableModel(
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
        tblOrdem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrdemMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblOrdemMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblOrdem);

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hol/es/manotec/icones/1467648571_search.png"))); // NOI18N

        rdbOrcamento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdbOrcamento.setText("Orçamentos");
        rdbOrcamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdbOrcamentoMouseClicked(evt);
            }
        });

        rdbOrdem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdbOrdem.setText("Ordem de serviços");
        rdbOrdem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbOrdemActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Mostrar somente : ");

        comPesquisa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comPesquisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "N° Os" }));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Pesquisar por ");

        panBtn.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hol/es/manotec/icones/1467844348_icon-136-document-edit.png"))); // NOI18N
        btnUpdate.setToolTipText("Editar");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnGrafico.setText("teste");
        btnGrafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panBtnLayout = new javax.swing.GroupLayout(panBtn);
        panBtn.setLayout(panBtnLayout);
        panBtnLayout.setHorizontalGroup(
            panBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBtnLayout.createSequentialGroup()
                .addGap(413, 413, 413)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGrafico)
                .addContainerGap(341, Short.MAX_VALUE))
        );
        panBtnLayout.setVerticalGroup(
            panBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panBtnLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(panBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Valor");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Técnico");

        txtNomeCliente.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                txtNomeClienteComponentAdded(evt);
            }
        });
        txtNomeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeClienteActionPerformed(evt);
            }
        });
        txtNomeCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeClienteKeyReleased(evt);
            }
        });

        txtOs.setEditable(false);

        combSit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Na bancada", "Orçamento aprovado", "Orçamento reprovado", "Pronto" }));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Situação");

        txtEquipamento.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                txtEquipamentoComponentAdded(evt);
            }
        });
        txtEquipamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEquipamentoKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Nome");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Equipamento");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("OS");

        buttonGroup1.add(rdborc);
        rdborc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdborc.setText("Orçamento");

        buttonGroup1.add(rdbOrd);
        rdbOrd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdbOrd.setText("Ordem de serviço");

        txtDefeito.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                txtDefeitoComponentAdded(evt);
            }
        });
        txtDefeito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDefeitoKeyReleased(evt);
            }
        });

        txtServico.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                txtServicoComponentAdded(evt);
            }
        });
        txtServico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtServicoKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Serviço");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Defeito");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(comPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdbOrcamento)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdbOrdem)
                                        .addGap(60, 60, 60)))
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel11)
                                        .addGap(4, 4, 4)
                                        .addComponent(txtEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel10)
                                        .addGap(14, 14, 14)
                                        .addComponent(combSit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(rdborc)
                                        .addGap(5, 5, 5)
                                        .addComponent(rdbOrd))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel6)
                                        .addGap(22, 22, 22)
                                        .addComponent(txtServico, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(29, 29, 29)
                                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9)
                                        .addGap(10, 10, 10)
                                        .addComponent(txtTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel7)
                                        .addGap(24, 24, 24)
                                        .addComponent(txtDefeito, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdbOrcamento)
                            .addComponent(rdbOrdem)
                            .addComponent(jLabel2))))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addComponent(jLabel10)
                    .addComponent(combSit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(rdborc))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(rdbOrd)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(txtDefeito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addComponent(panBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
       pesquisar();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void rdbOrcamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdbOrcamentoMouseClicked
        // TODO add your handling code here:
        pesquisar();
    }//GEN-LAST:event_rdbOrcamentoMouseClicked

    private void rdbOrdemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbOrdemActionPerformed
        pesquisar();        // TODO add your handling code here:
        mostrar_btn_editar();
    }//GEN-LAST:event_rdbOrdemActionPerformed

    private void tblOrdemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrdemMousePressed
        // TODO add your handling code here:
      
        tblOrdem.editingCanceled(null);
        tblOrdem.editingStopped(null);
        mostrar_btn_editar();
    }//GEN-LAST:event_tblOrdemMousePressed

    private void txtNomeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeClienteActionPerformed

    private void txtDefeitoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDefeitoKeyReleased
        // TODO add your handling code here:
        mostrar_btn_editar();
    }//GEN-LAST:event_txtDefeitoKeyReleased

    private void txtServicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtServicoKeyReleased
        // TODO add your handling code here:
        mostrar_btn_editar();
    }//GEN-LAST:event_txtServicoKeyReleased

    private void txtEquipamentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEquipamentoKeyReleased
        // TODO add your handling code here:
        mostrar_btn_editar();
    }//GEN-LAST:event_txtEquipamentoKeyReleased

    private void tblOrdemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrdemMouseClicked
        // TODO add your handling code here:
        mostrar_btn_editar();
        int linha = tblOrdem.getSelectedRow();
        
        txtDefeito.setText(tblOrdem.getModel().getValueAt(linha, 3).toString());
        txtOs.setText(tblOrdem.getModel().getValueAt(linha, 0).toString());
        txtNomeCliente.setText(tblOrdem.getModel().getValueAt(linha, 8).toString());
        txtEquipamento.setText(tblOrdem.getModel().getValueAt(linha, 2).toString());
        txtServico.setText(tblOrdem.getModel().getValueAt(linha, 4).toString());
        txtTecnico.setText(tblOrdem.getModel().getValueAt(linha, 5).toString());
        txtValor.setText(tblOrdem.getModel().getValueAt(linha, 6).toString());
        if(tblOrdem.getModel().getValueAt(linha, 9).equals("Orçamento")) {
            rdborc.setSelected(true);
            rdbOrd.setSelected(false);
        } else {
            rdbOrd.setSelected(true);
            rdborc.setSelected(false);
        }
        combSit.setSelectedItem(tblOrdem.getModel().getValueAt(linha, 9).toString());
        
    }//GEN-LAST:event_tblOrdemMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        editar();
        pesquisar();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtNomeClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeClienteKeyReleased
        // TODO add your handling code here:
        mostrar_btn_editar();
    }//GEN-LAST:event_txtNomeClienteKeyReleased

    private void txtNomeClienteComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_txtNomeClienteComponentAdded
        // TODO add your handling code here:
        mostrar_btn_editar();
    }//GEN-LAST:event_txtNomeClienteComponentAdded

    private void txtServicoComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_txtServicoComponentAdded
        // TODO add your handling code here:
        mostrar_btn_editar();
    }//GEN-LAST:event_txtServicoComponentAdded

    private void txtDefeitoComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_txtDefeitoComponentAdded
        // TODO add your handling code here:
        mostrar_btn_editar();
    }//GEN-LAST:event_txtDefeitoComponentAdded

    private void txtEquipamentoComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_txtEquipamentoComponentAdded
        // TODO add your handling code here:
        mostrar_btn_editar();
    }//GEN-LAST:event_txtEquipamentoComponentAdded

    private void btnGraficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficoActionPerformed
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_btnGraficoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGrafico;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comPesquisa;
    private javax.swing.JComboBox<String> combSit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panBtn;
    private javax.swing.JRadioButton rdbOrcamento;
    private javax.swing.JRadioButton rdbOrd;
    private javax.swing.JRadioButton rdbOrdem;
    private javax.swing.JRadioButton rdborc;
    private javax.swing.JTable tblOrdem;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDefeito;
    private javax.swing.JTextField txtEquipamento;
    public static javax.swing.JTextField txtNomeCliente;
    public static javax.swing.JTextField txtOs;
    private javax.swing.JTextField txtServico;
    private javax.swing.JTextField txtTecnico;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
