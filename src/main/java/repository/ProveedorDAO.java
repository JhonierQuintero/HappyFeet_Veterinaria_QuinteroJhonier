package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.Proveedor;
import util.ConexionDB;

public class ProveedorDAO {

    public void agregar(Proveedor p) {
        String sql = "INSERT INTO proveedores (nombre_empresa, contacto, telefono, email, direccion, activo, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNombreEmpresa());
            ps.setString(2, p.getContacto());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getDireccion());
            ps.setBoolean(6, p.getActivo());
            ps.setTimestamp(7, p.getFechaRegistro());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) p.setId(rs.getInt(1));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public Proveedor leerPorId(int id) {
        Proveedor p = null;
        String sql = "SELECT * FROM proveedores WHERE id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = new Proveedor(rs.getInt("id"), rs.getString("nombre_empresa"), rs.getString("contacto"), rs.getString("telefono"), rs.getString("email"), rs.getString("direccion"), rs.getBoolean("activo"), rs.getTimestamp("fecha_registro"));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return p;
    }

    public List<Proveedor> listar() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedores";
        try (Connection con = ConexionDB.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Proveedor(rs.getInt("id"), 
                        rs.getString("nombre_empresa"), 
                        rs.getString("contacto"), 
                        rs.getString("telefono"), 
                        rs.getString("email"), 
                        rs.getString("direccion"), 
                        rs.getBoolean("activo"), 
                        rs.getTimestamp("fecha_registro")));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }

    public void actualizar(Proveedor p) {
        String sql = "UPDATE proveedores SET nombre_empresa=?, contacto=?, telefono=?, email=?, direccion=?, activo=? WHERE id=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombreEmpresa());
            ps.setString(2, p.getContacto());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getDireccion());
            ps.setBoolean(6, p.getActivo());
            ps.setInt(7, p.getId());
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM proveedores WHERE id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
}