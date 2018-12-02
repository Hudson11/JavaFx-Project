package Modelo;

public class Pedido {
	private int idPedido;
	private String nomeCliente;
	private double total;

	public Pedido(int idPedido, String nomeCliente, double total) {
		this.idPedido = idPedido;
		this.nomeCliente = nomeCliente;
		this.total = total;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
}
