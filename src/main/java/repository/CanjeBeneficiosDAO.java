package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.entities.CanjeBeneficios;
import util.ConexionDB;

public class CanjeBeneficiosDAO {

    public void agregar(CanjeBeneficios cb) {
        String sql = "INSERT INTO canjes_beneficios (club_mascotas_id, beneficio_id, fecha_canje, puntos_canjeados, estado, factura_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cb.getClubMascotasId());
            ps.setInt(2, cb.getBeneficioId());
            ps.setTimestamp(3, cb.getFechaCanje());
            ps.setInt(4, cb.getPuntosCanjeados());
            ps.setString(5, cb.getEstado().name().toUpperCase());
            ps.setObject(6, cb.getFacturaId());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) cb.setId(rs.getInt(1)); }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
}