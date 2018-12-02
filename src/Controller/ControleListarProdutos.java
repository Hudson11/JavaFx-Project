package Controller;

import Modelo.Produto;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


import Persistencia.ProdutoDAO;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControleListarProdutos implements Initializable {
	static ControleListarProdutos controller;
        
        private ProdutoDAO produtoDAO = new ProdutoDAO();
	
	private ObservableList <Produto> produtos = FXCollections.observableArrayList();
       
        @FXML
        private Button buttonADD;
       
       @FXML
	private TableView <Produto> tabela;
	
	@FXML 
	private TableColumn <Produto, Integer> tableId;
	
	@FXML 
	private TableColumn <Produto, String> tableNome;
	
	@FXML 
	private TableColumn <Produto, Double> tablePreco;
        
        @FXML
        private TextField textBusca;
        
        
        @FXML
       private void cadastrarProduto(ActionEvent event ){
            try {
			Parent formProduto = FXMLLoader.load(getClass().getResource("/Interface/ADDProdutos.fxml"));
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
		tabela.getItems().setAll(produtoDAO.listar());
	}
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
                controller = this;
		produtos.addAll(produtoDAO.listar());
		tabela.setItems(produtos);
		tableId.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("idProduto"));
		tableNome.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
		tablePreco.setCellValueFactory(new PropertyValueFactory<Produto, Double>("preco"));
	}
	@FXML
	public void removerProduto() {
		Produto p = tabela.getSelectionModel().getSelectedItem();
		if(produtoDAO.remover(p.getNome())) {
			int indice = tabela.getSelectionModel().getSelectedIndex();
			tabela.getItems().remove(indice);
		}
	}
        
        @FXML
       private void atualizarProdutos(ActionEvent event ){
            try {
			Parent formProduto = FXMLLoader.load(getClass().getResource("/Interface/FormAtualizarProduto.fxml"));
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
       private void buscarProduto(ActionEvent event) throws SQLException {
           try{
           int id = Integer.parseInt(textBusca.getText());
           produtos.clear();
           produtos.addAll(produtoDAO.produtoBuscaDAO(id));
		tabela.setItems(produtos);
		tableId.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("idProduto"));
		tableNome.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
		tablePreco.setCellValueFactory(new PropertyValueFactory<Produto, Double>("preco"));
       } catch(Exception e){          
       }
           textBusca.setText("");
       }
}
