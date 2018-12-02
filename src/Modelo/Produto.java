package Modelo;

public class Produto {
	private int idProduto;
	private String nome;
	private double preco;
	
	//para cadastrar
	public Produto(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
	}
	
	//para consultar
	public Produto(int id, String nome, double preco) {
		this.idProduto = id;
		this.nome = nome;
		this.preco = preco;
	}
	
	public Produto(int id){
		this.idProduto = id;
	}
	
	public int getIdProduto() {
		return idProduto;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public String toString() {
		return idProduto + " " + nome + " " + preco;
	}
	
}
