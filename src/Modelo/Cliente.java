package Modelo;

public class Cliente extends Usuario {
	private double credit;
	// construtor para inserir dados
	public Cliente(String nome,String cpf){
            super(nome,cpf);
        } 
        // construtor para listar dados
        public Cliente(int id,String nome, String cpf) {
		super(id,nome, cpf);
	}

	public double getFidelidade() {
		return credit;
	}

	public void setFidelidade(double credit) {
		this.credit = credit;
	}
	
}
