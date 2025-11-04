package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.MovimientoInventario;
import model.enums.TipoMovimientoInventario;
import util.ConexionDB;

public class MovimientoInventarioDAO {

    public void agregar(MovimientoInventario mi) {
        String sql = "INSERT INTO movimientos_inventario (producto_id, tipo_movimiento, cantidad, stock_anterior, stock_nuevo, motivo, referencia_consulta_id, referencia_procedimiento_id, usuario, fecha_movimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, mi.getProductoId());
            ps.setString(2, mi.getTipoMovimiento().name());
            ps.setInt(3, mi.getCantidad());
            ps.setInt(4, mi.getStockAnterior());
            ps.setInt(5, mi.getStockNuevo());
            ps.setString(6, mi.getMotivo());
            ps.setObject(7, mi.getReferenciaConsultaId());
            ps.setObject(8, mi.getReferenciaProcedimientoId());
            ps.setString(9, mi.getUsuario());
            ps.setTimestamp(10, mi.getFechaMovimiento());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) mi.setId(rs.getInt(1));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public List<MovimientoInventario> listarPorProducto(int productoId) {
        List<MovimientoInventario> lista = new ArrayList<>();
        String sql = "SELECT * FROM movimientos_inventario WHERE producto_id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, productoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TipoMovimientoInventario tipo = TipoMovimientoInventario.valueOf(rs.getString("tipo_movimiento").toUpperCase());
                    lista.add(new MovimientoInventario(rs.getInt("id"), 
                            rs.getInt("producto_id"), 
                            tipo, rs.getInt("cantidad"), 
                            rs.getInt("stock_anterior"), 
                            rs.getInt("stock_nuevo"), 
                            rs.getString("motivo"), 
                            (Integer) rs.getObject
        ("referencia_consulta_id"), (Integer) rs.getObject("referencia_procedimiento_id"), rs.getString("usuario"), rs.getTimestamp("fecha_movimiento")));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }
}