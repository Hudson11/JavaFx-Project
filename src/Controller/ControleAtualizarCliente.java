package Controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TextField;

import Modelo.Produto;
import Persistencia.ClienteDAO;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class ControleAtualizarCliente implements Initializable {
	@FXML
	private TextField textCpf;

	@FXML
	private TextField textNome;
        
        @FXML
        private TextField textId;
        
       
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	@FXML
	public void preencherForm(Produto p) {
		textCpf.setText(p.getNome());
		textCpf.setEditable(false);
		textNome.setText(Double.toString(p.getPreco()));
	}

	@FXML
	public void atualizarClientex(ActionEvent event) {
		String cpf = textCpf.getText();
		String nome = textNome.getText();
                int id = Integer.parseInt(textId.getText());
		ClienteDAO clienteDAO = new ClienteDAO();
                if(clienteDAO.verificarCpf(cpf)== false){
                clienteDAO.atualizarDadocliente(cpf,nome,id);
		fecharJanela(event);
                }else
                    JOptionPane.showMessageDialog(null,"Cpf já existe verifique novamente seus dados");
                    fecharJanela(event);
        }
	
	@FXML
	public void fecharJanela(ActionEvent event) {
		ControleListarCliente.controller.atualizarTabela();
		Button b =  (Button) event.getSource();
		Stage st = (Stage) b.getScene().getWindow();
		st.close();
	}
	
        @FXML
	public void limpar() {
		textCpf.setText("");
		textNome.setText("");
                textId.setText("");
	}
}
