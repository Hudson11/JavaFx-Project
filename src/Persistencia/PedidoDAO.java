package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Modelo.Pedido;

public class PedidoDAO {
	private final String INSERT = "INSERT INTO PEDIDO (IDCLIENTE, TOTAL) VALUES (?,0)";
    private final String DELETE = "DELETE FROM PEDIDO WHERE ID = ?";
    private final String LIST = "SELECT PEDIDO.ID, CLIENTE.NOME, PEDIDO.TOTAL FROM PEDIDO JOIN CLIENTE ON PEDIDO.IDCLIENTE = CLIENTE.ID";
    private final String LISTPEDIDO = "SELECT CLIENTE.NOME, PEDIDO.TOTAL FROM PEDIDO JOIN CLIENTE ON PEDIDO.IDCLIENTE = CLIENTE.ID AND PEDIDO.ID = ?";
    
    private Conexao con = new Conexao("127.0.0.1", "3306", "mydb", "root",""); 
	
	public void inserir(int idCliente) {
		try {
			// CONECTA
			con.conecta();
			PreparedStatement preparaInstrucao;
			preparaInstrucao = con.getConexao().prepareStatement(INSERT);

			// SETA OS VALORES DA INSTRUCAO
			// OBS: PASSA OS PARAMETROS NA ORDEM DAS ? DA INSTRUCAO
			preparaInstrucao.setInt(1, idCliente);

			// EXECUTA A INSTRUCAO
			preparaInstrucao.execute();
			JOptionPane.showMessageDialog(null, "Informe os produtos do pedido");

			// DESCONECTA
			con.desconecta();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar pedido no banco de dados " + e.getMessage());

		}
	}
	
	public void remover(int id) {
		try {
			 //CONECTA
			con.conecta();
			PreparedStatement preparaInstrucao;
			preparaInstrucao = con.getConexao().prepareStatement(DELETE); 

			//SETA OS VALORES DA INSTRUCAO
			//OBS: PASSA OS PARAMETROS NA ORDEM DAS ? DA INSTRUCAO
			preparaInstrucao.setInt(1, id);

			//EXECUTA A INSTRUCAO
			preparaInstrucao.execute();
			JOptionPane.showMessageDialog(null,"Pedido removido com sucesso");
			
			//DESCONECTA
			con.desconecta();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao remover pedido no banco de dados " + e.getMessage());

		}
	}

	public ArrayList<Pedido> listar() {
		ArrayList<Pedido> lista = new ArrayList<>(); //cria uma lista de objetos 

		try {
			// CONECTA
			con.conecta();
			PreparedStatement preparaInstrucao;
			preparaInstrucao = con.getConexao().prepareStatement(LIST); 
			
			// EXECUTA A INSTRUCAO
			ResultSet rs = preparaInstrucao.executeQuery(); 
			
			//TRATA O RETORNO DA CONSULTA
			while (rs.next()) { //enquanto houver registro
				Pedido p = new Pedido(rs.getInt("id"),rs.getString("nome"), rs.getDouble("total"));//cria objeto de Pedido
				lista.add(p); //adiciona o objeto a lista
			}
			// DESCONECTA
			con.desconecta();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public Pedido listarPedido(int idPedido) {
		Pedido p = null; 

		try {
			// CONECTA
			con.conecta();
			PreparedStatement preparaInstrucao;
			preparaInstrucao = con.getConexao().prepareStatement(LISTPEDIDO); 
			
			//SETA OS VALORES DA INSTRUCAO
			//OBS: PASSA OS PARAMETROS NA ORDEM DAS ? DA INSTRUCAO
			preparaInstrucao.setInt(1, idPedido);
			
			// EXECUTA A INSTRUCAO
			ResultSet rs = preparaInstrucao.executeQuery(); 
			
			//TRATA O RETORNO DA CONSULTA
			if (rs.next()) { //se houver registro
				p = new Pedido(idPedido, rs.getString("nome"), rs.getDouble("total"));//cria objeto de Pedido
			}
			// DESCONECTA
			con.desconecta();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}
}
