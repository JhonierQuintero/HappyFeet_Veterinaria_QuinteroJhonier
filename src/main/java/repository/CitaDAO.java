package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.Cita;
import util.ConexionDB;

public class CitaDAO {

    public void agregar(Cita c) {
        String sql = "INSERT INTO citas (mascota_id, veterinario_id, fecha_hora, motivo, estado_id, observaciones) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, c.getMascotaId());
            ps.setInt(2, c.getVeterinarioId());
            ps.setTimestamp(3, c.getFechaHora());
            ps.setString(4, c.getMotivo());
            ps.setInt(5, c.getEstadoId());
            ps.setString(6, c.getObservaciones());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setId(rs.getInt(1));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public Cita leerPorId(int id) {
        Cita cita = null;
        String sql = "SELECT * FROM citas WHERE id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cita = new Cita(rs.getInt("id"), 
                            rs.getInt("mascota_id"), 
                            rs.getInt("veterinario_id"), 
                            rs.getTimestamp("fecha_hora"), 
                            rs.getString("motivo"), 
                            rs.getInt("estado_id"), 
                            rs.getString("observaciones"));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return cita;
    }

    public List<Cita> listar() {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM citas";
        try (Connection con = ConexionDB.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Cita(rs.getInt("id"), rs.getInt("mascota_id"), rs.getInt("veterinario_id"), rs.getTimestamp("fecha_hora"), rs.getString("motivo"), rs.getInt("estado_id"), rs.getString("observaciones")));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }

    public void actualizar(Cita c) {
        String sql = "UPDATE citas SET mascota_id=?, veterinario_id=?, fecha_hora=?, motivo=?, estado_id=?, observaciones=? WHERE id=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, c.getMascotaId());
            ps.setInt(2, c.getVeterinarioId());
            ps.setTimestamp(3, c.getFechaHora());
            ps.setString(4, c.getMotivo());
            ps.setInt(5, c.getEstadoId());
            ps.setString(6, c.getObservaciones());
            ps.setInt(7, c.getId());
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM citas WHERE id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
}