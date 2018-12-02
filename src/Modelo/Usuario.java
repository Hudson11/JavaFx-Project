package Modelo;

import java.util.ArrayList;

public abstract class Usuario {
	private int id;
	private String nome;
	private String cpf;
        
     static ArrayList <Usuario> usuario = new ArrayList <> ();
	
	public Usuario(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}
        
        public Usuario(int id,String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
                this.id = id;
	}
        
        

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}	
	
	public String toString() {
		return id+" "+ nome + " "+cpf;
	}

    /**
     * @return the usuario
     */
    public ArrayList <Usuario> getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(ArrayList <Usuario> usuario) {
        this.usuario = usuario;
    }   
    
}
