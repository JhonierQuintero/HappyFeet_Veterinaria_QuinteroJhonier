package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.Factura;
import model.enums.EstadoFactura;
import model.enums.MetodoPago;
import util.ConexionDB;

public class FacturaDAO {

    public void agregar(Factura f) {
        String sql = "INSERT INTO facturas (dueno_id, numero_factura, fecha_emision, subtotal, impuesto, descuento, total, metodo_pago, estado, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, f.getDuenoId());
            ps.setString(2, f.getNumeroFactura());
            ps.setTimestamp(3, f.getFechaEmision());
            ps.setDouble(4, f.getSubtotal());
            ps.setDouble(5, f.getImpuesto());
            ps.setDouble(6, f.getDescuento());
            ps.setDouble(7, f.getTotal());
            ps.setString(8, f.getMetodoPago().name().toUpperCase());
            ps.setString(9, f.getEstado().name().toUpperCase());
            ps.setString(10, f.getObservaciones());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) f.setId(rs.getInt(1)); }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public Factura leerPorId(int id) {
        Factura f = null;
        String sql = "SELECT * FROM facturas WHERE id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    MetodoPago mp = MetodoPago.valueOf(rs.getString("metodo_pago").toUpperCase());
                    EstadoFactura ef = EstadoFactura.valueOf(rs.getString("estado").toUpperCase());
                    f = new Factura(rs.getInt("id"), rs.getInt("dueno_id"), rs.getString("numero_factura"), rs.getTimestamp("fecha_emision"), rs.getDouble("subtotal"), rs.getDouble("impuesto"), rs.getDouble("descuento"), rs.getDouble("total"), mp, ef, rs.getString("observaciones"));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return f;
    }
    
    public List<Factura> listar() {
        List<Factura> lista = new ArrayList<>();
        String sql = "SELECT * FROM facturas";
        try (Connection con = ConexionDB.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                MetodoPago mp = rs.getString("metodo_pago") != null ? MetodoPago.valueOf(rs.getString("metodo_pago").toUpperCase()) : null;
                EstadoFactura ef = EstadoFactura.valueOf(rs.getString("estado").toUpperCase());
                lista.add(new Factura(rs.getInt("id"), rs.getInt("dueno_id"), rs.getString("numero_factura"), rs.getTimestamp("fecha_emision"), rs.getDouble("subtotal"), rs.getDouble("impuesto"), rs.getDouble("descuento"), rs.getDouble("total"), mp, ef, rs.getString("observaciones")));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }

    public void actualizar(Factura f) {
        String sql = "UPDATE facturas SET dueno_id=?, subtotal=?, impuesto=?, descuento=?, total=?, metodo_pago=?, estado=?, observaciones=? WHERE id=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, f.getDuenoId());
            ps.setDouble(2, f.getSubtotal());
            ps.setDouble(3, f.getImpuesto());
            ps.setDouble(4, f.getDescuento());
            ps.setDouble(5, f.getTotal());
            ps.setString(6, f.getMetodoPago().name().toUpperCase());
            ps.setString(7, f.getEstado().name().toUpperCase());
            ps.setString(8, f.getObservaciones());
            ps.setInt(9, f.getId());
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
}