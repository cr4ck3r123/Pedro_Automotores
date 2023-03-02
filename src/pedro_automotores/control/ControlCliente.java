/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro_automotores.control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JOptionPane;
import pedro_automotores.dao.DaoCliente;
import pedro_automotores.dao.DaoEndereco;
import pedro_automotores.dao.DaoMarcas;
import pedro_automotores.dao.DaoModelos;
import pedro_automotores.dao.DaoVeiculo;
import pedro_automotores.model.ModelCliente;
import pedro_automotores.model.ModelVeiculo;
import pedro_automotores.model.ModelViaCep;

/**
 *
 * @author fernando.oliveira
 */
public class ControlCliente {

    //VALIDA DADOS INSERÇÃO CLIENTE
    public void validaCliente(ModelCliente cliente, ModelViaCep endereco, ModelVeiculo veiculo, String nomeMarca, String nomeModelo) throws SQLException {

        if (cliente.getNome().isEmpty() || cliente.getEmail().isEmpty() || cliente.getTel_casa().isEmpty() || cliente.getCel_whatssap().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Atenção, é necessário preencher todos os campos", "Atenção", 0);
        } else if (cliente.getCpf().equals("   .   .   .  ")) {
            JOptionPane.showMessageDialog(null, "Atenção, é necessário preencher o campo CPF", "Atenção", 0);
        } else if (endereco.getLogradouro().isEmpty() || endereco.getNumero().isEmpty() || endereco.getBairro().isEmpty() || endereco.getCep().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Atenção, é necessário preencher todos os campos", "Atenção", 0);
        } else if (veiculo.getPlaca().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Atenção, é necessário preencher a placa do veiculo", "Atenção", 0);
        } else {
            DaoEndereco daoEnd = new DaoEndereco();
            daoEnd.inserir(endereco);
            DaoCliente daoCli = new DaoCliente();
            cliente.setId_endereco(daoEnd.returnIdMax());
            daoCli.inserir(cliente);
            DaoVeiculo daoVeiculo = new DaoVeiculo();
            DaoMarcas dao = new DaoMarcas();
            DaoModelos daoM = new DaoModelos();
            veiculo.setId_marca(dao.returnId(nomeMarca));
            veiculo.setId_modelo(daoM.returnId(nomeModelo));
            veiculo.setIdcliente(daoCli.returnIdMax());
            daoVeiculo.inserir(veiculo);
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
        }
    }

    //PESQUISAR CLIENTE
      public ResultSet controlSelect(String usuario) throws ParseException, SQLException{         
        DaoCliente dao = new DaoCliente();     
        return  dao.pesquisar_usuario(usuario);
    }
    
      
    //VALIDA DADOS UPDATE CLIENTE
    public void validaClienteUpDATE(ModelCliente cliente, ModelViaCep endereco, ModelVeiculo veiculo, String nomeMarca, String nomeModelo) throws SQLException {

        if (cliente.getNome().isEmpty() || cliente.getEmail().isEmpty() || cliente.getTel_casa().isEmpty() || cliente.getCel_whatssap().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Atenção, é necessário preencher todos os campos", "Atenção", 0);
        } else if (cliente.getCpf().equals("   .   .   .  ")) {
            JOptionPane.showMessageDialog(null, "Atenção, é necessário preencher o campo CPF", "Atenção", 0);
        } else if (endereco.getLogradouro().isEmpty() || endereco.getNumero().isEmpty() || endereco.getBairro().isEmpty() || endereco.getCep().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Atenção, é necessário preencher todos os campos", "Atenção", 0);
        } else if (veiculo.getPlaca().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Atenção, é necessário preencher a placa do veiculo", "Atenção", 0);
        } else {
            DaoEndereco daoEnd = new DaoEndereco();
            daoEnd.inserir(endereco);
            DaoCliente daoCli = new DaoCliente();
            cliente.setId_endereco(daoEnd.returnIdMax());
            daoCli.inserir(cliente);
            DaoVeiculo daoVeiculo = new DaoVeiculo();
            DaoMarcas dao = new DaoMarcas();
            DaoModelos daoM = new DaoModelos();
            veiculo.setId_marca(dao.returnId(nomeMarca));
            veiculo.setId_modelo(daoM.returnId(nomeModelo));
            veiculo.setIdcliente(daoCli.returnIdMax());
            daoVeiculo.inserir(veiculo);
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
        }
    }
}
