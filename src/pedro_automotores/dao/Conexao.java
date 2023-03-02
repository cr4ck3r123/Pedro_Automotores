/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro_automotores.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class Conexao {
    
    
    private boolean status = false;
    private String mensagem = "";   //variavel que vai informar o status da conexao
    private Connection con = null;  //variavel para conexao
    private String servidor = "localhost";
    private String nomeDoBanco = "oficinamecanica";
    private String usuario = "root";
    private String senha = "Nando1287@";
    
    public Conexao(){}
  
 public static Connection conector() {
        java.sql.Connection conexao = null;
        String servidor = "localhost";
        String nomeDoBanco = "oficinamecanica";
        String usuario = "root";
        String senha = "Nando1287@";
        //A LINHA ABAIXO CHAMA O DRIVER 
        String driver = "com.mysql.jdbc.Driver";
        // ARMAZENANDO INFORMAÇÕES REFERENTES AO BANCO DE DADOS
        String url = "jdbc:mysql://" + servidor + "/" + nomeDoBanco;
      
        
        //ESTABELECENDO A CONEXAO COM O BANCO DE DADOS
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,usuario, senha);
            System.out.println("Conectado com sucesso "+conexao);
            return conexao;
            
        } catch (Exception e) {
            System.out.println("erro "+e);
           return null; 
        }
        
    }
    
    public Connection conectar() throws SQLException{
        java.sql.Connection conexao = null;
        
        try {
                   
            //Driver do PostgreSQL
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            //local do banco, nome do banco, usuario e senha
            String url = "jdbc:mysql://" + servidor + "/" + nomeDoBanco;            
            conexao = DriverManager.getConnection(url,usuario, senha);
            //se ocorrer tudo bem, ou seja, se conectar a linha a segui é executada
            this.status = true;
            System.out.println("Conectado com sucesso "+conexao);
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("erro "+e);
        }
        return conexao;
    }

    
    
}
