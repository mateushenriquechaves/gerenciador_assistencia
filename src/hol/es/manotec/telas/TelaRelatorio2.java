/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hol.es.manotec.telas;

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
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mateu
 */
public class TelaRelatorio2 extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaRelatorio2
     */
    Connection conexao = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    public TelaRelatorio2() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    
    
    // Função para criar o gráfico de orçamentos do mês de agosto
    public void agosto() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlAgosto = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 7";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlAgosto);
            rs = pst.executeQuery();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            int row = pst.getMaxRows();
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                if(type.equals("Orçamento") && mes == 7){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 7) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            dataset.setValue(quant_orcamento, "Orçamento", meses[7]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços", "Meses", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
        }
    }
    // Função para criar o gráfico de lucro do mês de agosto
    public void agostoLucro() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlAgosto = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 7";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlAgosto);
            rs = pst.executeQuery();
            int row = pst.getMaxRows();
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                double valor = rs.getDouble(7);
                if(type.equals("Orçamento") && mes == 7){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 7) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(valor_os, "Lucro", meses[7]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços", "Meses", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void agostoTudo() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlAgosto = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 7";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlAgosto);
            rs = pst.executeQuery();
            int row = pst.getMaxRows();
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                if(type.equals("Orçamento") && mes == 7){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if (type.equals("Ordem de serviço") && mes == 7) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(quant_orcamento, "Orçamento", meses[7]);
            dataset.setValue(valor_os, "Lucro", meses[7]);
            dataset.setValue(quant_os, "Ordem de serviço", meses[7]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços", "Meses", "Quantidade", dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);
        ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
        frame.setVisible(true);
        frame.setSize(498,350);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
        // Função para criar o gráfico de lucro do mês de agosto
     public void agostoOs() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlAgosto = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 7";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlAgosto);
            rs = pst.executeQuery();
            int row = pst.getMaxRows();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                double valor = rs.getDouble(7);
                if(type.equals("Orçamento") && mes == 7){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 7) {
                    quant_os++;
                    valor_os += valor;
                }
            }
            dataset.setValue(quant_os, "Ordem de serviço", meses[7]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços", "Meses", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
        }
    }
     
      public void janeiroOs() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 1";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            int row = pst.getFetchSize();
            System.out.println(row);
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                double valor = rs.getDouble(7);
                if(type.equals("Orçamento") && mes == 1){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 1) {
                    quant_os++;
                    valor_os += valor;
                }
            }
            dataset.setValue(quant_os, "Ordem de serviço", meses[0]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços", "Meses", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
        }
    }
      
      public void janeiroTudo() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 1";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            int row = rs.getRow();
            System.out.println(row);
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                if(type.equals("Orçamento") && mes == 1){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if (type.equals("Ordem de serviço") && mes == 1) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(quant_orcamento, "Orçamento", "Orçamento");
            dataset.setValue(valor_os, "Lucro", "Lucro");
            dataset.setValue(quant_os, "Ordem de serviço", "Ordens de serviços");
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços do mes de Janeiro", "", "Quantidade", dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);
        ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
        frame.setVisible(true);
        frame.setSize(498,350);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
      
      public void janeiroLucro() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 1";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            int row = pst.getMaxRows();
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                double valor = rs.getDouble(7);
                if(type.equals("Orçamento") && mes == 1){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 1) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(valor_os, "Lucro", meses[0]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços ","Mês", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
      
      public void janeiro() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 1";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            int row = pst.getMaxRows();
            System.out.println(row);
            while(rs.next()) {
                
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                if(type.equals("Orçamento") && mes == 1){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 1) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            dataset.setValue(quant_orcamento, "Orçamento", meses[0]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços", "Meses", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
        }
        
    }
    
    //Fevereiro Orçamento 
      
      public void fevereiro() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 2";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            int row = pst.getMaxRows();
            System.out.println(row);
            while(rs.next()) {
                
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                if(type.equals("Orçamento") && mes == 2){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 2) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            dataset.setValue(quant_orcamento, "Orçamento", meses[1]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços", "Meses", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
        }
        
    }
      
      //Fevereiro Ordem de serviços
      
       public void fevereiroOs() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 2";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            int row = pst.getFetchSize();
            System.out.println(row);
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                double valor = rs.getDouble(7);
                if(type.equals("Orçamento") && mes == 2){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 2) {
                    quant_os++;
                    valor_os += valor;
                }
            }
            dataset.setValue(quant_os, "Ordem de serviço", meses[1]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços", "Meses", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
        }
    }
    
    // Fevereiro tudo 
       public void fevereiroTudo() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 2";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            int row = rs.getRow();
            System.out.println(row);
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                if(type.equals("Orçamento") && mes == 2){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if (type.equals("Ordem de serviço") && mes == 2) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(quant_orcamento, "Orçamento", "Orçamento");
            dataset.setValue(valor_os, "Lucro", "Lucro");
            dataset.setValue(quant_os, "Ordem de serviço", "Ordens de serviços");
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços do mes de Fevereiro", "", "Quantidade", dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);
        ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
        frame.setVisible(true);
        frame.setSize(498,350);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
       
       // Fevereiro Lucro 
       
       public void fevereiroLucro() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 2";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            int row = pst.getMaxRows();
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                double valor = rs.getDouble(7);
                if(type.equals("Orçamento") && mes == 2){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 2) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(valor_os, "Lucro", meses[1]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços ","Mês", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
       
       // Março orçamento 
       
       public void marco() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 3";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            int row = pst.getMaxRows();
            while(rs.next()) {
                
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                if(type.equals("Orçamento") && mes == 3){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 3) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            dataset.setValue(quant_orcamento, "Orçamento", meses[2]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços", "Meses", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
        }
        
    }
       // Março ordem de serviço
       public void marcoOs() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 3";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            int row = pst.getFetchSize();
            System.out.println(row);
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                double valor = rs.getDouble(7);
                if(type.equals("Orçamento") && mes == 3){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 3) {
                    quant_os++;
                    valor_os += valor;
                }
            }
            dataset.setValue(quant_os, "Ordem de serviço", meses[2]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços", "Meses", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
        }
    }
       
       // Março Tudo
       public void marcoTudo() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 3";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            int row = rs.getRow();
            System.out.println(row);
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                if(type.equals("Orçamento") && mes == 3){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if (type.equals("Ordem de serviço") && mes == 3) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(quant_orcamento, "Orçamento", "Orçamento");
            dataset.setValue(valor_os, "Lucro", "Lucro");
            dataset.setValue(quant_os, "Ordem de serviço", "Ordens de serviços");
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços do mes de Março", "", "Quantidade", dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);
        ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
        frame.setVisible(true);
        frame.setSize(498,350);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
       // Tudo de março
        public void marcoLucro() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 3";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            int row = pst.getMaxRows();
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                double valor = rs.getDouble(7);
                if(type.equals("Orçamento") && mes == 3){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 3) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(valor_os, "Lucro", meses[2]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços ","Mês", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
        
        // Orçamentos do mês de abril 
        public void abril() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 4";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            int row = pst.getMaxRows();
            while(rs.next()) {
                
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                if(type.equals("Orçamento") && mes == 4){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 4) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            dataset.setValue(quant_orcamento, "Orçamento", meses[3]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços", "Meses", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
        }
        
    }
        
        // Ordens de serviço do mês de abril 
        
         public void abrilOs() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 4";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            int row = pst.getFetchSize();
            System.out.println(row);
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                double valor = rs.getDouble(7);
                if(type.equals("Orçamento") && mes == 4){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 4) {
                    quant_os++;
                    valor_os += valor;
                }
            }
            dataset.setValue(quant_os, "Ordem de serviço", meses[3]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços", "Meses", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
        }
    }
         
         // Tudo do mês de abril 
         
         public void abrilTudo() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 4";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            int row = rs.getRow();
            System.out.println(row);
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                if(type.equals("Orçamento") && mes == 4){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if (type.equals("Ordem de serviço") && mes == 4) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(quant_orcamento, "Orçamento", "Orçamento");
            dataset.setValue(valor_os, "Lucro", "Lucro");
            dataset.setValue(quant_os, "Ordem de serviço", "Ordens de serviços");
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços do mes de Abril", "", "Quantidade", dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);
        ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
        frame.setVisible(true);
        frame.setSize(498,350);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
         
         // Lucro do mês de abril 
         
         public void abrilLucro() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 4";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            int row = pst.getMaxRows();
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                double valor = rs.getDouble(7);
                if(type.equals("Orçamento") && mes == 4){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 4) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(valor_os, "Lucro", meses[3]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços ","Mês", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
        
       // Orçamentos de maio 
         
         public void maio() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 5";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            int row = pst.getMaxRows();
            while(rs.next()) {
                
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                if(type.equals("Orçamento") && mes == 5){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 5) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            dataset.setValue(quant_orcamento, "Orçamento", meses[4]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços", "Meses", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
        }
        
    }
         
         // Ordens de serviços do mês de maio
         public void maioOs() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 5";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            int row = pst.getFetchSize();
            System.out.println(row);
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                double valor = rs.getDouble(7);
                if(type.equals("Orçamento") && mes == 5){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 5) {
                    quant_os++;
                    valor_os += valor;
                }
            }
            dataset.setValue(quant_os, "Ordem de serviço", meses[4]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços", "Meses", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
        }
    }
         // Tudo do mes de maio
         public void maioTudo() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 5";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            int row = rs.getRow();
            System.out.println(row);
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                if(type.equals("Orçamento") && mes == 5){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if (type.equals("Ordem de serviço") && mes == 5) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(quant_orcamento, "Orçamento", "Orçamento");
            dataset.setValue(valor_os, "Lucro", "Lucro");
            dataset.setValue(quant_os, "Ordem de serviço", "Ordens de serviços");
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços do mes de Maio", "", "Quantidade", dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);
        ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
        frame.setVisible(true);
        frame.setSize(498,350);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
         // Lucro do mês de maio 
         
          public void maioLucro() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 5";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            int row = pst.getMaxRows();
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                double valor = rs.getDouble(7);
                if(type.equals("Orçamento") && mes == 5){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 5) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(valor_os, "Lucro", meses[4]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços ","Mês", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
        // Orçamentos do mês de junho 
           public void junho() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 6";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            int row = pst.getMaxRows();
            while(rs.next()) {
                
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                if(type.equals("Orçamento") && mes == 6){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 6) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            dataset.setValue(quant_orcamento, "Orçamento", meses[5]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços", "Meses", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
        }
        
    }
         
         // Ordens de serviços do mês de junho
         public void junhoOs() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 6";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            int row = pst.getFetchSize();
            System.out.println(row);
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                double valor = rs.getDouble(7);
                if(type.equals("Orçamento") && mes == 6){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 6) {
                    quant_os++;
                    valor_os += valor;
                }
            }
            dataset.setValue(quant_os, "Ordem de serviço", meses[5]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços", "Meses", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
        }
    }
         // Tudo do mes de junho
         public void junhoTudo() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 6";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            int row = rs.getRow();
            System.out.println(row);
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                if(type.equals("Orçamento") && mes == 6){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if (type.equals("Ordem de serviço") && mes == 6) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(quant_orcamento, "Orçamento", "Orçamento");
            dataset.setValue(valor_os, "Lucro", "Lucro");
            dataset.setValue(quant_os, "Ordem de serviço", "Ordens de serviços");
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços do mes de Junho", "", "Quantidade", dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);
        ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
        frame.setVisible(true);
        frame.setSize(498,350);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
         // Lucro do mês de junho 
         
          public void junhoLucro() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 6";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            int row = pst.getMaxRows();
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                double valor = rs.getDouble(7);
                if(type.equals("Orçamento") && mes == 6){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 6) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(valor_os, "Lucro", meses[5]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços ","Mês", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
          
          // Orçamentos do mês de julho 
          public void julho() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 7";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            int row = pst.getMaxRows();
            while(rs.next()) {
                
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                if(type.equals("Orçamento") && mes == 7){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 7) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            dataset.setValue(quant_orcamento, "Orçamento", meses[6]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços", "Meses", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
        }
        
    }
         
         // Ordens de serviços do mês de julho
         public void julhoOs() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 7";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            int row = pst.getFetchSize();
            System.out.println(row);
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                double valor = rs.getDouble(7);
                if(type.equals("Orçamento") && mes == 7){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 7) {
                    quant_os++;
                    valor_os += valor;
                }
            }
            dataset.setValue(quant_os, "Ordem de serviço", meses[6]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços", "Meses", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
        }
    }
         // Tudo do mes de julho
         public void julhoTudo() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 7";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            int row = rs.getRow();
            System.out.println(row);
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                if(type.equals("Orçamento") && mes == 7){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if (type.equals("Ordem de serviço") && mes == 7) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(quant_orcamento, "Orçamento", "Orçamento");
            dataset.setValue(valor_os, "Lucro", "Lucro");
            dataset.setValue(quant_os, "Ordem de serviço", "Ordens de serviços");
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços do mes de Julho", "", "Quantidade", dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);
        ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
        frame.setVisible(true);
        frame.setSize(498,350);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
         // Lucro do mês de maio 
         
          public void julhoLucro() {
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sqlJaneiro = "select * from tbos where tipo = 'Orçamento' or tipo = 'Ordem de serviço' AND mes = 7";
        int i = 0;
        int quant_orcamento = 0;
        double valor_orcamento = 0;
        double valor_os = 0;
        int quant_os = 0;
        try {
            pst = conexao.prepareStatement(sqlJaneiro);
            rs = pst.executeQuery();
            int row = pst.getMaxRows();
            while(rs.next()) {
                i++;
                String type = rs.getString(11);
                int mes = rs.getInt(12);
                double valor = rs.getDouble(7);
                if(type.equals("Orçamento") && mes == 7){
                    quant_orcamento++;
                    valor_orcamento += rs.getDouble(7);
                    
                }else if(type.equals("Ordem de serviço") && mes == 7) {
                    quant_os++;
                    valor_os += rs.getDouble(7);
                }
            }
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(valor_os, "Lucro", meses[6]);
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços ","Mês", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
       
    public void relatorio_anual(){
        String meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        String sql = "select * from tbos where ano = ?";
        Calendar ano = new GregorianCalendar();
        
        int a = ano.get((Calendar.YEAR));
        int i = 0;
        int janeiro = 0;
        int fevereiro = 0;
        int marco = 0;
        int abril = 0;
        int maio = 0;
        int junho = 0;
        int julho = 0;
        int agosto = 0;
        int setembro = 0;
        int outubro = 0;
        int novembro =0;
        int dezembro = 0;
        int janeiro_orcamento =0;
        int janeiro_os =0;
        int fevereiro_orcamento =0;
        int fevereiro_os =0;
        int marco_orcamento = 0;
        int marco_os = 0;
        int abril_orcamento=0;
        int abril_os = 0;
        int maio_orcamento = 0;
        int maio_os = 0;
        int junho_orcamento = 0;
        int junho_os = 0;
        int julho_orcamento = 0;
        int julho_os = 0;
        int agosto_orcamento = 0;
        int agosto_os = 0;
        int setembro_orcamento = 0;
        int setembro_os = 0;
        int outubro_orcamento = 0;
        int outubro_os = 0;
        int novembro_orcamento = 0;
        int novembro_os =0;
        int dezembro_orcamento = 0;
        int dezembro_os = 0;
        int valor_janeiro = 0;
        int valor_fevereiro =0;
        int valor_marco = 0;
        int valor_abril =0;
        int valor_maio = 0;
        int valor_junho = 0;
        int valor_julho =0;
        int valor_agosto = 0;
        int valor_setembro =0;
        int valor_outubro =0;
        int valor_novembro =0;
        int valor_dezembro =0;
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, a);
            rs = pst.executeQuery();
            int row = pst.getMaxRows();
            while(rs.next()){
                 int mes = rs.getInt(12);
            String type = rs.getString(11);
                 double valor = rs.getDouble(7);
                i++;
                if(mes == 0 && type.equals("Orçamento")){
                    janeiro_orcamento++;
                }else if(mes ==0 && type.equals("Ordem de serviço") ){
                    janeiro_os++;
                    valor_janeiro += valor;
                }
                else if(mes == 1 && type.equals("Orçamento") ) {
                    fevereiro_orcamento++;
                }else if(mes == 1 && type.equals("Ordem de serviço")) {
                    fevereiro_os++;
                    valor_fevereiro += valor;
                }
                else if(mes == 2 && type.equals("Oçamento")) {
                    marco_orcamento++;
                }else if(mes == 2 && type.equals("Ordem de serviço")){
                    marco_os++;
                    valor_marco += valor;
                }else if(mes ==3 && type.equals("Orçamento")) {
                    abril_orcamento++;
                }else if(mes ==3 && type.equals("Ordem de serviço")) {
                    abril_os++;
                    valor_abril += valor;
                }
                else if(mes ==4 && type.equals("Orçamento")){
                    maio_orcamento++;
                }else if(mes ==4 && type.equals("Ordem de serviço")) {
                    maio_os++;
                    valor_maio += maio;
                }else if(mes == 5 && type.equals("Orçamento")){
                    junho_orcamento++;
                }else if(mes ==5 && type.equals("Ordem de serviço")) {
                    junho_os++;
                    valor_junho += valor;
                }else if(mes == 6  && type.equals("Orçamento")){
                    julho_orcamento++;
                }else if(mes ==6 && type.equals("Ordem de serviço")) {
                    julho_os++;
                    valor_julho += valor;
                }else if(mes == 7 && type.equals("Orçamento")){
                    agosto_orcamento++;
                }else if(mes == 7 && type.equals("Ordem de serviço")) {
                    agosto_os++;
                    valor_agosto += valor;
                }else if(mes == 8 && type.equals("Orçamento")){
                    setembro_orcamento++;
                }else if(mes == 8 && type.equals("Ordem de serviço")){
                    setembro_os++;
                    valor_setembro += valor;
                }else if(mes == 9 && type.equals("Orçamento")){
                    outubro_orcamento++;
                }else if(mes == 9 && type.equals("Ordem de serviço")) {
                    outubro_os++;
                    valor_outubro += valor;
                }else if (mes == 10 && type.equals("Orçamento")) {
                    novembro_orcamento++;
                }else if(mes == 10 && type.equals("Ordem de serviço")){
                    novembro_os++;
                    valor_novembro += valor;
                }else if(mes ==11 && type.equals("Orçamento")) {
                    dezembro_orcamento++;
                }else if(mes == 11 && type.equals("Ordem de serviço")){
                    dezembro_os++;
                    valor_dezembro += valor;
                }
                
                
            }
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.setValue(janeiro_orcamento, "Orçamento", meses[0]);
            dataset.setValue(janeiro_os, "Ordem de serviço", meses[0]);
            dataset.setValue(janeiro_orcamento + janeiro_os , "Total", meses[0]);
            dataset.setValue(valor_janeiro, "Lucro", meses[0]);
            
            dataset.setValue(fevereiro_orcamento, "Orçamento", meses[1]);
            dataset.setValue(fevereiro_os, "Ordem de serviço", meses[1]);
            dataset.setValue(fevereiro_orcamento + fevereiro_os , "Total", meses[1]);
             dataset.setValue(valor_fevereiro, "Lucro", meses[1]);
            
            dataset.setValue(marco_orcamento, "Orçamento", meses[2]);
            dataset.setValue(marco_os, "Ordem de serviço", meses[2]);
            dataset.setValue(marco_orcamento + marco_os , "Total", meses[2]);
            dataset.setValue(valor_marco, "Lucro", meses[2]);
            
            
             dataset.setValue(abril_orcamento, "Orçamento", meses[3]);
            dataset.setValue(abril_os, "Ordem de serviço", meses[3]);
            dataset.setValue(abril_orcamento + abril_os , "Total", meses[3]);
            dataset.setValue(valor_abril, "Lucro", meses[3]);           

            
            dataset.setValue(maio_orcamento, "Orçamento", meses[4]);
            dataset.setValue(maio_os, "Ordem de serviço", meses[4]);
            dataset.setValue(maio_orcamento + maio_os , "Total", meses[4]);
            dataset.setValue(valor_maio, "Lucro", meses[4]);
            
            dataset.setValue(junho_orcamento, "Orçamento", meses[5]);
            dataset.setValue(junho_os, "Ordem de serviço", meses[5]);
            dataset.setValue(junho_orcamento + junho_os , "Total", meses[5]);
            dataset.setValue(valor_junho, "Lucro", meses[5]);
            
            dataset.setValue(julho_orcamento, "Orçamento", meses[6]);
            dataset.setValue(julho_os, "Ordem de serviço", meses[6]);
            dataset.setValue(julho_orcamento + julho_os , "Total", meses[6]);
            dataset.setValue(valor_julho, "Lucro", meses[6]);
            
            dataset.setValue(agosto_orcamento, "Orçamento", meses[7]);
            dataset.setValue(agosto_os, "Ordem de serviço", meses[7]);
            dataset.setValue(agosto_orcamento + agosto_os , "Total", meses[7]);
            dataset.setValue(valor_agosto, "Lucro", meses[7]);
            
            dataset.setValue(setembro_orcamento, "Orçamento", meses[8]);
            dataset.setValue(setembro_os, "Ordem de serviço", meses[8]);
            dataset.setValue(setembro_orcamento + setembro_os , "Total", meses[8]);
            dataset.setValue(valor_setembro, "Lucro", meses[8]);
            
            dataset.setValue(outubro_orcamento, "Orçamento", meses[9]);
            dataset.setValue(outubro_os, "Ordem de serviço", meses[9]);
            dataset.setValue(outubro_orcamento + outubro_os , "Total", meses[9]);
            dataset.setValue(valor_outubro, "Lucro", meses[9]);
            
            dataset.setValue(novembro_orcamento, "Orçamento", meses[10]);
            dataset.setValue(novembro_os, "Ordem de serviço", meses[10]);
            dataset.setValue(novembro_orcamento + novembro_os , "Total", meses[10]);
            dataset.setValue(valor_novembro, "Lucro", meses[10]);
            
            dataset.setValue(dezembro_orcamento, "Orçamento", meses[11]);
            dataset.setValue(dezembro_os, "Ordem de serviço", meses[11]);
            dataset.setValue(dezembro_orcamento + dezembro_os , "Total", meses[11]);
            dataset.setValue(valor_dezembro, "Lucro", meses[11]);
            
            JFreeChart chart = ChartFactory.createBarChart("Orçamentos e Ordem de serviços", "Meses", "Quantidade", dataset, PlotOrientation.VERTICAL,               false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLACK);
             ChartFrame frame = new ChartFrame("Gráfico de barras", chart);
            frame.setVisible(true);
            frame.setSize(498,350);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
       
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
                rs = pst.executeQuery();
                while(rs.next()) {
                    String type = rs.getString(11);
                    int mees = rs.getInt(12);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        combMes = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        combOrg = new javax.swing.JComboBox<>();
        btnGra = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle(" Relatório");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        combMes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ano Todo", "Bimestral", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Selecione o periodo");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Organizar por: ");

        combOrg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combOrg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tudo", "Orçamento", "Ordem de serviços", "Lucro" }));

        btnGra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hol/es/manotec/icones/1468383182_icon-67-document-graph.png"))); // NOI18N
        btnGra.setToolTipText("Mostrar gráfico");
        btnGra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combOrg, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combMes, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGra, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combOrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(btnGra, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(304, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(277, 277, 277))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 441, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraActionPerformed
        // TODO add your handling code here:
       
        if(combMes.getSelectedItem().toString().equals("Agosto")){
            if(combOrg.getSelectedItem().toString().equals("Tudo") && combMes.getSelectedItem().toString().equals("Agosto")){
                agostoTudo();
                System.out.println("teste");
            }else if(combOrg.getSelectedItem().toString().equals("Lucro")) {
                agostoLucro();
            }else if(combOrg.getSelectedItem().toString().equals("Ordem de serviços")){
                agostoOs();
            }else if(combOrg.getSelectedItem().toString().equals("Orçamento")){
                agosto();
            }
        }
        if(combMes.getSelectedItem().toString().equals("Ano Todo")) {
            relatorio_anual();
            System.out.println("uadhuasdhuashduashduashd");
        }else if(combMes.getSelectedItem().toString().equals("Janeiro") && combOrg.getSelectedItem().toString().equals("Ordem de serviços")) {
            janeiroOs();
        }else if(combMes.getSelectedItem().toString().equals("Janeiro") && combOrg.getSelectedItem().toString().equals("Tudo")) {
            janeiroTudo();
        }else if(combMes.getSelectedItem().toString().equals("Janeiro") && combOrg.getSelectedItem().toString().equals("Lucro")){
            janeiroLucro();
        }else if(combMes.getSelectedItem().toString().equals("Janeiro") && combOrg.getSelectedItem().toString().equals("Orçamento")) {
            janeiro();
        }else if(combMes.getSelectedItem().toString().equals("Fevereiro") && combOrg.getSelectedItem().toString().equals("Ordem de serviços")) {
            fevereiroOs();
        }else if(combMes.getSelectedItem().toString().equals("Fevereiro") && combOrg.getSelectedItem().toString().equals("Tudo")) {
            fevereiroTudo();
        }else if(combMes.getSelectedItem().toString().equals("Fevereiro") && combOrg.getSelectedItem().toString().equals("Lucro")){
            fevereiroLucro();
        }else if(combMes.getSelectedItem().toString().equals("Fevereiro") && combOrg.getSelectedItem().toString().equals("Orçamento")){
            fevereiro();
        }else if(combMes.getSelectedItem().toString().equals("Março") && combOrg.getSelectedItem().toString().equals("Ordem de seviços")){
            marco();
        }else if(combMes.getSelectedItem().toString().equals("Março") && combOrg.getSelectedItem().toString().equals("Tudo")){
            marcoTudo();
        }else if(combMes.getSelectedItem().toString().equals("Março") && combOrg.getSelectedItem().toString().equals("Lucro")){
            marcoLucro();
        }else if(combMes.getSelectedItem().toString().equals("Março") && combOrg.getSelectedItem().toString().equals("Orçamento")){
            marco();
        }else if(combMes.getSelectedItem().toString().equals("Março") && combOrg.getSelectedItem().toString().equals("Ordem de serviços")){
            marcoOs();
        }else if(combMes.getSelectedItem().toString().equals("Abril") && combOrg.getSelectedItem().toString().equals("Tudo")){
            abrilTudo();
        }else if(combMes.getSelectedItem().toString().equals("Abril") && combOrg.getSelectedItem().toString().equals("Orçamento")){
            abril();
        }else if(combMes.getSelectedItem().toString().equals("Abril") && combOrg.getSelectedItem().toString().equals("Ordem de serviços")) {
            abrilOs();
        }else if(combMes.getSelectedItem().toString().equals("Abril") && combOrg.getSelectedItem().toString().equals("Lucro")){
            abrilLucro();
        }else if(combMes.getSelectedItem().toString().equals("Maio") && combOrg.getSelectedItem().toString().equals("Tudo")){
            maioTudo();
        }else if(combMes.getSelectedItem().toString().equals("Maio") && combOrg.getSelectedItem().toString().equals("Lucro")){
            maioLucro();
        }else if(combMes.getSelectedItem().toString().equals("Maio") && combOrg.getSelectedItem().toString().equals("Ordem de serviços")){
            maioOs();
        }else if(combMes.getSelectedItem().toString().equals("Maio") && combOrg.getSelectedItem().toString().equals("Orçamento")){
            maio();
        }else if(combMes.getSelectedItem().toString().equals("Junho") && combOrg.getSelectedItem().toString().equals("Orçamento")){
            junho();
        }else if(combMes.getSelectedItem().toString().equals("Junho") && combOrg.getSelectedItem().toString().equals("Ordem de serviços")){
            junhoOs();
        }else if(combMes.getSelectedItem().toString().equals("Junho") && combOrg.getSelectedItem().toString().equals("Lucro")){
            junhoLucro();
        }else if(combMes.getSelectedItem().toString().equals("Junho") && combOrg.getSelectedItem().toString().equals("Tudo")){
            junhoTudo();        
        }else if(combMes.getSelectedItem().toString().equals("Julho") && combOrg.getSelectedItem().toString().equals("Tudo")){
            julhoTudo();
        }else if(combMes.getSelectedItem().toString().equals("Julho") && combOrg.getSelectedItem().toString().equals("Orçamento")){
            julho();
        }else if(combMes.getSelectedItem().toString().equals("Julho") && combOrg.getSelectedItem().toString().equals("Lucro")){
            julhoLucro();
        }else if(combMes.getSelectedItem().toString().equals("Julho") && combOrg.getSelectedItem().toString().equals("Ordem de serviços")){
            julhoOs();
        }
    }//GEN-LAST:event_btnGraActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGra;
    private javax.swing.JComboBox<String> combMes;
    private javax.swing.JComboBox<String> combOrg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
