package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.Inventario;
import util.ConexionDB;

public class InventarioDAO {

    public void agregar(Inventario i) {
        String sql = "INSERT INTO inventario (nombre_producto, producto_tipo_id, descripcion, fabricante, proveedor_id, lote, cantidad_stock, stock_minimo, unidad_medida, fecha_vencimiento, precio_compra, precio_venta, requiere_receta, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, i.getNombreProducto());
            ps.setInt(2, i.getProductoTipoId());
            ps.setString(3, i.getDescripcion());
            ps.setString(4, i.getFabricante());
            ps.setObject(5, i.getProveedorId());
            ps.setString(6, i.getLote());
            ps.setInt(7, i.getCantidadStock());
            ps.setInt(8, i.getStockMinimo());
            ps.setString(9, i.getUnidadMedida());
            ps.setDate(10, i.getFechaVencimiento());
            ps.setDouble(11, i.getPrecioCompra());
            ps.setDouble(12, i.getPrecioVenta());
            ps.setBoolean(13, i.getRequiereReceta());
            ps.setBoolean(14, i.getActivo());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) i.setId(rs.getInt(1));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public Inventario leerPorId(int id) {
        Inventario i = null;
        String sql = "SELECT * FROM inventario WHERE id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    i = new Inventario(rs.getInt("id"), 
                            rs.getString("nombre_producto"), 
                            rs.getInt("producto_tipo_id"), 
                            rs.getString("descripcion"), 
                            rs.getString("fabricante"), 
                            (Integer) rs.getObject("proveedor_id"), 
                            rs.getString("lote"), 
                            rs.getInt("cantidad_stock"), 
                            rs.getInt("stock_minimo"), 
                            rs.getString("unidad_medida"), 
                            rs.getDate("fecha_vencimiento"), 
                            rs.getDouble("precio_compra"), 
                            rs.getDouble("precio_venta"), 
                            rs.getBoolean("requiere_receta"), 
                            rs.getBoolean("activo"));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return i;
    }

    public List<Inventario> listar() {
        List<Inventario> lista = new ArrayList<>();
        String sql = "SELECT * FROM inventario";
        try (Connection con = ConexionDB.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Inventario(rs.getInt("id"), 
                        rs.getString("nombre_producto"), 
                        rs.getInt("producto_tipo_id"), 
                        rs.getString("descripcion"), 
                        rs.getString("fabricante"), 
                        (Integer) rs.getObject("proveedor_id"), 
                        rs.getString("lote"), rs.getInt("cantidad_stock"), 
                        rs.getInt("stock_minimo"), 
                        rs.getString("unidad_medida"), 
                        rs.getDate("fecha_vencimiento"), 
                        rs.getDouble("precio_compra"), 
                        rs.getDouble("precio_venta"), 
                        rs.getBoolean("requiere_receta"), 
                        rs.getBoolean("activo")));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }

    public void actualizar(Inventario i) {
        String sql = "UPDATE inventario SET nombre_producto=?, producto_tipo_id=?, descripcion=?, fabricante=?, proveedor_id=?, lote=?, cantidad_stock=?, stock_minimo=?, unidad_medida=?, fecha_vencimiento=?, precio_compra=?, precio_venta=?, requiere_receta=?, activo=? WHERE id=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, i.getNombreProducto());
            ps.setInt(2, i.getProductoTipoId());
            ps.setString(3, i.getDescripcion());
            ps.setString(4, i.getFabricante());
            ps.setObject(5, i.getProveedorId());
            ps.setString(6, i.getLote());
            ps.setInt(7, i.getCantidadStock());
            ps.setInt(8, i.getStockMinimo());
            ps.setString(9, i.getUnidadMedida());
            ps.setDate(10, i.getFechaVencimiento());
            ps.setDouble(11, i.getPrecioCompra());
            ps.setDouble(12, i.getPrecioVenta());
            ps.setBoolean(13, i.getRequiereReceta());
            ps.setBoolean(14, i.getActivo());
            ps.setInt(15, i.getId());
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM inventario WHERE id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
}