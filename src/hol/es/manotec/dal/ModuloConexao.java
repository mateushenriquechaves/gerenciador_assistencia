/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hol.es.manotec.dal;
import java.sql.*;

/**
 *
 * @author mateu
 */
public class ModuloConexao {
    // Método responsável por estabelecer a conexão com o banco de dados
    
    public static Connection conector() {
        java.sql.Connection conexao = null;
        // Conectando com o driver
        String driver = "com.mysql.jdbc.Driver";
        // Informações para conexão com o banco
        String url = "jdbc:mysql://localhost:3306/dbos";
        String user = "root";
        String password = "";
        
        // Estabelecendo a conexão com o banco
        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            // Esclarecendo o erro
           // System.out.println(e);
            return null;
        }
    }
}
