package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.TransaccionPuntos;
import model.enums.TipoTransaccionPuntos;
import util.ConexionDB;

public class TransaccionPuntosDAO {
    
    public void agregar(TransaccionPuntos tp) {
        String sql = "INSERT INTO transacciones_puntos (club_mascotas_id, factura_id, puntos, tipo, fecha, descripcion, saldo_anterior, saldo_nuevo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, tp.getClubMascotasId());
            ps.setObject(2, tp.getFacturaId());
            ps.setInt(3, tp.getPuntos());
            ps.setString(4, tp.getTipo().name().toUpperCase());
            ps.setTimestamp(5, tp.getFecha());
            ps.setString(6, tp.getDescripcion());
            ps.setInt(7, tp.getSaldoAnterior());
            ps.setInt(8, tp.getSaldoNuevo());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) tp.setId(rs.getInt(1)); }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public List<TransaccionPuntos> listarPorClubId(int clubId) {
        List<TransaccionPuntos> lista = new ArrayList<>();
        String sql = "SELECT * FROM transacciones_puntos WHERE club_mascotas_id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, clubId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new TransaccionPuntos(rs.getInt("id"), rs.getInt("club_mascotas_id"), (Integer) rs.getObject("factura_id"), rs.getInt("puntos"), TipoTransaccionPuntos.valueOf(rs.getString("tipo").toUpperCase()), rs.getTimestamp("fecha"), rs.getString("descripcion"), rs.getInt("saldo_anterior"), rs.getInt("saldo_nuevo")));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }
}