package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;



public class ControleHome implements Initializable {
  @FXML
   private Button buttonCliente,buttonFuncionario,buttonProduto;
  @FXML
   private AnchorPane  anchorinicio;
  @FXML
   private BorderPane Principal;
   
   
       @FXML
	private void listar(ActionEvent event) {
		String arquivoFXML = null;

		if (event.getSource().equals(buttonFuncionario)) {
			arquivoFXML = "/Interface/ListarFuncionario.fxml";
		}  else if(event.getSource().equals(buttonCliente)){
                        arquivoFXML = "/Interface/ListarCliente.fxml";
                }  else if (event.getSource().equals(buttonProduto)) {
			arquivoFXML = "/Interface/ListarProdutos.fxml";
		} 

		try {
			BorderPane cena = (BorderPane) FXMLLoader.load(getClass().getResource(arquivoFXML));
			Principal.setCenter(cena);
		} catch (IOException e) {
			e.printStackTrace();
                        System.out.println("erro ao carregar FXML");
		}
	}
        
        
        
        
    
    @FXML
    private void sair(){
         System.exit(0);
     }
    
    @FXML
	private void home() {
		Principal.setCenter(anchorinicio);
	}
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
}

   
