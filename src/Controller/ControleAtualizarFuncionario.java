package Controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TextField;

import Modelo.Produto;
import Persistencia.FuncionarioDAO;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;


public class ControleAtualizarFuncionario implements Initializable {
	
        @FXML
	private TextField textId;

	@FXML
	private PasswordField textPassword;
        
       
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	@FXML
	public void preencherForm(Produto p) {
		textId.setText(p.getNome());
		textId.setEditable(false);
		textPassword.setText(Double.toString(p.getPreco()));
	}

	@FXML
	public void atualizarFuncionariox(ActionEvent event) {
		String cpf = textId.getText();
		String senha = textPassword.getText();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                funcionarioDAO.atualizarSenha(senha, cpf);
		fecharJanela(event);
	}
	
	@FXML 
	public void fecharJanela(ActionEvent event) {
		ControleListarFuncionario.controller.atualizarTabela();
		Button b =  (Button) event.getSource();
		Stage st = (Stage) b.getScene().getWindow();
		st.close();
	}
	
        @FXML
	public void limpar() {
		textId.setText("");
		textPassword.setText("");
	}
}
