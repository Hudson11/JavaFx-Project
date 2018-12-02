package Persistencia;

import java.util.ArrayList;

import Modelo.Usuario;

public interface UsuarioDAO {
	void inserir(Usuario u);

	void remover(String cpf);

	ArrayList<Usuario> listar();
}
