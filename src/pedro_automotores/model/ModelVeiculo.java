/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro_automotores.model;

/**
 *
 * @author fernando.oliveira
 */
public class ModelVeiculo extends ModelMarcas {
    
    int idveiculo;
    String cor;
    int ano;
    String placa;
    Double km;
    String img_veiculo;
    String descricao;
    int id_modelo;
    int id_marca;
    int idcliente;

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }    
    

    public ModelVeiculo() {
    }

    public int getIdveiculo() {
        return idveiculo;
    }

    public void setIdveiculo(int idveiculo) {
        this.idveiculo = idveiculo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Double getKm() {
        return km;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public String getImg_veiculo() {
        return img_veiculo;
    }

    public void setImg_veiculo(String img_veiculo) {
        this.img_veiculo = img_veiculo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId_modelo() {
        return id_modelo;
    }

    public void setId_modelo(int id_modelo) {
        this.id_modelo = id_modelo;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }
    
    
    
    
}
