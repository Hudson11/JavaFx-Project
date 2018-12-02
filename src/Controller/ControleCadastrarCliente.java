package Controller;
import Modelo.Cliente;


import Persistencia.ClienteDAO;


import java.net.URL;

import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;



public class ControleCadastrarCliente implements Initializable {
    
    private ClienteDAO usuario = new ClienteDAO();
    
    @FXML
    TextField textNome,textCpf;
    
    
 
    public void initialize(URL url, ResourceBundle rb) {
       
    }  

    @FXML
    public void cadastrar(ActionEvent event){
        String nome = textNome.getText();
        String cpf = textCpf.getText();
        ClienteDAO cliente = new ClienteDAO();
       
        if(cliente.verificarCpf(cpf) == false){
        Cliente c = new Cliente(nome,cpf);
        cliente.inserir(c);
        fecharJanela(event);
        }else
            JOptionPane.showMessageDialog(null,"Cpf já existe verifique novamente seus dados");
            fecharJanela(event);
       }
    
    @FXML
	public void limpar() {
		textNome.setText("");
		textCpf.setText("");
	}
    
        @FXML
	public void fecharJanela(ActionEvent event) {
		ControleListarCliente.controller.atualizarTabela();
		Button b =  (Button) event.getSource();
		Stage st = (Stage) b.getScene().getWindow();
		st.close();
	}
}
