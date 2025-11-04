package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.ItemFactura;
import model.enums.TipoItemFactura;
import util.ConexionDB;

public class ItemFacturaDAO {

    public void agregar(ItemFactura item) {
        String sql = "INSERT INTO items_factura (factura_id, tipo_item, producto_id, servicio_id, servicio_descripcion, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, item.getFacturaId());
            ps.setString(2, item.getTipoItem().name().toUpperCase());
            ps.setObject(3, item.getProductoId());
            ps.setObject(4, item.getServicioId());
            ps.setString(5, item.getServicioDescripcion());
            ps.setInt(6, item.getCantidad());
            ps.setDouble(7, item.getPrecioUnitario());
            ps.setDouble(8, item.getSubtotal());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) item.setId(rs.getInt(1)); }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public List<ItemFactura> listarPorFacturaId(int facturaId) {
        List<ItemFactura> lista = new ArrayList<>();
        String sql = "SELECT * FROM items_factura WHERE factura_id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, facturaId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TipoItemFactura tipo = TipoItemFactura.valueOf(rs.getString("tipo_item").toUpperCase());
                    lista.add(new ItemFactura(rs.getInt("id"), rs.getInt("factura_id"), tipo, (Integer) rs.getObject("producto_id"), (Integer) rs.getObject("servicio_id"), rs.getString("servicio_descripcion"), rs.getInt("cantidad"), rs.getDouble("precio_unitario"), rs.getDouble("subtotal")));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }
}