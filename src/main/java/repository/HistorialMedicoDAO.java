package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.HistorialMedico;
import util.ConexionDB;

public class HistorialMedicoDAO {

    public void agregar(HistorialMedico hm) {
        String sql = "INSERT INTO historial_medico (mascota_id, fecha_evento, evento_tipo_id, descripcion, veterinario_id, consulta_id, procedimiento_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, hm.getMascotaId());
            ps.setDate(2, hm.getFechaEvento());
            ps.setInt(3, hm.getEventoTipoId());
            ps.setString(4, hm.getDescripcion());
            ps.setObject(5, hm.getVeterinarioId());
            ps.setObject(6, hm.getConsultaId());
            ps.setObject(7, hm.getProcedimientoId());
            ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){ if(rs.next()) hm.setId(rs.getInt(1)); }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public HistorialMedico leerPorId(int id) {
        HistorialMedico hm = null;
        String sql = "SELECT * FROM historial_medico WHERE id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    hm = new HistorialMedico(rs.getInt("id"), 
                            rs.getInt("mascota_id"), 
                            rs.getDate("fecha_evento"), 
                            rs.getInt("evento_tipo_id"), 
                            rs.getString("descripcion"), 
                            (Integer) rs.getObject("veterinario_id"), 
                            (Integer) rs.getObject("consulta_id"), 
                            (Integer) rs.getObject("procedimiento_id"));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return hm;
    }

    public List<HistorialMedico> listarPorMascota(int mascotaId) {
        List<HistorialMedico> lista = new ArrayList<>();
        String sql = "SELECT * FROM historial_medico WHERE mascota_id = ? ORDER BY fecha_evento DESC";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, mascotaId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new HistorialMedico(rs.getInt("id"), 
                            rs.getInt("mascota_id"), 
                            rs.getDate("fecha_evento"), 
                            rs.getInt("evento_tipo_id"), 
                            rs.getString("descripcion"), 
                            (Integer) rs.getObject("veterinario_id"), 
                            (Integer) rs.getObject("consulta_id"), 
                            (Integer) rs.getObject("procedimiento_id")));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }

    public void actualizar(HistorialMedico hm) {
        String sql = "UPDATE historial_medico SET mascota_id=?, fecha_evento=?, evento_tipo_id=?, descripcion=?, veterinario_id=?, consulta_id=?, procedimiento_id=? WHERE id=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, hm.getMascotaId());
            ps.setDate(2, hm.getFechaEvento());
            ps.setInt(3, hm.getEventoTipoId());
            ps.setString(4, hm.getDescripcion());
            ps.setObject(5, hm.getVeterinarioId());
            ps.setObject(6, hm.getConsultaId());
            ps.setObject(7, hm.getProcedimientoId());
            ps.setInt(8, hm.getId());
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
    
    public void eliminar(int id) {
        String sql = "DELETE FROM historial_medico WHERE id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
}