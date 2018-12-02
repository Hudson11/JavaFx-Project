package Controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TextField;

import Modelo.Produto;
import Persistencia.ProdutoDAO;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControleAtualizarProduto implements Initializable {
	@FXML
	private TextField textId;

	@FXML
	private TextField textValor;
        
       
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	@FXML
	public void preencherForm(Produto p) {
		textId.setText(p.getNome());
		textId.setEditable(false);
		textValor.setText(Double.toString(p.getPreco()));
	}

	@FXML
	public void atualizarProduto(ActionEvent event) {
		int id = Integer.parseInt(textId.getText());
		double p = Double.parseDouble(textValor.getText().replace(',', '.'));
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.atualizarPreco(p, id);
		fecharJanela(event);
	}
	
	@FXML
	public void fecharJanela(ActionEvent event) {
		ControleListarProdutos.controller.atualizarTabela();
		Button b =  (Button) event.getSource();
		Stage st = (Stage) b.getScene().getWindow();
		st.close();
	}
	
        @FXML
	public void limpar() {
		textId.setText("");
		textValor.setText("");
	}
}
