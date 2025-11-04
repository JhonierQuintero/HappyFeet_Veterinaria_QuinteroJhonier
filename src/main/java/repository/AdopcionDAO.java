package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.Adopcion;
import util.ConexionDB;

public class AdopcionDAO {

    public void agregar(Adopcion a) {
        String sql = "INSERT INTO adopciones (mascota_adopcion_id, dueno_id, fecha_adopcion, contrato_texto) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, a.getMascotaAdopcionId());
            ps.setInt(2, a.getDuenoId());
            ps.setDate(3, a.getFechaAdopcion());
            ps.setString(4, a.getContratoTexto());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) a.setId(rs.getInt(1)); }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public List<Adopcion> listar() {
        List<Adopcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM adopciones";
        try (Connection con = ConexionDB.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Adopcion(rs.getInt("id"), rs.getInt("mascota_adopcion_id"), rs.getInt("dueno_id"), rs.getDate("fecha_adopcion"), rs.getString("contrato_texto")));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }
}