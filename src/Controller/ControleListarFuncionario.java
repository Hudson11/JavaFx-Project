package Controller;


import Modelo.Usuario;

import Persistencia.FuncionarioDAO;
import Persistencia.UsuarioDAO;
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

public class ControleListarFuncionario implements Initializable {
	static ControleListarFuncionario controller;
        
        private UsuarioDAO usuarioDAO = new FuncionarioDAO();
        
        private FuncionarioDAO fun = new FuncionarioDAO();
	
	private ObservableList  <Usuario> funcionario = FXCollections.observableArrayList();
       
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
       private void cadastrarFuncionario(ActionEvent event ){
            try {
			Parent formProduto = FXMLLoader.load(getClass().getResource("/Interface/ADDFuncionario.fxml"));
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
    
       public void atualizarTabela() {
		tabela.getItems().setAll(usuarioDAO.listar());
	}
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
            // TODO   
                controller = this;
		funcionario.addAll(usuarioDAO.listar());
		tabela.setItems(funcionario);
                tableId.setCellValueFactory(new PropertyValueFactory<Usuario,Integer>("id"));
		tableNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
		tablePreco.setCellValueFactory(new PropertyValueFactory<Usuario, Double>("cpf"));
	}
	@FXML
	public void removerFuncionario(ActionEvent event) {
		Usuario u =  tabela.getSelectionModel().getSelectedItem();
		usuarioDAO.remover(u.getNome());
              //  if(usuarioDAO.remover(u.getNome())) {
			int indice = tabela.getSelectionModel().getSelectedIndex();
			tabela.getItems().remove(indice);
                
		} 
        
        @FXML
       private void atualizarFuncionario(ActionEvent event ){
            try {
			Parent formProduto = FXMLLoader.load(getClass().getResource("/Interface/FormAtualizarFuncionario.fxml"));
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
       private void buscarFuncionario(ActionEvent event) throws SQLException {
           String cpf = textBusca.getText();
           funcionario.clear();
           funcionario.addAll(fun.funcionarioBuscaDAO(cpf));
           tabela.setItems(funcionario);
           tableId.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("id"));
            tableNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
            tablePreco.setCellValueFactory(new PropertyValueFactory<Usuario, Double>("cpf"));
           textBusca.setText("");
       }
        
	}

