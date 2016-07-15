/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hol.es.manotec.telas;
import java.sql.*;
import hol.es.manotec.dal.ModuloConexao;
import java.awt.Color;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class TelaRelatorio extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaRelatorio
     */
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ResultSet rss = null;
    public TelaRelatorio() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    public void pesquisar_quantidade_orcamentos(){
        
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        Calendar mes = new GregorianCalendar();
        Calendar ano = new GregorianCalendar();
        int m = mes.get((Calendar.MONTH));
        int a = ano.get((Calendar.YEAR));
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = '0'";
        String sqlFevereiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = '1'";
        String sqlMarço = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = '2'";
        String sqlAbril = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = '3'";
        String sqlMaio = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = '4'";
        String sqlJunho = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = '5'";
        String sqlJulho = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = '6'";
        String sqlAgosto = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = '7'";
        String sqlSetembro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = '8'";
        String sqlOutubro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = '9'";
        String sqlNovembro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = '10'";
        String sqlDez = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = '11'";
        
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            int os = 0;
            int or = 0;
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            while(rs.next()) {
                String type = rs.getString(11);
                if (type.equals("Orçamento")){
                    or++;
                    
                }else {
                    os++;
                }
            }
            
            String sqlmes = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = ? AND ano = ? ";
            try {
                pst = conexao.prepareCall(sqlmes);
                pst.setInt(1, m);
                pst.setInt(2, a);
                rss = pst.executeQuery();
                while(rss.next()) {
                    String type = rss.getString(11);
                    int mees = rss.getInt(12);
                    if(type.equals("Orçamento")){
                    dataset.setValue(or, "Orçamento", meses[mees]);  
                    }else {
                        
                    }
                }
            } catch (Exception e) {
            }
                JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços", "Meses", "Quantidade", dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);
        ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
        frame.setVisible(true);
        frame.setSize(498,350);
            
        
        
        
        } catch (Exception e) {
        }
    }
     public void grafico(){
         
         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(80, "orçamentos", "Janeiro");
        dataset.setValue(50, "OS", "Janeiro");
        dataset.setValue(89, "orçamentos", "Fevereiro");
        dataset.setValue(65, "Ordem de serviços", "Janeiro");
        dataset.setValue(80, "orçamentos", "Janeiro");
        JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços", "Meses", "Quantidade", dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);
        ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
        frame.setVisible(true);
        frame.setSize(498,350);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        comSeletor = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        lblResultado = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Relatório");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        comSeletor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comSeletor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Orçamentos nesse mês", "Ordens de serviço nesse mês", " ", " " }));
        comSeletor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comSeletorActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Selecione um item");

        lblResultado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(318, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comSeletor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(303, 303, 303))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblResultado)
                        .addGap(404, 404, 404))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comSeletor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(lblResultado)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(419, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void comSeletorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comSeletorActionPerformed
        // TODO add your handling code here:
        
        
        if(comSeletor.getSelectedItem().toString().equals("Orçamentos nesse mês")) {
            
           grafico();
        }else if (comSeletor.getSelectedItem().toString().equals("Ordens de serviço nesse mês")) {
           // pesquisar_quantidade_ordem();
        }
        
    }//GEN-LAST:event_comSeletorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comSeletor;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblResultado;
    // End of variables declaration//GEN-END:variables
}
