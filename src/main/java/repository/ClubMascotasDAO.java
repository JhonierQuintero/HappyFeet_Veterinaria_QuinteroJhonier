package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.ClubMascotas;
import util.ConexionDB;

public class ClubMascotasDAO {

    public void agregar(ClubMascotas cm) {
        String sql = "INSERT INTO club_mascotas (dueno_id, puntos_disponibles, fecha_inscripcion, activo) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cm.getDuenoId());
            ps.setInt(2, cm.getPuntosDisponibles());
            ps.setDate(3, cm.getFechaInscripcion());
            ps.setBoolean(4, cm.getActivo());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) cm.setId(rs.getInt(1)); }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public ClubMascotas leerPorDuenoId(int duenoId) {
        ClubMascotas cm = null;
        String sql = "SELECT * FROM club_mascotas WHERE dueno_id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, duenoId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cm = new ClubMascotas(rs.getInt("id"), rs.getInt("dueno_id"), rs.getInt("puntos_disponibles"), rs.getDate("fecha_inscripcion"), rs.getBoolean("activo"));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return cm;
    }

    public List<ClubMascotas> listar() {
        List<ClubMascotas> lista = new ArrayList<>();
        String sql = "SELECT * FROM club_mascotas";
        try (Connection con = ConexionDB.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new ClubMascotas(rs.getInt("id"), rs.getInt("dueno_id"), rs.getInt("puntos_disponibles"), rs.getDate("fecha_inscripcion"), rs.getBoolean("activo")));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }

    public void actualizar(ClubMascotas cm) {
        String sql = "UPDATE club_mascotas SET dueno_id=?, puntos_disponibles=?, fecha_inscripcion=?, activo=? WHERE id=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cm.getDuenoId());
            ps.setInt(2, cm.getPuntosDisponibles());
            ps.setDate(3, cm.getFechaInscripcion());
            ps.setBoolean(4, cm.getActivo());
            ps.setInt(5, cm.getId());
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
    
    public ClubMascotas leerPorId(int id) {
        ClubMascotas cm = null;
        String sql = "SELECT * FROM club_mascotas WHERE id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cm = new ClubMascotas(rs.getInt("id"), rs.getInt("dueno_id"), rs.getInt("puntos_disponibles"), rs.getDate("fecha_inscripcion"), rs.getBoolean("activo"));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return cm;
    }
}