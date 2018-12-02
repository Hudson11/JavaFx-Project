package Controller;

import Modelo.Funcionario;

import Persistencia.FuncionarioDAO;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class ControleCadastrarFuncionario implements Initializable {
    
    @FXML
    TextField textNome,textCpf;
    
    @FXML
    PasswordField textSenha;
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  

    @FXML
    public void cadastrar(ActionEvent event){
        String nome = textNome.getText();
        String cpf = textCpf.getText();
        String senha = textSenha.getText();
        FuncionarioDAO funcionario = new FuncionarioDAO();
        if(funcionario.verificarCpf(cpf) == false){
        Funcionario f = new Funcionario(nome,cpf,senha);
        funcionario.inserir(f);
        fecharJanela(event);
        }else
            JOptionPane.showMessageDialog(null,"Cpf já existe verifique novamente seus dados");
            fecharJanela(event);
    }
   
    @FXML
	public void limpar() {
		textNome.setText("");
		textCpf.setText("");
                textSenha.setText("");
	}
    
    @FXML
	public void fecharJanela(ActionEvent event) {
		ControleListarFuncionario.controller.atualizarTabela();
		Button b =  (Button) event.getSource();
		Stage st = (Stage) b.getScene().getWindow();
		st.close();
	}
    
    
    
}
