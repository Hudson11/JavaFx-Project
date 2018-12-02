package Modelo;

public class Funcionario extends Usuario {
	private String senha;
        // construtor para listar dados
	public Funcionario(int id,String nome, String cpf,String senha) {
		super (id,nome, cpf);
		this.senha = senha;
	}
        // construtor para inserir dados
        public Funcionario(String nome, String cpf,String senha) {
		super (nome, cpf);
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
