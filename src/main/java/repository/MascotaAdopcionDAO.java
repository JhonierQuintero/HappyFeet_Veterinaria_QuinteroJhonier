package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.MascotaAdopcion;
import model.enums.EstadoAdopcion;
import util.ConexionDB;

public class MascotaAdopcionDAO {

    public void agregar(MascotaAdopcion ma) {
        String sql = "INSERT INTO mascotas_adopcion (mascota_id, fecha_ingreso, estado, historia) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, ma.getMascotaId());
            ps.setDate(2, ma.getFechaIngreso());
            ps.setString(3, ma.getEstado().name().toUpperCase());
            ps.setString(4, ma.getHistoria());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) ma.setId(rs.getInt(1)); }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public MascotaAdopcion leerPorId(int id) {
        MascotaAdopcion ma = null;
        String sql = "SELECT * FROM mascotas_adopcion WHERE id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ma = new MascotaAdopcion(rs.getInt("id"), rs.getInt("mascota_id"), rs.getDate("fecha_ingreso"), EstadoAdopcion.valueOf(rs.getString("estado").toUpperCase()), rs.getString("historia"));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return ma;
    }

    public List<MascotaAdopcion> listar() {
        List<MascotaAdopcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascotas_adopcion";
        try (Connection con = ConexionDB.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new MascotaAdopcion(rs.getInt("id"), rs.getInt("mascota_id"), rs.getDate("fecha_ingreso"), EstadoAdopcion.valueOf(rs.getString("estado").toUpperCase()), rs.getString("historia")));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }

    public void actualizar(MascotaAdopcion ma) {
        String sql = "UPDATE mascotas_adopcion SET mascota_id=?, fecha_ingreso=?, estado=?, historia=? WHERE id=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, ma.getMascotaId());
            ps.setDate(2, ma.getFechaIngreso());
            ps.setString(3, ma.getEstado().name().toUpperCase());
            ps.setString(4, ma.getHistoria());
            ps.setInt(5, ma.getId());
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM mascotas_adopcion WHERE id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
}