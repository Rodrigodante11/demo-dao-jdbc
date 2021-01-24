package model.dao;

import java.util.List;

import model.entidade.Departamento;
import model.entidade.Vendedor;

public interface VendedorDao {
	void inserir(Vendedor obj);
	void atualiza(Vendedor obj);
	void deletarPorId(Integer id);
	Vendedor acharPorId(Integer id);
	List<Vendedor> acharTodos();
	List<Vendedor> acharPorDepartamento(Departamento departamento);

}
