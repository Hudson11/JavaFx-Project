package Controller;


import Modelo.Usuario;
import Persistencia.ClienteDAO;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControleListarCliente implements Initializable {
	static ControleListarCliente controller;
        
        private ClienteDAO usuarioDAO = new ClienteDAO();
	
	private ObservableList <Usuario> cliente = FXCollections.observableArrayList();
       
        @FXML
        private Button buttonADD;
       
       @FXML
	private TableView <Usuario> tabela;
	
	@FXML 
	private TableColumn <Usuario, Integer> tableId;
	
	@FXML 
	private TableColumn <Usuario, String> tableNome;
	
	@FXML 
	private TableColumn <Usuario, Double> tablePreco;
        
        @FXML
        private TextField textBusca;
        
        @FXML
       private void cadastrarCliente(ActionEvent event ){
            try {
			Parent formProduto = FXMLLoader.load(getClass().getResource("/Interface/ADDCliente.fxml"));
			Scene scene = new Scene(formProduto);
			Stage st = new Stage();
			st.setScene(scene);
			st.setTitle("Cadastrar Produto");
			st.setResizable(false);
			st.show();
                     //   st.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        }
    
       public void atualizarTabela() {
		tabela.getItems().setAll(usuarioDAO.listar());
	}
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
            // TODO   
                controller = this;
		cliente.addAll(usuarioDAO.listar());
		tabela.setItems(cliente);
		tableId.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("id"));
		tableNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
		tablePreco.setCellValueFactory(new PropertyValueFactory<Usuario, Double>("cpf"));
	}
	@FXML
	public void removerCliente() {
		Usuario p =  tabela.getSelectionModel().getSelectedItem();
		usuarioDAO.remover(p.getNome());
			int indice = tabela.getSelectionModel().getSelectedIndex();
			tabela.getItems().remove(indice);
		
	}
        
        @FXML
       private void atualizarCliente(ActionEvent event ){
            try {
			Parent formProduto = FXMLLoader.load(getClass().getResource("/Interface/FormAtualizarCliente.fxml"));
			Scene scene = new Scene(formProduto);
			Stage st = new Stage();
			st.setScene(scene);
			st.setTitle("Cadastrar Produto");
			st.setResizable(false);
			st.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
        }
       @FXML
       private void buscarCliente(ActionEvent event) throws SQLException{
           try{
           String cpf = textBusca.getText();
           cliente.clear();
           cliente.addAll(usuarioDAO.clienteBuscaDAO(cpf));
           tabela.setItems(cliente);
           tableId.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("id"));
            tableNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
            tablePreco.setCellValueFactory(new PropertyValueFactory<Usuario, Double>("cpf"));
           
           } catch(Exception e){
           }
           textBusca.setText("");
       }
}
