package aplication;


import java.util.Date;
import java.util.List;
import model.dao.DaoFabrica;
import model.dao.VendedorDao;
import model.entidade.Departamento;
import model.entidade.Vendedor;

public class Programa {
	
	public static void main(String[] args) {
		
		
		VendedorDao vendedorDao=DaoFabrica.criaVendedorDao();
		System.out.println("=== TEST 1: vendedor encontraPorId =====");
		Vendedor vendedor=vendedorDao.acharPorId(3);
		System.out.println(vendedor);
		
		System.out.println("\n=== TEST 2: vendedor encontratPorDepartamento =====");
		Departamento departamento= new Departamento(2,null);
		List<Vendedor>listaa=vendedorDao.acharPorDepartamento(departamento);
		
		for(Vendedor obj:listaa)
		{
			System.out.println(obj);
		}
		
		System.out.println("\n=== TEST 3: vendedor encontrar todoso =====");
		listaa=vendedorDao.acharTodos();
		for(Vendedor obj:listaa)
		{
			System.out.println(obj);
		}
		 
		
		System.out.println("\n=== TEST 4: vendedor Inserir =====");
		Vendedor newVendedor= new Vendedor(null,"Gred","gred@gmail.com",new Date(),4000.0,departamento);
		vendedorDao.inserir(newVendedor);
		System.out.println("Inserido!  new Id= "+newVendedor.getId());
	}

}
