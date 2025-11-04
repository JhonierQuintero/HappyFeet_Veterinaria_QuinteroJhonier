package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.JornadaVacunacion;
import model.enums.EstadoJornada;
import util.ConexionDB;

public class JornadaVacunacionDAO {

    public void agregar(JornadaVacunacion jv) {
        String sql = "INSERT INTO jornadas_vacunacion (nombre, fecha, ubicacion, estado) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, jv.getNombre());
            ps.setDate(2, jv.getFecha());
            ps.setString(3, jv.getUbicacion());
            ps.setString(4, jv.getEstado().name().toUpperCase());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) jv.setId(rs.getInt(1)); }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public JornadaVacunacion leerPorId(int id) {
        JornadaVacunacion jv = null;
        String sql = "SELECT * FROM jornadas_vacunacion WHERE id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    jv = new JornadaVacunacion(rs.getInt("id"), rs.getString("nombre"), rs.getDate("fecha"), rs.getString("ubicacion"), EstadoJornada.valueOf(rs.getString("estado").toUpperCase()));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return jv;
    }

    public List<JornadaVacunacion> listar() {
        List<JornadaVacunacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM jornadas_vacunacion";
        try (Connection con = ConexionDB.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new JornadaVacunacion(rs.getInt("id"), rs.getString("nombre"), rs.getDate("fecha"), rs.getString("ubicacion"), EstadoJornada.valueOf(rs.getString("estado").toUpperCase())));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }

    public void actualizar(JornadaVacunacion jv) {
        String sql = "UPDATE jornadas_vacunacion SET nombre=?, fecha=?, ubicacion=?, estado=? WHERE id=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, jv.getNombre());
            ps.setDate(2, jv.getFecha());
            ps.setString(3, jv.getUbicacion());
            ps.setString(4, jv.getEstado().name().toUpperCase());
            ps.setInt(5, jv.getId());
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
}