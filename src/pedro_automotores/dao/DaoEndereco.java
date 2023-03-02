/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro_automotores.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pedro_automotores.model.ModelViaCep;

/**
 *
 * @author fernando.oliveira
 */
public class DaoEndereco {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rst = null;

    //INSERT
    public void inserir(ModelViaCep endereco) {

        String sql = "insert into endereco (rua, numero, bairro, cidade, cep, complemento) values (?,?,?,?,?,?)";

        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, endereco.getLogradouro());
            pst.setString(2, endereco.getNumero());
            pst.setString(3, endereco.getBairro());
            pst.setString(4, endereco.getLocalidade());
            pst.setString(5, endereco.getCep());
            pst.setString(6, endereco.getComplemento());
            
            pst.execute();
            conexao.close();
        } catch (SQLException e) {
            System.out.print(e);
        }

    }

    
    //PEGA ULTIMO ID
    public int returnIdMax(){
        try {
           conexao = Conexao.conector();
           String sql = "select max(idendereco) as idendereco from endereco";           
            pst = conexao.prepareStatement(sql);
            rst = pst.executeQuery();
            rst.next();
            int id = rst.getInt("idendereco");
          //  System.out.print("chegou"+id);
            return id;
           
        } catch (Exception e) {
            System.out.print(e);
        }
        
        return 0;
    }
    
    //UPDATE ENDEREÇO
        public void update(ModelViaCep endereco, int idendereco) {

          String sql = "UPDATE endereco SET rua = ?, numero = ?, bairro = ?, cidade = ?, cep = ?, complemento = ?  WHERE idendereco = ?";

        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
             //UPDATE ENDERECO
    
            pst.setString(1, endereco.getLogradouro());
            pst.setString(2, endereco.getNumero());
            pst.setString(3, endereco.getBairro());
            pst.setString(4, endereco.getLocalidade());
            pst.setString(5, endereco.getCep());
            pst.setString(6, endereco.getComplemento());
            pst.setInt(7, idendereco);
            pst.execute();        
            

        } catch (SQLException e) {
            System.out.print(e);
        }

    }
        
            //PEGA ID DO ENDEREÇO DO USUARIO
    public int returnIdUser(int idcliente){
        try {
           conexao = Conexao.conector();
           String sql = "select id_endereco from cliente where idcliente = ?";           
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idcliente);
            rst = pst.executeQuery();
            rst.next();
            int id = rst.getInt("id_endereco");
          //  System.out.print("chegou"+id);
            return id;
           
        } catch (Exception e) {
            System.out.print(e);
        }
        
        return 0;
    }
        
}
