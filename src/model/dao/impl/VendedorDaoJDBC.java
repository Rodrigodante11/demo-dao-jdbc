package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		PreparedStatement st=null;
		
		try {
			st=conn.prepareStatement(
					"INSERT INTO seller "
					+"(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+"VALUES "
					+"(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
					
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getAniversario().getTime()));
			st.setDouble(4, obj.getSalario());
			st.setInt(5, obj.getDepartamento().getId());
			
			int linhasAfetadas=st.executeUpdate();
			
			if(linhasAfetadas>0)
			{
				ResultSet rs= st.getGeneratedKeys();
				if(rs.next()) {
					int id=rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}else {
				throw new DbException("Erro inesperado! Nenhuma linha foi afetada!");
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
		
		
		
		
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
		
		PreparedStatement st=null;
		ResultSet rs= null;
		try {
			
				st=conn.prepareStatement(
						"SELECT seller.*,department.Name as DepName "
						+ "FROM seller INNER JOIN department "
						+ "ON seller.DepartmentId = department.Id "
						+ "ORDER BY Name");
				
				rs=st.executeQuery();
				List<Vendedor>listaa= new ArrayList<>();
				Map<Integer, Departamento>map=new HashMap<>();
				
				while(rs.next())
				{
					
					Departamento dep=map.get(rs.getInt("DepartmentId"));
					if(dep ==null) {
						dep=intanciandoDepartamento(rs);
						map.put(rs.getInt("DepartmentId"), dep);
					}
					
					
					
					Vendedor obj=intanciandoVendedor(rs,dep);
					listaa.add(obj);
				}
				return listaa;
					
			}catch(SQLException e)
			{
				throw new DbException(e.getMessage());
			}finally {
				DB.closeStatement(st);
				DB.closeResultSet(rs);
				
			}
	}
	@Override
	public List<Vendedor> acharPorDepartamento(Departamento departamento) {
		
		PreparedStatement st=null;
		ResultSet rs= null;
		try {
			
				st=conn.prepareStatement(
						"SELECT seller.*,department.Name as DepName "
						+ "FROM seller INNER JOIN department "
						+ "ON seller.DepartmentId = department.Id "
						+ "WHERE DepartmentId = ? "
						+ "ORDER BY Name");
				
				
				st.setInt(1,departamento.getId());
				rs=st.executeQuery();
				List<Vendedor>listaa= new ArrayList<>();
				Map<Integer, Departamento>map=new HashMap<>();
				
				while(rs.next())
				{
					
					Departamento dep=map.get(rs.getInt("DepartmentId"));
					if(dep ==null) {
						dep=intanciandoDepartamento(rs);
						map.put(rs.getInt("DepartmentId"), dep);
					}
					
					
					
					Vendedor obj=intanciandoVendedor(rs,dep);
					listaa.add(obj);
				}
				return listaa;
					
			}catch(SQLException e)
			{
				throw new DbException(e.getMessage());
			}finally {
				DB.closeStatement(st);
				DB.closeResultSet(rs);
				
			}
	}
	
	

}
