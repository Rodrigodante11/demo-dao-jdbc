package aplication;




import model.dao.DaoFabrica;
import model.dao.VendedorDao;

import model.entidade.Vendedor;

public class Programa {
	
	public static void main(String[] args) {
		
		
		VendedorDao vendedorDao=DaoFabrica.criaVendedorDao();
		Vendedor vendedor=vendedorDao.acharPorId(3);
		System.out.println(vendedor);
		 
	}

}
