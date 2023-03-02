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
import pedro_automotores.model.ModelVeiculo;
import pedro_automotores.model.ModelViaCep;

/**
 *
 * @author fernando.oliveira
 */
public class DaoVeiculo {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rst = null;

    public void inserir(ModelVeiculo veiculo) {

        String sql = "insert into veiculo (cor, ano, placa, km, img_veiculo, descricao, idmodelo, idmarca, idcliente) values (?,?,?,?,?,?,?,?,?)";

        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, veiculo.getCor());
            pst.setInt(2, veiculo.getAno());
            pst.setString(3, veiculo.getPlaca());
            pst.setDouble(4, veiculo.getKm());
            pst.setString(5, veiculo.getImg_veiculo());
            pst.setString(6, veiculo.getDescricao());
            pst.setInt(7, veiculo.getId_modelo());
            pst.setInt(8, veiculo.getId_marca());
            pst.setInt(9, veiculo.getIdcliente());
            pst.execute();

        } catch (Exception e) {
            System.out.print(e);
        }

    }

    //PEGA ULTIMO ID
    public int returnIdMax() {
        try {
            conexao = Conexao.conector();
            String sql = "select max(idveiculo) as idveiculo from veiculo";
            pst = conexao.prepareStatement(sql);
            rst = pst.executeQuery();
            rst.next();
            int id = rst.getInt("idveiculo");
            //  System.out.print("chegou"+id);
            return id;

        } catch (Exception e) {
            System.out.print(e);
        }

        return 0;
    }

    public ModelVeiculo dadosVeiculo(int id) throws SQLException {
        ModelVeiculo veiculo = new ModelVeiculo();
        conexao = Conexao.conector();
        String sql = "SELECT idveiculo, cor, ano, placa, km, img_veiculo, descricao, veiculo.idmodelo, veiculo.idmarca\n"
                + "FROM `veiculo` "
                + "INNER JOIN marca on marca.idMarca = veiculo.idmarca\n"
                + "INNER JOIN modelo on modelo.idmodelo = veiculo.idmodelo\n"
                + "WHERE idcliente = ?";
        
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, id);
        pst.execute();

        while (pst.getResultSet().next()) {
            veiculo.setIdveiculo(pst.getResultSetType());
            veiculo.setCor(pst.getResultSet().getString(2));
            veiculo.setAno(pst.getResultSet().getInt(3));
            veiculo.setPlaca(pst.getResultSet().getString(4));
            veiculo.setKm(pst.getResultSet().getDouble(5));
            veiculo.setImg_veiculo(pst.getResultSet().getString(6));
            veiculo.setDescricao(pst.getResultSet().getString(7));
            veiculo.setId_modelo(pst.getResultSet().getInt(8));
            veiculo.setId_marca(pst.getResultSet().getInt(9));
        }
      //   conexao.close();
        return veiculo;
    }
    
    //SETAR VEICULO POR ID
   public ModelVeiculo dadosVeiculoId(int id) throws SQLException {
        ModelVeiculo veiculo = new ModelVeiculo();
        conexao = Conexao.conector();
        String sql = "select marca.nome_marca, modelo.nome_modelo, veiculo.cor, veiculo.ano, veiculo.img_veiculo, veiculo.descricao, veiculo.placa, veiculo.km from veiculo \n"
                    + "INNER JOIN marca on veiculo.idmarca = marca.idmarca\n"
                    + "INNER JOIN modelo on veiculo.idmodelo = modelo.idmodelo \n"
                    + "where idveiculo = ?";
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, id);
        pst.execute();

        while (pst.getResultSet().next()) {
            veiculo.setMarca(pst.getResultSet().getString(1));
            veiculo.setNome_modelo(pst.getResultSet().getString(2));
            veiculo.setCor(pst.getResultSet().getString(3));
            veiculo.setAno(pst.getResultSet().getInt(4));
            veiculo.setImg_veiculo(pst.getResultSet().getString(5));
            veiculo.setDescricao(pst.getResultSet().getString(6));
            veiculo.setPlaca(pst.getResultSet().getString(7));
            veiculo.setKm(pst.getResultSet().getDouble(8));
        }
        return veiculo;
    }

    //LISTA VECULOS DO CLIENTE
    public ArrayList<ModelVeiculo> veiculos(int id) {
        try {

            ArrayList<ModelVeiculo> lista = new ArrayList();
            conexao = Conexao.conector();
            String sql = "select veiculo.idveiculo, marca.nome_marca, modelo.nome_modelo, veiculo.cor, veiculo.ano, veiculo.img_veiculo, veiculo.descricao, veiculo.placa from veiculo \n"
                    + "INNER JOIN marca on veiculo.idmarca = marca.idmarca\n"
                    + "INNER JOIN modelo on veiculo.idmodelo = modelo.idmodelo \n"
                    + "where idcliente = ? ORDER BY veiculo.idveiculo DESC";
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();

            //  System.out.print("chegou"+id);
            while (pst.getResultSet().next()) {
                ModelVeiculo veiculo = new ModelVeiculo();
                veiculo.setIdveiculo(pst.getResultSet().getInt(1));
                veiculo.setMarca(pst.getResultSet().getString(2));
                veiculo.setNome_modelo(pst.getResultSet().getString(3));
                veiculo.setCor(pst.getResultSet().getString(4));
                veiculo.setAno(pst.getResultSet().getInt(5));
                veiculo.setImg_veiculo(pst.getResultSet().getString(6));
                veiculo.setDescricao(pst.getResultSet().getString(7));
                veiculo.setPlaca(pst.getResultSet().getString(8));
                lista.add(veiculo);
            }
            return lista;

        } catch (SQLException e) {
            System.out.print(e);
        }

        return null;
    }
    
    //CONSULTA PLACA 
    public int consultaPlaca(String placa){
          try {
            conexao = Conexao.conector();
            String sql = "SELECT placa FROM `veiculo` WHERE placa = ?";
            pst = conexao.prepareStatement(sql);
            pst.setString(1, placa);
            rst = pst.executeQuery();
            rst.next();
            String result = rst.getString("placa");
            
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
 
    //UPDATE VEICULO
        public void update(ModelVeiculo veiculo) {

        String sql = "UPDATE veiculo SET cor = ?, ano = ?, placa = ?, km = ?, img_veiculo = ?, descricao = ?, idmodelo = ?, idmarca = ?  WHERE idveiculo = ?";

        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, veiculo.getCor());
            pst.setInt(2, veiculo.getAno());
            pst.setString(3, veiculo.getPlaca());
            pst.setDouble(4, veiculo.getKm());
            pst.setString(5, veiculo.getImg_veiculo());
            pst.setString(6, veiculo.getDescricao());
            pst.setInt(7, veiculo.getId_modelo());
            pst.setInt(8, veiculo.getId_marca());
            //pst.setInt(9, veiculo.getIdcliente());
            pst.setInt(9, veiculo.getIdveiculo());
            pst.execute();

        } catch (SQLException e) {
            System.out.print(e);
        }

    }
    
}
