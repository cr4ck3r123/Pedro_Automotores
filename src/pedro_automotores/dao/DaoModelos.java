/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro_automotores.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pedro_automotores.model.ModelMarcas;
import pedro_automotores.model.ModelModelos;

/**
 *
 * @author fernando.oliveira
 */
public class DaoModelos {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rst = null;

    
    //INSERT
    public boolean inserir(ModelMarcas modelo) {
        boolean verifica = true;
        String sql = "insert into modelo (nome_marca) values (?)";

        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, modelo.getMarca());
            verifica = pst.execute();
            System.out.print("aki=>" + verifica);

        } catch (Exception e) {
            System.out.print(e);
        }

        return verifica;
    }
    
      //LIST
    public List listar(int id) {       
        String sql = "select * from modelo where id_Marca = ?";
        List<ModelModelos> lista = new ArrayList<>();

        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
             pst.setInt(1, id);
            rst = pst.executeQuery();

            while (rst.next()) {
                ModelModelos modelo = new ModelModelos();
                modelo.setIdmodelo(rst.getInt("idmodelo"));
                modelo.setId_marca(rst.getInt("id_marca"));
                modelo.setNome_modelo(rst.getString("nome_modelo"));
                lista.add(modelo);
            }
            conexao.close();
        } catch (Exception e) {
            System.out.print(e);
        }
        return lista;
    } 
    
         //RETORNA ID MODELO
    public int returnId(String nome) throws SQLException {       
              
        try {
           conexao = Conexao.conector();
           String sql = "select idmodelo from modelo where nome_modelo = ?";           
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            rst = pst.executeQuery();
            rst.next();
            int id = rst.getInt("idmodelo");
            System.out.print("chegou"+id);
            return id;
           
        } catch (Exception e) {
            System.out.print(e);
        }
        return 0;
    }
    
        public String returnaModeloPeloId(int id) {

        try {
            conexao = Conexao.conector();
            String sql = "select nome_modelo from modelo where idmodelo = ?";
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, id);
            rst = pst.executeQuery();
            rst.next();
            String nome = rst.getString("nome_modelo");
            //  System.out.print("chegou"+id);
            return nome;
        } catch (Exception e) {
        }

        return null;
    }
}
