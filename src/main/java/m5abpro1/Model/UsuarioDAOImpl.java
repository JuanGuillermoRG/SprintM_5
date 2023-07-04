package m5abpro1.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import m5abpro1.Model.Conexion.Conexion;
import m5abpro1.Model.DAO.IUsuarioDAO;

public class UsuarioDAOImpl implements IUsuarioDAO {
	private Connection cn = null;
	
	@Override
	public void create(Usuario u) {
		String campo = "";
		String tipo = null;
        String valorCampo = null;
        
        
		try {
		cn = Conexion.getConn();
		TipoUsuario tipoUsuario = u.getTipo();
        if (tipoUsuario == TipoUsuario.CLIENTE) {
            tipo = "Cliente";
            campo = "rut";
            valorCampo = u.getRut();
        } else if (tipoUsuario == TipoUsuario.ADMINISTRATIVO) {
            tipo = "Administrativo";
            campo = "areaTrabajo";
            valorCampo = u.getArea();
    
        } else if (tipoUsuario == TipoUsuario.PROFESIONAL) {
            tipo = "Profesional";
            campo = "titulo";
            valorCampo = u.getTitulo();
        }
        
        String sql = "INSERT INTO usuarios(pw,nombre,tipo," + campo + ") VALUES(?,?,?,?)";
        PreparedStatement ppStm = cn.prepareStatement(sql);
    	ppStm.setString(1, u.getPw());
		ppStm.setString(2, u.getNombre());
		ppStm.setString(3, tipo);
		ppStm.setString(4, valorCampo);
        
		
		if (!ppStm.execute()) {
			System.out.println("Registro creado");
		} else {
			System.out.println("Registro Fallido");
		}
		ppStm.close();
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Usuario readOne(int id) {
		String sql = "SELECT nombre, pw FROM usuarios WHERE id = "+id;
		Usuario resUser = new Usuario();
		try {
			cn = Conexion.getConn();
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			if ( rs.next()) {
				resUser.setNombre(rs.getString("nombre"));
				resUser.setPw(rs.getString("pw"));
			} 
			rs.close();
			stm.close();
			
		} catch (Exception e) {
			
		}
		
		
		return resUser;
	}

	@Override
	public List<Usuario> readAll() {
		String sql = "SELECT id, nombre, tipo FROM usuarios";
		List<Usuario> users = new ArrayList<Usuario>();
		try {
			cn = Conexion.getConn();
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while (rs.next()) {
				
				String tipo = rs.getString("tipo");
				TipoUsuario tipoEnum = null;
				
				if (tipo.equals("Cliente")) {
		            tipoEnum = TipoUsuario.CLIENTE;
		        } else if (tipo.equals("Profesional")) {
		        	tipoEnum = TipoUsuario.PROFESIONAL;
		        } else if (tipo.equals("Administrativo")) {
		        	tipoEnum = TipoUsuario.ADMINISTRATIVO;
		        }
				Usuario newUser = new Usuario();
				newUser.setId(rs.getInt("id"));
				newUser.setNombre(rs.getString("nombre"));
				newUser.setTipo(tipoEnum);
				users.add(newUser);
			}
			
			rs.close();
			stm.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void update(Usuario u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Usuario u) {
		// TODO Auto-generated method stub
		
	}

}
