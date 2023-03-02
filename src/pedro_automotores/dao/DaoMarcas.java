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

/**
 *
 * @author fernando.oliveira
 */
public class DaoMarcas {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rst = null;

    //INSERT
    public boolean inserir(ModelMarcas marca) {
        boolean verifica = true;
        String sql = "insert into marca (nome_marca) values (?)";

        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, marca.getMarca());
            verifica = pst.execute();
            System.out.print("aki=>" + verifica);

        } catch (Exception e) {
            System.out.print(e);
        }

        return verifica;
    }

    //LIST
    public List listar() {
        String sql = "select * from marca";
        List<ModelMarcas> lista = new ArrayList<>();

        try {
            conexao = Conexao.conector();
            pst = conexao.prepareStatement(sql);
            rst = pst.executeQuery();

            while (rst.next()) {
                ModelMarcas marca = new ModelMarcas();
                marca.setId(rst.getInt("idMarca"));
                marca.setMarca(rst.getString("nome_marca"));
                lista.add(marca);
            }
            conexao.close();
        } catch (Exception e) {
            System.out.print(e);
        }
        return lista;
    }

    //RETORNA ID MARCA
    public int returnId(String nome) throws SQLException {

        try {
            conexao = Conexao.conector();
            String sql = "select idMarca from marca where nome_marca = ?";
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome);
            rst = pst.executeQuery();
            rst.next();
            int id = rst.getInt("idMarca");
            //  System.out.print("chegou"+id);
            return id;

        } catch (Exception e) {
            System.out.print(e);
        }
        return 0;
    }

    public String returnaMarcaPeloId(int id) {

        try {
            conexao = Conexao.conector();
            String sql = "select nome_marca from marca where idMarca = ?";
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, id);
            rst = pst.executeQuery();
            rst.next();
            String nome = rst.getString("nome_marca");
            //  System.out.print("chegou"+id);
            return nome;
        } catch (Exception e) {
        }

        return null;
    }

}
