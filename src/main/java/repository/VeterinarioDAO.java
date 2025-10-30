package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.Veterinario;
import util.ConexionDB;

public class VeterinarioDAO {

    public void agregar(Veterinario v) {
        String sql = "INSERT INTO veterinarios (nombre_completo, documento_identidad, licencia_profesional, especialidad, telefono, email, fecha_contratacion, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, v.getNombreCompleto());
            ps.setString(2, v.getDocumentoIdentidad());
            ps.setString(3, v.getLicenciaProfesional());
            ps.setString(4, v.getEspecialidad());
            ps.setString(5, v.getTelefono());
            ps.setString(6, v.getEmail());
            ps.setDate(7, v.getFechaContratacion());
            ps.setBoolean(8, v.getActivo());
            
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    v.setId(rs.getInt(1));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al agregar veterinario: " + ex.getMessage());
        }
    }

    public List<Veterinario> listar() {
        List<Veterinario> lista = new ArrayList<>();
        String sql = "SELECT id, nombre_completo, documento_identidad, licencia_profesional, especialidad, telefono, email, fecha_contratacion, activo FROM veterinarios";
        
        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Veterinario v = new Veterinario(
                    rs.getInt("id"),
                    rs.getString("nombre_completo"),
                    rs.getString("documento_identidad"),
                    rs.getString("licencia_profesional"),
                    rs.getString("especialidad"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getDate("fecha_contratacion"),
                    rs.getBoolean("activo")
                );
                lista.add(v);
            }
        } catch (SQLException e) {
            System.out.println("Error SQL al listar veterinarios: " + e.getMessage());
        }
        return lista;
    }

    public Veterinario leerPorId(int id) {
        Veterinario v = null;
        String sql = "SELECT id, nombre_completo, documento_identidad, licencia_profesional, especialidad, telefono, email, fecha_contratacion, activo FROM veterinarios WHERE id = ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    v = new Veterinario(
                        rs.getInt("id"),
                        rs.getString("nombre_completo"),
                        rs.getString("documento_identidad"),
                        rs.getString("licencia_profesional"),
                        rs.getString("especialidad"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getDate("fecha_contratacion"),
                        rs.getBoolean("activo")
                    );
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al leer veterinario por ID: " + ex.getMessage());
        }
        return v;
    }

    public void actualizar(Veterinario v) {
        String sql = "UPDATE veterinarios SET nombre_completo = ?, documento_identidad = ?, licencia_profesional = ?, especialidad = ?, telefono = ?, email = ?, activo = ? WHERE id = ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, v.getNombreCompleto());
            ps.setString(2, v.getDocumentoIdentidad());
            ps.setString(3, v.getLicenciaProfesional());
            ps.setString(4, v.getEspecialidad());
            ps.setString(5, v.getTelefono());
            ps.setString(6, v.getEmail());
            ps.setBoolean(7, v.getActivo());
            ps.setInt(8, v.getId());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error SQL al actualizar veterinario: " + ex.getMessage());
        }
    }

    public void eliminar(int idVeterinario) {
        String sql = "DELETE FROM veterinarios WHERE id = ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idVeterinario);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error SQL al eliminar veterinario: " + ex.getMessage());
        }
    }
}