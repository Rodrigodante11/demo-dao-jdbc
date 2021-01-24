package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entidade.Departamento;
import model.entidade.Vendedor;

public class VendedorDaoJDBC implements VendedorDao {

	private Connection conn;
	
	public VendedorDaoJDBC(Connection conn) {
		this.conn=conn;
	}
	@Override
	public void inserir(Vendedor obj) {
		
		
	}

	@Override
	public void atualiza(Vendedor obj) {
		
		
	}

	@Override
	public void deletarPorId(Integer id) {
		
		
	}

	@Override
	public Vendedor acharPorId(Integer id) {
		
		PreparedStatement st=null;
		ResultSet rs= null;
		try {
			
				st=conn.prepareStatement(
						"SELECT seller.*,department.Name as DepName "
						+"FROM seller INNER JOIN department "
						+"ON seller.DepartmentId = department.Id "
						+"WHERE seller.Id = ?");
				
				
				st.setInt(1,id);
				rs=st.executeQuery();
				if(rs.next())
				{
					Departamento dep=intanciandoDepartamento(rs);
					
					Vendedor obj=intanciandoVendedor(rs,dep);
					return obj;
				}
				return null;
					
			}catch(SQLException e)
			{
				throw new DbException(e.getMessage());
			}finally {
				DB.closeStatement(st);
				DB.closeResultSet(rs);
				
			}
			
		}
	private Vendedor intanciandoVendedor(ResultSet rs, Departamento dep) throws SQLException {
		Vendedor obj=new Vendedor();
		obj.setId(rs.getInt("Id"));
		obj.setNome(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setSalario(rs.getDouble("BaseSalary"));
		obj.setAniversario(rs.getDate("BirthDate"));
		obj.setDepartamento(dep);
		return obj;
	}
	private Departamento intanciandoDepartamento(ResultSet rs) throws SQLException {
		Departamento dep=new Departamento();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setNome(rs.getString("DepName"));
		return dep;
	}
		
		
	

	@Override
	public List<Vendedor> acharTodos() {
		
		return null;
	}
	
	

}
