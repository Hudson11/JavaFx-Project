package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Modelo.Cliente;
import Modelo.Usuario;

public class ClienteDAO implements UsuarioDAO {
    private final String INSERT = "INSERT INTO CLIENTE (NOME, CPF) VALUES (?,?)";
    private final String DELETE = "DELETE FROM CLIENTE WHERE NOME =?";
    private final String LIST = "SELECT ID,NOME,CPF FROM CLIENTE";
    private final String UPDATE = "UPDATE CLIENTE SET NOME=?,CPF=? WHERE ID=?";
    private final String BUSCAR = "SELECT ID,NOME,CPF FROM CLIENTE WHERE CPF = ?";
    private final String BUSCARCPF = "SELECT CPF FROM CLIENTE WHERE CPF=?";
    
    private Conexao con = new Conexao("127.0.0.1", "3306", "mydb", "root",""); 
	
	public void inserir(Usuario u) {
		try {
			// CONECTA
			con.conecta();
			PreparedStatement preparaInstrucao;
			preparaInstrucao = con.getConexao().prepareStatement(INSERT);

			// SETA OS VALORES DA INSTRUCAO
			// OBS: PASSA OS PARAMETROS NA ORDEM DAS ? DA INSTRUCAO
			preparaInstrucao.setString(1, u.getNome());
			preparaInstrucao.setString(2, u.getCpf());

			// EXECUTA A INSTRUCAO
			preparaInstrucao.execute();
			JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");

			// DESCONECTA
			con.desconecta();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir cliente no banco de dados " + e.getMessage());

		}
	}

	public void remover(String cpf) {
		try {
			 //CONECTA
			con.conecta();
			PreparedStatement preparaInstrucao;
			preparaInstrucao = con.getConexao().prepareStatement(DELETE); 

			//SETA OS VALORES DA INSTRUCAO
			//OBS: PASSA OS PARAMETROS NA ORDEM DAS ? DA INSTRUCAO
			preparaInstrucao.setString(1, cpf);

			//EXECUTA A INSTRUCAO
			preparaInstrucao.execute();
			JOptionPane.showMessageDialog(null,"Cliente removido com sucesso");
			
			//DESCONECTA
			con.desconecta();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao remover cliente do banco de dados " + e.getMessage());

		}
	}

	public ArrayList<Usuario> listar() {
		ArrayList<Usuario> lista = new ArrayList<>(); //cria uma lista de objetos Usuario

		try {
			// CONECTA
			con.conecta();
			PreparedStatement preparaInstrucao;
			preparaInstrucao = con.getConexao().prepareStatement(LIST); 
			
			// EXECUTA A INSTRUCAO
			ResultSet rs = preparaInstrucao.executeQuery(); 
			
			//TRATA O RETORNO DA CONSULTA
			while (rs.next()) { //enquanto houver registro
				Cliente c = new Cliente(rs.getInt("id"),rs.getString("nome"), rs.getString("cpf")); //cria objeto de cliente
				lista.add(c); //adiciona o objeto a lista
			}
			
			// DESCONECTA
			con.desconecta();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public int buscarCliente(String cpf) {
		int idCliente = 0;

		try {
			// CONECTA
			con.conecta();
			PreparedStatement preparaInstrucao;
			preparaInstrucao = con.getConexao().prepareStatement(BUSCAR); 
			
			//SETA OS VALORES DA INSTRUCAO
			//OBS: PASSA OS PARAMETROS NA ORDEM DAS ? DA INSTRUCAO
			preparaInstrucao.setString(1, cpf);
			
			// EXECUTA A INSTRUCAO
			ResultSet rs = preparaInstrucao.executeQuery(); 
			
			//TRATA O RETORNO DA CONSULTA
			if(rs.next())
				idCliente = rs.getInt("id");
			
			// DESCONECTA
			con.desconecta();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idCliente;
	}
        
        public void atualizarDadocliente(String cpf,String nome,int id) {
            //CONECTA
		try{
                    con.conecta();
                
			PreparedStatement preparaInstrucao;
			preparaInstrucao = con.getConexao().prepareStatement(UPDATE); 

			//SETA OS VALORES DA INSTRUCAO
			//OBS: PASSA OS PARAMETROS NA ORDEM DAS ? DA INSTRUCAO
			preparaInstrucao.setString(1, nome);
			preparaInstrucao.setString(2, cpf);
                        preparaInstrucao.setInt(3,id);
			//EXECUTA A INSTRUCAO
			preparaInstrucao.execute();
			JOptionPane.showMessageDialog(null, "Dados Atualizados com Sucesso");
			
			//DESCONECTA
			con.desconecta();
                        
		} catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar senha do funcionario no banco de dados " + e.getMessage());
                }}
        
        public ArrayList<Cliente> clienteBuscaDAO (String cpf) throws SQLException{
           ArrayList <Cliente> cli = new ArrayList<>();
            try{
                con.conecta();
                PreparedStatement preparaInstrucao;
                preparaInstrucao = con.getConexao().prepareStatement(BUSCAR);
                
                preparaInstrucao.setString(1,cpf);
                ResultSet  rs = preparaInstrucao.executeQuery();
                
               while(rs.next() == true){
               Cliente cliente = new Cliente(rs.getInt("ID"),rs.getString("NOME"),rs.getString("CPF"));
               cli.add(cliente);
               }
               con.desconecta();
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Não há Cliente Cadastrado com esse CPF ");
            } 
        return cli;
        }
        
        public boolean verificarCpf(String Cpf){
        try{
            con.conecta();
            
            PreparedStatement st;
            st = con.getConexao().prepareStatement(BUSCARCPF);
            
            st.setString(1, Cpf);
            ResultSet rs = st.executeQuery();
            
            if(rs.next() == true){
            if(Cpf.equalsIgnoreCase(rs.getString("CPF"))){
             return true;
            }
            }
        }catch(Exception e ){
        }
        return false;
        }
}
        
        

