package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.InsumoProcedimiento;
import util.ConexionDB;

public class InsumoProcedimientoDAO {

    public void agregar(InsumoProcedimiento ip) {
        String sql = "INSERT INTO insumos_procedimientos (procedimiento_id, producto_id, cantidad_usada, observaciones) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, ip.getProcedimientoId());
            ps.setInt(2, ip.getProductoId());
            ps.setInt(3, ip.getCantidadUsada());
            ps.setString(4, ip.getObservaciones());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) ip.setId(rs.getInt(1));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public List<InsumoProcedimiento> listarPorProcedimiento(int procedimientoId) {
        List<InsumoProcedimiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM insumos_procedimientos WHERE procedimiento_id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, procedimientoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new InsumoProcedimiento(rs.getInt("id"), 
                            rs.getInt("procedimiento_id"), 
                            rs.getInt("producto_id"), 
                            rs.getInt("cantidad_usada"), 
                            rs.getString("observaciones")));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }
}