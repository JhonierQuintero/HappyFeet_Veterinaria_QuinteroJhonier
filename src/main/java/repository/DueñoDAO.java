package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.Dueño;
import util.ConexionDB;

public class DueñoDAO {

    public void agregar(Dueño d) {
        String sql = "INSERT INTO duenos (nombre_completo, documento_identidad, direccion, telefono, email, contacto_emergencia, fecha_registro, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, d.getNombre());
            ps.setString(2, d.getNumeroDocumento());
            ps.setString(3, d.getDireccion());
            ps.setString(4, d.getTelefono());
            ps.setString(5, d.getEmail());
            ps.setString(6, d.getContactoEmergencia());
            ps.setDate(7, d.getFechaRegistro());
            ps.setBoolean(8, d.getActivo());
            
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    d.setId(rs.getInt(1));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al agregar dueño: " + ex.getMessage());
        }
    }

    public List<Dueño> listar() {
        List<Dueño> lista = new ArrayList<>();
        String sql = "SELECT id, nombre_completo, documento_identidad, direccion, telefono, email, contacto_emergencia, fecha_registro, activo FROM duenos";
        
        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Dueño d = new Dueño(
                    rs.getInt("id"),
                    rs.getString("nombre_completo"),
                    rs.getString("documento_identidad"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("contacto_emergencia"),
                    rs.getDate("fecha_registro"),
                    rs.getBoolean("activo")
                );
                lista.add(d);
            }
        } catch (SQLException e) {
            System.out.println("Error SQL al listar dueños: " + e.getMessage());
        }
        return lista;
    }
    
    public Dueño leerPorId(int id) {
        Dueño d = null;
        String sql = "SELECT id, nombre_completo, documento_identidad, direccion, telefono, email, contacto_emergencia, fecha_registro, activo FROM duenos WHERE id = ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    d = new Dueño(
                        rs.getInt("id"),
                        rs.getString("nombre_completo"),
                        rs.getString("documento_identidad"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("contacto_emergencia"),
                        rs.getDate("fecha_registro"),
                        rs.getBoolean("activo")
                    );
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al leer dueño por ID: " + ex.getMessage());
        }
        return d;
    }

    public Dueño leerPorDocumento(String documento) {
        Dueño d = null;
        String sql = "SELECT id, nombre_completo, documento_identidad, direccion, telefono, email, contacto_emergencia, fecha_registro, activo FROM duenos WHERE documento_identidad = ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, documento);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                     d = new Dueño(
                        rs.getInt("id"),
                        rs.getString("nombre_completo"),
                        rs.getString("documento_identidad"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("contacto_emergencia"),
                        rs.getDate("fecha_registro"),
                        rs.getBoolean("activo")
                    );
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al leer dueño por documento: " + ex.getMessage());
        }
        return d;
    }

    public void actualizar(Dueño d) {
        String sql = "UPDATE duenos SET nombre_completo = ?, documento_identidad = ?, direccion = ?, telefono = ?, email = ?, contacto_emergencia = ?, activo = ? WHERE id = ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, d.getNombre());
            ps.setString(2, d.getNumeroDocumento());
            ps.setString(3, d.getDireccion());
            ps.setString(4, d.getTelefono());
            ps.setString(5, d.getEmail());
            ps.setString(6, d.getContactoEmergencia());
            ps.setBoolean(7, d.getActivo());
            ps.setInt(8, d.getId());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error SQL al actualizar dueño: " + ex.getMessage());
        }
    }

    public void eliminar(int idDueño) {
        String sql = "DELETE FROM duenos WHERE id = ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idDueño);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error SQL al eliminar dueño: " + ex.getMessage());
        }
    }
}