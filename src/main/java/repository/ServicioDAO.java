package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.Servicio;
import util.ConexionDB;

public class ServicioDAO {

    public void agregar(Servicio s) {
        String sql = "INSERT INTO servicios (nombre, descripcion, categoria, precio_base, duracion_estimada_minutos, activo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, s.getNombre());
            ps.setString(2, s.getDescripcion());
            ps.setString(3, s.getCategoria());
            ps.setDouble(4, s.getPrecioBase());
            ps.setInt(5, s.getDuracionEstimadaMinutos());
            ps.setBoolean(6, s.getActivo());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) s.setId(rs.getInt(1)); }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public Servicio leerPorId(int id) {
        Servicio s = null;
        String sql = "SELECT * FROM servicios WHERE id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    s = new Servicio(rs.getInt("id"), 
                            rs.getString("nombre"), 
                            rs.getString("descripcion"), 
                            rs.getString("categoria"), 
                            rs.getDouble("precio_base"), 
                            rs.getInt("duracion_estimada_minutos"), 
                            rs.getBoolean("activo"));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return s;
    }

    public List<Servicio> listar() {
        List<Servicio> lista = new ArrayList<>();
        String sql = "SELECT * FROM servicios";
        try (Connection con = ConexionDB.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Servicio(rs.getInt("id"), 
                        rs.getString("nombre"), 
                        rs.getString("descripcion"), 
                        rs.getString("categoria"), 
                        rs.getDouble("precio_base"), 
                        rs.getInt("duracion_estimada_minutos"), 
                        rs.getBoolean("activo")));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }

    public void actualizar(Servicio s) {
        String sql = "UPDATE servicios SET nombre=?, descripcion=?, categoria=?, precio_base=?, duracion_estimada_minutos=?, activo=? WHERE id=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getNombre());
            ps.setString(2, s.getDescripcion());
            ps.setString(3, s.getCategoria());
            ps.setDouble(4, s.getPrecioBase());
            ps.setInt(5, s.getDuracionEstimadaMinutos());
            ps.setBoolean(6, s.getActivo());
            ps.setInt(7, s.getId());
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM servicios WHERE id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
}