package Modelo;

public class ItemPedido extends Produto{
	private int idPedido;
	private int quantidade;
	
	public ItemPedido(int idProduto, String nome, double preco, int idPedido, int quantidade){
		super(idProduto, nome, preco);
		this.idPedido = idPedido;
		this.quantidade = quantidade;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
