/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro_automotores.model;

/**
 *
 * @author fernando.oliveira
 */
public class ModelCliente extends ModelViaCep {
    int id;
    String nome;
    String cpf;
    String email;
    String tel_casa;
    String cel_whatssap;
    int id_veiculo;
    int id_endereco;

    public ModelCliente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel_casa() {
        return tel_casa;
    }

    public void setTel_casa(String tel_casa) {
        this.tel_casa = tel_casa;
    }

    public String getCel_whatssap() {
        return cel_whatssap;
    }

    public void setCel_whatssap(String cel_whatssap) {
        this.cel_whatssap = cel_whatssap;
    }

    public int getId_veiculo() {
        return id_veiculo;
    }

    public void setId_veiculo(int id_veiculo) {
        this.id_veiculo = id_veiculo;
    }

    public int getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(int id_endereco) {
        this.id_endereco = id_endereco;
    }
    
    
}
