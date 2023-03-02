/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pedro_automotores.view;

import com.google.gson.Gson;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import org.apache.commons.io.FileUtils;
import pedro_automotores.control.ControlCliente;
import pedro_automotores.dao.DaoCliente;
import pedro_automotores.dao.DaoEndereco;
import pedro_automotores.model.ModelViaCep;
import pedro_automotores.dao.DaoMarcas;
import pedro_automotores.dao.DaoModelos;
import pedro_automotores.dao.DaoVeiculo;
import pedro_automotores.model.ModelCliente;
import pedro_automotores.model.ModelMarcas;
import pedro_automotores.model.ModelModelos;
import pedro_automotores.model.ModelVeiculo;
import pedro_automotores.service.HttpExemplo;

/**
 *
 * @author fernando.oliveira
 */
public class ViewClient extends javax.swing.JFrame {

    private static ViewClient instance;

    public static ViewClient getInstance() {
        try {
            if (instance == null) {
                instance = new ViewClient();
              
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewClient.class.getName()).log(Level.SEVERE, null, ex);

        }
        return instance;
    }

    
    JFileChooser jfc;
    File file;

    /**
     * Creates new form ViewClient
     */
    public ViewClient() throws SQLException {
        initComponents();
        jTabbedPane1.setEnabledAt(1, false);
        listComboBoxMarcas();
        carregarCliente();
       
    }

    //SETAR CAMPOS
    private void setarCampos() throws ParseException, SQLException, IOException {
        int setar = tblCliente.getSelectedRow();
        DaoCliente dao = new DaoCliente();
        ModelCliente cliente = new ModelCliente();
        txtId.setText(tblCliente.getModel().getValueAt(setar, 0).toString());
        int id = Integer.parseInt(txtId.getText());
        cliente = dao.select(id);

        txtNome.setText(cliente.getNome());
        txtCpf.setText(cliente.getCpf());
        txtEmail.setText(cliente.getEmail());
        txtTelefone.setText(cliente.getTel_casa());
        txtCelular.setText(cliente.getCel_whatssap());
        txtRua.setText(cliente.getLogradouro());
        txtNumero.setText(cliente.getNumero());
        txtBairro.setText(cliente.getBairro());
        txtCidade.setText(cliente.getLocalidade());
        txtComplemento.setText(cliente.getComplemento());
        txtCep.setText(cliente.getCep());

        setaCamposClienteEndereco();

        DaoVeiculo daoVeiculo = new DaoVeiculo();
        DaoMarcas daoMarca = new DaoMarcas();
        DaoModelos daoModelo = new DaoModelos();
        ModelVeiculo veiculo = new ModelVeiculo();
        veiculo = daoVeiculo.dadosVeiculo(id);
        cbMarcas.setSelectedItem(daoMarca.returnaMarcaPeloId(veiculo.getId_marca()));
        cbModelos.setSelectedItem(daoModelo.returnaModeloPeloId(veiculo.getId_modelo()));
        cbAno.setSelectedItem(String.valueOf(veiculo.getAno()));
        txtCor.setText(veiculo.getCor());
        txtPlaca.setText(veiculo.getPlaca());
        txtKm.setText(veiculo.getKm().toString());
        descricao.setText(veiculo.getDescricao());

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        String path = new File(".").getCanonicalPath();
        Image image = toolkit.getImage(path + "/images/" + veiculo.getImg_veiculo());

        //                Image image=toolkit.getImage(getClass().getResource("/image/"+book.getImage()));
        Image imagedResized = image.getScaledInstance(200, 250, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(imagedResized);
        lblnmfile.setText(veiculo.getImg_veiculo());
        lblimage.setIcon(icon);

        listarVeiculos(id);
//        selecao();
    }

    //SETAR VEICULO
    public void setarVeiculo() throws SQLException, IOException {
        int setar = tblVeiculo.getSelectedRow();
        int id = Integer.parseInt(tblVeiculo.getModel().getValueAt(setar, 0).toString());
        DaoVeiculo daoVeiculo = new DaoVeiculo();
        ModelVeiculo veiculo = new ModelVeiculo();
        veiculo = daoVeiculo.dadosVeiculoId(id);
        cbMarcas.setSelectedItem(veiculo.getMarca());
        cbModelos.setSelectedItem(veiculo.getNome_modelo());
        cbAno.setSelectedItem(String.valueOf(veiculo.getAno()));
        txtCor.setText(veiculo.getCor());
        txtPlaca.setText(veiculo.getPlaca());
        txtKm.setText(veiculo.getKm().toString());
        descricao.setText(veiculo.getDescricao());
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        String path = new File(".").getCanonicalPath();
        Image image = toolkit.getImage(path + "/images/" + veiculo.getImg_veiculo());
        Image imagedResized = image.getScaledInstance(200, 250, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(imagedResized);
        lblnmfile.setText(veiculo.getImg_veiculo());
        lblimage.setIcon(icon);
    }

    //LISTA CLIENTES
    private void carregarCliente() throws SQLException {

        DaoCliente dao = new DaoCliente();
        ArrayList<ModelCliente> listaCliente = new ArrayList<>();

        listaCliente = dao.list();
        DefaultTableModel modelo = (DefaultTableModel) tblCliente.getModel();

        modelo.setNumRows(0);
        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = listaCliente.size();
        for (int i = 0; i < cont; i++) {
            modelo.addRow(new Object[]{
                listaCliente.get(i).getId(),
                listaCliente.get(i).getNome(),
                listaCliente.get(i).getCel_whatssap(),});

        }

    }

    //LIST JCOMBOBOX MODELOS
    public void listComboBoxModelo(String nome) throws SQLException {
        DaoMarcas daoM = new DaoMarcas();
        DaoModelos dao = new DaoModelos();
        int id = daoM.returnId(nome);
        List<ModelModelos> lista = dao.listar(id);
        //  lista = dao.listar(id);
        cbModelos.removeAll();

        for (ModelModelos modelo : lista) {
            cbModelos.addItem(modelo.getNome_modelo());
        }

    }

    //LIST JCOMBOBOX MARCAS
    public int listComboBoxMarcas() {

        DaoMarcas dao = new DaoMarcas();
        List<ModelMarcas> lista = dao.listar();
        lista = dao.listar();
        cbMarcas.removeAll();
        int id = 0;
        for (ModelMarcas marca : lista) {
            cbMarcas.addItem(marca.getMarca());
        }

        return id;
    }

    //ABRIR LINK
    public static void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //BUSCA ENDEREÇO
    public void buscaEndereco(String cep) throws Exception {
        HttpExemplo http = new HttpExemplo();
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        String json = http.sendGet(url);
//        if(json == "0"){
//             JOptionPane.showMessageDialog(null, "Atenção, este CPF não é valido!", "Atenção", 0);
//            System.out.printf("CPF INVALIDO "+ url);
//        }else{        
        ModelViaCep endereco = new Gson().fromJson(json, ModelViaCep.class);
        System.out.print(endereco.getBairro());
//        txtRua.setText(endereco.getLogradouro());
//        txtBairro.setText(endereco.getBairro());
//        txtCidade.setText(endereco.getLocalidade());
      //  }
        }

    //DESATIVA CAMPOS
    //DADOS
    public void ativaCamposClienteEndereco() {
        jpPesquisar.setEnabled(false);
        lbPesNome.setEnabled(false);
        txtPesquisar.setEnabled(false);
        btnPesquisar.setEnabled(false);
        tblCliente.enable(false);

        jpCliente.setEnabled(true);
        lbNome.setEnabled(true);
        txtNome.setEnabled(true);
        lbCpf.setEnabled(true);
        txtCpf.setEnabled(true);
        txtCpf.setEditable(true);
        lbEmail.setEnabled(true);
        txtEmail.setEnabled(true);
        lbTelefone.setEnabled(true);
        txtTelefone.setEnabled(true);
        lbCelular.setEnabled(true);
        txtCelular.setEnabled(true);
        jpEndereco.setEnabled(true);
        lbRua.setEnabled(true);
        txtRua.setEnabled(true);
        lbNumero.setEnabled(true);
        txtNumero.setEnabled(true);
        lbCep.setEnabled(true);
        txtCep.setEnabled(true);
        lbCidade.setEnabled(true);
        txtCidade.setEnabled(true);
        lbBairro.setEnabled(true);
        txtBairro.setEnabled(true);
        lbComplemento.setEnabled(true);
        txtComplemento.setEnabled(true);
        btnProximo.setEnabled(true);
        btnEditar.setEnabled(false);
        btnNovo.setEnabled(false);
        btnSalvar.setEnabled(true);
        btnaddVeiculo.setEnabled(false);
    }

    //DADOS
    public void setaCamposClienteEndereco() {
//        jpPesquisar.setEnabled(false);
//        lbPesNome.setEnabled(false);
//        txtPesquisar.setEnabled(false);
//        btnPesquisar.setEnabled(false);
//        tblCliente.enable(false);

        jpCliente.setEnabled(true);
        lbNome.setEnabled(true);
        txtNome.setEnabled(true);
        lbCpf.setEnabled(true);
        txtCpf.setEnabled(true);
        lbEmail.setEnabled(true);
        txtEmail.setEnabled(true);
        lbTelefone.setEnabled(true);
        txtTelefone.setEnabled(true);
        lbCelular.setEnabled(true);
        txtCelular.setEnabled(true);
        jpEndereco.setEnabled(true);
        lbRua.setEnabled(true);
        txtRua.setEnabled(true);
        lbNumero.setEnabled(true);
        txtNumero.setEnabled(true);
        lbCep.setEnabled(true);
        txtCep.setEnabled(true);
        lbCidade.setEnabled(true);
        txtCidade.setEnabled(true);
        lbBairro.setEnabled(true);
        txtBairro.setEnabled(true);
        lbComplemento.setEnabled(true);
        txtComplemento.setEnabled(true);
        btnProximo.setEnabled(true);
        btnEditar.setEnabled(true);
        btnNovo.setEnabled(false);
        btnSalvar.setEnabled(false);
    }

    //ATIVA CAMPOS
    public void DesativaCamposClienteEndereco() {
        jpPesquisar.setEnabled(true);
        lbPesNome.setEnabled(true);
        txtPesquisar.setEnabled(true);
        btnPesquisar.setEnabled(true);
        tblCliente.enable(true);

        jpCliente.setEnabled(false);
        lbNome.setEnabled(false);
        txtNome.setEnabled(false);
        lbCpf.setEnabled(false);
        txtCpf.setEnabled(false);
        lbEmail.setEnabled(false);
        txtEmail.setEnabled(false);
        lbTelefone.setEnabled(false);
        txtTelefone.setEnabled(false);
        lbCelular.setEnabled(false);
        txtCelular.setEnabled(false);
        jpEndereco.setEnabled(false);
        lbRua.setEnabled(false);
        txtRua.setEnabled(false);
        lbNumero.setEnabled(false);
        txtNumero.setEnabled(false);
        lbCep.setEnabled(false);
        txtCep.setEnabled(false);
        lbCidade.setEnabled(false);
        txtCidade.setEnabled(false);
        lbBairro.setEnabled(false);
        txtBairro.setEnabled(false);
        lbComplemento.setEnabled(false);
        txtComplemento.setEnabled(false);
        btnProximo.setEnabled(false);
        btnEditar.setEnabled(false);
        btnNovo.setEnabled(true);
        btnaddVeiculo.setEnabled(true);

    }

    //PROXIMA ETAPA
    public void proximo() {
        jTabbedPane1.setEnabledAt(1, true);
        jTabbedPane1.setEnabledAt(0, false);
        jTabbedPane1.setSelectedIndex(1);
        // btnSalvar.setEnabled(true);
    }

    //PROXIMA ETAPA
    public void back() {
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(0, true);
        jTabbedPane1.setSelectedIndex(0);
        // btnSalvar.setEnabled(true);
    }

    //SALVAR
    public void salvar(String nomeMarca, String nomeModelo) throws SQLException {
        DaoMarcas dao = new DaoMarcas();
        DaoModelos daoM = new DaoModelos();
        ModelVeiculo veiculo = new ModelVeiculo();
        DaoEndereco daoEnd = new DaoEndereco();
        ModelViaCep endereco = new ModelViaCep();
        ControlCliente controle = new ControlCliente();
        DaoCliente daoCli = new DaoCliente();

        endereco.setLogradouro(txtRua.getText().toUpperCase());
        endereco.setNumero(txtNumero.getText().toUpperCase());
        endereco.setCep(txtCep.getText().toUpperCase());
        endereco.setBairro(txtBairro.getText().toUpperCase());
        endereco.setLocalidade(txtCidade.getText().toUpperCase());
        endereco.setComplemento(txtComplemento.getText().toUpperCase());
         
        ModelCliente cliente = new ModelCliente();
        cliente.setNome(txtNome.getText().toUpperCase());
        cliente.setCpf(txtCpf.getText().toUpperCase());
        cliente.setEmail(txtEmail.getText());
        cliente.setTel_casa(txtTelefone.getText());
        cliente.setCel_whatssap(txtCelular.getText());
       
        veiculo.setCor(txtCor.getText().toUpperCase()); 
        veiculo.setAno(Integer.parseInt(cbAno.getSelectedItem().toString()));    
        veiculo.setPlaca(txtPlaca.getText().toUpperCase());
        veiculo.setKm(Double.parseDouble(txtKm.getText()));
        veiculo.setImg_veiculo(lblnmfile.getText());
        veiculo.setDescricao(descricao.getText().toUpperCase());
       
        controle.validaCliente(cliente, endereco, veiculo, nomeMarca, nomeModelo);
        
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(0, true);
        jTabbedPane1.setSelectedIndex(0);
        DesativaCamposClienteEndereco();

    }

    //UPDATE
    public void update(String nomeMarca, String nomeModelo) throws SQLException, ParseException {
        DaoMarcas dao = new DaoMarcas();
        DaoModelos daoM = new DaoModelos();
        DaoVeiculo daoVeiculo = new DaoVeiculo();
        ModelVeiculo veiculo = new ModelVeiculo();
        DaoEndereco daoEnd = new DaoEndereco();
        ModelViaCep endereco = new ModelViaCep();       
        
        endereco.setLogradouro(txtRua.getText().toUpperCase());
        endereco.setNumero(txtNumero.getText().toUpperCase());
        endereco.setCep(txtCep.getText().toUpperCase());
        endereco.setBairro(txtBairro.getText().toUpperCase());
        endereco.setLocalidade(txtCidade.getText().toUpperCase());
        endereco.setComplemento(txtComplemento.getText().toUpperCase());
        int idcliente = daoEnd.returnIdUser(Integer.parseInt(txtId.getText()));
        daoEnd.update(endereco, idcliente);

        ModelCliente cliente = new ModelCliente();
        DaoCliente daoCli = new DaoCliente();
        cliente.setId(Integer.parseInt(txtId.getText()));
        cliente.setNome(txtNome.getText().toUpperCase());
        cliente.setCpf(txtCpf.getText().toUpperCase());
        cliente.setEmail(txtEmail.getText());
        cliente.setTel_casa(txtTelefone.getText());
        cliente.setCel_whatssap(txtCelular.getText());
        cliente.setId_endereco(daoEnd.returnIdMax());
        daoCli.update(cliente);

        veiculo.setCor(txtCor.getText().toUpperCase());
        veiculo.setAno(Integer.parseInt(cbAno.getSelectedItem().toString()));
        veiculo.setPlaca(txtPlaca.getText().toUpperCase());
        veiculo.setKm(Double.parseDouble(txtKm.getText()));
        veiculo.setImg_veiculo(lblnmfile.getText());
        veiculo.setDescricao(descricao.getText().toUpperCase());
        veiculo.setId_marca(dao.returnId(nomeMarca));
        veiculo.setId_modelo(daoM.returnId(nomeModelo));
        veiculo.setIdcliente(Integer.parseInt(txtId.getText()));
        
       int setar = tblVeiculo.getSelectedRow();   
    
       if(setar == -1){
         setar = 0;             
         veiculo.setIdveiculo(Integer.parseInt(tblVeiculo.getModel().getValueAt(setar, 0).toString()));
      
       }else{
          setar = tblVeiculo.getSelectedRow();    
         veiculo.setIdveiculo(Integer.parseInt(tblVeiculo.getModel().getValueAt(setar, 0).toString()));
          }     
        daoVeiculo.update(veiculo);
        
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(0, true);
        jTabbedPane1.setSelectedIndex(0);
        DesativaCamposClienteEndereco();

    }

    //INSERIR MAIS VEICULOS
    public void inserirVeiculo() {
        try {
            ModelVeiculo veiculo = new ModelVeiculo();
            DaoMarcas dao = new DaoMarcas();
            DaoModelos daoM = new DaoModelos();
            DaoVeiculo daoVeiculo = new DaoVeiculo();
            veiculo.setCor(txtCor.getText().toUpperCase());
            veiculo.setAno(Integer.parseInt(cbAno.getSelectedItem().toString()));
            veiculo.setPlaca(txtPlaca.getText().toUpperCase());
            veiculo.setKm(Double.parseDouble(txtKm.getText()));
            veiculo.setImg_veiculo(lblnmfile.getText());
            veiculo.setDescricao(descricao.getText().toUpperCase());
            veiculo.setId_marca(dao.returnId(cbMarcas.getSelectedItem().toString()));
            veiculo.setId_modelo(daoM.returnId(cbModelos.getSelectedItem().toString()));
            veiculo.setIdcliente(Integer.parseInt(txtId.getText()));
            daoVeiculo.inserir(veiculo);
            JOptionPane.showMessageDialog(txtComplemento, "Veiculo inserido com sucesso!");
            listarVeiculos(Integer.parseInt(txtId.getText()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(txtComplemento, "A imagem não foi salva");
        }

    }

    //LIMPA CAMPOS
    public void limpaCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        txtCelular.setText("");
        txtRua.setText("");
        txtNumero.setText("");
        txtCep.setText("");
        txtBairro.setText("");
        txtCidade.setText("");
        txtCor.setText("");
        txtKm.setText("");
        txtPlaca.setText("");
        descricao.setText("");
        lblimage.setIcon(null);
        lblnmfile.setText("Imagem");
        cbMarcas.setSelectedIndex(0);
        cbAno.setSelectedIndex(0);
        txtComplemento.setText("");
    }

    //LISTAR VEICULOS DO CLIENTE
    public void listarVeiculos(int id) {
        DaoVeiculo dao = new DaoVeiculo();
        ArrayList<ModelVeiculo> lista = new ArrayList<>();

        lista = dao.veiculos(id);
        DefaultTableModel modelo = (DefaultTableModel) tblVeiculo.getModel();

        modelo.setNumRows(0);
        //CARREGA OS DADOS DA LISTA NA TABELA
        int cont = lista.size();
        for (int i = 0; i < cont; i++) {
            modelo.addRow(new Object[]{
                lista.get(i).getIdveiculo(),
                lista.get(i).getNome_modelo(),
                lista.get(i).getCor(),
                lista.get(i).getPlaca(),});

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField5 = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jpDados = new javax.swing.JPanel();
        jpCliente = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lbNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lbCpf = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lbTelefone = new javax.swing.JLabel();
        lbCelular = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        txtTelefone = new javax.swing.JFormattedTextField();
        txtCelular = new javax.swing.JFormattedTextField();
        jpPesquisar = new javax.swing.JPanel();
        lbPesNome = new javax.swing.JLabel();
        txtPesquisar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        btnPesquisar = new javax.swing.JButton();
        jpEndereco = new javax.swing.JPanel();
        lbRua = new javax.swing.JLabel();
        txtRua = new javax.swing.JTextField();
        lbComplemento = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        lbCep = new javax.swing.JLabel();
        txtCep = new javax.swing.JTextField();
        lbCidade = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        lbBairro = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        txtComplemento = new javax.swing.JTextField();
        lbNumero = new javax.swing.JLabel();
        btnProximo = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        jpVeiculo = new javax.swing.JPanel();
        jpPesquisar1 = new javax.swing.JPanel();
        lbPesNome1 = new javax.swing.JLabel();
        cbMarcas = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cbModelos = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtCor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbAno = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtKm = new javax.swing.JTextField();
        lblnmfile = new javax.swing.JLabel();
        lblimage = new javax.swing.JLabel();
        btnaddVeiculo = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descricao = new javax.swing.JTextArea();
        btnadd1 = new javax.swing.JButton();
        btnadd2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblVeiculo = new javax.swing.JTable();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnProximo1 = new javax.swing.JButton();

        jTextField5.setText("jTextField5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Cliente");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        jpDados.setPreferredSize(new java.awt.Dimension(700, 1052));

        jpCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N
        jpCliente.setEnabled(false);

        jLabel1.setText("ID");
        jLabel1.setEnabled(false);

        txtId.setEnabled(false);

        lbNome.setText("NOME");
        lbNome.setEnabled(false);

        txtNome.setEnabled(false);

        lbCpf.setText("CPF");
        lbCpf.setEnabled(false);

        lbEmail.setText("EMAIL");
        lbEmail.setEnabled(false);

        txtEmail.setEnabled(false);

        lbTelefone.setText("TELEFONE");
        lbTelefone.setEnabled(false);

        lbCelular.setText("CELULAR");
        lbCelular.setEnabled(false);

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###.##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.setEnabled(false);
        txtCpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCpfFocusLost(evt);
            }
        });

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefone.setEnabled(false);

        try {
            txtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCelular.setEnabled(false);

        javax.swing.GroupLayout jpClienteLayout = new javax.swing.GroupLayout(jpCliente);
        jpCliente.setLayout(jpClienteLayout);
        jpClienteLayout.setHorizontalGroup(
            jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpClienteLayout.createSequentialGroup()
                        .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNome)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpClienteLayout.createSequentialGroup()
                        .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCpf)
                            .addComponent(lbTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(268, 268, 268)
                        .addComponent(lbCelular))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpClienteLayout.createSequentialGroup()
                        .addComponent(txtTelefone)
                        .addGap(18, 18, 18)
                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpClienteLayout.createSequentialGroup()
                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEmail))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpClienteLayout.setVerticalGroup(
            jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClienteLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCpf)
                    .addComponent(lbEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTelefone)
                    .addComponent(lbCelular))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73))
        );

        jpPesquisar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        lbPesNome.setText("NOME");

        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "NOME", "CELULAR"
            }
        ));
        tblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCliente);

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedro_automotores/icon/loupe.png"))); // NOI18N
        btnPesquisar.setText("Pesquisar");

        javax.swing.GroupLayout jpPesquisarLayout = new javax.swing.GroupLayout(jpPesquisar);
        jpPesquisar.setLayout(jpPesquisarLayout);
        jpPesquisarLayout.setHorizontalGroup(
            jpPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPesquisarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPesquisarLayout.createSequentialGroup()
                        .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(jpPesquisarLayout.createSequentialGroup()
                        .addComponent(lbPesNome)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpPesquisarLayout.setVerticalGroup(
            jpPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPesquisarLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(lbPesNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPesquisarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpEndereco.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Endereço", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N
        jpEndereco.setEnabled(false);

        lbRua.setText("RUA");
        lbRua.setEnabled(false);

        txtRua.setEnabled(false);

        lbComplemento.setText("COMPLEMENTO");
        lbComplemento.setEnabled(false);

        txtNumero.setEnabled(false);

        lbCep.setText("CEP");
        lbCep.setEnabled(false);

        txtCep.setEnabled(false);
        txtCep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCepFocusLost(evt);
            }
        });
        txtCep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtCepMouseExited(evt);
            }
        });
        txtCep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCepKeyReleased(evt);
            }
        });

        lbCidade.setText("CIDADE");
        lbCidade.setEnabled(false);

        txtCidade.setEnabled(false);

        lbBairro.setText("BAIRRO");
        lbBairro.setEnabled(false);

        txtBairro.setEnabled(false);

        txtComplemento.setEnabled(false);

        lbNumero.setText("NUMERO");
        lbNumero.setEnabled(false);

        javax.swing.GroupLayout jpEnderecoLayout = new javax.swing.GroupLayout(jpEndereco);
        jpEndereco.setLayout(jpEnderecoLayout);
        jpEnderecoLayout.setHorizontalGroup(
            jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpEnderecoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpEnderecoLayout.createSequentialGroup()
                        .addGroup(jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRua))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNumero))
                        .addGap(18, 18, 18)
                        .addGroup(jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpEnderecoLayout.createSequentialGroup()
                                .addComponent(lbComplemento)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtComplemento)))
                    .addGroup(jpEnderecoLayout.createSequentialGroup()
                        .addGroup(jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCep))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbBairro)
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCidade))))
                .addContainerGap())
        );
        jpEnderecoLayout.setVerticalGroup(
            jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpEnderecoLayout.createSequentialGroup()
                .addGroup(jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpEnderecoLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNumero, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbRua))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumero, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCep)
                            .addComponent(lbBairro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpEnderecoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbCidade)))
                .addGap(27, 27, 27))
        );

        btnProximo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedro_automotores/icon/right-arrow.png"))); // NOI18N
        btnProximo.setText("PROXIMO");
        btnProximo.setEnabled(false);
        btnProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProximoActionPerformed(evt);
            }
        });

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedro_automotores/icon/new (1).png"))); // NOI18N
        btnNovo.setText("NOVO");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpDadosLayout = new javax.swing.GroupLayout(jpDados);
        jpDados.setLayout(jpDadosLayout);
        jpDadosLayout.setHorizontalGroup(
            jpDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDadosLayout.createSequentialGroup()
                .addGroup(jpDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jpPesquisar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 24, Short.MAX_VALUE))
            .addComponent(jpEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpDadosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpDadosLayout.setVerticalGroup(
            jpDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(jpDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dados", jpDados);

        jpPesquisar1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados Veiculo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        lbPesNome1.setText("MARCA");

        cbMarcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMarcasActionPerformed(evt);
            }
        });

        jLabel2.setText("MODELO");

        jLabel3.setText("COR");

        jLabel4.setText("ANO");

        cbAno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONE", "2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990" }));

        jLabel5.setText("PLACA");

        txtPlaca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPlacaFocusLost(evt);
            }
        });

        jLabel6.setText("KM");

        lblnmfile.setText("Imagem");

        lblimage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblimage.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnaddVeiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedro_automotores/icon/vehicle.png"))); // NOI18N
        btnaddVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddVeiculoActionPerformed(evt);
            }
        });

        jLabel8.setText("Descrição");

        descricao.setColumns(20);
        descricao.setRows(5);
        jScrollPane2.setViewportView(descricao);

        btnadd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedro_automotores/icon/loupe.png"))); // NOI18N
        btnadd1.setText("Visualizar ");
        btnadd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnadd1ActionPerformed(evt);
            }
        });

        btnadd2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedro_automotores/icon/picture.png"))); // NOI18N
        btnadd2.setText("Imagem");
        btnadd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnadd2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpPesquisar1Layout = new javax.swing.GroupLayout(jpPesquisar1);
        jpPesquisar1.setLayout(jpPesquisar1Layout);
        jpPesquisar1Layout.setHorizontalGroup(
            jpPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPesquisar1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPesquisar1Layout.createSequentialGroup()
                        .addGroup(jpPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbPesNome1)
                            .addComponent(cbMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpPesquisar1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cbModelos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpPesquisar1Layout.createSequentialGroup()
                        .addGroup(jpPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpPesquisar1Layout.createSequentialGroup()
                                .addGroup(jpPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbAno, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(28, 28, 28)
                                .addGroup(jpPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPesquisar1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(353, 353, 353))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPesquisar1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jpPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addGroup(jpPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblimage, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPesquisar1Layout.createSequentialGroup()
                                        .addGap(143, 143, 143)
                                        .addComponent(lblnmfile)
                                        .addGap(159, 159, 159)))
                                .addComponent(txtKm, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpPesquisar1Layout.createSequentialGroup()
                                .addComponent(btnadd2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnadd1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnaddVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 20, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpPesquisar1Layout.setVerticalGroup(
            jpPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPesquisar1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jpPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPesNome1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbMarcas, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(cbModelos)
                    .addComponent(txtCor))
                .addGap(18, 18, 18)
                .addGroup(jpPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbAno, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtKm, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jpPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPesquisar1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpPesquisar1Layout.createSequentialGroup()
                        .addComponent(lblnmfile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblimage, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jpPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnaddVeiculo)
                            .addGroup(jpPesquisar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnadd1)
                                .addComponent(btnadd2)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Veiculos do Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        tblVeiculo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "VEICULO", "COR", "PLACA"
            }
        ));
        tblVeiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVeiculoMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblVeiculo);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedro_automotores/icon/salve-.png"))); // NOI18N
        btnSalvar.setText("SALVAR");
        btnSalvar.setEnabled(false);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedro_automotores/icon/pencil.png"))); // NOI18N
        btnEditar.setText("EDITAR");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnProximo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedro_automotores/icon/right-back.png"))); // NOI18N
        btnProximo1.setText("VOLTAR");
        btnProximo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProximo1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpVeiculoLayout = new javax.swing.GroupLayout(jpVeiculo);
        jpVeiculo.setLayout(jpVeiculoLayout);
        jpVeiculoLayout.setHorizontalGroup(
            jpVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVeiculoLayout.createSequentialGroup()
                .addGroup(jpVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpVeiculoLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jpPesquisar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpVeiculoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpVeiculoLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnProximo1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpVeiculoLayout.setVerticalGroup(
            jpVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVeiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpPesquisar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpVeiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnProximo1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Veiculo", jpVeiculo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 970, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
              try {
            if(txtPlaca.getText().equals(null)){
                 JOptionPane.showMessageDialog(null, "Atenção, preencha todos os campos!", "Atenção", 2);
            }else{
            update(cbMarcas.getSelectedItem().toString(), cbModelos.getSelectedItem().toString());
            limpaCampos();
            carregarCliente();
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Atenção, preencha todos os campos!", "Atenção", 2);
            Logger.getLogger(ViewClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ViewClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProximoActionPerformed
        proximo();
    }//GEN-LAST:event_btnProximoActionPerformed

    private void btnaddVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddVeiculoActionPerformed
   DaoVeiculo dao = new DaoVeiculo();    
        if(dao.consultaPlaca(txtPlaca.getText()) == 1){
           JOptionPane.showMessageDialog(null, "Atenção, esta Placa já se encontra cadastrada!", "Atenção", 2);
         txtPlaca.setText(""); 
        
    }else{
             inserirVeiculo();
       }
       
    }//GEN-LAST:event_btnaddVeiculoActionPerformed

    private void btnadd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnadd1ActionPerformed

        try {
            String urlString = lblnmfile.getText();
            String path = new File(".").getCanonicalPath();
            openWebpage("file:///" + path + "/images/" + urlString);
        } catch (IOException ex) {
            Logger.getLogger(ViewClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnadd1ActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        ativaCamposClienteEndereco();

    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            if(txtPlaca.getText().equals(null)){
                 JOptionPane.showMessageDialog(null, "Atenção, preencha todos os campos!", "Atenção", 2);
            }else{
            salvar(cbMarcas.getSelectedItem().toString(), cbModelos.getSelectedItem().toString());
            limpaCampos();
            carregarCliente();
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Atenção, preencha todos os campos!", "Atenção", 2);
            Logger.getLogger(ViewClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtCepMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCepMouseExited

    }//GEN-LAST:event_txtCepMouseExited

    private void txtCepKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCepKeyReleased

    }//GEN-LAST:event_txtCepKeyReleased

    private void txtCepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCepFocusLost

        try {
          buscaEndereco(txtCep.getText());
               
           
        } catch (Exception ex) {
            Logger.getLogger(ViewClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtCepFocusLost

    private void cbMarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMarcasActionPerformed
        try {
            cbModelos.removeAllItems();
            listComboBoxModelo(cbMarcas.getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(ViewClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbMarcasActionPerformed

    private void tblClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClienteMouseClicked
        try {
            setarCampos();
            txtCpf.setEditable(false);
            txtCpf.setFocusable(false);
          //txtCpf.setEnabled(false);
      //  txtCpf.disable();

        } catch (ParseException ex) {
            Logger.getLogger(ViewClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ViewClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ViewClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblClienteMouseClicked

    private void btnProximo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProximo1ActionPerformed
        back();
    }//GEN-LAST:event_btnProximo1ActionPerformed

    private void btnadd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnadd2ActionPerformed
        jfc = new JFileChooser();
        if (jfc.showOpenDialog(lblimage) == JFileChooser.APPROVE_OPTION) {

            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image image = toolkit.getImage(jfc.getSelectedFile().getAbsolutePath());
            Image imagedResized = image.getScaledInstance(200, 300, Image.SCALE_DEFAULT);
            ImageIcon imageIcon = new ImageIcon(imagedResized);

            lblimage.setIcon(imageIcon);
            lblnmfile.setText(jfc.getSelectedFile().getName());

            file = new File(jfc.getSelectedFile().getPath());
            String path;
            try {
                path = new File(".").getCanonicalPath();
                FileUtils.copyFileToDirectory(file, new File(path + "/images"));
            } catch (IOException ex) {
                Logger.getLogger(ViewClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnadd2ActionPerformed

    private void tblVeiculoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVeiculoMouseClicked
        try {
            setarVeiculo();
        } catch (SQLException ex) {
            Logger.getLogger(ViewClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ViewClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblVeiculoMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        try {
            instance = new ViewClient();
        } catch (Throwable ex) {
            Logger.getLogger(ViewClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosed

    private void txtCpfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCpfFocusLost
    DaoCliente dao = new DaoCliente();
    if(dao.validaCpf(txtCpf.getText()) == 1){
         JOptionPane.showMessageDialog(null, "Atenção, este CPF já se encontra cadastrado!", "Atenção", 2);
         txtCpf.setText(""); 
    }
      
    }//GEN-LAST:event_txtCpfFocusLost

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
      ControlCliente controle = new ControlCliente();
      
        try {
            String evento = txtPesquisar.getText();
          
            System.out.print(controle.controlSelect(evento));
            tblCliente.setModel(DbUtils.resultSetToTableModel(controle.controlSelect(evento)));
            //System.out.print());
        } catch (SQLException ex) {
            Logger.getLogger(ViewClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ViewClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void txtPlacaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPlacaFocusLost
         DaoVeiculo dao = new DaoVeiculo();
    if(dao.consultaPlaca(txtPlaca.getText()) == 1){
         JOptionPane.showMessageDialog(null, "Atenção, esta Placa já se encontra cadastrada!", "Atenção", 2);
         txtPlaca.setText(""); 
    }
    }//GEN-LAST:event_txtPlacaFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                try {
                    new ViewClient().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ViewClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnProximo;
    private javax.swing.JButton btnProximo1;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnadd1;
    private javax.swing.JButton btnadd2;
    private javax.swing.JButton btnaddVeiculo;
    private javax.swing.JComboBox<String> cbAno;
    private javax.swing.JComboBox<String> cbMarcas;
    private javax.swing.JComboBox<String> cbModelos;
    private javax.swing.JTextArea descricao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JPanel jpCliente;
    private javax.swing.JPanel jpDados;
    private javax.swing.JPanel jpEndereco;
    private javax.swing.JPanel jpPesquisar;
    private javax.swing.JPanel jpPesquisar1;
    private javax.swing.JPanel jpVeiculo;
    private javax.swing.JLabel lbBairro;
    private javax.swing.JLabel lbCelular;
    private javax.swing.JLabel lbCep;
    private javax.swing.JLabel lbCidade;
    private javax.swing.JLabel lbComplemento;
    private javax.swing.JLabel lbCpf;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbNumero;
    private javax.swing.JLabel lbPesNome;
    private javax.swing.JLabel lbPesNome1;
    private javax.swing.JLabel lbRua;
    private javax.swing.JLabel lbTelefone;
    private javax.swing.JLabel lblimage;
    private javax.swing.JLabel lblnmfile;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTable tblVeiculo;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtCelular;
    private javax.swing.JTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JTextField txtCor;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtKm;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtRua;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
