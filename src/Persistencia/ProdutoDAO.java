package Persistencia;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Modelo.Produto;
import javax.swing.JOptionPane;

public class ProdutoDAO {
    private final String INSERT = "INSERT INTO PRODUTO (NOME, PRECO) VALUES (?,?)";
    private final String UPDATEPRECO = "UPDATE PRODUTO SET PRECO=? WHERE ID=?";
    private final String DELETE = "DELETE FROM PRODUTO WHERE NOME =?";
    private final String LIST = "SELECT * FROM PRODUTO";
    private final String BUSCAR = "SELECT ID,NOME,PRECO FROM PRODUTO WHERE ID=?";
    
    private Conexao con = new Conexao("127.0.0.1", "3306", "mydb", "root",""); 
	
	public boolean inserir(Produto p) {
		try {
			// CONECTA
			con.conecta();
			PreparedStatement preparaInstrucao;
			preparaInstrucao = con.getConexao().prepareStatement(INSERT);

			// SETA OS VALORES DA INSTRUCAO
			// OBS: PASSA OS PARAMETROS NA ORDEM DAS ? DA INSTRUCAO
			preparaInstrucao.setString(1, p.getNome());
			preparaInstrucao.setDouble(2, p.getPreco());

			// EXECUTA A INSTRUCAO
			preparaInstrucao.execute();

			// DESCONECTA
			con.desconecta();
			                 JOptionPane.showMessageDialog(null, "Produto Inserido com Sucesso ");
			return true;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao tentar inserir Produto");
                    return false;
                        

		}
	}

	public boolean atualizarPreco(double preco, int id) {
		try {
			 //CONECTA
			con.conecta();
			PreparedStatement preparaInstrucao;
			preparaInstrucao = con.getConexao().prepareStatement(UPDATEPRECO); 

			//SETA OS VALORES DA INSTRUCAO
			//OBS: PASSA OS PARAMETROS NA ORDEM DAS ? DA INSTRUCAO
			preparaInstrucao.setDouble(1, preco);
			preparaInstrucao.setInt(2, id);
                       // preparaInstrucao.setString(3,nome);

			//EXECUTA A INSTRUCAO
			preparaInstrucao.execute();
			
			//DESCONECTA
			con.desconecta();
                        JOptionPane.showMessageDialog(null, "Atualização Concluida Com Sucesso");
			return true;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao Atualizar Produto ");
                        return false;

		}
	}
	
	public boolean remover(String nome) {
		try {
			 //CONECTA
			con.conecta();
			PreparedStatement preparaInstrucao;
			preparaInstrucao = con.getConexao().prepareStatement(DELETE); 

			//SETA OS VALORES DA INSTRUCAO
			//OBS: PASSA OS PARAMETROS NA ORDEM DAS ? DA INSTRUCAO
			preparaInstrucao.setString(1, nome);

			//EXECUTA A INSTRUCAO
			preparaInstrucao.execute();
			
			//DESCONECTA
			con.desconecta();
                        JOptionPane.showMessageDialog(null, "Produto Removido com Sucesso ");
			return true;

		} catch (Exception e) {
			return false;

		}
	}

	public ArrayList<Produto> listar() {
		ArrayList<Produto> lista = new ArrayList<>(); //cria uma lista de objetos produtos

		try {
			// CONECTA
			con.conecta();
			PreparedStatement preparaInstrucao;
			preparaInstrucao = con.getConexao().prepareStatement(LIST); 
			
			// EXECUTA A INSTRUCAO
			ResultSet rs = preparaInstrucao.executeQuery(); 
			
			//TRATA O RETORNO DA CONSULTA
			while (rs.next()) { //enquanto houver registro
				Produto p = new Produto(rs.getInt("id"),rs.getString("nome"), rs.getDouble("preco"));//cria objeto de Produto
				lista.add(p); //adiciona o objeto a list
			}
                        
			// DESCONECTA
			con.desconecta();
                       // JOptionPane.showMessageDialog(null, "Esses são todos os Produtos cadastrados ate o momento ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}

    private static class e {

        private static String getMessage() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        
    }
    
    
    
    public ArrayList<Produto> produtoBuscaDAO (int id) throws SQLException{
           ArrayList <Produto> cli = new ArrayList<>();
            try{
                con.conecta();
                PreparedStatement preparaInstrucao;
                preparaInstrucao = con.getConexao().prepareStatement(BUSCAR);
                
                preparaInstrucao.setInt(1,id);
                ResultSet  rs = preparaInstrucao.executeQuery();
                
               while(rs.next() == true){
               Produto fun = new Produto(rs.getInt("ID"),rs.getString("NOME"),rs.getDouble("PRECO"));
               cli.add(fun);
               }
               con.desconecta();
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Não há Produto Cadastrado com esse ID ");
            } 
        return cli;

}
}
