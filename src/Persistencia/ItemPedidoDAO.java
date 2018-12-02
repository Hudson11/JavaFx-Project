package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Modelo.ItemPedido;

public class ItemPedidoDAO {
	private final String INSERT = "INSERT INTO ITEMPEDIDO (IDPEDIDO, IDPRODUTO, QUANTIDADE) VALUES (?,?,?)";
    private final String DELETEPRODUTO = "DELETE FROM ITEMPEDIDO WHERE IDPRODUTO =?";
	private final String LISTPEDIDO = "SELECT PRODUTO.ID, PRODUTO.NOME, PRODUTO.PRECO, ITEMPEDIDO.QUANTIDADE FROM PRODUTO JOIN ITEMPEDIDO ON ITEMPEDIDO.IDPRODUTO = PRODUTO.ID AND ITEMPEDIDO.IDPEDIDO = ?";
    private final String UPDATETOTAL = "UPDATE PEDIDO SET TOTAL = TOTAL + ? WHERE ID=?";
    
    private Conexao con = new Conexao("127.0.0.1", "3306", "mydb", "root",""); 
	
	public void inserir(ItemPedido i) {
		try {
			// CONECTA
			con.conecta();
			PreparedStatement preparaInstrucao;
			preparaInstrucao = con.getConexao().prepareStatement(INSERT);

			// SETA OS VALORES DA INSTRUCAO
			// OBS: PASSA OS PARAMETROS NA ORDEM DAS ? DA INSTRUCAO
			preparaInstrucao.setInt(1, i.getIdPedido());
			preparaInstrucao.setInt(2, i.getIdProduto());
			preparaInstrucao.setInt(3, i.getQuantidade());

			// EXECUTA A INSTRUCAO
			preparaInstrucao.execute();
			JOptionPane.showMessageDialog(null, "Produto adicionado ao pedido com sucesso");

			// DESCONECTA
			con.desconecta();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto no pedido no banco de dados " + e.getMessage());

		}
		
		atualizarTotal(i, false);
	}

	public void remover(ItemPedido i) {
		try {
			 //CONECTA
			con.conecta();
			PreparedStatement preparaInstrucao;
			preparaInstrucao = con.getConexao().prepareStatement(DELETEPRODUTO); 

			//SETA OS VALORES DA INSTRUCAO
			//OBS: PASSA OS PARAMETROS NA ORDEM DAS ? DA INSTRUCAO
			preparaInstrucao.setInt(1, i.getIdProduto());

			//EXECUTA A INSTRUCAO
			preparaInstrucao.execute();
			JOptionPane.showMessageDialog(null,"Produto removido de pedido com sucesso");
			
			//DESCONECTA
			con.desconecta();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao remover produto de pedido no banco de dados " + e.getMessage());

		}
		atualizarTotal(i, true);
	}

	public ArrayList<ItemPedido> listar(int idPedido) {
		ArrayList<ItemPedido> lista = new ArrayList<>(); //cria uma lista de objetos agencia

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
			while (rs.next()) { //enquanto houver registro
				ItemPedido i = new ItemPedido(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"), idPedido, rs.getInt("quantidade"));
				lista.add(i); //adiciona o objeto a lista
			}
			// DESCONECTA
			con.desconecta();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public void atualizarTotal(ItemPedido i, boolean remover) {
		double valor = i.getPreco() * i.getQuantidade();
		if(remover)
			valor = -valor;
		try {
			 //CONECTA
			con.conecta();
			PreparedStatement preparaInstrucao;
			preparaInstrucao = con.getConexao().prepareStatement(UPDATETOTAL); 

			//SETA OS VALORES DA INSTRUCAO
			//OBS: PASSA OS PARAMETROS NA ORDEM DAS ? DA INSTRUCAO
			preparaInstrucao.setDouble(1, valor);
			preparaInstrucao.setInt(2, i.getIdPedido());

			//EXECUTA A INSTRUCAO
			preparaInstrucao.execute();
			
			//DESCONECTA
			con.desconecta();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao acrescentar produto ao pedido no banco de dados " + e.getMessage());

		}
	}
}

