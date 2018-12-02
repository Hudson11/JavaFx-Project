package Controller;

import Modelo.Produto;
import Persistencia.ProdutoDAO;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ControleCadastrarProduto implements Initializable {
    
    @FXML
    TextField textNomep,textValorp;
   
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
public void cadastrar(ActionEvent event){
        String nome = textNomep.getText();
        String valor = textValorp.getText();
        
        double number = Double.parseDouble(valor);
        
        Produto a = new Produto(nome,number);
        ProdutoDAO produto = new ProdutoDAO();
        produto.inserir(a); 
        fecharJanela(event);
    }

@FXML
public void limpar() {
	textNomep.setText("");
	textValorp.setText("");
	}

/*@FXML
	public void atualizarProduto(ActionEvent event) {
		String n = textNomep.getText();
		double p = Double.parseDouble(textValorp.getText().replace(',', '.'));
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.atualizarPreco(n, p);
		fecharJanela(event);
	}*/
        
        @FXML
	public void fecharJanela(ActionEvent event) {
		ControleListarProdutos.controller.atualizarTabela();
		Button b =  (Button) event.getSource();
		Stage st = (Stage) b.getScene().getWindow();
		st.close();
	}

}



   
