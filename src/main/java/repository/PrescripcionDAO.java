package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.Prescripcion;
import util.ConexionDB;

public class PrescripcionDAO {

    public void agregar(Prescripcion p) {
        String sql = "INSERT INTO prescripciones (consulta_id, procedimiento_id, producto_id, cantidad, dosis, frecuencia, duracion_dias, instrucciones, fecha_prescripcion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, p.getConsultaId());
            ps.setObject(2, p.getProcedimientoId());
            ps.setInt(3, p.getProductoId());
            ps.setInt(4, p.getCantidad());
            ps.setString(5, p.getDosis());
            ps.setString(6, p.getFrecuencia());
            ps.setInt(7, p.getDuracionDias());
            ps.setString(8, p.getInstrucciones());
            ps.setTimestamp(9, p.getFechaPrescripcion());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) p.setId(rs.getInt(1));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public Prescripcion leerPorId(int id) {
        Prescripcion p = null;
        String sql = "SELECT * FROM prescripciones WHERE id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = new Prescripcion(rs.getInt("id"), 
                            (Integer) rs.getObject("consulta_id"), 
                            (Integer) rs.getObject("procedimiento_id"), 
                            rs.getInt("producto_id"), 
                            rs.getInt("cantidad"), 
                            rs.getString("dosis"), 
                            rs.getString("frecuencia"), 
                            rs.getInt("duracion_dias"), 
                            rs.getString("instrucciones"), 
                            rs.getTimestamp("fecha_prescripcion"));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return p;
    }

    public List<Prescripcion> listar() {
        List<Prescripcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM prescripciones";
        try (Connection con = ConexionDB.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Prescripcion(rs.getInt("id"), 
                        (Integer) rs.getObject("consulta_id"), 
                        (Integer) rs.getObject("procedimiento_id"), 
                        rs.getInt("producto_id"), rs.getInt("cantidad"), 
                        rs.getString("dosis"), 
                        rs.getString("frecuencia"),
                        rs.getInt("duracion_dias"), 
                        rs.getString("instrucciones"), 
                        rs.getTimestamp("fecha_prescripcion")));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }
}