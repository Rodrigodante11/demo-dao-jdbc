package model.dao;

import java.util.List;

import model.entidade.Departamento;

public interface DepartamentoDao {
	
	void inserir(Departamento obj);
	void atualiza(Departamento obj);
	void deletarPorId(Integer id);
	Departamento acharPorId(Integer id);
	List<Departamento> acharTodos();
	
}
