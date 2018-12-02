package Persistencia;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Modelo.Funcionario;
import Modelo.Usuario;


public class FuncionarioDAO implements UsuarioDAO{
    private final String INSERT = "INSERT INTO FUNCIONARIO (NOME, CPF, SENHA) VALUES (?,?,?)";
    private final String UPDATESENHA = "UPDATE FUNCIONARIO SET SENHA=? WHERE CPF=?";
    private final String DELETE = "DELETE FROM FUNCIONARIO WHERE NOME =?";
    private final String LIST = "SELECT ID,NOME, CPF FROM FUNCIONARIO";
    private final String LOGIN = "SELECT CPF,SENHA FROM FUNCIONARIO WHERE CPF=? AND SENHA=?";
    private final String BUSCAR = "SELECT ID,NOME,CPF FROM FUNCIONARIO WHERE CPF=?";
    private final String BUSCARCPF = "SELECT ID,NOME,CPF FROM FUNCIONARIO WHERE CPF=?";
    
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
			preparaInstrucao.setString(3, ((Funcionario) u).getSenha());

			// EXECUTA A INSTRUCAO
			preparaInstrucao.execute();
			JOptionPane.showMessageDialog(null, "Funcionario cadastrado com sucesso");

			// DESCONECTA
			con.desconecta();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir funcionario no banco de dados " + e.getMessage());

		}
	}
	
	public void atualizarSenha(String senha, String cpf) {
		try {
			 //CONECTA
			con.conecta();
			PreparedStatement preparaInstrucao;
			preparaInstrucao = con.getConexao().prepareStatement(UPDATESENHA); 

			//SETA OS VALORES DA INSTRUCAO
			//OBS: PASSA OS PARAMETROS NA ORDEM DAS ? DA INSTRUCAO
			preparaInstrucao.setString(1, senha);
			preparaInstrucao.setString(2, cpf);

			//EXECUTA A INSTRUCAO
			preparaInstrucao.execute();
			JOptionPane.showMessageDialog(null,"Senha atualizada com sucesso");
			
			//DESCONECTA
			con.desconecta();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar senha do funcionario no banco de dados " + e.getMessage());

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
			JOptionPane.showMessageDialog(null,"Funcionario removido com sucesso");
			
			//DESCONECTA
			con.desconecta();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao remover funcionario do banco de dados " + e.getMessage());

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
				Funcionario f = new Funcionario(rs.getInt("id"),rs.getString("nome"), rs.getString("cpf"), null); //cria objeto de Funcionario
				lista.add(f); //adiciona o objeto a lista
			}
			// DESCONECTA
			con.desconecta();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
        public boolean revificarFuncionario(String cpf,String senha) throws SQLException{
            try{
                con.conecta();
                PreparedStatement preparaInstrucao;
                preparaInstrucao = con.getConexao().prepareStatement(LOGIN);
                
                preparaInstrucao.setString(1,cpf);
                preparaInstrucao.setString(2,senha);
                
               ResultSet  resultado = preparaInstrucao.executeQuery(); 
               if(resultado.next()){ 
                  if(cpf.equalsIgnoreCase(resultado.getString("cpf")) && senha.equalsIgnoreCase(resultado.getString("senha"))){
                        return true;
                    }                                          
                       }               
               resultado.close();
               con.desconecta();
                
            }catch (Exception e){
            }
            return false;
        }
        
        public ArrayList<Funcionario> funcionarioBuscaDAO (String cpf) throws SQLException{
           ArrayList <Funcionario> cli = new ArrayList<>();
            try{
                con.conecta();
                PreparedStatement preparaInstrucao;
                preparaInstrucao = con.getConexao().prepareStatement(BUSCAR);
                
                preparaInstrucao.setString(1,cpf);
                ResultSet  rs = preparaInstrucao.executeQuery();
                
               while(rs.next() == true){
               Funcionario fun = new Funcionario(rs.getInt("ID"),rs.getString("NOME"),rs.getString("CPF"),null);
               cli.add(fun);
               }
               con.desconecta();
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Não há Funcionario Cadastrado com esse CPF ");
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
