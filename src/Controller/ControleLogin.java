/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Persistencia.FuncionarioDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Hudso
 */
public class ControleLogin implements Initializable {
    @FXML
    private TextField textCpf;
    @FXML
    private PasswordField textLogin;
            
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public void verificarUsuario(ActionEvent event) throws SQLException{
        String cpf = textCpf.getText();
        String senha =textLogin.getText();
        
        FuncionarioDAO funcionario =new FuncionarioDAO();
        if(funcionario.revificarFuncionario(cpf, senha) == true){
                home(event);
                fecharJanela(event);
             }  else if (cpf.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("admin")){
                home(event); // super usuario apenas para testes mais rapidos
                fecharJanela(event);
             }else
            JOptionPane.showMessageDialog(null,"Erro na autenticação por favor Verifique seus dados e tente novamente");
          }
    
    
    @FXML
	public void fecharJanela(ActionEvent event) {
		Button b =  (Button) event.getSource();
		Stage st = (Stage) b.getScene().getWindow();
		st.close();
	}
    
        public void home(ActionEvent event){
            try {
			Parent formProduto = FXMLLoader.load(getClass().getResource("/Interface/home.fxml"));
			Scene scene = new Scene(formProduto);
			Stage st = new Stage();
			st.setScene(scene);
			st.setTitle("Cadastrar Produto");
			st.setResizable(false);
			st.show();
                     //   st.close();
		} catch (IOException e) {			
		}
        }
        
}
