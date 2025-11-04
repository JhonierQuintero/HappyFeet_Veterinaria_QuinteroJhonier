package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.RegistroJornadaVacunacion;
import util.ConexionDB;

public class RegistroJornadaVacunacionDAO {

    public void agregar(RegistroJornadaVacunacion rjv) {
        String sql = "INSERT INTO registro_jornada_vacunacion (jornada_id, mascota_id, dueno_id, vacuna_id, veterinario_id, fecha_hora, lote_vacuna, proxima_dosis, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, rjv.getJornadaId());
            ps.setInt(2, rjv.getMascotaId());
            ps.setInt(3, rjv.getDuenoId());
            ps.setInt(4, rjv.getVacunaId());
            ps.setObject(5, rjv.getVeterinarioId());
            ps.setTimestamp(6, rjv.getFechaHora());
            ps.setString(7, rjv.getLoteVacuna());
            ps.setDate(8, rjv.getProximaDosis());
            ps.setString(9, rjv.getObservaciones());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) rjv.setId(rs.getInt(1)); }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public RegistroJornadaVacunacion leerPorId(int id) {
        RegistroJornadaVacunacion rjv = null;
        String sql = "SELECT * FROM registro_jornada_vacunacion WHERE id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    rjv = new RegistroJornadaVacunacion(rs.getInt("id"), rs.getInt("jornada_id"), rs.getInt("mascota_id"), rs.getInt("dueno_id"), rs.getInt("vacuna_id"), (Integer) rs.getObject("veterinario_id"), rs.getTimestamp("fecha_hora"), rs.getString("lote_vacuna"), rs.getDate("proxima_dosis"), rs.getString("observaciones"));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return rjv;
    }

    public List<RegistroJornadaVacunacion> listarPorJornada(int jornadaId) {
        List<RegistroJornadaVacunacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM registro_jornada_vacunacion WHERE jornada_id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, jornadaId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new RegistroJornadaVacunacion(rs.getInt("id"), rs.getInt("jornada_id"), rs.getInt("mascota_id"), rs.getInt("dueno_id"), rs.getInt("vacuna_id"), (Integer) rs.getObject("veterinario_id"), rs.getTimestamp("fecha_hora"), rs.getString("lote_vacuna"), rs.getDate("proxima_dosis"), rs.getString("observaciones")));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }
    
    public List<RegistroJornadaVacunacion> listar() {
        List<RegistroJornadaVacunacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM registro_jornada_vacunacion";
        try (Connection con = ConexionDB.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new RegistroJornadaVacunacion(rs.getInt("id"), rs.getInt("jornada_id"), rs.getInt("mascota_id"), rs.getInt("dueno_id"), rs.getInt("vacuna_id"), (Integer) rs.getObject("veterinario_id"), rs.getTimestamp("fecha_hora"), rs.getString("lote_vacuna"), rs.getDate("proxima_dosis"), rs.getString("observaciones")));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }
}