package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.ConsultaMedica;
import util.ConexionDB;

public class ConsultaMedicaDAO {
    
    public void agregar(ConsultaMedica cm) {
        String sql = "INSERT INTO consultas_medicas (mascota_id, veterinario_id, cita_id, fecha_hora, motivo, sintomas, diagnostico, recomendaciones, peso_registrado, temperatura) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cm.getMascotaId());
            ps.setInt(2, cm.getVeterinarioId());
            ps.setObject(3, cm.getCitaId());
            ps.setTimestamp(4, cm.getFechaHora());
            ps.setString(5, cm.getMotivo());
            ps.setString(6, cm.getSintomas());
            ps.setString(7, cm.getDiagnostico());
            ps.setString(8, cm.getRecomendaciones());
            ps.setDouble(9, cm.getPesoRegistrado());
            ps.setDouble(10, cm.getTemperatura());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) cm.setId(rs.getInt(1));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
    
    public ConsultaMedica leerPorId(int id) {
        ConsultaMedica cm = null;
        String sql = "SELECT * FROM consultas_medicas WHERE id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cm = new ConsultaMedica(rs.getInt("id"), 
                            rs.getInt("mascota_id"), 
                            rs.getInt("veterinario_id"), 
                            (Integer) rs.getObject("cita_id"), 
                            rs.getTimestamp("fecha_hora"), 
                            rs.getString("motivo"), 
                            rs.getString("sintomas"), 
                            rs.getString("diagnostico"), 
                            rs.getString("recomendaciones"), 
                            rs.getDouble("peso_registrado"), 
                            rs.getDouble("temperatura"));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return cm;
    }

    public List<ConsultaMedica> listar() {
        List<ConsultaMedica> lista = new ArrayList<>();
        String sql = "SELECT * FROM consultas_medicas";
        try (Connection con = ConexionDB.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new ConsultaMedica(rs.getInt("id"), rs.getInt("mascota_id"), rs.getInt("veterinario_id"), (Integer) rs.getObject("cita_id"), rs.getTimestamp("fecha_hora"), rs.getString("motivo"), rs.getString("sintomas"), rs.getString("diagnostico"), rs.getString("recomendaciones"), rs.getDouble("peso_registrado"), rs.getDouble("temperatura")));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }

    public void actualizar(ConsultaMedica cm) {
        String sql = "UPDATE consultas_medicas SET mascota_id=?, veterinario_id=?, cita_id=?, fecha_hora=?, motivo=?, sintomas=?, diagnostico=?, recomendaciones=?, peso_registrado=?, temperatura=? WHERE id=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cm.getMascotaId());
            ps.setInt(2, cm.getVeterinarioId());
            ps.setObject(3, cm.getCitaId());
            ps.setTimestamp(4, cm.getFechaHora());
            ps.setString(5, cm.getMotivo());
            ps.setString(6, cm.getSintomas());
            ps.setString(7, cm.getDiagnostico());
            ps.setString(8, cm.getRecomendaciones());
            ps.setDouble(9, cm.getPesoRegistrado());
            ps.setDouble(10, cm.getTemperatura());
            ps.setInt(11, cm.getId());
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM consultas_medicas WHERE id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
}