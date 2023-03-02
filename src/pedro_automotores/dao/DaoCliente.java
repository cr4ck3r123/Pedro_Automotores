/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro_automotores.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import pedro_automotores.model.ModelCliente;
import pedro_automotores.model.ModelVeiculo;
import pedro_automotores.model.ModelViaCep;

/**
 *
 * @author fernando.oliveira
 */
public class DaoCliente {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rst = null;

    //INSERT
    public void inserir(ModelCliente cliente) {

        String sql = "insert into cliente (nome, cpf, email, tel_casa, cel_whatssap, id_endereco) values (?,?,?,?,?,?)";

        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getCpf());
            pst.setString(3, cliente.getEmail());
            pst.setString(4, cliente.getTel_casa());
            pst.setString(5, cliente.getCel_whatssap());
            pst.setInt(6, cliente.getId_endereco());

            pst.execute();

        } catch (SQLException e) {
            System.out.print(e);
        }

    }

    //LIST
    public ArrayList<ModelCliente> list() {

        String sql = "select * from cliente";
        ArrayList<ModelCliente> lista = new ArrayList();

        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
            pst.execute();

            while (pst.getResultSet().next()) {
                ModelCliente cliente = new ModelCliente();
                cliente.setId(pst.getResultSet().getInt(1));
                cliente.setNome(pst.getResultSet().getString(2));
                cliente.setCpf(pst.getResultSet().getString(3));
                cliente.setEmail(pst.getResultSet().getString(4));
                cliente.setTel_casa(pst.getResultSet().getString(5));
                cliente.setCel_whatssap(pst.getResultSet().getString(6));
                lista.add(cliente);
            }

            return lista;
        } catch (Exception e) {
            System.out.print(e);
        }
        return null;
    }

    //LIST FROM ID
    public ModelCliente select(int id) throws ParseException {
        conexao = Conexao.conector();
        ModelCliente cliente = new ModelCliente();

        String sql = "select * from cliente \n"
                + "inner join endereco on cliente.id_endereco = endereco.idendereco\n"
                + "where idcliente = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();

            while (pst.getResultSet().next()) {
                cliente.setId(pst.getResultSet().getInt(1));
                cliente.setNome(pst.getResultSet().getString(2));
                cliente.setCpf(pst.getResultSet().getString(3));
                cliente.setEmail(pst.getResultSet().getString(4));
                cliente.setTel_casa(pst.getResultSet().getString(5));
                cliente.setCel_whatssap(pst.getResultSet().getString(6));
                cliente.setId_endereco(pst.getResultSet().getInt(8));
                cliente.setLogradouro(pst.getResultSet().getString(9));
                cliente.setNumero(pst.getResultSet().getString(10));
                cliente.setBairro(pst.getResultSet().getString(11));
                cliente.setLocalidade(pst.getResultSet().getString(12));
                cliente.setCep(pst.getResultSet().getString(13));
                cliente.setComplemento(pst.getResultSet().getString(16));
            }
            return cliente;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public void update(ModelCliente cliente) {

        String sql1 = "UPDATE cliente SET nome = ?, cpf = ?, email = ?, tel_casa = ?, cel_whatssap = ?  WHERE idcliente = ?";
    

        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql1);
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getCpf());
            pst.setString(3, cliente.getEmail());
            pst.setString(4, cliente.getTel_casa());
            pst.setString(5, cliente.getCel_whatssap());
            pst.setInt(6, cliente.getId());
            pst.execute();
             
           
        } catch (SQLException e) {
            System.out.print(e);
        }

    }

    //PEGA ULTIMO ID
    public int returnIdMax() {
        try {
            conexao = Conexao.conector();
            String sql = "select max(idcliente) as idcliente from cliente";
            pst = conexao.prepareStatement(sql);
            rst = pst.executeQuery();
            rst.next();
            int id = rst.getInt("idcliente");
            //  System.out.print("chegou"+id);
            return id;

        } catch (Exception e) {
            System.out.print(e);
        }

        return 0;
    }

    public int validaCpf(String cpf){
        
         try {
            conexao = Conexao.conector();
            String sql = "SELECT cpf FROM `cliente` WHERE cpf = ?";
            pst = conexao.prepareStatement(sql);
            pst.setString(1, cpf);
            rst = pst.executeQuery();
            rst.next();
            String result = rst.getString("cpf");
            
            if(!result.isEmpty()){
               return 1;
            }
         conexao.close();
        } catch (Exception e) {
            System.out.print(e);
            return 0;
        }
    return 0;
    }
    
    
    //
        //Metodo para pesquisar clientes pelo nome com filtro
 //PESQUISAR USUARIO
    public ResultSet pesquisar_usuario(String pesquisar) {
        String sql = "select idcliente, nome, cel_whatssap from cliente where nome like ?";
        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, pesquisar + "%");
            rst = pst.executeQuery();
            //a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
            return rst;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
}
