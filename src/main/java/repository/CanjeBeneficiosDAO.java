package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.CanjeBeneficios;
import model.enums.EstadoCanje;
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

    public List<CanjeBeneficios> listarPorClubId(int clubId) {
        List<CanjeBeneficios> lista = new ArrayList<>();
        String sql = "SELECT * FROM canjes_beneficios WHERE club_mascotas_id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, clubId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new CanjeBeneficios(rs.getInt("id"), rs.getInt("club_mascotas_id"), rs.getInt("beneficio_id"), rs.getTimestamp("fecha_canje"), rs.getInt("puntos_canjeados"), EstadoCanje.valueOf(rs.getString("estado").toUpperCase()), (Integer) rs.getObject("factura_id")));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }

    public void actualizar(CanjeBeneficios cb) {
        String sql = "UPDATE canjes_beneficios SET estado=?, factura_id=? WHERE id=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cb.getEstado().name().toUpperCase());
            ps.setObject(2, cb.getFacturaId());
            ps.setInt(3, cb.getId());
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
}